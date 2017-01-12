package controllers

import play.api.mvc.{WebSocket, Controller}
import java.util.concurrent.atomic.AtomicLong
import play.api.libs.iteratee.{Iteratee, Concurrent}
import concurrent.ExecutionContext.Implicits.global

/**
 * User: jconley
 * Date: 11/26/13
 */
object Chat extends Controller {
  // Concurrent.broadcast creates an Enumerator and Channel that
  // let us imperatively push messages to many Iteratees
  lazy val (enumerator, channel) = Concurrent.broadcast[String]

  val userId = new AtomicLong(0)

  def chat = WebSocket.using[String] { request =>
    val username = s"user${userId.incrementAndGet()}"

    broadcastMessage("system", s"$username connected!")

    val iteratee = Iteratee.foreach[String]{msg =>
      broadcastMessage(username, msg)
    }.map{ _ =>
      broadcastMessage("system", s"$username disconnected")
    }

    (iteratee, enumerator)
  }

  def broadcastMessage(username: String, message: String): Unit = {
    channel.push(s"[$username]: $message")
  }
}
