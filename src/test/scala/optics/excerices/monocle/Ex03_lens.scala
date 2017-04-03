package optics
package excerices.monocle

import monocle._
import monocle.syntax.all._
import monocle.macros.GenLens
import monocle.macros.Lenses

import org.specs2.Specification
import org.specs2.cats.CatsEqMatcher

class LensSpec extends Specification with CatsEqMatcher {
  def is =
    s2"""

      Get ball's velocity $test01.
      Get ball's X position $tes02.
      Get ball's Y position using applyLens $test03.
      Move ball in x direction by 1 unit $test04.
      Move ball in y direction to 0 $test05.

    """


  def test01 = Ball._ms.get(ball) must beEqvTo(MS(1.0))

  def tes02 = (Ball._pos composeLens Pos._x).get(ball) must beEqualTo(X(0))

  def test03 = (ball applyLens Ball._pos composeLens Pos._y).get must beEqualTo(Y(0))


  def test04 = ko("implement me")


  def test05 = ko("implement me")


  val ball: (Pos, MS) = (Pos(X(0), Y(0)), MS(1.0))


  /**
    *
    *
    */
  final case class X(x: Int)

  object X {
    val _x: Lens[X, Int] = Lens[X, Int](_.x)(i => x => x.copy(x = i))
  }


  /**
    *
    *
    */
  final case class Y(y: Int)

  object Y {
    val _y: Lens[Y, Int] = GenLens[Y](_.y)
  }


  /**
    *
    */
  @Lenses("_") case class Pos(x: X, y: Y)


  val _siIso: Iso[MS, KMH] = Iso[MS, KMH](ms => KMH(ms.v * 3.6))(kmh => MS(kmh.v * 0.2778))


  type Ball = (Pos, MS)

  object Ball {
    val _pos: Lens[Ball, Pos] = GenLens[Ball](_._1)
    val _ms: Lens[Ball, MS] = GenLens[Ball](_._2)
  }

}
