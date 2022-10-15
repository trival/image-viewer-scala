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

  val id: String =
    val hex = hashCode.toHexString
    if idSuffix > 0
    then hex + "_" + idSuffix.toHexString
    else hex

end Media

object Media:
  val webImageExtensions =
    Set("jpg", "jpeg", "png", "gif", "webp", "avif", "svg")
  val imageExtensions = webImageExtensions ++ Set("bmp", "heic", "tiff", "raw")
  val webVideoExtensions = Set("webm", "mp4")
  val videoExtensions = webVideoExtensions ++ Set(
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

  def collectInRootPath(rootPath: os.Path): Seq[Media] =
    val files = os
      .walk(rootPath)
      .filter(file =>
        os.isFile(file) && mediaExtensions.contains(file.ext.toLowerCase)
      )
    val media = files.map(file =>
      val relativePath = file.relativeTo(rootPath).toString
      val (directory, filename) =
        relativePath.splitAt(relativePath.lastIndexOf('/'))
      val mediaType =
        if imageExtensions.contains(file.ext.toLowerCase) then MediaType.Image
        else if videoExtensions.contains(file.ext.toLowerCase) then
          MediaType.Video
        else throw new Exception("Unknown media type")
      Media(directory, filename.replace("/", ""), mediaType)
    )
    createDestinctIds(media).sorted

  def collectInRootPath(rootPath: String): Seq[Media] =
    collectInRootPath(os.Path(rootPath))

  def createDestinctIds(media: Seq[Media]): Seq[Media] =
    val grouped = media.groupBy(m => m.hashCode)
    grouped.values.flatMap { ms =>
      if ms.length == 1 then ms
      else
        ms.zipWithIndex.map { case (m, i) =>
          m.copy(idSuffix = i)
        }
    }.toSeq
