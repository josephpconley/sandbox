package unbounded

import akka.actor._
import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory


class FastSender(slow: ActorRef) extends Actor with ActorLogging {
  override def postStop() {
    log.info("FastSender#postStop")
    context.system.scheduler.scheduleOnce(2.seconds, slow, PoisonPill)(context.dispatcher)
  }

  def receive: Actor.Receive = {
    case _ =>
      var i = 0
      while(true){
        slow ! s"[$i]"
        i += 1
      }
      log.info("Done sending all")
      self ! PoisonPill
  }
}

class SlowReceiver extends Actor with ActorLogging {
  override def postStop() {
    log.info("SlowReceiver#postStop")
  }

  def receive: Actor.Receive = {
    case msg: String =>
      log.info(s"Received: $msg")
      Thread.sleep(100)
  }
}

case object Ping

object UnboundedApp extends App {
//  val config = ConfigFactory.parseString("""
//      akka{
//        actor {
//          queued-dispatcher {
//    	      mailbox-type =”akka.dispatch.UnboundedDequeueBasedMailBox”
//          }
//        }
//      }""")

  implicit val system = ActorSystem("UnboundedActorSystem")//ConfigFactory.load(config)

  val slow = system.actorOf(Props[SlowReceiver], "slow")
  val fast = system.actorOf(Props(classOf[FastSender], slow), "fast")
  fast ! Ping

  system.shutdown()

}
