package samples

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

/**
 * User: joe
 * Date: 1/21/14
 */

object Counter {
  case object Greet
  case object Done
  case object GetCount
  case class Count(count: Int)
}

class Counter extends Actor {

  var helloCount = 0

  def receive = {
    case Counter.Greet => {
      println("Hello World!")
      helloCount +=1
      sender ! Greeter.Done
    }
    case Counter.GetCount => sender ! Counter.Count(helloCount)
  }
}

object HelloWorld2 extends App {
  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val helloActor = system.actorOf(Props[Counter], "helloactor")

  helloActor ! Counter.Greet
  helloActor ! Counter.Greet
  helloActor ! Counter.Greet

  implicit val timeout = Timeout(5 seconds)
  (helloActor ? Counter.GetCount).map { response =>
    println(response)
  }

  system.shutdown()
}