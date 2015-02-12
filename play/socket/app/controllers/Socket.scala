package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee.{Enumeratee, Concurrent, Iteratee, Enumerator}
import concurrent.ExecutionContext.Implicits.global

object Socket extends Controller {

  // Generic, reusable adapter code that adds a prefix
  // to each message
  val formatMessage = Enumeratee.map[String] { msg =>
    s"[echo]: $msg"
  }

  // Use Enumeratee.collect to only pass through messages that
  // are not empty and contain non-whitespace characters
  val hasJoe = Enumeratee.collect[String] {
    case valid if valid.trim.contains("joe") => valid
  }

  val filters = formatMessage.compose(hasJoe)

  def connect = WebSocket.using[String] { request =>
    Logger.info("Someone just connected!")

    // Create an Enumerator that sends a message to the
    // client
//    val enumerator = Enumerator("Hello!")

    // Create an Iteratee that listens for messages from
    // the client
//    val iteratee = Iteratee.foreach[String] { msg =>
//      Logger.info(s"Got message $msg")
//    }
    val (iteratee, enumerator) = Concurrent.joined[String]

    (filters(iteratee), enumerator)
  }
}