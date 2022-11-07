package xyz.trival.image_viewer.modules.tag.service

import zio.Task
import xyz.trival.image_viewer.modules.tag.model.{Tag, TagMediaLink}
import java.util.UUID

enum TagServiceError:
  case TagNotFound(id: UUID)
  case MediaNotFound(media: String)
  case TagMediaLinkNotFound(tagId: UUID, media: String)
  case EnexpecectedError(err: Throwable)

trait TagService:
  def createTag(name: String, color: Option[String]): Task[Tag]
  def createTag(name: String): Task[Tag] = createTag(name, None)
  def updateTag(
      id: UUID,
      name: Option[String],
      color: Option[String],
  ): Task[Tag]
  def deleteTag(id: UUID): Task[Unit]

  def addMediaLink(media: String, tagId: UUID): Task[TagMediaLink]
  def removeMediaLink(media: String, tagId: UUID): Task[Unit]
  def removeAllMediaLinks(media: String): Task[Unit]
