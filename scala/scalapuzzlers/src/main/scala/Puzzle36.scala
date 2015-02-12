/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle36 extends App{
  import collection.mutable
  def howManyItems(lunchbox: mutable.Set[String], itemToAdd: String): Int = (lunchbox + itemToAdd).size

  def howManyItemsRefac(lunchbox: mutable.Iterable[String], itemToAdd: String): Int = (lunchbox + itemToAdd).size

  val lunchbox = mutable.Set("chocolate bar", "orange juice", "sandwich")

  println(howManyItems(lunchbox, "apple"))
  println(howManyItemsRefac(lunchbox, "apple"))
  println(lunchbox.size)

  /*
  The main reason for the existence of any2stringadd is interoperability with
Java, which also supports concatenation of any object with a string.

When using + with a string argument, be aware that the
Scala compiler can always fall back to treating the
expression as string concatenation.
   */
}