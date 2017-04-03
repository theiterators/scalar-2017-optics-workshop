package optics
package classic

import laws.PrismLaws
import laws.Equals._
import org.scalacheck.Arbitrary
import org.scalacheck.Prop._
import org.specs2.ScalaCheck
import org.specs2.Specification
import org.typelevel.discipline.Laws
import org.typelevel.discipline.specs2.Discipline

import util.Try

class PrismLawsSpec extends Specification with ScalaCheck with Discipline {
  def is =
    s2"""
      $laws
    """

  def laws = checkAll("prism", PrismLawsRules(prism))

  lazy val prism: Prism[String, MS] = Prism[String, MS](s => Try(s.toDouble).toOption.map(MS.apply))(_.v.toString)

  implicit val arbMs: Arbitrary[MS] = Arbitrary(for {
    x <- Arbitrary.arbInt.arbitrary
  } yield MS(x))

}

object PrismLawsRules extends Laws {

  import cats.Eq

  def apply[S: Arbitrary: Eq, T: Arbitrary: Eq](prism: Prism[S, T]): RuleSet = {
    new SimpleRuleSet(
      "Prism",
      "get after reverse get" -> forAll((t: T) => PrismLaws.getAfterReverseGet(t)(prism).isEqual),
      "reverse get after get" -> forAll((s: S) => PrismLaws.reverseGetAfterGet(s)(prism).isEqual)
    )
  }

}
