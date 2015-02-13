package cake

trait FooAbleComponent {
  val fooAble: FooAble
  class FooAble {
    def foo = "here is your foo"
  }

  class Foo2Able extends FooAble {
    override def foo = "here is your foo2"
  }
}

trait BazAbleComponent {
  val bazAble: BazAble
  class BazAble {
    def baz = "baz too"
  }
}

class BarUsingFooAble {
  this: FooAbleComponent with BazAbleComponent =>
  def bar = s"bar calls foo: ${fooAble.foo} and baz: ${bazAble.baz}"
}

object Cake extends App {
  val barWithFoo = new BarUsingFooAble with FooAbleComponent with BazAbleComponent {
    val fooAble = new Foo2Able() //or any other implementation
    val bazAble = new BazAble() //or any other implementation
  }
  println(barWithFoo.bar)
}