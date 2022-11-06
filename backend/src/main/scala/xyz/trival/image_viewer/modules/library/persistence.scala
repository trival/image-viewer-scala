package xyz.trival.image_viewer.modules.library.persistence

import java.util.UUID
import zio.*
import xyz.trival.image_viewer.modules.library.model.{Library, LibraryInfo}

enum LibraryPersistenceError:
  case LibraryNotFound(id: UUID)
  case UnexpectedError(err: Throwable)

trait LibraryStorage:
  def getLibraries(): UIO[Seq[LibraryInfo]]
  def loadLibrary(lib: Library): Library
  // TODO: create save library input type
  def saveLibrary(lib: Library): IO[Unit, LibraryPersistenceError]
  def deleteLibrary(libId: UUID): IO[Unit, LibraryPersistenceError]
