package optics
package excerices.monocle

import monocle._
import monocle.function.all._
import monocle.syntax.all._
import monocle.macros.Lenses
import monocle.syntax.ApplyOptional
import org.specs2.Specification

class IndexSpec extends Specification {

  def is =
    s2"""

      Monocle comes with usefull typeclasses which help worrying with lenses
      Index typeclass is a Optional from source to target at an index. It can only access fields.

      Composing Optional with other Lenses allows to:
        - select a ball at given index and update it if exist $test01
        - leave list unchanged if given index does not exist $test02
        - change ball's velocity $test03

    """


  def test01 = {
    val balls = List(
      Ball(Pos(X(0), Y(0)), MS(1.0))
    )

    val moved: List[Ball] = ballLensPosition(balls)(0)(_ + 1)

    moved.headOption.map(_.pos.x) must beSome(X(1))
  }


  def test02 = {
    val balls = List(
      Ball(Pos(X(0), Y(0)), MS(1.0))
    )

    ballLensPosition(balls)(99)(_ => 1) must beEqualTo(balls)
  }

  def test03 = {
    val balls = List(
      Ball(Pos(X(0), Y(0)), MS(1.0))
    )

    ballLensVelocity(balls)(0)(_ + 3.0).headOption.map(_.v) must beSome(MS(4.0))

  }

  val ballLensPosition: List[Ball] => Int => (Int => Int) => List[Ball] = bs => idx => f => {
    (bs
      applyOptional index(idx)
      composeLens Ball._pos
      composeLens Pos._x
      composeLens X._x).modify(f)
  }

  val ballLensVelocity: List[Ball] => Int => (Double => Double) => List[Ball] =  bs => idx  => f => {
    (bs
      applyOptional index(idx)
      composeLens Ball._v
      composeLens msLens).modify(f)
  }


  val ball: Ball = Ball(Pos(X(0), Y(0)), MS(1.0))

  @Lenses("_") final case class X(x: Int)

  @Lenses("_") final case class Y(y: Int)

  @Lenses("_") final case class Pos(x: X, y: Y)

  @Lenses("_") final case class Ball(pos: Pos, v: MS)


  object MSIso {
    val _iso: Iso[MS, KMH] = Iso[MS, KMH](ms => KMH(ms.v * 3.6))(kmh => MS(kmh.v * 0.2778))
  }

  val msLens = Lens[MS, Double](_.v)(v => ms => ms.copy(v = v))
}
