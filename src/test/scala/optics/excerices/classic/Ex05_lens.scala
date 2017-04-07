package optics
package excerices.classic

import optics.classic.Lens
import org.specs2.Specification
import org.specs2.cats.CatsEqMatcher

class LensSpec extends Specification with CatsEqMatcher {

  def is =
    s2"""
      Lens is a optic which allows to focus (zoom) inside a case class to access its fields*.
      Once Lens is defined, it allows to use the supplied functions:
        - get to access field $test01
        - and set to override current value $test02.
      It's possible to modify the target of Lens with a function, this is equivalent to call get and then set $test03

      *In fact Lens allows to focus inside any Product type.
    """

  def test01 = lens.get(ms) must beEqualTo(27.78)

  def test02 = lens.set(100)(ms) must beEqvTo(MS(100))

  def test03 = lens.modify(_ + 30)(ms) must beEqvTo (lens.set(lens.get(ms) + 30)(ms))


  /**
    * Given a velocity in m/s
    */
  lazy val ms: MS = MS(27.78)

  /**
    * TODO: Implement me
    *
    * Hint: Use the apply method from Lens
    *
    */
  lazy val lens: Lens[MS, Double] = ???

}
