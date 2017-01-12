package samples

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

/*
case object Money
case object FullPint
case object EmptyPint

class Bartender extends Actor with ActorLogging {
  def receive = {
    case Money => {
      log.info("1 pint coming right up")

      Thread.sleep(1000)

      log.info("Your pint is ready, here you go")

      sender ! FullPint
    }
    case EmptyPint => {
      log.info("I think you're done for the day")
      FullBar.system.shutdown
    }
  }
}

class Person extends Actor with ActorLogging {
  def receive = {
    case FullPint =>
      log.info("I'll make short work of this")
      Thread.sleep(1000)
      log.info("I'm ready for the next")
      sender ! EmptyPint
  }
}

object FullBar extends App {
  val system = ActorSystem("howdy-akka")

  val zed = system.actorOf(Props[Bartender], "zed")

  val alice = system.actorOf(Props[Person], "alice")

  zed.tell(Money, alice)
}
*/