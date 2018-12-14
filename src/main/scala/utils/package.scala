import scala.util.Random

package object utils {
  trait RandomOf[T]{
    def random: T
  }

  implicit val randomBoolean: RandomOf[Boolean] = new RandomOf[Boolean] {
    override def random: Boolean = Random.nextBoolean()
  }
}
