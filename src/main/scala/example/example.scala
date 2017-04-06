package example

import monocle.macros.Lenses

import monocle.syntax.all._

object Example extends App {


  @Lenses("_") final case class Id(id: Int) extends AnyVal

  @Lenses("_") final case class Email(email: String) extends AnyVal

  @Lenses("_") final case class Name(name: String) extends AnyVal

  @Lenses("_") final case class Street(street: String) extends AnyVal

  @Lenses("_") final case class Address(street: Street, building: Int)

  @Lenses("_") final case class User(name: Name, address: Address, email: Email)

  @Lenses("_") final case class Account(id: Id, user: User)


  val account = Account(
    Id(1),
    User(
      Name("Foo Bar"),
      Address(Street("Baz"), 1),
      Email("foobar@baz")
    )
  )


  val updated = account.copy(
    user = account.user.copy(
      email = account.user.email.copy(
        email = "barbaz@foo")))






  (account
    applyLens Account._user
    composeLens User._email
    composeLens Email._email
    ).set("barbar@foo")








  (account
    &|-> Account._user
    ^|-> User._email
    ^|-> Email._email
    ).set("barbar@foo")


}
