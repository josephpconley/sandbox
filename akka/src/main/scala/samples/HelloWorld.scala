package samples

import akka.actor.{Actor, ActorSystem, Props}

/**
 * User: joe
 * Date: 1/21/14
 */

object Greeter {
  case object Greet
  case object Done
}

class Greeter extends Actor {
  def receive = {
    case Greeter.Greet => {
      println("Hello World!")
      sender ! Greeter.Done
    }
  }
}

object HelloWorld extends App {
  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val helloActor = system.actorOf(Props[Greeter], "helloactor")

  helloActor ! Greeter.Greet

//  implicit val timeout = Timeout(5 seconds)
//  (helloActor ? Greeter.Greet).map { response =>
//    println(response)
//  }

  system.shutdown()
}