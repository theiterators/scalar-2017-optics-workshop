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
      Contrary to Prism, Lens can focus on Product type like case classes.

      Some of available operations are:
        Get part of product
          - ball's velocity $test01.
          - ball's X position $tes02.
          - ball's Y position using applyLens $test03.
        Modify product by applying function to part of it
          - move ball in x direction by 1 unit $test04.
          - move ball in y direction to 0 $test05.

    """


  def test01 = Ball._ms.get(ball) must beEqvTo(MS(1.0))

  /**
    * Compose two lenses to zoom into nested field
    *
    */
  def tes02 = (Ball._pos composeLens Pos._x).get(ball) must beEqualTo(X(0))


  /**
    * Another way to compose lenses (handles better types resolution)
    *
    */
  def test03 = (ball applyLens Ball._pos composeLens Pos._y).get must beEqualTo(Y(0))


  /**
    * TODO: Implement me!
    *
    * Compose lenses which allow to move ball in X direction
    *
    */
  def test04 = {
    val ball: (Pos, MS) = (Pos(X(0), Y(0)), MS(1.0))

    val composed: Lens[Ball, Int] = ???

    val (pos, _) = composed.modify(_ + 1)(ball)

    pos.x must beEqualTo(X(1))

  }



  /**
    * TODO: Implement me!
    *
    * Compose lenses which allow to move ball in Y
    *
    */
  def test05 = {

    val ball: (Pos, MS) = (Pos(X(0), Y(3)), MS(1.0))

    val composed: Lens[Ball, Int] = ???

    val (pos, _) = composed.set(0)(ball)

    pos.y must beEqualTo(Y(1))

  }


  val ball: (Pos, MS) = (Pos(X(0), Y(0)), MS(1.0))


  /**
    * It's possible to create lens manually
    *
    */
  final case class X(x: Int)

  object X {
    val _x: Lens[X, Int] = Lens[X, Int](_.x)(i => x => x.copy(x = i))
  }


  /**
    * Or semi-automatic using macro
    *
    */
  final case class Y(y: Int)

  object Y {
    val _y: Lens[Y, Int] = GenLens[Y](_.y)
  }


  /**
    * Or completely automatic.
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
