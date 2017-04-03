package optics
package laws

import Equals.IsEq.Syntax
import Equals._

import cats.Eq

object LensLaws {
  def getAfterSet[S: Eq, T: Eq](s: S, t: T)(lens: ALens[S, T]): IsEq[T] =
    lens.get(lens.set(t)(s)) =?= t

  def setAfterGet[S: Eq, T: Eq](s: S, t: T)(lens: ALens[S, T]): IsEq[S] =
    lens.set(lens.get(s))(s) =?= s

  def setAfterSet[S: Eq, T: Eq](s: S, t1: T, t2: T)(lens: ALens[S, T]): IsEq[S] =
    lens.set(t1)(lens.set(t2)(s)) =?= lens.set(t1)(s)
}
