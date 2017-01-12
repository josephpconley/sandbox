package closures

object Closures extends App {
  var factor = 3
  val multiplier = (i:Int) => i * factor

  println( "multiplier(1) value = " +  multiplier(1) )
  println( "multiplier(2) value = " +  multiplier(2) )
}