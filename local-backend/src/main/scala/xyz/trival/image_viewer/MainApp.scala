package xyz.trival.image_viewer

import xyz.trival.image_viewer.graphql.*
import caliban.GraphQL.graphQL
import caliban.{RootResolver, ZHttpAdapter}
import zhttp.http.*
import zhttp.service.Server
import zio.*
import zio.stream.*
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

        val httpApp = Http.collectHttp[Request] {
          case _ -> !! / "graphql" =>
            ZHttpAdapter.makeHttpService(interpreter)
          case _ -> !! / "graphiql" => graphiql
        } @@ Middleware.cors(corsCfg)

        Server
          .start(
            port = 8088,
            http = httpApp,
          )
      })
      .provide(LibraryServiceImpl.layer, InMemoryLibraryStore.layer)
      .exitCode
