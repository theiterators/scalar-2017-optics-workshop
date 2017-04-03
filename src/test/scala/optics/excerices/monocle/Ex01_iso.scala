package optics
package excerices.monocle

import monocle.Iso
import org.specs2.Specification
import org.specs2.cats.CatsEqMatcher

class IsoSpec extends Specification with CatsEqMatcher {

  def is =
    s2"""

      $forward
      $backward
      $modify
      $reverse

    """

  def forward = iso.get(MS(27.78)) must beEqvTo(KMH(100.0))

  def backward = iso.reverseGet(KMH(100.0)) must beEqvTo(MS(27.78))

  def modify = iso.modify(v => KMH(v.v + 1.0))(MS(27.78)) must beEqvTo(MS(28.06))

  /**
    *
    * val iso: Iso[MS, KMH] = ???
    * val rIso: Iso[KMH, MS] = iso.reverse
    *
    */
  def reverse = iso.reverse.modify((ms: MS) => MS(ms.v * 2))(KMH(100.0)) must beEqvTo(KMH(200.0))

  lazy val iso: Iso[MS, KMH] = Iso[MS, KMH](ms => KMH(ms.v * 3.6))(kmh => MS(kmh.v / 3.6))

}
