package xyz.trival.image_viewer.modules.media.service

import xyz.trival.image_viewer.modules.library.model.{Library}
import zio.*
import java.util.UUID
import xyz.trival.image_viewer.modules.library.service.LibraryErrors.LibraryNotFound
import xyz.trival.image_viewer.modules.media.model.Media
import xyz.trival.image_viewer.modules.media.service.MediaErrors.PathNotFound

object MediaErrors:
  case class PathNotFound(path: String)
  case class MediaNotFound(mediaId: String)

trait MediaService:
  def getLibraryMedia(libId: UUID): IO[LibraryNotFound, Seq[Media]]
  def reloadLibraryMedia(libId: UUID): IO[LibraryNotFound, Seq[Media]]
  def reloadLibraryMediaPath(
      libId: UUID,
      path: String,
  ): IO[LibraryNotFound | PathNotFound, Seq[Media]]
