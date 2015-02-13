package scalapuzzlers

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle25 extends App{
  val (x, y) = (List(1,3,5), List(2,4,6)).zipped.find(_._1 > 10).getOrElse(10)
  println(s"Found $x")

  /*
  zipped returns a List of (Int, Int)
  getOrElse doesn't have to return the same type, it accepts a wider type than the one you start with.

  the tuple assignment expression isn't a regular type definition, it's a pattern defintion

  this can be prevented by explicitly defining the type of the tuple, rather than get inferred into (Any, Any)
   */
}
