package algebra

sealed trait Parent

case class Foo(id: String) extends Parent
case class Bar(id: String) extends Parent
case class Baz(id: String) extends Parent

sealed trait Parent2 extends Product with Serializable

case class Foo2(id: String) extends Parent2
case class Bar2(id: String) extends Parent2
case class Baz2(id: String) extends Parent2


object ParentTest extends App {

  //returns List[Product with Serializable with Parent]
  val list = List(Foo("1"), Bar("2"), Baz("3"))

  //returns List[Parent2]
  val list2 = List(Foo2("1"), Bar2("2"), Baz2("3"))
}