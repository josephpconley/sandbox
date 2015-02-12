/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle14 extends App{
  def sumItUp: Int = {
    def one(x: Int): Int = { return x; 1 }
    val two = (x: Int) => { return x; 2 }
    1 + one(2) + two(3)
  }
  println(sumItUp)

  /*
  one always return x
  two is an unnamed function, so return will not return right away

  A return expression return e must occur inside the body of
some enclosing named method or function.
   */
}
