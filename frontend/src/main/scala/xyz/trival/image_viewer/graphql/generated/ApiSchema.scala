package xyz.trival.image_viewer.graphql.generated

import caliban.client.FieldBuilder._
import caliban.client._

object ApiSchema {

  type ID = String

  type Library
  object Library {
    def id: SelectionBuilder[Library, String] =
      _root_.caliban.client.SelectionBuilder.Field("id", Scalar())
    def name: SelectionBuilder[Library, String] =
      _root_.caliban.client.SelectionBuilder.Field("name", Scalar())
    def rootPath: SelectionBuilder[Library, String] =
      _root_.caliban.client.SelectionBuilder.Field("rootPath", Scalar())
    def ignorePaths: SelectionBuilder[Library, List[String]] =
      _root_.caliban.client.SelectionBuilder
        .Field("ignorePaths", ListOf(Scalar()))
  }

  type Queries = _root_.caliban.client.Operations.RootQuery
  object Queries {

    /** test query
      */
    def test
        : SelectionBuilder[_root_.caliban.client.Operations.RootQuery, String] =
      _root_.caliban.client.SelectionBuilder.Field("test", Scalar())
    def getLibraries[A](
        innerSelection: SelectionBuilder[Library, A],
    ): SelectionBuilder[_root_.caliban.client.Operations.RootQuery, List[A]] =
      _root_.caliban.client.SelectionBuilder
        .Field("getLibraries", ListOf(Obj(innerSelection)))
  }

}

