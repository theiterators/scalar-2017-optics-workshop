package example.json

import monocle.function.Plated

object Sanitization extends App {

  lazy val sanitize: (Json) => Json = Plated.rewrite[Json] {
    case JsString(s) if s.matches(""".*@.*\..*""") => Some(JsString("***********"))
    case JsNumber(cc) if cc.toString.matches("^5[1-5][0-9]{7}") => Some(JsString(s"**** **** **** ${cc.toString.drop(5)}"))
    case _ => None
  }


  lazy val json: Json = JsObject(Map(
    "first_name" -> JsString("John"),
    "last_name" -> JsString("Doe"),
    "age" -> JsNumber(28),
    "email" -> JsString("JohnDoe@gmail.com"),
    "cc" -> JsNumber(510129054),
    "siblings" -> JsArray(List(
      JsObject(Map(
        "first_name" -> JsString("Elia"),
        "age" -> JsNumber(23),
        "email" -> JsString("EliaDoe@gmail.com"),
        "cc" -> JsNumber(510113445)
      )),
      JsObject(Map(
        "first_name" -> JsString("Robert"),
        "age" -> JsNumber(25),
        "email" -> JsString("RobertDoe@gmail.com"),
        "mobile" -> JsNumber(692343546)
      ))
    ))
  ))

  pprint.pprintln{
    sanitize(json)
  }
}
