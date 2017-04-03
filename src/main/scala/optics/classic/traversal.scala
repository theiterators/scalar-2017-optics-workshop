package optics.classic

abstract class Traversal[S, T] { self =>

  def set(t: T): S => S

  def getAll(s: S): List[T]

  final def modify(f: T => T): S => S = ???

  final def composeTraversal[C](other: Traversal[T, C]): Traversal[S, C] = ???


}
