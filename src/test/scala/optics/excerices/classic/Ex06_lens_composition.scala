package optics
package excerices.classic

import classic.Lens
import org.specs2.Specification
import org.specs2.cats.CatsEqMatcher

class LensCompositionSpec extends Specification with CatsEqMatcher {
  def is =
    s2"""

      $test01
      $test02
      $test03
      $test04
      $test05

    """

  def test01 = msLens.modify(v => v + 1)(ms) must beEqvTo(MS(28.78))

  def test02 = ballLens.modify((ms: MS) => MS(ms.v + 1))(ball) must beEqualTo(Ball(MS(28.78)))

  def test03 = composedLens.get(ball) must beEqualTo(27.78)

  def test04 = composedLens.set(10.0)(ball) must beEqualTo(Ball(MS(10.0)))

  def test05 = composedLens.modify(_ + 10.0)(ball) must beEqualTo(Ball(MS(37.78)))

  lazy val ms: MS = MS(27.78)
  lazy val ball: Ball = Ball(ms)

  final case class Ball(v: MS)

  lazy val ballLens: Lens[Ball, MS] = ???
  lazy val msLens: Lens[MS, Double] = ???

  lazy val composedLens: Lens[Ball, Double] = ???
}
