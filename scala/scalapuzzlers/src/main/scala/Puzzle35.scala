/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle35 extends App{
  class QuietType {
    implicit val stringToInt = (_: String).toInt
    println("4" - 2)
  }
  class OutspokenType {
    //endless loop, we specified String (not StringOps)
    implicit val stringToInt: String => Int = _.toInt
    println("4" - 2)
  }
  new QuietType()
  new OutspokenType()

  /*
  Use implicit defs rather than implicit function vals to
ensure potential “ambiguous implicit” errors are not hidden
by implementation-specific compiler behavior. Explicitly
state the return type of implicit defs and avoid cases in
which the implicit conversion being declared may be
applicable to its own definition, potentially causing endless
loops. As a general rule, avoid implicit conversions of base
types where possible. Such conversions are usually too
broadly applicable, and can be applied by the compiler in
unintended places, leading to unexpected behavior.
   */
}
