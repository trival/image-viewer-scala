package xyz.trival.image_viewer.domain.media

import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

import java.util.UUID
import scala.util.hashing.MurmurHash3

enum MediaType:
  case Image, Video

case class Media(
    directory: String,
    filename: String,
    /** size in bytes */
    size: Long = 0,
    /** width in pixels */
    width: Int = 0,
    /** height in pixels */
    height: Int = 0,
    /** Optional creation date */
    date: Option[Long] = None,
    /** Optional video length in seconds */
    length: Option[Int] = None,
    idSuffix: Int = 0,
) extends Ordered[Media]:

  override def hashCode: Int =
    MurmurHash3.stringHash(
      directory + ':' + filename + ':' + size + ':' + width + ':' + height,
    )

  override def equals(other: Any): Boolean = other match
    case that: Media =>
      this.directory == that.directory && this.filename == that.filename
    case _ => false

  override def compare(that: Media): Int =
    val c = this.directory.compare(that.directory)
    if c == 0 then this.filename.compare(that.filename) else c

  val id =
    val hex = hashCode.toHexString
    if idSuffix > 0
    then hex + "_" + idSuffix.toHexString
    else hex

  val mediaType =
    // extract file extension from filename
    val ext =
      filename
        .substring(filename.lastIndexOf(".") + 1, filename.length)
        .toLowerCase

    if Media.imageExtensions.contains(ext) then MediaType.Image
    else if Media.videoExtensions.contains(ext) then MediaType.Video
    else throw new Exception("Unknown media type")

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
    "f4b",
  )
  val mediaExtensions = imageExtensions ++ videoExtensions

  def collectInRootPath(
      rootPath: os.Path,
      ignorePaths: List[os.Path],
  ): Seq[Media] =
    val files = os
      .walk(rootPath)
      .filter(file =>
        os.isFile(file)
          && ignorePaths.forall(ignorePath => !file.startsWith(ignorePath))
          && mediaExtensions.contains(file.ext.toLowerCase),
      )
    val media = files.map(file =>
      val relativePath = file.relativeTo(rootPath).toString
      val (directory, filename) =
        relativePath.splitAt(relativePath.lastIndexOf('/'))
      Media(directory, filename.replace("/", "")),
    )
    createDestinctIds(media).sorted

  def collectInRootPath(
      rootPath: String,
      ignorePaths: List[String] = List(),
  ): Seq[Media] =
    val path = os.Path(rootPath)
    collectInRootPath(path, ignorePaths.map(path / _))

  def createDestinctIds(media: Seq[Media]): Seq[Media] =
    val grouped = media.groupBy(m => m.hashCode)
    grouped.values.flatMap { ms =>
      if ms.length == 1 then ms
      else
        ms.zipWithIndex.map { case (m, i) =>
          m.copy(idSuffix = i)
        }
    }.toSeq

  given JsonDecoder[Media] = DeriveJsonDecoder.gen[Media]
  given JsonEncoder[Media] = DeriveJsonEncoder.gen[Media]
