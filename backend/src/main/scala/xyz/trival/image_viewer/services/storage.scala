package xyz.trival.image_viewer.services

import xyz.trival.image_viewer.domain.media.Media
import xyz.trival.image_viewer.domain.library.Library
import java.util.UUID

trait Storage:
  def getLibraries(): Seq[Library]
  def loadLibraryMedia(lib: Library): Library
  def saveLibrary(lib: Library): Unit
  def deleteLibrary(libId: UUID): Unit
  def saveLibraryMedia(lib: Library, media: Seq[Media]): Unit
  def deleteLibraryMedia(lib: Library, mediaIds: Seq[String]): Unit
