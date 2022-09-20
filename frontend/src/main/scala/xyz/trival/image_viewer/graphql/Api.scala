package xyz.trival.image_viewer.graphql

import sttp.client3.{*, given}
import sttp.client3.impl.zio.FetchZioBackend
import caliban.client.SelectionBuilder
import caliban.client.Operations.RootQuery

object Api:

  val backend = FetchZioBackend()
  val serverUrl = uri"http://localhost:8088/api/graphql"

  def createRequest[Result](query: SelectionBuilder[RootQuery, Result]) =
    query.toRequest(serverUrl).send(backend).map(_.body).absolve
