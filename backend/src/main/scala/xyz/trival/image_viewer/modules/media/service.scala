package xyz.trival.image_viewer.modules.media.service

import xyz.trival.image_viewer.modules.library.model.{Library, LibraryInfo}
import zio.Task

import java.util.UUID

trait MediaService:
  def reloadLibraryMedia(libraryId: UUID): Task[Library]
  def reloadLibraryMediaPath(libraryId: UUID, path: String): Task[Library]
