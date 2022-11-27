package xyz.trival.image_viewer.modules.library.persistence

import zio.*
import zio.test.{test, *}
import zio.test.Assertion.*
import java.util.UUID
import xyz.trival.image_viewer.modules.library.service.LibraryErrors.LibraryNotFound
import xyz.trival.image_viewer.modules.library.model.Library

def libraryStoreSpec(name: String) = suite(name)(
  test("fails to get library if it doesn't exist") {
    val id = UUID.randomUUID()
    LibraryStorage
      .getLibrary(id)
      .exit
      .map(assert(_)(fails(equalTo(LibraryNotFound(id)))))
  },
  test("saves and gets library") {
    val lib = Library("name", "rootPath")
    for
      _ <- LibraryStorage.saveLibrary(lib)
      result <- LibraryStorage.getLibrary(lib.id)
    yield assertTrue(result == lib)
  },
  test("getLibraries") {
    val lib1 = Library("name1", "rootPath1")
    val lib2 = Library("name2", "rootPath2")
    for
      _ <- LibraryStorage.saveLibrary(lib1)
      _ <- LibraryStorage.saveLibrary(lib2)
      result <- LibraryStorage.getLibraries()
      set = result.toSet
      _ <- assertTrue(result.size == 2)
    yield assertTrue(set == Set(lib1, lib2))
  },
  test("overriding library") {
    val lib1 = Library("name1", "rootPath1")
    val lib2 = lib1.copy(name = "name2")
    for
      _ <- LibraryStorage.saveLibrary(lib1)
      _ <- LibraryStorage.saveLibrary(lib2)
      result <- LibraryStorage.getLibraries()
    yield assertTrue(result == Seq(lib2))
  },
  test("delete library") {
    val lib1 = Library("name1", "rootPath1")
    val lib2 = Library("name2", "rootPath2")
    for
      _ <- LibraryStorage.saveLibrary(lib1)
      _ <- LibraryStorage.saveLibrary(lib2)
      _ <- LibraryStorage.deleteLibrary(lib1.id)
      result <- LibraryStorage.getLibraries()
    yield assertTrue(result == Seq(lib2))
  },
)

object InMemoryLibraryStoreTest extends ZIOSpecDefault:
  def spec =
    libraryStoreSpec("InMemoryLibraryStore").provideLayer(
      InMemoryLibraryStore.layer,
    )
