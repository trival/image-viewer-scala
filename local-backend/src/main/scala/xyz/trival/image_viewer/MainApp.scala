package xyz.trival.image_viewer

import xyz.trival.image_viewer.graphql.*
import caliban.GraphQL.graphQL
import caliban.{RootResolver, ZHttpAdapter}
import zio.http.*
import zio.*
import zio.stream.*
import xyz.trival.image_viewer.modules.media.service.MediaServiceImpl
import xyz.trival.image_viewer.modules.media.persistence.InMemoryMediaStore
import xyz.trival.image_viewer.modules.library.service.LibraryServiceImpl
import xyz.trival.image_viewer.modules.library.persistence.InMemoryLibraryStore
import java.io.File
import zio.http.internal.middlewares.Cors.CorsConfig
import zio.http.Header.{
  AccessControlAllowMethods,
  AccessControlAllowOrigin,
  Origin,
}
import zio.http.HttpAppMiddleware.cors
import caliban.interop.tapir.HttpInterpreter

object MainApp extends ZIOAppDefault:
  import sttp.tapir.json.zio.*

  val graphiql = Http.fromFile(File("graphiql.html"))

  val corsCfg: CorsConfig =
    CorsConfig(
      allowedOrigin = _ => Some(AccessControlAllowOrigin.All),
      allowedMethods =
        AccessControlAllowMethods(Method.GET, Method.POST, Method.OPTIONS),
    )

  override def run =
    GraphQLApi.api.interpreter
      .flatMap(interpreter => {

        val httpMediaStream = Http.collect[Request] {
          case Method.GET -> !! / "media" / "preview" / mediaId =>
            Response.text(s"Media preview: $mediaId")
          case Method.GET -> !! / "media" / mediaId =>
            Response.text(s"Media full: $mediaId")
        }

        val httpGraphql = Http.collectHttp[Request] {
          case _ -> !! / "graphql" =>
            ZHttpAdapter.makeHttpService(HttpInterpreter(interpreter))
          case Method.GET -> !! / "graphiql" => graphiql
        }

        val app = (httpMediaStream ++ httpGraphql) @@ cors(corsCfg)

        Server.serve(app.withDefaultErrorResponse)
      })
      .provide(
        // LibraryServiceImpl.layer,
        // InMemoryLibraryStore.layer,
        // MediaServiceImpl.layer,
        // InMemoryMediaStore.layer,
        Server.defaultWithPort(8088),
      )
      .exitCode
