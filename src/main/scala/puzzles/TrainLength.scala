package puzzles

import utils.MutableNode
import utils.MutableLinkedListUtils._

import scala.annotation.tailrec
import scala.util.Random

object TrainLength {
  def main(args: Array[String]): Unit =
    assert {
      (1 to 100)
        .map(_ => Random.nextInt(1000))
        .forall {
          size => findLength(generateCyclic(size)) == size
        }
    }

  // We explore one side of the train cycle, wagon by wagon, always coming back at the starting position.
  // We turn off the lights for all wagons, except the last one explored. The last wagon explored in a traversal will have the lights on.
  // If the start wagon has the lights on, we traversed a full cycle.
  // An alternative solution is using two pointers (one at the current position and the check of starting position as the while-loop flag. But hard to model
  private def findLength(maybeStartWagon: Option[MutableNode[Boolean]]): Int =
    maybeStartWagon
      .map { sw => // Start by making sure the light in the first wagon is closed
        sw.light = false
        sw
      }
      .map(iterate(_, 1))
      .getOrElse(0)

  @tailrec
  private def iterate(startWagon: MutableNode[Boolean], depth: Int): Int =
    if (traverse(startWagon, depth).light)
      depth - 1
    else
      iterate(startWagon, depth + 1)

  private def traverse(startWagon: MutableNode[Boolean], count: Int): MutableNode[Boolean] =
    Stream
      .iterate(startWagon) { currentWagon =>
        currentWagon.light = false
        currentWagon.next.light = true
        currentWagon.next
      }
      .take(count)
      .toList
      .head
}
