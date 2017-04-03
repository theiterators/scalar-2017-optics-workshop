package optics.excerices.monocle

import monocle.Prism
import monocle.macros.GenPrism
import monocle.macros.Lenses
import org.specs2.Specification

class PrismSpec extends Specification {
  def is =
    s2"""


    """

  final case class X(x: Int)

  object X {
    import util.Try
    val _x: Prism[String, X] = Prism[String, X](s => Try(s.toInt).toOption.map(X.apply))(_.x.toString)
  }

}
