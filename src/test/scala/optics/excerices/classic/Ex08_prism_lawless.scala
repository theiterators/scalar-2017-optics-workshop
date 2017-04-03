package optics
package excerices.classic

import optics.classic.Prism
import org.specs2.Specification

class PrismLawlessSpec extends Specification {
  def is = s2"""
      Write a Prism which validates laws and a proof that the Prism's instance is lawless
        $test01
        $test02

    """

  def test01 = ko("implement me")

  def test02 = ko("implement me")

  lazy val prism: Prism[String, Int] = ???
}
