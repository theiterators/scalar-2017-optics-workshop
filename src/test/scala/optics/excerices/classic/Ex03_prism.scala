package optics
package excerices.classic

import optics.classic.Prism
import optics.laws.Equals._
import org.specs2.Specification
import org.specs2.cats.CatsEqMatcher

import scala.util.Try

class PrismSpec extends Specification with CatsEqMatcher {

  def is =
    s2"""
        A Prism is a Iso that can be partial in one direction (but this is only a part o a truth)
        A Prism is an optic which converts S into T if S is defined in T. Backward conversion is always valid.

        Valid prim should convert string into m/s if a string contains valid numeric value $test01.
        Otherwise it returns None $test02.
        Converting from m/s to string is always possible because it's valid string $test03.
        Prism allows to modify source value $test04 or return unchanged if conversion is impossible $test05.

        Again this is not a full truth about prism but for simplicity we will stick with it.
        If you are curious checkout the lenses documentation.
    """

  def test01 = prism.getOption("2.0") must beEqvTo(Option(MS(2.0)))

  def test02 = prism.getOption("grrr") must beNone

  def test03 = prism.reverseGet(MS(2)) must beEqvTo("2.0")

  def test04 = prism.modify( (ms: MS) => MS(ms.v + 1) )("2.0") must beEqvTo("3.0")

  def test05 = prism.modify((ms: MS) => MS(ms.v + 1))("grrr") must beEqvTo("grrr")


  /**
    * TODO: Implement me!
    *
    * Compose both isomorphism ${si} and ${imperial} to obtain a iso between m/s and mph
    *
    * Hint: Types should align like Iso[MS, KMH] -> Iso[KMH, MPS] -> Iso[MS, MPS]
    *
    */
  lazy val prism: Prism[String, MS] = ???

}
