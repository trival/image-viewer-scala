package xyz.trival.image_viewer.domain.library.model

import java.util.UUID

case class Library(
    id: UUID,
    name: String,
    rootPath: String,
    ignorePaths: Set[String]
)
