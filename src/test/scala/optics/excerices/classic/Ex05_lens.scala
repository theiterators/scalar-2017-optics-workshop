package optics
package excerices.classic

import optics.classic.Lens
import org.specs2.Specification
import org.specs2.cats.CatsEqMatcher

class LensSpec extends Specification with CatsEqMatcher {

  def is =
    s2"""

      $test01
      $test02
      $test03

    """

  def test01 = lens.get(ms) must beEqualTo(27.78)

  def test02 = lens.set(100)(ms) must beEqvTo(MS(100))

  def test03 = lens.modify(_ + 30)(ms) must beEqvTo(MS(57.78))


  lazy val ms: MS = MS(27.78)

  lazy val lens: Lens[MS, Double] = ???

}
