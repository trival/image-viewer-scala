package xyz.trival.image_viewer.utils

case class Diff(
    added: Set[String] = Set(),
    removed: Set[String] = Set(),
):
  def isEmpty: Boolean = added.isEmpty && removed.isEmpty

def diff(
    oldIds: Seq[String],
    newIds: Seq[String],
): Diff =
  val oldSet = oldIds.toSet
  val newSet = newIds.toSet

  val added = newSet.diff(oldSet)
  val removed = oldSet.diff(newSet)

  Diff(added, removed)
