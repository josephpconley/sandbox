package scalapuzzlers

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle28 extends App{
  /*
  Scala offers two parameter evaluation strategies:
    1. By-value - which causes arguments to be evaluated before being passed
       to the method. This is the default style.
    2. By-name - which allows arguments to be evaluated only when referenced
       inside the method. By-name parameter types are prefixed with
       the => symbol (a.k.a, fat arrow or rocket).

       By-name forces evaluation at each reference, i.e.
   */

  def mod(a: => Double) = if (a >= 0) a else a
//  mod({println("evaluating"); 5.2})

  class Printer(prompter: => Unit) {
    def print(message: String, prompted: Boolean = false) {
      if (prompted) prompter
      println(message)
    }
  }
  def prompt() {
    print("puzzler$ ")
  }
  new Printer { prompt } print (message = "Puzzled yet?")
  new Printer { prompt } print (message = "Puzzled yet?", prompted = true)

  /*
  The key part is that
  parentheses can be replaced with curly braces only in case of method arguments.

  Above is the syntax for
  creating an anonymous subclass and defining a primary constructor (with no
  arguments).  So prompt gets executed as part of the constructor code

  You always need to provide constructor arguments within
  parentheses. You can replace parentheses with curly braces
  only in case of method arguments.
   */
}