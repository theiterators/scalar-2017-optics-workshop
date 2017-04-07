package optics.excerices.extras

import optics.classic.Iso
import org.specs2.Specification

class IsoLawlessSpec extends Specification {

  def is =
    s2"""
         Write an Iso which validates laws and a proof that the iso's instance is lawless
          source -> target -> source $test01
          target -> source -> target $test02
    """

  /**
    * Going from source to target and than backward should return unchanged value
    */
  def test01 = ko("implement me")

  /**
    * Going from target to source and than backward should return unchanged value
    */
  def test02 = ko("implement me")


  lazy val iso: Iso[String, List[Char]] = ???
}
