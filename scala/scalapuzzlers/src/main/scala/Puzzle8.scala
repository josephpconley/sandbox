/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle8 extends App{
  val xs = Seq(Seq("a", "b", "c"), Seq("d", "e", "f"), Seq("g", "h"), Seq("i", "j", "k"))

  val ys = for (Seq(x, y, z) <- xs) yield x + y + z
  println(ys)

  //MatchError
//  val zs = xs map { case Seq(x, y, z) => x + y + z }
//  println(zs)

  //for comprehension skips the probelmatic value, pattern match is strict

  /*
  Unlike filter, which creates a new collection and so incurs the overhead of an entire run
through the source collection, withFilter is simply a “view.” It lazily restricts the items
passed on to subsequent map, flatMap, foreach, and withFilter calls and is specifically
designed for efficient chaining of these operations.
   */

  val view = xs withFilter { case Seq(x, y, z) => true; case _ => false }
}
