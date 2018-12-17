package utils

import scala.util.Random

object MutableLinkedListUtils {
  def generateCyclic(length: Int): Option[MutableNode[Boolean]] =
    if (length == 0)
      None
    else {
      val head = MutableNode(Random.nextBoolean(), null)

      val tail = List.fill(length - 1)(MutableNode(Random.nextBoolean(), null))

      val last = (head :: tail).reduceLeft(chain)

      last.next = head
      Some(head)
    }

  private def chain(first: MutableNode[Boolean], second: MutableNode[Boolean]): MutableNode[Boolean] = {
    first.next = second
    second
  }
}
