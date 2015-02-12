/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle33 extends App{
  import collection.mutable.Queue
  val mailboxFn: Map[String, Queue[String]] =
    Map() withDefault {_ => Queue("Welcome to your new Fun account!") }
  val mailboxVal: Map[String, Queue[String]] = Map() withDefaultValue Queue("Welcome to your new Val account!")

  println(mailboxFn("Alice").dequeue)
  println(mailboxVal("Alice").dequeue)

  println(mailboxFn("Bob").dequeue)
  println(mailboxVal("Bob").dequeue)

  /*
  withDefaultValue does not work like a factory (like withDefault), it produces one value
  should only use this with IMMUTABLE values (use withDefault for mutable values)
   */
}
