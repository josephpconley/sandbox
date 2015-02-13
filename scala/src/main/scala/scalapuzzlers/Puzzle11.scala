package scalapuzzlers

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle11 extends App{
  var x = 0
  lazy val y = 1 / x
  try {
    println(y)
  } catch {
    case _: Exception =>
      x = 1
      println(y)
  }

  /*
  lazy values have the interesting property that if an
  exception is thrown during their initialization, they will be recomputed when
  accessed again
   */
}
