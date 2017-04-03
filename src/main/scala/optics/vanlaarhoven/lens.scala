package optics
package vanlaarhoven


import cats.Functor
import scala.language.higherKinds

trait Lens[S, T] extends ALens[S, T] { self =>

  def apply[F[_]](f: T => F[T])(implicit F: Functor[F]): (S => F[S])

  final def get(s: S): T  = ???

  final def set(t: T): S => S = ???

  final def modify(f: T => T): S => S = ???

  final def composeLens[C](other: Lens[T, C]): Lens[S, C] = ???


}

object Lens {
  def apply[S, T](_get: S => T, _set: T => S => S): ALens[S, T] = new Lens[S, T] {
    override def apply[F[_]](f: (T) => F[T])(implicit F: Functor[F]): (S) => F[S] = s => {
      F.map(f(_get(s)))(_set(_)(s))
    }
  }
}