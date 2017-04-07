package optics.excerices.monocle

import monocle.Optional
import optics.MS
import org.specs2.Specification


class OptionalSpec extends Specification {
  def is =
    s2"""
        Optional allows to zoom inside a Product type, similar to Lens but as in Prism this value may or may not exist.

       It allows to modify list if it contains value $test01 or return unchanged $test02.

        Stolen from monocle examples
      """

  def test01 = listHeadOptional.modify(_ + 4)(List(1, 2)) must beEqualTo(List(5, 2))


  def test02 = listHeadOptional.modify(_ + 4)(List.empty[Int]) must beEmpty


  val listHeadOptional: Optional[List[Int], Int]  = Optional[List[Int], Int] {
    case x::_ => Some(x)
    case _ => None
  } { x => {
    case  _::xs => x::xs
    case Nil => Nil
  }}



}
