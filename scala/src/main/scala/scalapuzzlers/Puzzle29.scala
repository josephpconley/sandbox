package scalapuzzlers

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle29 extends App{
  val ints = Map(1 -> List(1, 2, 3, 4, 5))
  val intsIter1 = ints map { case (k, v) => (k, v.toIterator) }
  val intsIter2 = ints mapValues (_.toIterator)

  println(intsIter1(1).next, intsIter1(1).next)
  println(intsIter2(1).next, intsIter2(1).next)

  /*
  The documentation for mapValues simply states that it “transforms the map by applying a function
  to every value,” but the important detail is hidden in the returns part:
      returns a map view that maps every key of this map to
      f(this(key)). The resulting map wraps the original map without
      copying any elements.

  So with mapValues, each retrieval of the map causes a new evaluation
   */
}
