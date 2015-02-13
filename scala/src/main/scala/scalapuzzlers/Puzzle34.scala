package scalapuzzlers

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle34 extends App{
  class AirportDay {
    def tryCheckBag(weight: Int): String =
      "It's not a full flight. Your bag is OK."
  }
  class StartOfVacation extends AirportDay {
    override def tryCheckBag(weight: Int): String =
      if (weight > 25) {
        "Your bag is too heavy. Please repack it."
      } else {
        "Your bag is OK."
      }
  }
  def goToCheckIn(bagWeight: Int)(implicit ad: AirportDay) {
    println(s"The agent says: ${ad tryCheckBag bagWeight}")
  }
  object AirportSim {
    def main(args: Array[String]): Unit = {
      implicit val quietTuesday = new AirportDay
      goToCheckIn(26)

      //this is more specific so the compiler chooses this one
      implicit val busyMonday = new StartOfVacation
      goToCheckIn(26)
    }
  }
  object AirportSimV2 extends App {
    implicit val quietTuesday = new AirportDay
    goToCheckIn(26)
    implicit val busyMonday = new StartOfVacation
    goToCheckIn(26)
  }

  AirportSim main Array()
  AirportSimV2 main Array()

  /*
  Unlike local values in a method, which are not in scope until
they are declared, values declared at the top level of the
body of a class or object are members and in scope
throughout the class or object. They are only initialized,
however, when the initialization statement declaring them is
executed.
   */
}
