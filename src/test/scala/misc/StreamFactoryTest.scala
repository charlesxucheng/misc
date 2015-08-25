package misc

import scala.concurrent.duration.FiniteDuration
import org.scalatest.FlatSpec
import org.scalatest.FlatSpecLike
import org.scalatest.Inside
import org.scalatest.Inspectors
import org.scalatest.Matchers
import org.scalatest.OptionValues
import org.scalatest.prop.PropertyChecks
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalacheck.Gen
import java.math.BigInteger

abstract class UnitSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors with PropertyChecks

@RunWith(classOf[JUnitRunner])
class StreamFactoryTest extends UnitSpec {
  import StreamFactory._

"countDigit" should "return a list of digits and their consecutive occurences in order" in {
    countDigit(Nil) should be(Nil)
    countDigit(List(1)) should be(List((1, 1)))
    countDigit(List(1, 1)) should be(List((1, 2)))
    countDigit(List(5)) should be(List((5, 1)))
    countDigit(List(1, 3, 3, 4)) should be(List((1, 1), (3, 2), (4, 1)))
    countDigit(List(1, 2, 1, 1)) should be(List((1, 1), (2, 1), (1, 2)))
    countDigit(List(5, 5, 2, 1, 1, 1, 3)) should be(List((5, 2), (2, 1), (1, 3), (3, 1)))
    countDigit(List(1, 0, 1, 0, 1)) should be(List((1, 1), (0, 1), (1, 1), (0, 1), (1, 1)))
    countDigit(List(1, 2, 3, 4, 5)) should be(List((1, 1), (2, 1), (3, 1), (4, 1), (5, 1)))
    countDigit(List(8, 8, 8, 8, 8, 8, 8, 8)) should be(List((8, 8)))

  }

  "lookSay" should "return a BigInt of digits and their consecutive occurences in order" in {
    lookSay(BigInt(1)) should be(BigInt(11))
    lookSay(BigInt(21)) should be(BigInt(1211))
    lookSay(BigInt(0)) should be(BigInt(10))
    lookSay(BigInt(1211)) should be(BigInt(111221))
    lookSay(BigInt(6789)) should be(BigInt(16171819))
  }

  "A LookSayStream" should "return the absolute value of the value passed in as the first element" in {
    var ls = StreamFactory.getLookSayStream(1)
    ls(0) should be(1)

    ls = StreamFactory.getLookSayStream(-5)
    ls(0) should be(5)

    forAll { (seed: BigInt) => {
       ls = StreamFactory.getLookSayStream(seed)
       ls(0) should be (seed.abs)
      }
    }

  }

  it should "return the 2nd value onwards based on the formula" in {
    var ls = StreamFactory.getLookSayStream(1)
    ls take 5 should be(Stream(1, 11, 21, 1211, 111221))

    ls = StreamFactory.getLookSayStream(5);
    ls take 5 should be(Stream(5, 15, 1115, 3115, 132115))

    ls = StreamFactory.getLookSayStream(-5);
    ls take 5 should be(Stream(5, 15, 1115, 3115, 132115))

    ls = StreamFactory.getLookSayStream(0);
    ls take 5 should be(Stream(0, 10, 1110, 3110, 132110))

    ls = StreamFactory.getLookSayStream(42);
    ls take 5 should be(Stream[BigInt](42, 1412, 11141112, 31143112, BigInt("132114132112")))

  }
}