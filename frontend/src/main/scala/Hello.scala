package test_scalajs

import xyz.trival.image_viewer.graphql.generated.ApiClient
import sttp.client3.{*, given}
import sttp.client3.impl.zio.{*, given}
import zio.*

object Hello extends ZIOAppDefault:
  val backend = FetchZioBackend()
  val serverUrl = uri"http://localhost:8088/api/graphql"

  val q = ApiClient.Queries.test

  val result =
    q.toRequest(serverUrl).send(backend).map(_.body).absolve

  def run = for
    _ <- Console.printLine("Hello caliban client!")
    test <- result
    _ <- Console.printLine(test)
  yield ()
