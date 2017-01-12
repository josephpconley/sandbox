package scalapuzzlers

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle20 extends App{
  def traceIt[T <: Iterator[_]](it: T) = {
    println(s"TRACE: using iterator '${it}'")
    it
  }
  val msg = "I love Scala"
  println("First match index: " + traceIt("a".r.findAllIn(msg)).start)

  println("First match index: " + "a".r.findAllIn(msg).start)
}
