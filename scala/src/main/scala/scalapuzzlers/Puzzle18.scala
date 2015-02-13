package scalapuzzlers

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle18 extends App{
  object Oh {
    def overloadA(u: Unit) = "I accept a Unit"
    def overloadA(u: Unit, n: Nothing) = "I accept a Unit and Nothing"
    def overloadB(n: Unit) = "I accept a Unit"
    def overloadB(n: Nothing) = "I accept Nothing"
  }

//  println(Oh overloadA 99)
//  println(Oh overloadB 99)

  /*
  Here, the value conversion that, in the case of overloadA, turned 99
  into a Unit is also not available, since it only applies if the expected type
  of the value is known.
   */
}
