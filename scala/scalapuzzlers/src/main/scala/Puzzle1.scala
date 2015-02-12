/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle1 extends App{
  //returns a function that does print then increments
  println(List(1, 2).map{i => println("yo"); i + 1})

  //prints, then returns the increment function
  println(List(1, 2).map{println("yo"); _ + 1})

  //parentheses expects just the arugments, a block is more flexible
}
