package xyz.trival.image_viewer.modules.tag.service

import zio.*
import xyz.trival.image_viewer.modules.tag.model.{Tag, TagMediaLink}
import java.util.UUID
import xyz.trival.image_viewer.modules.library.service.LibraryErrors.LibraryNotFound
import xyz.trival.image_viewer.modules.tag.service.TagErrors.TagNotFound
import xyz.trival.image_viewer.modules.media.service.MediaErrors.MediaNotFound
import xyz.trival.image_viewer.modules.tag.service.TagErrors.TagMediaLinkNotFound

object TagErrors:
  case class TagNotFound(id: UUID)
  case class TagMediaLinkNotFound(tagId: UUID, media: String)

trait TagService:
  def getTags(libId: UUID): IO[LibraryNotFound, Seq[Tag]]

  def createTag(
      libId: UUID,
      name: String,
      color: Option[String],
  ): IO[LibraryNotFound, Tag]

  def updateTag(
      id: UUID,
      name: Option[String],
      color: Option[String],
  ): IO[TagNotFound, Tag]

  def deleteTag(id: UUID): IO[TagNotFound, Unit]

trait TagMediaLinkService:
  def getTagMediaLinks(libId: UUID): IO[LibraryNotFound, Seq[TagMediaLink]]

  def addLink(
      media: String,
      tagId: UUID,
  ): IO[TagNotFound | MediaNotFound, TagMediaLink]

  def removeLink(
      media: String,
      tagId: UUID,
  ): IO[TagNotFound | MediaNotFound | TagMediaLinkNotFound, Unit]

  def removeAllMediaLinks(media: String): IO[MediaNotFound, Unit]
