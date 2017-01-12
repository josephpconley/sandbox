package samples

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

case object Pint

class Person extends Actor with ActorLogging {
  def receive = {
    case Pint => log.info("Thanks for the pint")
  }
}

object Bar extends App {
  val system = ActorSystem("howdy-akka")

  val alice = system.actorOf(Props[Person], "alice")

  alice ! Pint

  system.shutdown
}