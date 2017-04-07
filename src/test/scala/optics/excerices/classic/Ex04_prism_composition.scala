package optics
package excerices.classic

import optics.laws.Equals._

import optics.classic.Prism
import org.specs2.Specification
import org.specs2.cats.CatsEqMatcher

import scala.util.Try

class PrismCompositionSpec extends Specification with CatsEqMatcher {

  def is =
    s2"""
      Given a conversion between two prisms it's possible to compose them to get another prism

      First prism should convert string to double if conversion is allowed $test01.
      Second prism should convert double to m/s if input is non negative number $test02
      or return None otherwise $test03.

      Composition of both prism should allow to convert string to m/s if conversion is possible $test04
      or return None otherwise $test05.

    """

  def test01 = stringPrism.getOption("2") must beSome(2)
  def test02 = positiveNumberPrism.getOption(2) must beEqvTo(Some(MS(2)))
  def test03 = positiveNumberPrism.getOption(-2) must beNone

  def test04 = msPrism.getOption("2") must beEqvTo(Some(MS(2)))
  def test05 = msPrism.getOption("x") must beNone

  /**
    * Copied from previous example.
    */
  lazy val stringPrism: Prism[String, Double] = Prism[String, Double](s => Try(s.toDouble).toOption)(_.toString)

  /**
    * TODO: Define a prism which will convert double to m/s if input is non-negative number
    */
  lazy val positiveNumberPrism: Prism[Double, MS] = ???

  /**
    * Compose both prisms Prism[String, Double] -> Prism[Double, MS] to obtain conversion between String and MS
    */
  lazy val msPrism: Prism[String, MS] = ???
}
