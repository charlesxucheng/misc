package misc

import scala.collection.immutable.Stream
import scala.BigInt
import scala.collection.immutable.Stream.consWrapper

object StreamFactory {

  def getLookSayStream(n: BigInt): Stream[BigInt] = {
    def s: Stream[BigInt] = n.abs#:: s.map(StreamFactory.lookSay)
    s
  }

  /**
   * Produce a "LookSay" number on the input number.
   */
  def lookSay(n: BigInt): BigInt = {
    val digits = n.toString.toList.map(_.asDigit)
    val strCount = countDigit(digits).flatMap(x => List(x._2.toString, x._1.toString)).reduceLeft((x,y) => x + y)
    BigInt(strCount)
  }

  /**
   * Counts consecutive occurrence of digits of a list of digits and returns the result in a list of Pair(Digit, Count) form.
   */
  def countDigit(s: List[Int]): List[(Int, Int)] = {
    s match {
      case Nil => Nil
      case x::xs =>
        val (s1, s2) = s.span( i => i == x)
        (x, s1.length)::countDigit(s2)
    }
  }
}
