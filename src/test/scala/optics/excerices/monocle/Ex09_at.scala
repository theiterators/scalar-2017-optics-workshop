package optics
package excerices.monocle

import monocle._
import monocle.function.all._
import monocle.syntax.all._
import monocle.macros.GenLens
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

    """


  def test01 = ko("implement me")
  def test02 = ko("implement me")
  def test03 = ko("implement me")

  def modifyAt = {

    def remove(cbs: ColorBalls)(color: String) = cbs applyLens at(color) set None

    def set(cbs: ColorBalls)(color: String, bs: Balls) = cbs applyLens at(color) set Some(bs)

    def cb(cbs: ColorBalls): ApplyTraversal[ColorBalls, ColorBalls, KMH, KMH] =
      (colorBalls
        applyLens at("red") //  Lens[ColorBalls, Option[List[Ball]]]
        composeTraversal each //  Traversal[ColorBalls, List[Ball]]
        composeTraversal each //  Traversal[ColorBalls, Ball]
        composeLens Ball._ms //  Lens[ColorBalls, MS]
        composeIso msIso //  Iso[ColorBalls, KHM]
        )

    println(remove(colorBalls)("red"))
    println(set(colorBalls)("red", List()))
    println(cb(colorBalls).set(KMH(100)))
    ok
  }

  def addNewBall = {

    //    println {
    //      (colorBalls
    //        applyLens at("red")
    //        composeTraversal each
    //        composeTraversal each).set((Pos(X(100), Y(100)), MS(1000)))
    //    }

  }

  def removeAllReadColor = {

    //remote all red balls with ms > 2

    //    pprint.pprintln {
    //
    //      (colorBalls
    //        applyLens at("red")   //Lens[ColorBalls, Option[List[Ball]]]
    //        composeTraversal each //Lens[ColorBalls, List[Ball]]
    //      ).modify(_.filter(_._2.v <= 2))
    //    }

    //  val x: Map[String, Balls] = CBalls._cb.get("s")

    val xx: Option[List[(Pos, MS)]] = (colorBalls applyLens at("red")).get

    //

    ok

  }


  type ColorBalls = Map[String, Balls]

  @Lenses("_") case class CBalls(cb: ColorBalls)

  val colorBalls: ColorBalls = Map(
    "red" -> List(
      (Pos(X(1), Y(1)), MS(1.0))),
    "blue" -> List(
      (Pos(X(0), Y(0)), MS(1.0)),
      (Pos(X(0), Y(1)), MS(2.0))
    ),
    "white" -> List(
      (Pos(X(3), Y(1)), MS(1.0)),
      (Pos(X(4), Y(1)), MS(2.0)),
      (Pos(X(5), Y(0)), MS(3.0)),
      (Pos(X(5), Y(1)), MS(4.0)))
  )

  type Balls = List[Ball]

  lazy val balls: Balls = List(
    (Pos(X(0), Y(0)), MS(1.0)),
    (Pos(X(0), Y(1)), MS(2.0)),
    (Pos(X(1), Y(0)), MS(3.0)),
    (Pos(X(1), Y(1)), MS(4.0))
  )

  val ball: (Pos, MS) = (Pos(X(0), Y(0)), MS(1.0))

  final case class X(x: Int)

  object X {
    val _x: Lens[X, Int] = Lens[X, Int](_.x)(i => x => x.copy(x = i))
  }

  final case class Y(y: Int)

  object Y {
    val _y: Lens[Y, Int] = GenLens[Y](_.y)
  }

  final case class Pos(x: X, y: Y) //TODO Add @Lenses which requires macro paradise

  object Pos {
    val _x: Lens[Pos, X] = GenLens[Pos](_.x)
    val _y: Lens[Pos, Y] = GenLens[Pos](_.y)
  }

  object MSIso {
    val _iso: Iso[MS, KMH] = Iso[MS, KMH](ms => KMH(ms.v * 3.6))(kmh => MS(kmh.v * 0.2778))
  }

  type Ball = (Pos, MS)

  val msLens = Lens[MS, Double](_.v)(v => ms => ms.copy(v = v))
  val msIso: Iso[MS, KMH] = Iso[MS, KMH](ms => KMH(ms.v * 3.6))(kmh => MS(kmh.v * 0.2778))

  object Ball {
    val _pos: Lens[Ball, Pos] = GenLens[Ball](_._1)
    val _ms: Lens[Ball, MS] = GenLens[Ball](_._2)
  }

}
