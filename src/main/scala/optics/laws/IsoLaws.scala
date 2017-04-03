package optics
package laws

import Equals.IsEq.Syntax
import Equals._

import cats.Eq

object IsoLaws {

  def reverseGetAfterGet[S: Eq, T: Eq](s: S)(iso: AIso[S, T]): IsEq[S] =
    iso.reverseGet(iso.get(s)) =?= s

  def getAfterReverseGet[S: Eq, T: Eq](t: T)(iso: AIso[S, T]): IsEq[T] =
    iso.get(iso.reverseGet(t)) =?= t

}
