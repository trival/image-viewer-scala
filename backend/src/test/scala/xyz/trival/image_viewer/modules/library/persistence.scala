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
)

object InMemoryLibraryStoreTest extends ZIOSpecDefault:
  def spec =
    libraryStoreSpec("InMemoryLibraryStore").provideLayer(
      InMemoryLibraryStore.layer,
    )
