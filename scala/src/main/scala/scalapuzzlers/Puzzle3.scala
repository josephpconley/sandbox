package scalapuzzlers

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle3 extends App{
  trait A {
    val audience: String
    println("Hello " + audience)
  }
  class BMember(a: String = "World") extends A {
    val audience = a
    println("I repeat: Hello " + audience)
  }
  class BConstructor(val audience: String = "World") extends A {
    println("I repeat: Hello " + audience)
  }

  new BMember("Readers")
  new BConstructor("Readers")

  /*
  Initialization sequence

  evaluate argument
  evaluate superclass constructor
  evalute class body
   */
}
