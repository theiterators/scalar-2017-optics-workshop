package optics.excerices.extras

import optics.classic.Lens
import org.specs2.Specification

class LensLawlessSpec extends Specification {

  def is =
    s2"""
    Lens should fulfill three laws:
     - setting a field and than getting it back should return originally set field value - SetGet
     - getting a field and then setting it back should return  the same field value - GetSet
     - setting a field twice is the same as setting it once with last value. Last one wins. - PutPut

    Write a lens which validates above laws and a proof that the laws are validated.
        setget $setget
        getset $getset
        putput $putput
    """


  def setget = ko("implement me")

  def getset = ko("implement me")

  def putput = ko("implement me")

  case class Account(email: String, name: String, counter: Int, id: Option[Int] = None)

  object Account {
    val _email: Lens[Account, String] = ???
  }

  lazy val newEmail = "new_user@example.com"
  lazy val account  = Account("user@example.com", "user", 0, Some(1))

}
