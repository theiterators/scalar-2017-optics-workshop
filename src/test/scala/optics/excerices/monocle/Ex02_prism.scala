package optics.excerices.monocle

import monocle.Prism
import monocle.macros.GenPrism
import monocle.macros.Lenses
import org.specs2.Specification

class PrismSpec extends Specification {
  def is =
    s2"""

      For Sum types, like sealed trait and its 'siblings' or enums Prism can be generated automatically using macro.
      For other cases we need to do this manually.

      Some of available operations are:
        - select part of sum type applying jsstring: $test01
        - select part of sum type applying jsnumber: $test02

      It's possible to create prism using partial function rather than Option
        - select int from json applying jsnumber $test03
        - select int from json applying jsstring  $test04

      Having both of prism its possible to compose them
        - compose prism to go from Json to double applying jsnumber $test05
        - compose prism to go from Json to double applying jsstring $test06
    """


  /**
    * TODO: Implement me!
    * Fill the blanks to make this test pass
    */
  def test01 = {
    val probablyJsonString: Json = JsString("Hey, Select me!")
    Json._jsString.getOption(probablyJsonString) //must beSome or beNone
    ko("implement me")
  }


  /**
    * TODO: Implement me!
    * Fill the blanks to make this test pass
    */
  def test02 = {
    val defiantlyNotJsonString: Json = JsNumber(1)
    Json._jsString.getOption(defiantlyNotJsonString)  //must beSome or beNone
    ko("implement me")
  }

  /**
    * TODO: Implement me!
    * Fill the blanks to make this test pass
    */
  def test03 = {
    val maybeJsonNumber: Json = JsNumber(1)

    Json._jsNumber.getOption(maybeJsonNumber) //must beSome or beNone
    ko("implement me")
  }


  /**
    * TODO: Implement me!
    * Fill the blanks to make this test pass
    */
  def test04 = {
    val defiantlyNotJsonNumber: Json = JsString("grrrr")

    Json._jsNumber.getOption(defiantlyNotJsonNumber) //must beSome or beNone
    ko("implement me")
  }


  /**
    * TODO: Implement me!
    * Fill the blanks to make this test pass
    *
    * Hint: maybe some prism are missing
    */
  def test05 = {

    val composed: Prism[Json, Double] = ???

    composed.getOption(JsNumber(2)) must beSome(2)
  }

  /**
    * TODO: Implement me!
    * Fill the blanks to make this test pass
    *
    * Hint: maybe some prism are missing
    */
  def test06 = {

    val composed: Prism[Json, Double] = ???

    composed.getOption(JsString("grrr")) must beNone
  }

  sealed trait Json extends Product with Serializable
  final case class JsString(s: String) extends Json
  final case class JsNumber(n: Int) extends Json


  object Json {
    val _jsString: Prism[Json, JsString] = GenPrism[Json, JsString]

    //It's possible to create prism using partial function rather than Option
    val _jsNumber: Prism[Json, Int] = Prism.partial[Json, Int]{case JsNumber(num) => num}(JsNumber.apply)
  }


}


