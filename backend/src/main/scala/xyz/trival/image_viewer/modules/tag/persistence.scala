package xyz.trival.image_viewer.modules.tag.persistence

import xyz.trival.image_viewer.modules.tag.model.{Tag, TagMediaLink}
import java.util.UUID
import zio.*

enum TagStorageError:
  case TagNotFound(id: UUID)
  case MediaNotFound(media: String)
  case TagMediaLinkNotFound(tagId: UUID, media: String)
  case EnexpecectedError(err: Throwable)

trait TagStorage:
  def getTags(libId: UUID): IO[TagStorageError, Seq[Tag]]
