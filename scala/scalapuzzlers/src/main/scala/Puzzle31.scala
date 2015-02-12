/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle31 extends App{
  def prependIfLong(candidate: Any, elems: Any*): Seq[Any] = if (candidate.toString().length > 1) candidate +: elems else elems
  println(prependIfLong("I", "love", "Scala")(0))

  def prependIfLongRefac(candidate: Any)(elems: Any*): Seq[Any] = if (candidate.toString().length > 1) candidate +: elems else elems
  println(prependIfLongRefac("I", "love", "Scala")(0))

  /*
  After the compiler has unsuccessfully attempted to find a version of
  prependIfLongRefac that can take three arguments, it tries one last option:
  to pack all arguments into a tuple and try to apply the function to that. This
  auto-tupling actually succeeds in our case, binding candidate to the threetuple
  ("I", "love", "Scala").
   */
}
