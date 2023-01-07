package xyz.trival.image_viewer.graphql

import caliban.RootResolver
import caliban.GraphQL.graphQL
import caliban.schema.Annotations.GQLDescription
import zio.*
import xyz.trival.image_viewer.modules.library.model.Library
import xyz.trival.image_viewer.modules.library.service.LibraryService
import xyz.trival.image_viewer.modules.library.service.LibraryErrors.LibraryNotFound
import java.util.UUID

// === Root schema definition ===

object Operations:

  case class Queries(
      @GQLDescription("test query")
      test: () => UIO[String],
      getLibraries: () => URIO[LibraryService, Seq[Library]],
  )

  // Mutations

  case class CreateLibraryArgs(
      name: String,
      rootPath: String,
  )

  case class UpdateLibraryArgs(
      id: String,
      name: Option[String],
      rootPath: Option[String],
      ignorePaths: Option[Set[String]],
  )

  case class DeleteLibraryArgs(
      id: String,
  )

  case class Mutations(
      createLibrary: CreateLibraryArgs => URIO[LibraryService, Library],
      updateLibrary: UpdateLibraryArgs => ZIO[
        LibraryService,
        LibraryNotFound,
        Library,
      ],
      deleteLibrary: DeleteLibraryArgs => URIO[
        LibraryService,
        Boolean,
      ],
  )

end Operations

// === GraphQL schema implementation ===

object Resolver:

  val queries = Operations.Queries(
    test = () =>
      Clock.nanoTime
        .map("test query result, hello caliban graphql! " + _.toString),
    getLibraries = () => LibraryService.getLibraries,
  )

  val mutations = Operations.Mutations(
    createLibrary =
      args => LibraryService.createLibrary(args.name, args.rootPath),
    updateLibrary = args =>
      LibraryService
        .updateLibrary(
          UUID.fromString(args.id),
          args.name,
          args.rootPath,
          args.ignorePaths,
        ),
    deleteLibrary = args =>
      LibraryService
        .deleteLibrary(UUID.fromString(args.id))
        .as(true)
        .orElseSucceed(false),
  )

  val root = RootResolver(queries, mutations)

end Resolver

// === Api export ===

type GqlEnv = LibraryService

object GraphQLApi:
  val api = graphQL[GqlEnv, Operations.Queries, Operations.Mutations, Unit](
    Resolver.root,
  )
