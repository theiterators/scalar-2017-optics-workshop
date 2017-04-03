package optics
package classic

abstract class Optional[S, T] { self =>

  def getOption(s: S): Option[T]

  def set(t: T): S => S

  final def modify(f: T => T): S => S = ???

  final def composeOptional[C](other: Optional[T, C]): Optional[S, C] = ???
}

object Optional {
  def apply[S, T](_getOption: S => Option[T])(_set: T => S => S): Optional[S, T] = new Optional[S, T] {

    override def set(t: T): S => S = _set(t)

    override def getOption(s: S): Option[T] = _getOption(s)
  }
}