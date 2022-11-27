package xyz.trival.image_viewer.modules.tag.model

import java.util.UUID

case class Tag(
    id: UUID,
    name: String,
    color: Option[String] = None,
)

case class TagMediaLink(
    tagId: UUID,
    media: String,
)
