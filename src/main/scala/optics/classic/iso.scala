package optics
package classic

abstract class Iso[S, T] extends AIso[S, T] { self =>

  def get(s: S): T

  def reverseGet(t: T): S

  def reverse: Iso[T, S] = ???

  final def modify(f: T => T): S => S = ???

  final def composeIso[C](other: Iso[T, C]): Iso[S, C] = ???

}

object Iso {
  def apply[S, T](_get: S => T)(_reverseGet: T => S): Iso[S, T] =
    new Iso[S, T] { st =>
      def get(s: S): T        = _get(s)
      def reverseGet(t: T): S = _reverseGet(t)
    }
}