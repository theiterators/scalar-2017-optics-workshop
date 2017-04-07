package optics
package excerices.classic

import classic.Lens
import org.specs2.Specification
import org.specs2.cats.CatsEqMatcher

class LensCompositionSpec extends Specification with CatsEqMatcher {
  def is =
    s2"""

         Given two lenses (if the types align) it's possible to compose them to get a lens for accessing (deeper) nested fields.

          First lens allows to zoom inside ms object to modify it $test01.
          Similarly the second lens $test02.

          By composing both it's possible to _shell_ nested value $test03, override it $test04 or modify $test05.

    """

  def test01 = msLens.modify(v => v + 1)(ms) must beEqvTo(MS(28.78))

  def test02 = ballLens.modify((ms: MS) => MS(ms.v + 1))(ball) must beEqualTo(Ball(MS(28.78)))

  def test03 = composedLens.get(ball) must beEqualTo(27.78)

  def test04 = composedLens.set(10.0)(ball) must beEqualTo(Ball(MS(10.0)))

  def test05 = composedLens.modify(_ + 10.0)(ball) must beEqualTo(Ball(MS(37.78)))

  /**
    * Given a type representing Ball with velocity and its instances.
    */
  final case class Ball(v: MS)

  lazy val ms: MS = MS(27.78)
  lazy val ball: Ball = Ball(ms)

  /**
    * Given a lens zooming inside a velocity type to access primitive value
    */
  lazy val msLens: Lens[MS, Double] = Lens[MS, Double](_.v)(v => ms => ms.copy(v = v))

  /**
    * TODO: Implement me!
    *
    * Define a lens which will 'zoom' inside a ball accessing its velocity.
    */
  lazy val ballLens: Lens[Ball, MS] = ???

  /**
    * TODO: Implement me!
    *
    * Compose both lenses to zoom deep inside a ball.
    */
  lazy val composedLens: Lens[Ball, Double] = ???
}
