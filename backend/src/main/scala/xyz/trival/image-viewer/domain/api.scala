import xyz.trival.image_viewer.domain.entities.{Library, LibraryInfo, MediaTag, Tag}
import zio.Task

import java.util.UUID

trait API:
  def getLibraries: Task[List[LibraryInfo]]
  def getLibrary(id: UUID): Task[Option[Library]]

  def createLibrary(name: String, rootPath: String): Task[Library]
  def updateLibrary(
      id: UUID,
      name: Option[String],
      rootPath: Option[String],
      ignorePaths: Option[Set[String]]
  ): Task[Library]
  def deleteLibrary(id: UUID): Task[Unit]
  // TODO: convert to progress stream if possible
  def reloadLibraryMedia(libraryId: UUID): Task[Library]
  def reloadLibraryMediaPath(libraryId: UUID, path: String): Task[Library]

  def createTag(name: String, color: Option[String]): Task[Tag]
  def createTag(name: String): Task[Tag] = createTag(name, None)
  def updateTag(
      id: UUID,
      name: Option[String],
      color: Option[String]
  ): Task[Tag]
  def deleteTag(id: UUID): Task[Unit]

  def addMediaTag(media: String, tagId: UUID): Task[MediaTag]
  def removeMediaTag(media: String, tagId: UUID): Task[Unit]
