package xyz.trival.image_viewer.domain.entities

import java.util.UUID

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
    rootPath: String,
)

enum MediaType:
  case Image, Video

case class Media(
    // TODO: Hash directory and filename as id
    id: String,
    directory: String,
    filename: String,
    mediaType: MediaType,
    size: Int,
    width: Int,
    height: Int,
    date: Option[Int],
    length: Option[Int]
)

case class Tag(
    id: UUID,
    name: String,
    color: Option[String] = None
)

case class MediaTag(
    tagId: UUID,
    media: String
)
