package xyz.trival.image_viewer.domain.media

import java.util.UUID
import scala.util.hashing.MurmurHash3

enum MediaType:
  case Image, Video

case class Media(
    directory: String,
    filename: String,
    mediaType: MediaType,
    size: Int = 0,
    width: Int = 0,
    height: Int = 0,
    date: Option[Int] = None,
    length: Option[Int] = None,
    idSuffix: Int = 0
) extends Ordered[Media]:

  override def hashCode: Int =
    MurmurHash3.stringHash(directory + ':' + filename)

  override def equals(other: Any): Boolean = other match
    case that: Media =>
      this.directory == that.directory && this.filename == that.filename
    case _ => false

  override def compare(that: Media): Int =
    val c = this.directory.compare(that.directory)
    if c == 0 then this.filename.compare(that.filename) else c

  val id: String = hashCode.toString + idSuffix.toString

object Media:
  val imageExtensions = Set("jpg", "jpeg", "png", "gif", "bmp", "webp", "heic")
  val videoExtensions = Set(
    "mp4",
    "webm",
    "mkv",
    "avi",
    "mov",
    "wmv",
    "flv",
    "mpg",
    "mpeg",
    "m4v",
    "3gp",
    "3g2",
    "ogv",
    "ogg",
    "ogm",
    "ogx",
    "rm",
    "rmvb",
    "vob",
    "asf",
    "amv",
    "m2v",
    "m4v",
    "svi",
    "mxf",
    "roq",
    "nsv",
    "f4v",
    "f4p",
    "f4a",
    "f4b"
  )
  val mediaExtensions = imageExtensions ++ videoExtensions

  def collectInRootPath(rootPath: String): Seq[Media] =
    val root = os.Path(rootPath)
    val files = os
      .walk(root)
      .filter(file =>
        os.isFile(file) && mediaExtensions.contains(file.ext.toLowerCase)
      )
    files.map { file =>
      val relativePath = file.relativeTo(root).toString
      val (directory, filename) =
        relativePath.splitAt(relativePath.lastIndexOf('/'))
      Media(directory, filename, MediaType.Image)
    }
