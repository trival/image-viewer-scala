package xyz.trival.image_viewer.modules.media.persistence

import xyz.trival.image_viewer.modules.media.model.Media
import java.util.UUID

trait MediaStorage:
  def saveLibraryMedia(libId: UUID, media: Seq[Media]): Unit
  def deleteLibraryMedia(libId: UUID, mediaIds: Seq[String]): Unit
