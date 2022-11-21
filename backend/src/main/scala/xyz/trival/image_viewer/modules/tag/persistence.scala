package xyz.trival.image_viewer.modules.tag.persistence

import xyz.trival.image_viewer.modules.tag.model.{Tag, TagMediaLink}
import java.util.UUID
import zio.*
import xyz.trival.image_viewer.modules.library.service.LibraryErrors.LibraryNotFound
import xyz.trival.image_viewer.modules.tag.service.TagErrors.TagNotFound

trait TagStorage:
  def getTags(libId: UUID): IO[LibraryNotFound, Seq[Tag]]
  def getTagMediaLinks(libId: UUID): IO[LibraryNotFound, Seq[TagMediaLink]]

  def deleteTag(
      libId: UUID,
      tagId: UUID,
  ): IO[LibraryNotFound | TagNotFound, Unit]
