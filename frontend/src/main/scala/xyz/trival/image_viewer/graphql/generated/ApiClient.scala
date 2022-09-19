package xyz.trival.image_viewer.graphql.generated

import caliban.client.FieldBuilder._
import caliban.client._

object ApiClient {

  type Queries = _root_.caliban.client.Operations.RootQuery
  object Queries {

    /** test query
      */
    def test
        : SelectionBuilder[_root_.caliban.client.Operations.RootQuery, String] =
      _root_.caliban.client.SelectionBuilder.Field("test", Scalar())
  }

}

