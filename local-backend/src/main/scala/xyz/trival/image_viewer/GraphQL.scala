package xyz.trival.image_viewer.graphql

import caliban.RootResolver
import caliban.GraphQL.graphQL
import caliban.schema.Annotations.GQLDescription
import zio.*
import xyz.trival.image_viewer.modules.library.model.Library
import xyz.trival.image_viewer.modules.library.service.LibraryService

// === Root schema definition ===

object Operations:

  case class Queries(
      @GQLDescription("test query")
      test: () => UIO[String],
      getLibraries: () => URIO[LibraryService, Seq[Library]],
  )

  case class Mutations()

end Operations

// === GraphQL schema implementation ===

object Resolver:

  val queries = Operations.Queries(
    test = () =>
      Clock.nanoTime
        .map("test query result, hello caliban graphql! " + _.toString),
    getLibraries = () => LibraryService.getLibraries,
  )

  val mutations = Operations.Mutations()

  val root = RootResolver(queries)

end Resolver

// === Api export ===

type GqlEnv = LibraryService

object GraphQLApi:
  val api = graphQL[GqlEnv, Operations.Queries, Unit, Unit](
    Resolver.root,
  )
