package xyz.trival.image_viewer.modules.media.persistence

import xyz.trival.image_viewer.modules.library.model.Library
import xyz.trival.image_viewer.modules.media.model.Media

trait MediaStorage:
  def saveLibraryMedia(lib: Library, media: Seq[Media]): Unit
  def deleteLibraryMedia(lib: Library, mediaIds: Seq[String]): Unit
