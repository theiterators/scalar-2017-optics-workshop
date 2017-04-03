package optics
package excerices.classic

import optics.classic.Iso
import org.specs2.Specification
import org.specs2.cats.CatsEqMatcher

class IsoCompositionSpec extends Specification with CatsEqMatcher {
  def is =
    s2"""
      Given a conversion between metric units ( m/s <-> km/h )
        m/s to km/h (and backward) $test01
      and one from metric to imperial units ( kmh/h <-> m/s )
        km/h to mph (and backward) $test02
      We can compose both conversion and create new one between
        m/s to mph $test03
      and
        mph to m/s $test04
    """

  def test01 = si.get(MS(27.78)) must beEqvTo(KMH(100.0))

  def test02 = imperial.get(KMH(100)) must beEqvTo(MPH(62.14))

  def test03 = si2imperial.get(MS(27.78)) must beEqvTo(MPH(62.14))

  def test04 = si2imperial.reverseGet(MPH(62.14)) must beEqvTo(MS(27.78))

  /**
    * TODO: Implement me! Or copy from previous ex
    *
    * Conversion rule:
    *   ms -> km/h:   1 [m/s]   * 3600 / 1000 -> 1 * 3.6 -> 1 [k/m]
    *   km/h -> s:    1 [km/h]  * 1000 / 3600 -> 1 / 3.6 -> 1 [m/s]
    *
    * Hint: Use the apply method from Iso
    */
  lazy val si: Iso[MS, KMH] = ???

  /**
    * TODO: Implement me!
    *
    * Conversion rule:
    *   km/h  -> mph:     1 [km/m]  * 0.62137 -> 1 [mph]
    *   mph   -> km/h:    1 [mph]  *  1.60934 -> 1 [km/h]
    *
    * Hint: Use the apply method from Iso
    */
  lazy val imperial: Iso[KMH, MPH] = ???

  /**
    * TODO: Implement me!
    *
    * Compose both isomorphism ${si} and ${imperial} to obtain a iso between m/s and mph
    *
    * Hint: Types should align like Iso[MS, KMH] -> Iso[KMH, MPS] -> Iso[MS, MPS]
    *
    */
  lazy val si2imperial: Iso[MS, MPH] = si composeIso imperial //TODO: Implement

}
