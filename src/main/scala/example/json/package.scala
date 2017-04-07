package example
package object json {

  import scala.language.higherKinds
  import monocle.Traversal
  import monocle.function.Plated

  implicit val jsonPlated: Plated[Json] = new Plated[Json] {

    import scalaz.Applicative
    import scalaz.std.list._
    import scalaz.std.map._
    import scalaz.syntax.traverse._

    val plate: Traversal[Json, Json] = new Traversal[Json, Json] {
      def modifyF[F[_] : Applicative](f: Json => F[Json])(a: Json): F[Json] =
        a match {
          case j@(JsString(_) | JsNumber(_)) => Applicative[F].point(j)
          case JsArray(l) => l.traverse(f).map(JsArray)
          case JsObject(m) => m.traverse(f).map(JsObject)
        }
    }
  }


}
