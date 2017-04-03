package optics

trait AIso[S, T] {

  def get(s: S): T

  def reverseGet(t: T): S
}

trait APrism[S, T] {

  def getOption(s: S): Option[T]

  def reverseGet(t: T): S
}

trait ALens[S, T] {

  def get(s: S): T

  def set(t: T): S => S
}
