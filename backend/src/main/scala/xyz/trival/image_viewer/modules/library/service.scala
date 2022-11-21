package xyz.trival.image_viewer.modules.library.service

import xyz.trival.image_viewer.modules.library.model.{Library}
import zio.*
import java.util.UUID
import xyz.trival.image_viewer.modules.library.service.LibraryErrors.LibraryNotFound

object LibraryErrors:
  case class LibraryNotFound(id: UUID)

trait LibraryService:
  def getLibraries: UIO[List[Library]]

  def getLibrary(
      id: UUID,
  ): IO[LibraryNotFound, Library]

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
