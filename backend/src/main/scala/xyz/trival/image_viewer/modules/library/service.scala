package xyz.trival.image_viewer.modules.library.service

import xyz.trival.image_viewer.modules.library.model.{Library}
import zio.*
import java.util.UUID
import xyz.trival.image_viewer.modules.library.service.LibraryErrors.LibraryNotFound
import xyz.trival.image_viewer.modules.library.persistence.LibraryStorage

object LibraryErrors:
  case class LibraryNotFound(id: UUID)

trait LibraryService:
  def getLibraries: UIO[Seq[Library]]

  def createLibrary(
      name: String,
      rootPath: String,
  ): UIO[Library]

  def updateLibrary(
      id: UUID,
      name: Option[String],
      rootPath: Option[String],
      ignorePaths: Option[Set[String]],
  ): IO[LibraryNotFound, Library]

  def deleteLibrary(id: UUID): IO[LibraryNotFound, Unit]

case class LibraryServiceImpl(
    store: LibraryStorage,
) extends LibraryService:
  def getLibraries: UIO[Seq[Library]] =
    store.getLibraries()

  def createLibrary(
      name: String,
      rootPath: String,
  ): UIO[Library] =
    val lib = Library(name, rootPath)
    store.saveLibrary(lib).as(lib)

  def updateLibrary(
      id: UUID,
      name: Option[String],
      rootPath: Option[String],
      ignorePaths: Option[Set[String]],
  ): IO[LibraryNotFound, Library] =
    for
      lib <- store.getLibrary(id)
      updatedLib = lib.copy(
        name = name.getOrElse(lib.name),
        rootPath = rootPath.getOrElse(lib.rootPath),
        ignorePaths = ignorePaths.getOrElse(lib.ignorePaths),
      )
      _ <- store.saveLibrary(updatedLib)
    yield updatedLib

  def deleteLibrary(id: UUID): IO[LibraryNotFound, Unit] =
    store.deleteLibrary(id)

object LibraryServiceImpl:
  def layer: ZLayer[LibraryStorage, Nothing, LibraryService] =
    ZLayer {
      for store <- ZIO.service[LibraryStorage]
      yield LibraryServiceImpl(store)
    }
