package optics

import cats.{Eq, Show}

final case class MPH(v: Double) extends AnyVal

object MPH {
  implicit val show: Show[MPH] = Show.show(t => f"${t.v}%1.2f mph")
  implicit val eq: Eq[MPH]     = Eq.instance((l, r) => Math.abs(l.v - r.v) < 0.1)
}

final case class KMH(v: Double) extends AnyVal

object KMH {
  implicit val show: Show[KMH] = Show.show(t => f"${t.v}%1.2f km/h")
  implicit val eq: Eq[KMH]     = Eq.instance((l, r) => Math.abs(l.v - r.v) < 0.1)
}

final case class MS(v: Double) extends AnyVal

object MS {
  implicit val show: Show[MS] = Show.show(t => f"${t.v}%1.2f m/s")
  implicit val eq: Eq[MS]     = Eq.instance((l, r) => Math.abs(l.v - r.v) < 0.1)
}
