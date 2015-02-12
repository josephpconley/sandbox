/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle32 extends App{
  val numbers = List("1", "2").toSet.apply() + "3"
  println(numbers)

  /*
  Adding the parens after toSet calls apply on the set, which checks for set membership and returns a boolean
   */
}
