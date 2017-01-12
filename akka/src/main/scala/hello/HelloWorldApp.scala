package hello

import akka.actor.{ActorSystem, Actor, Props}
import akka.util.Timeout
import akka.pattern.ask
import concurrent.duration._
import concurrent.ExecutionContext.Implicits.global

object Greeter {
  case object Greet
  case class Respond(msg: String)
}

class Greeter extends Actor {
  def receive = {
    case Greeter.Greet => {
      println("Hello World!")
      sender ! Greeter.Respond("Hello World")
    }
    case s: String => {
      println(s"Got $s!")
      sender ! "Hello!"
    }
  }
}

object HelloWorldApp extends App {
  val system = ActorSystem("HelloSystem")

  // default Actor constructor
  val helloActor = system.actorOf(Props[Greeter], "helloactor")
  println(helloActor.path)

//  helloActor ! Greeter.Greet
//
//  implicit val timeout = Timeout(5 seconds)
//  (helloActor ? Greeter.Greet).map { response =>
//    println(response)
//  }

//  system.shutdown()
}