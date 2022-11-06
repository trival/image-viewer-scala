package xyz.trival.image_viewer.modules.tag.service

import zio.Task
import xyz.trival.image_viewer.modules.tag.model.{Tag, MediaTag}

import java.util.UUID

trait TagService:
  def createTag(name: String, color: Option[String]): Task[Tag]
  def createTag(name: String): Task[Tag] = createTag(name, None)
  def updateTag(
      id: UUID,
      name: Option[String],
      color: Option[String],
  ): Task[Tag]
  def deleteTag(id: UUID): Task[Unit]

  def addMediaTag(media: String, tagId: UUID): Task[MediaTag]
  def removeMediaTag(media: String, tagId: UUID): Task[Unit]
