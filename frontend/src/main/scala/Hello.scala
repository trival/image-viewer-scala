package test_scalajs

import xyz.trival.image_viewer.graphql.generated.ApiClient
import sttp.client3.*
import zio.*

@main def main() =
  println("Hello World")
  val backend = FetchZioBackend()
  val q = ApiClient.Queries.test
  val serverUrl = uri"http://localhost:8088/api/graphql"
  val result: Task[List[String]] =
    q.toRequest(serverUrl).send(backend).map(_.body).absolve
