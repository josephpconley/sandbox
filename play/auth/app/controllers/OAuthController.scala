package controllers

import play.api.mvc.{Cookie, Action, Controller}
import play.api.libs.oauth._
import play.api.libs.ws.WS

import concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import models.OAuthProvider
import play.api.Logger
import play.api.libs.json.Json

/**
  * User: jconley
  * Date: 12/6/13
  */
object OAuthController extends Controller {

  var twitter = OAuthProvider("twitter", "epHjEGpeeeeDpEB4eC1g", "3CI7WO1VcosQLSMA583G5K4aY3DLzsUX4kpW2Ln4Mw",
                              "https://api.twitter.com/oauth/request_token",
                              "https://api.twitter.com/oauth/access_token",
                              "https://api.twitter.com/oauth/authorize",
                              "https://api.twitter.com/1.1/statuses/home_timeline.json")

  val oauths = Seq(twitter)

  def index = Action { implicit req =>
    Ok(oauths.mkString("\n"))
  }

  def notFound(id: String) = Future.successful(BadRequest(Json.obj("message" -> s"Not found: $id")))

  def home(id: String) = Action.async { implicit request =>
    oauths.find(_.id == id).map{ provider =>
      provider.requestToken.map{ tokens =>
        WS.url(provider.homeUrl).sign(OAuthCalculator(provider.consumerKey, tokens)).get.map(r => Ok(r.body))
      }.getOrElse(Future(Redirect(routes.OAuthController.auth(id))))
    }.getOrElse(notFound(id))
  }

  def call(id: String, url: String) = Action.async { implicit request =>
    oauths.find(_.id == id).map { provider =>
      provider.requestToken.map{ tokens =>
        WS.url(url).sign(OAuthCalculator(provider.consumerKey, tokens)).get.map(r => Ok(r.body))
      }.getOrElse(Future(Redirect(routes.OAuthController.auth(id))))
    }.getOrElse(notFound(id))
  }

  def auth(id: String) = Action { implicit request =>
    val provider = oauths.find(_.id == id).get
    request.queryString.get("oauth_verifier").flatMap(_.headOption).map { verifier =>
      Logger.info("Retrieving access token" + provider)
      Logger.info(provider.consumerKey.toString)
      provider.retrieveAccessToken(verifier) match {
        case Right(t) => {
          // We received the authorized tokens in the OAuth object - store it before we proceed
          val cookie = Cookie(provider.cookieKey, Seq(t.token, t.secret).mkString(","))
          Redirect(routes.OAuthController.home(id)).withCookies(cookie)
        }
        case Left(e) => throw e
      }
    }.getOrElse{
      Logger.info("Retrieving request token: " + provider)
      provider.oauth.retrieveRequestToken(s"http://localhost:9000/oauth/$id/auth") match {
        case Right(t) => {
          // We received the unauthorized tokens in the OAuth object - store it before we proceed
          Logger.info("store unauth tokens " + t)
          val cookie = Cookie(provider.cookieKey, Seq(t.token, t.secret).mkString(","))
          Redirect(provider.oauth.redirectUrl(t.token)).withCookies(cookie)
        }
        case Left(e) => throw e
      }
    }
  }
}