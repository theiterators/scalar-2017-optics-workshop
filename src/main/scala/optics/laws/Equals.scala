package optics
package laws

object Equals {
  import cats.Eq
  import cats.Show

  implicit def optionEq[A: Eq]: Eq[Option[A]] = Eq.instance[Option[A]] {
    case (Some(l), Some(r)) => Eq.eqv(l, r)
    case (None, None)       => true
    case _                  => false
  }

  implicit def optionShow[A]: Show[Option[A]] = Show.fromToString

  implicit val stringEq: Eq[String]     = Eq.instance { case (l, r) => l.equals(r) }
  implicit val stringShow: Show[String] = Show.fromToString

  final case class IsEq[A: Eq](lhs: A, rhs: A) {
    def isEqual: Boolean = Eq[A].eqv(lhs, rhs)
  }

  object IsEq {

    implicit class Syntax[A: Eq](lhs: A) {
      def =?=(rhs: A): IsEq[A] = IsEq(lhs, rhs)
    }

  }

}
