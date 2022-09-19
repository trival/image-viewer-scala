package xyz.trival.image_viewer.graphql

import caliban.RootResolver
import caliban.GraphQL.graphQL
import caliban.schema.Annotations.GQLDescription

object Operations:
  case class Queries(
      @GQLDescription("test query")
      test: () => String
  )

  case class Mutations()

object Resolver:
  val queries = Operations.Queries(
    test = () => "test"
  )

  val mutations = Operations.Mutations()

  val root = RootResolver(queries)

object GraphQLApi:

  val api = graphQL(
    Resolver.root
  )
