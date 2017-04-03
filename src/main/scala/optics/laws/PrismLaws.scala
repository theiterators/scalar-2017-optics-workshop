package optics
package laws

import Equals.IsEq.Syntax
import Equals._

import cats.Eq

object PrismLaws {

  def getAfterReverseGet[S: Eq, T: Eq](t: T)(prism: APrism[S, T]): IsEq[Option[T]] =
    prism.getOption(prism.reverseGet(t)) =?= Some(t)

  def reverseGetAfterGet[S: Eq, T: Eq](s: S)(prism: APrism[S, T]): IsEq[Option[S]] =
    prism.getOption(s).map(prism.reverseGet) =?= (prism.getOption(s) match {
      case Some(_) => Some(s)
      case None    => None
    })

}
