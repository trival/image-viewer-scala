package xyz.trival.image_viewer.modules.library.persistence

import java.util.UUID
import zio.*
import xyz.trival.image_viewer.modules.library.model.{Library}

trait LibraryStorage:
  def getLibraries(): UIO[Seq[Library]]
  def loadLibrary(lib: Library): UIO[Library]
  // TODO: create save library input type
  def saveLibrary(lib: Library): UIO[Unit]
  def deleteLibrary(libId: UUID): UIO[Unit]
