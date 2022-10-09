package xyz.trival.image_viewer

import utest.*

object TestSuite extends TestSuite:
  val tests = Tests {
    test("test1") {
      1 + 1 ==> 2
    }
    test("test2") {
      assert(1 == 1)
    }
  }
