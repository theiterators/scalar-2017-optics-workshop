package optics
package excerices.monocle

import monocle._
import monocle.function.all._
import monocle.syntax.all._
import monocle.macros.Lenses
import org.specs2.Specification

class EachSpec extends Specification {
  def is =
    s2"""
      Each is a typeclass that defines a Traversal from a container S to all of its elements of type A.


      $modifyEach

    """

  def modifyEach = {
    val bs = List(
      Ball(Pos(X(0), Y(0)), MS(1.0)),
      Ball(Pos(X(0), Y(1)), MS(2.0)),
      Ball(Pos(X(1), Y(0)), MS(3.0)),
      Ball(Pos(X(1), Y(1)), MS(4.0))
    )

    ballLensVelocity(bs)(v => if (v > 2) 1 else v) must beEqualTo(
      List(Ball(Pos(X(0), Y(0)), MS(1.0)),
      Ball(Pos(X(0), Y(1)), MS(2.0)),
      Ball(Pos(X(1), Y(0)), MS(1.0)),
      Ball(Pos(X(1), Y(1)), MS(1.0))
    ))


  }

  val ballLensVelocity: List[Ball] => (Double => Double) => List[Ball] = bs => f => (
    bs
      applyTraversal each
      composeLens Ball._v
      composeLens msLens).modify(f)



  @Lenses("_") final case class Ball(pos: Pos, v: MS)
  @Lenses("_") final case class X(x: Int)
  @Lenses("_") final case class Y(y: Int)
  @Lenses("_") final case class Pos(x: X, y: Y)

  val msLens = Lens[MS, Double](_.v)(v => ms => ms.copy(v = v))



}
