package optics
package classic

import cats.Eq
import laws.LensLaws
import org.scalacheck.Arbitrary
import org.scalacheck.Prop._
import org.specs2.Specification
import org.specs2.ScalaCheck
import org.typelevel.discipline.Laws
import org.typelevel.discipline.specs2.Discipline

class LensLawsSpec extends Specification with ScalaCheck with Discipline {

  def is =
    s2"""

    $laws

    """

  def laws = checkAll("lens", LensLawsRules(lens))

  lazy val lens: Lens[MS, Double] = Lens[MS, Double](_.v)(v => ms => ms.copy(v = v))

  implicit val arbMs: Arbitrary[MS] = Arbitrary(for {
    x <- Arbitrary.arbInt.arbitrary
  } yield MS(x))

  implicit val arDouble: Arbitrary[Double] = Arbitrary(Arbitrary.arbDouble.arbitrary)

  implicit val eqDouble: Eq[Double] = Eq.instance { case (l, r) => l == r }

}

object LensLawsRules extends Laws {

  import cats.Eq

  def apply[S: Arbitrary: Eq, T: Arbitrary: Eq](lens: Lens[S, T]): RuleSet = {
    new SimpleRuleSet(
      "Lens",
      "get after set returns put value"        -> forAll((s: S, t: T) => LensLaws.getAfterSet(s, t)(lens).isEqual),
      "set after get returns unchanged source" -> forAll((s: S, t: T) => LensLaws.setAfterGet(s, t)(lens).isEqual),
      "set after set overwrites previous set" -> forAll(
        (s: S, t1: T, t2: T) => LensLaws.setAfterSet(s, t1, t2)(lens).isEqual)
    )
  }

}
