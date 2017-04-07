package optics
package excerices.monocle

import monocle.Iso
import org.specs2.Specification
import org.typelevel.discipline.specs2.Discipline

import scalaz._
import Scalaz._

class LawsSpec extends Specification with Discipline {

  def is =
    s2"""
      Monocle comes with build in testing support.
      E.g. it allows to test custom iso implementation $test01
    """


  def test01 = checkAll("iso", monocle.law.discipline.IsoTests(iso))


  lazy val iso: Iso[String, List[Char]] = Iso[String, List[Char]](_.toList)(_.mkString(""))

}
