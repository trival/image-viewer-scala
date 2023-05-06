package xyz.trival.image_viewer.modules.media.model

import utest.*
import java.util.Date
import zio.json.EncoderOps
import java.text.DateFormat
import java.util.Calendar
import java.util.GregorianCalendar

object MediaTests extends TestSuite:
  val tests = Tests {
    test("collectInRootPath") {
      val ms = Media.collectInRootPath(os.pwd / "test-lib-1", List())
      for m <- ms do println(m)
      assert(ms.length > 1)

      val ms2 = Media.collectInRootPath((os.pwd / "test-lib-1").toString)
      assert(ms == ms2)

      val ms3 =
        Media.collectInRootPath((os.pwd / "test-lib-1").toString, List("test2"))

      assert(ms3.length < ms.length)
      ms3.length ==> ms.length - 6
    }

    test("json") {
      val m =
        Media(
          directory = "test-dir",
          filename = "test.jpg",
          size = 234,
          width = 400,
          height = 300,
          date = Some(GregorianCalendar(2020, 12, 24).getTimeInMillis),
          length = Some(500),
        )

      m.toJson ==> """{"directory":"test-dir","filename":"test.jpg","size":234,"width":400,"height":300,"date":1611442800000,"length":500}"""
    }

    test("ids") {
      val m1 = Media("test", "test.jpg")
      val m2 = Media("test", "test.jpg")
      m1 ==> m2
    }
  }
