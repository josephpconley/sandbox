package scalapuzzlers

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle21 extends App{
  implicit class Padder(val sb: StringBuilder) extends AnyVal {
    def pad2(width: Int) = {
      1 to width - sb.length foreach { sb += '*' }
      sb
    }
  }
  // length == 14
  val greeting = new StringBuilder("Hello, kitteh!")
  println(greeting pad2 20)

  // length == 9
  val farewell = new StringBuilder("U go now.")
  println(farewell pad2 20)

  /*

  The argument to foreach, sb += '*' gets evaluated ONCE

  Method becomes:

  def pad2(width: Int) = {
    val appendedSb = sb += '*'
    1 to (width - sb.length) foreach appendedSb.charAt
    sb
  }

  Solution is to pass the argument as { _ => sb += '*' }

   */
}
