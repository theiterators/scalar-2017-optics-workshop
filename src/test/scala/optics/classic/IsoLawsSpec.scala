package optics
package classic

import laws.IsoLaws
import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll
import org.specs2.ScalaCheck
import org.specs2.Specification
import org.typelevel.discipline.Laws
import org.typelevel.discipline.specs2.Discipline

class IsoLawsSpec extends Specification with ScalaCheck with Discipline {
  def is =
    s2"""

    $laws

  """

  def laws = checkAll("iso", IsoLawsRules(iso))

  val iso: Iso[MS, KMH] = Iso[MS, KMH](ms => KMH(ms.v * 3.6))(kmh => MS(kmh.v / 3.6))

  implicit val arbMs: Arbitrary[MS] = Arbitrary(for {
    x <- Arbitrary.arbInt.arbitrary
  } yield MS(x))

  implicit val arbKMH: Arbitrary[KMH] = Arbitrary(for {
    x <- Arbitrary.arbInt.arbitrary
  } yield KMH(x))

}

object IsoLawsRules extends Laws {

  import cats.Eq

  def apply[S: Arbitrary: Eq, T: Arbitrary: Eq](iso: AIso[S, T]): RuleSet = {
    new SimpleRuleSet(
      "Lens",
      "moving forward. get and than reverse get should return source value" -> forAll(
        (s: S) => IsoLaws.reverseGetAfterGet(s)(iso).isEqual),
      "moving backward. reverse get and than get should return target value" -> forAll(
        (t: T) => IsoLaws.getAfterReverseGet(t)(iso).isEqual)
    )
  }

}
