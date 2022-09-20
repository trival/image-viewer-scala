package test_scalajs

import xyz.trival.image_viewer.graphql.generated.ApiSchema.Queries
import xyz.trival.image_viewer.graphql.GraphqlApi
import zio.*

object Hello extends ZIOAppDefault:

  val request = GraphqlApi.createRequest(Queries.test)

  def run = for
    _ <- Console.printLine("Hello caliban client!")
    test <- request
    _ <- Console.printLine(test)
    test <- request
    _ <- Console.printLine(test)
    - <- Console.printLine("Goodbye caliban client!")
  yield ()
