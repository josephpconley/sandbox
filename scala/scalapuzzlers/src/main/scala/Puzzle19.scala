/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle19 extends App{
  class SimpleAdder {
    def add(x: Int = 1, y: Int = 2): Int = x + y
  }
  class AdderWithBonus extends SimpleAdder {
    override def add(y: Int = 3, x: Int = 4): Int =
      super.add(x, y) + 10
  }

  val adder: SimpleAdder = new AdderWithBonus
  println(adder add (y = 0))
  println(adder add 0)

  /*
  “Names are compile time; values are run time.”
   */
}