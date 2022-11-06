package xyz.trival.image_viewer.modules.library.service

import xyz.trival.image_viewer.modules.library.model.{Library, LibraryInfo}
import zio.Task

import java.util.UUID

trait LibraryService:
  def getLibraries: Task[List[LibraryInfo]]
  def getLibrary(id: UUID): Task[Option[Library]]

  def createLibrary(name: String, rootPath: String): Task[Library]
  def updateLibrary(
      id: UUID,
      name: Option[String],
      rootPath: Option[String],
      ignorePaths: Option[Set[String]],
  ): Task[Library]
  def deleteLibrary(id: UUID): Task[Unit]
