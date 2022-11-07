package xyz.trival.image_viewer.modules.library.model

import xyz.trival.image_viewer.modules.media.model.Media
import xyz.trival.image_viewer.modules.tag.model.{Tag, TagMediaLink}
import java.util.UUID
import scala.util.hashing.MurmurHash3

case class Library(
    id: UUID,
    name: String,
    rootPath: String,
    ignorePaths: Set[String],
    media: Set[Media],
    tags: Set[Tag],
    MediaTags: Set[TagMediaLink],
)

case class LibraryInfo(
    id: UUID,
    name: String,
    rootPath: String,
)

given Conversion[Library, LibraryInfo] with
  def apply(lib: Library): LibraryInfo =
    LibraryInfo(lib.id, lib.name, lib.rootPath)
