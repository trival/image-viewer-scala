package xyz.trival.image_viewer

import xyz.trival.image_viewer.models.*
import caliban.GraphQL.graphQL
import caliban.{RootResolver, ZHttpAdapter}
import zhttp.http.*
import zhttp.service.Server
import zio.*

import scala.language.postfixOps
import caliban.CalibanError

object MainApp extends ZIOAppDefault:

  private val employees = List(
    Employee("Alex", Role.DevOps),
    Employee("Maria", Role.SoftwareDeveloper),
    Employee("James", Role.SiteReliabilityEngineer),
    Employee("Peter", Role.SoftwareDeveloper),
    Employee("Julia", Role.SiteReliabilityEngineer),
    Employee("Roberta", Role.DevOps)
  )

  override def run =
    graphQL[Any, Queries, Unit, Unit](
      RootResolver(
        Queries(
          args => employees.filter(e => args.role == e.role),
          args => employees.find(e => e.name == args.name)
        )
      )
    ).interpreter.flatMap(interpreter => {
      Server
        .start(
          port = 8088,
          http = Http.collectHttp { case _ -> !! / "api" / "graphql" =>
            ZHttpAdapter.makeHttpService(interpreter)
          }
        )
    })
