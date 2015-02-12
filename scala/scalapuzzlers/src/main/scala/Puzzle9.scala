/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle9 extends App{
  object XY {
    object X {
      val value: Int = Y.value + 1
    }
    object Y {
      val value: Int = X.value + 1
    }
  }

  println(if (math.random > 0.5) XY.X.value else XY.Y.value)

  /*
  Object values are initialized lazily
  Values can't be initialized multiple times, so once we loop, we return default value of 0
   */
}
