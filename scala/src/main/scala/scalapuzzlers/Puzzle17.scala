package scalapuzzlers

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle17 extends App{
  var x = 0
  def counter() = { x += 1; x }
  def add(a: Int)(b: Int) = a + b
  val adder1 = add(counter)(_)
  val adder2 = add(counter) _
  println("x = " + x)
  println(adder1(10))
  println("x = " + x)
  println(adder2(10))
  println("x = " + x)

  /*
  In adder1, it has not been evaluated yet and will instead be evaluated each time adder1 is used.
  In adder2, counter has already been evaluated and will never be evaluated again.
   */
}
