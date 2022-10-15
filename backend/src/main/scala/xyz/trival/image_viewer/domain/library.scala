package xyz.trival.image_viewer.domain.library

import xyz.trival.image_viewer.domain.media.Media

import java.util.UUID
import scala.util.hashing.MurmurHash3

case class Library(
    id: UUID,
    name: String,
    rootPath: String,
    ignorePaths: Set[String],
    media: Set[Media],
    tags: Set[Tag],
    MediaTags: Set[MediaTag]
)

case class LibraryInfo(
    id: UUID,
    name: String,
    rootPath: String
)

given Conversion[Library, LibraryInfo] with
  def apply(lib: Library): LibraryInfo =
    LibraryInfo(lib.id, lib.name, lib.rootPath)

case class Tag(
    id: UUID,
    name: String,
    color: Option[String] = None
)

case class MediaTag(
    tagId: UUID,
    media: String
)
