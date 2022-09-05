package xyz.trival.image_viewer.domain.album

import java.util.UUID

case class Album(
    id: UUID,
    name: String,
    color: Option[String] = None
)
