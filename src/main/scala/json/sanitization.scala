package json

import monocle.function.Plated

object Sanitization extends App {

  lazy val output = Plated.rewrite[Json] {
    case JsString(s) => Some(JsString("*"))
    case _ => None
  }(json)


  lazy val json: Json = JsObject(Map(
    "first_name" -> JsString("John"),
    "last_name" -> JsString("Doe"),
    "age" -> JsNumber(28),
    "email" -> JsString("JohnDoe@gmail.com"),
    "siblings" -> JsArray(List(
      JsObject(Map(
        "first_name" -> JsString("Elia"),
        "age" -> JsNumber(23),
        "email" -> JsString("EliaDoe@gmail.com")
      )),
      JsObject(Map(
        "first_name" -> JsString("Robert"),
        "age" -> JsNumber(25),
        "email" -> JsString("RobertDoe@gmail.com")
      ))
    ))
  ))


  pprint.pprintln(output)

}
