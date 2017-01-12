package scalapuzzlers

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle26 extends App{
  object Scanner {
    trait Console { def display(item: String) }
    trait AlarmHandler extends (() => Unit)
    def scanItem(item: String)(implicit c: Console) {
      c.display(item)
    }

    def hitAlarmButton()(implicit ah: AlarmHandler) { ah() }
  }

  object NormalMode {
    implicit val ConsoleRenderer = new Scanner.Console {
      def display(item: String) { println(s"Found a ${item}") }
    }
    implicit val DefaultAlarmHandler = new Scanner.AlarmHandler {
      def apply() { println("ALARM! ALARM!") }
    }
  }

  object TestMode {
    implicit val ConsoleRenderer = new Scanner.Console {
      def display(item: String) { println(s"Found a detonator") }
    }
    implicit val TestAlarmHandler = new Scanner.AlarmHandler {
      def apply() { println("Test successful. Well done!") }
    }
  }

  import NormalMode._
  Scanner scanItem "knife"
  Scanner.hitAlarmButton()

  import TestMode._
//  Scanner scanItem "shoe"
//  Scanner.hitAlarmButton()

  /*
  Names of implicits matter
  The first test call works since the new ConsoleRenderer shadows the first
  The second test call has two alarm handlers to choose from, throwing an error

  When defining a set of implicits that are intended to be
  overridden if needed, place them in a “default context”
  class or trait that can be extended. Define overriding
  implicits in a subclass or subtrait of the default
  context. Overriding implicits do not need to have the
  same name as the implicits they are intended to
  replace in this case.

   */
}
