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

      $test01
      $test02
      $test03

      $test04
      $test05

    """

  def test01 = stringPrism.getOption("2") must beSome(2)
  def test02 = positiveNumberPrism.getOption(2) must beEqvTo(Some(MS(2)))
  def test03 = positiveNumberPrism.getOption(-2) must beNone

  def test04 = msPrism.getOption("2") must beEqvTo(Some(MS(2)))
  def test05 = msPrism.getOption("x") must beNone


  lazy val stringPrism: Prism[String, Double] = ???

  lazy val positiveNumberPrism: Prism[Double, MS] = ???

  lazy val msPrism: Prism[String, MS] = ???
}
