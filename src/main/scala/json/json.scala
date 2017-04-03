package json

import monocle._

sealed trait Json extends Product with Serializable

final case class JsString(s: String) extends Json

final case class JsNumber(n: Int) extends Json

final case class JsArray(ar: List[Json]) extends Json

final case class JsObject(m: Map[String, Json]) extends Json

object Json {

  val _string: Prism[Json, String] = Prism[Json, String] {
    case JsString(s) => Some(s)
    case _ => None
  }(JsString.apply)

  val _number: Prism[Json, Int] = Prism[Json, Int] {
    case JsNumber(n) => Some(n)
    case _ => None
  }(JsNumber.apply)

  val _array: Prism[Json, List[Json]] = Prism[Json, List[Json]] {
    case JsArray(ar) => Some(ar)
    case _ => None
  }(JsArray.apply)

  val _object: Prism[Json, Map[String, Json]] = Prism[Json, Map[String, Json]] {
    case JsObject(m) => Some(m)
    case _ => None
  }(JsObject.apply)
}
