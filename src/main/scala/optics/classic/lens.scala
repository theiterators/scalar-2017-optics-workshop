package optics
package classic

abstract class Lens[S, T] extends ALens[S, T] {self =>

  def get(s: S): T

  def set(t: T): S => S

  def modify(f: T => T): S => S = ???

  def composeLens[C](other: Lens[T, C]): Lens[S, C] = ???

}

object Lens {
  def apply[S, T](_get: S => T)(_set: T => S => S): Lens[S, T] = new Lens[S, T] {

    def get(s: S): T = _get(s)

    def set(t: T): S => S = s => _set(t)(s)
  }
}
