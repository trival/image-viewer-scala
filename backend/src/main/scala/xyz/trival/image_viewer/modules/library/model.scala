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
)
