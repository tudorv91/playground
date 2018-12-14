package puzzles

import utils.MutableNode
import utils.MutableLinkedListUtils._

import scala.annotation.tailrec

object TrainLength {
  def main(args: Array[String]): Unit = {
    assert(findLength(generateCyclic(1)) == 1)
    assert(findLength(generateCyclic(0)) == 0)
    assert(findLength(generateCyclic(7)) == 7)
    assert(findLength(generateCyclic(49)) == 49)
  }

  // We explore one side of the train cycle, wagon by wagon, always coming back at the starting position.
  // We turn off the lights for all wagons, except the last one explored. The last wagon explored in a traversal will have the lights on.
  // If the start wagon has the lights on, we traversed a full cycle.
  // An alternative solution is using two pointers (one at the current position and the check of starting position as the while-loop flag. But hard to model
  private def findLength(startWagon: MutableNode[Boolean]): Int =
    if (startWagon == null)
      0
    else {
      var counter = 0
      startWagon.data = false

      while (!startWagon.data) {
        counter += 1
        traverse(startWagon, counter)
      }

      counter
    }

  @tailrec
  private def traverse(currentWagon: MutableNode[Boolean], count: Int): Unit =
    if (count == 0)
      currentWagon.data = true
    else {
      currentWagon.data = false
      traverse(currentWagon.next, count - 1)
    }
}
