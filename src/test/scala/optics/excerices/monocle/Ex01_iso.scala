package optics
package excerices.monocle

import monocle.Iso
import monocle.macros.GenIso
import org.specs2.Specification
import org.specs2.cats.CatsEqMatcher

class IsoSpec extends Specification with CatsEqMatcher {

  def is =
    s2"""
        Entering the Monocle world.

        For some simple cases Iso can be generated automatically using build in macro.

        Some of available operation are:
          - get $test01
          - reverseGet $test02
          - modify $test03
          - reverse $test04

        Of course it's possible to create Iso manually and compose them like it was done ealier.

    """

  def test01 = iso.get(MS(27.78)) must beEqualTo(27.78)

  def test02 = iso.reverseGet(27.78) must beEqvTo(MS(27.78))

  def test03 = iso.modify(_ + 10)(MS(27.78)) must beEqvTo(MS(28.06))

  def test04 = iso.reverse.modify((ms: MS) => MS(ms.v * 2))(100.0) must beEqualTo(200.0)

  lazy val iso: Iso[MS, Double] = GenIso[MS, Double]

}
