package misc

import scala.collection.immutable.Stream
import scala.BigInt
import scala.collection.immutable.Stream.consWrapper

object StreamFactory {
  def getLookSayStream(n: BigInt): Stream[BigInt] = {
    def s: Stream[BigInt] = n.abs#:: s.map(StreamFactory.lookSay)
    s
  }
  def lookSay(n: BigInt): BigInt = {
    val digits = n.toString.toList.map(_.asDigit)
    val strCount = countDigit(digits).flatMap(x => List(x._2.toString, x._1.toString)).reduceLeft((x,y) => x + y)
    BigInt(strCount)
  }

  def countDigit(s: List[Int]): List[(Int, Int)] = {
    s match {
      case Nil => Nil
      case x::xs =>
        val (s1, s2) = s.span( i => i == x)
        (x, s1.length)::countDigit(s2)
    }
  }
}
