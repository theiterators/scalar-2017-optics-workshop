package optics
package excerices.classic

import optics.classic.Iso
import org.specs2.Specification
import org.specs2.cats.CatsEqMatcher

class IsoSpec extends Specification with CatsEqMatcher {
  def is =
    s2"""
        An Iso is an optic which converts elements of type S into elements of type T without loss.

        Valid iso should convert m/s to k/m and backward:
          should convert m/s to km/h        $test01
          should convert km/h to m/s        $test02
          should reverse source with target $test03
          should modify m/s                 $test04
      """

  def test01 = iso.get(MS(27.78)) must beEqvTo(KMH(100.0))

  def test02 = iso.reverseGet(KMH(100.0)) must beEqvTo(MS(27.78))

  def test03 = iso.reverse.get(KMH(100.0)) must beEqvTo(MS(27.78))

  def test04 = iso.modify(v => KMH(v.v * 2.0))(MS(27.78)) must beEqvTo(MS(55.56))


  /**
    * TODO: Implement me!
    *
    * Conversion rule:
    *   ms -> km/h:   1 [m/s]   * 3600 / 1000 -> 1 * 3.6 -> 1 [k/m]
    *   km/h -> s:    1 [km/h]  * 1000 / 3600 -> 1 / 3.6 -> 1 [m/s]
    *
    * Hint: Use the apply method from Iso
    */
  lazy val iso: Iso[MS, KMH] = ???

}
