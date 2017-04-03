package optics
package excerices.monocle

import monocle._
import monocle.function.all._
import monocle.syntax.all._
import monocle.macros.GenLens
import org.specs2.Specification

class IndexSpec extends Specification {

  def is =
    s2"""
    $updateAtIndex
    """

  def updateAtIndex = {

    def move(bs: List[Ball])(idx: Int, value: Int) =
      bs applyOptional index(idx) composeLens Ball._pos composeLens Pos._x composeLens X._x modify (_ + value)

    def accelerate(bs: List[Ball])(idx: Int) =
      bs applyOptional index(idx) composeLens Ball._ms composeLens msLens modify (v => if (v > 3) 2 else v)

    println(move(balls)(0, 10))
    println(move(balls)(100, -1))

    println(accelerate(balls)(0))
    println(accelerate(balls)(3))

    ok
  }

  val balls = List(
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

  object Ball {
    val _pos: Lens[Ball, Pos] = GenLens[Ball](_._1)
    val _ms: Lens[Ball, MS]   = GenLens[Ball](_._2)
  }

}
