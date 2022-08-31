package xyz.trival.image_viewer

import xyz.trival.image_viewer.queries.*
import caliban.GraphQL.graphQL
import caliban.{RootResolver, ZHttpAdapter}
import zhttp.http.*
import zhttp.service.Server
import zio.*
import zio.stream.*

object MainApp extends ZIOAppDefault:

  private val graphiql = Http.fromStream(ZStream.fromResource("graphiql.html"))

  override def run =
    graphQL(
      RootResolver(
        Queries()
      )
    ).interpreter.flatMap(interpreter => {
      Server
        .start(
          port = 8088,
          http = Http.collectHttp[Request] {
            case _ -> !! / "api" / "graphql" =>
              ZHttpAdapter.makeHttpService(interpreter)
            case _ -> !! / "graphiql" => graphiql
          }
        )
    })
