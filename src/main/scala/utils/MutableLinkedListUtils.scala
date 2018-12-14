package utils

object MutableLinkedListUtils {
  def generateCyclic[T: RandomOf](length: Int): MutableNode[T] = {
    val randomGenerator = implicitly[RandomOf[T]]

    if(length == 0) null
    else {
      val head = MutableNode(randomGenerator.random, null, null)

      var tail = head
      for(_ <- 1 until length) {
        val newTail = MutableNode(randomGenerator.random, tail, null)
        tail.next = newTail
        tail = newTail
      }
      tail.next = head

      head
    }
  }
}
