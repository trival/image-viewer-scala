package xyz.trival.image_viewer

import xyz.trival.image_viewer.graphql.*
import caliban.GraphQL.graphQL
import caliban.{RootResolver, ZHttpAdapter}
import zhttp.http.*
import zhttp.service.Server
import zio.*
import zio.stream.*
import xyz.trival.image_viewer.modules.media.service.MediaServiceImpl
import xyz.trival.image_viewer.modules.media.persistence.InMemoryMediaStore
import xyz.trival.image_viewer.modules.library.service.LibraryServiceImpl
import xyz.trival.image_viewer.modules.library.persistence.InMemoryLibraryStore
import zhttp.http.middleware.Cors.CorsConfig

object MainApp extends ZIOAppDefault:

  val graphiql = Http.fromStream(ZStream.fromResource("graphiql.html"))

  val corsCfg: CorsConfig =
    CorsConfig(
      anyOrigin = true,
      anyMethod = true,
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
            ZHttpAdapter.makeHttpService(interpreter)
          case Method.GET -> !! / "graphiql" => graphiql
        }

        Server
          .start(
            port = 8088,
            http = (httpMediaStream ++ httpGraphql) @@ Middleware.cors(corsCfg),
          )
      })
      .provide(
        LibraryServiceImpl.layer,
        InMemoryLibraryStore.layer,
        MediaServiceImpl.layer,
        InMemoryMediaStore.layer,
      )
      .exitCode
