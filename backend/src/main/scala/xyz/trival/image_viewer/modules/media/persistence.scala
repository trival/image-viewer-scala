package xyz.trival.image_viewer.modules.media.persistence

trait MediaStorage:
  def saveLibraryMedia(lib: Library, media: Seq[Media]): Unit
  def deleteLibraryMedia(lib: Library, mediaIds: Seq[String]): Unit
