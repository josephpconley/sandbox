package scalapuzzlers

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle23 extends App{
  trait Recipe {
    type T <: AnyVal
    def sugarAmount: T
    def howMuchSugar() {
      println(s"Add ${sugarAmount} tablespoons of sugar")
    }
  }
  val approximateCake = new Recipe {
    type T = Int
    val sugarAmount = 5
  }

  approximateCake.howMuchSugar()

  val gourmetCake = new Recipe {
    type T = Double
    val sugarAmount = 5.13124
  }

  gourmetCake.howMuchSugar()

  trait HasAnyVal {
    type T <: AnyVal
    var x: T = _
  }

  val hasBoolean = new HasAnyVal {
    type T = Boolean
  }

  println(hasBoolean.x)
  println(!hasBoolean.x)

  /*
  Default value of T is null
  Autoboxing causes Boolean to default to false
   */
}
