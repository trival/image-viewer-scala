package xyz.trival.image_viewer.domain.media

import utest.*

object MediaTests extends TestSuite:
  val tests = Tests {
    test("collectInRootPath") {
      val ms = Media.collectInRootPath(os.pwd / "test-lib-1")
      for m <- ms do println(m)
      assert(ms.length > 1)

      val ms2 = Media.collectInRootPath((os.pwd / "test-lib-1").toString)
      assert(ms == ms2)
    }
  }
