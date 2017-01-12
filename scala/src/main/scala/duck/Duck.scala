package duck

object Duck extends App {

  def quacker(duck: {def quack(value: String): String}) {
    println (duck.quack("Quack"))
  }

  object BigDuck {
    def quack(value: String) = value.toUpperCase
  }

  object SmallDuck {
    def quack(value: String) = value.toLowerCase
  }

  object IamNotReallyADuck {
    def quack(value: String) = "prrrrrp"
  }

  quacker(BigDuck)
  quacker(SmallDuck)
  quacker(IamNotReallyADuck)
}
