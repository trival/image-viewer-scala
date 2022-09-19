package xyz.trival.image_viewer.domain.media.model

import java.util.UUID

enum MediaType:
  case Image, Video

case class Media(
    id: UUID,
    libraryId: UUID,
    mediaType: MediaType,
    directory: String,
    fullPath: String,
    thumbPath: Option[String],
    albums: List[UUID],
    fileMeta: Option[FileMeta],
    mediaMeta: Option[MediaMeta]
)

case class FileMeta(
    filename: String,
    size: Long,
    createdAt: Long,
    updatedAt: Long
)

case class MediaMeta(
    width: Int,
    height: Int,
    date: Long,
    length: Long
)
