package test_scalajs

import xyz.trival.image_viewer.graphql.generated.ApiSchema
import xyz.trival.image_viewer.graphql.Api
import zio.*

object Hello extends ZIOAppDefault:

  val request = Api.createRequest(ApiSchema.Queries.test)

  def run = for
    _ <- Console.printLine("Hello caliban client!")
    test <- request
    _ <- Console.printLine(test)
    test <- request
    _ <- Console.printLine(test)
    - <- Console.printLine("Goodbye caliban client!")
  yield ()
