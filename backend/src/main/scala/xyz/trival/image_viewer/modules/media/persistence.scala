package xyz.trival.image_viewer.modules.media.persistence

import xyz.trival.image_viewer.modules.media.model.Media
import java.util.UUID
import zio.*
import xyz.trival.image_viewer.modules.library.service.LibraryErrors.LibraryNotFound

type ProcessedMediaId = String

trait MediaStorage:
  def getLibraryMedia(libId: UUID): IO[LibraryNotFound, Seq[Media]]

  def saveLibraryMedia(
      libId: UUID,
      media: Seq[Media],
  ): UIO[Seq[ProcessedMediaId]]

  def deleteLibraryMedia(
      libId: UUID,
      mediaIds: Seq[String],
  ): UIO[Seq[ProcessedMediaId]]
