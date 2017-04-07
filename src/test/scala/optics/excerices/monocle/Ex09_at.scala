package optics
package excerices.monocle

import monocle._
import monocle.function.all._
import monocle.syntax.all._
import monocle.macros.GenLens
import monocle.macros.Lenses
import monocle.macros.Lenses
import monocle.macros.Lenses
import monocle.macros.Lenses
import monocle.syntax.ApplyTraversal
import org.specs2.Specification

class AtSpec extends Specification {

  def is =
    s2"""

      Given
        Map(
          "red" -> List(
            (Pos(X(1), Y(1)), MS(1.0))),
          "blue" -> List(
            (Pos(X(0), Y(0)), MS(1.0)),
            (Pos(X(0), Y(1)), MS(2.0))),
          "white" -> List(
            (Pos(X(3), Y(1)), MS(1.0)),
            (Pos(X(4), Y(1)), MS(2.0)),
            (Pos(X(5), Y(0)), MS(3.0)),
            (Pos(X(5), Y(1)), MS(4.0))
          )
        )

      Remove all blue balls $test01
      Add new color $test02
      Add new red ball $test03

      Modify at $modifyAt

    """

//  lazy val colorBalls: ColorBalls = Map(
//    "red" -> List(
//      Ball(Pos(X(1), Y(1)), MS(1.0))),
//    "blue" -> List(
//      Ball(Pos(X(0), Y(0)), MS(1.0)),
//      Ball(Pos(X(0), Y(1)), MS(2.0))
//    ),
//    "white" -> List(
//      Ball(Pos(X(3), Y(1)), MS(1.0)),
//      Ball(Pos(X(4), Y(1)), MS(2.0)),
//      Ball(Pos(X(5), Y(0)), MS(3.0)),
//      Ball(Pos(X(5), Y(1)), MS(4.0)))
//  )

  def test01 = ko("implement me")

  def test02 = ko("implement me")

  def test03 = ko("implement me")

  def modifyAt = {

//    def cb: ColorBalls => String => Lens[ColorBalls, KMH] = cbs => color =>
//      (cbs
//        applyLens at(color) //  Lens[ColorBalls, Option[List[Ball]]]
//        composeTraversal each //  Traversal[ColorBalls, List[Ball]]
//        composeTraversal each //  Traversal[ColorBalls, Ball]
//        composeLens Ball._v //  Lens[ColorBalls, MS]
//        composeIso msIso //  Iso[ColorBalls, KHM]
//        )
//
//    cb(colorBalls)("red").set(KMH(100))
    ok
  }


//  type ColorBalls = Map[String, Balls]
//
//  @Lenses("_") case class CBalls(cb: ColorBalls)
//
//  lazy val balls: Balls = List(
//    Ball(Pos(X(0), Y(0)), MS(1.0)),
//    Ball(Pos(X(0), Y(1)), MS(2.0)),
//    Ball(Pos(X(1), Y(0)), MS(3.0)),
//    Ball(Pos(X(1), Y(1)), MS(4.0))
//  )
//
//  type Balls = List[Ball]
//
//  @Lenses("_") final case class X(x: Int)
//
//  @Lenses("_") final case class Y(y: Int)
//
//  @Lenses("_") final case class Pos(x: X, y: Y)
//
//  @Lenses("_") final case class Ball(pos: Pos, v: MS)
//
//  object MSIso {
//    val _iso: Iso[MS, KMH] = Iso[MS, KMH](ms => KMH(ms.v * 3.6))(kmh => MS(kmh.v * 0.2778))
//  }
//
//
//  val msLens = Lens[MS, Double](_.v)(v => ms => ms.copy(v = v))
//  val msIso: Iso[MS, KMH] = Iso[MS, KMH](ms => KMH(ms.v * 3.6))(kmh => MS(kmh.v * 0.2778))
//

}
