package optics.excerices.monocle

import monocle.Traversal
import org.specs2.Specification

class TraversalSpec extends Specification {
  def is =
    s2"""
      A Traversal is the generalisation of an Optional to several targets.

      Traversal can be build from traversable using scalaz traverse $test01
      More interesting is that Traversal can focus on more than on field at the same time $test02
  """


  def test01 = {
    import scalaz.std.list._

    val traversal = Traversal.fromTraverse[List, Int]
    traversal.modify(_ + 1)(List(1, 2, 3)) must beEqualTo(List(2, 3, 4))
  }

  def test02 = {

    case class Point(id: String, x: Int, y: Int)

    val points = Traversal.apply2[Point, Int](_.x, _.y)((x, y, p) => p.copy(x = x, y = y))


    points.modify(_ + 3)(Point("bottom-left", 3 ,5)) must beEqualTo(Point("bottom-left", 6, 8))


  }
}
