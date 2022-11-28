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

  private val graphiql = Http.fromStream(ZStream.fromResource("graphiql.html"))

  override def run =
    GraphQLApi.api.interpreter
      .flatMap(interpreter => {
        val config: CorsConfig =
          CorsConfig(
            anyOrigin = true,
            anyMethod = true,
          )

        val httpApp = Http.collectHttp[Request] {
          case _ -> !! / "graphql" =>
            ZHttpAdapter.makeHttpService(interpreter)
          case _ -> !! / "graphiql" => graphiql
        } @@ Middleware.cors(config)

        Server
          .start(
            port = 8088,
            http = httpApp,
          )
      })
      .provide(LibraryServiceImpl.layer, InMemoryLibraryStore.layer)
      .exitCode
