package xyz.trival.image_viewer.graphql

import caliban.RootResolver
import caliban.GraphQL.graphQL
import caliban.schema.Annotations.GQLDescription

// root schema definition
object Operations:

  case class Queries(
      @GQLDescription("test query")
      test: () => String
  )

  case class Mutations()

end Operations

// graphQL schema implementation
object Resolver:

  val queries = Operations.Queries(
    test = () => "test query result, hello caliban graphql!"
  )

  val mutations = Operations.Mutations()

  val root = RootResolver(queries)

end Resolver

// api export
object GraphQLApi:
  val api = graphQL(
    Resolver.root
  )
