package controllers

import play.api.mvc.{RequestHeader, Cookie, Action, Controller}
import play.api.libs.oauth._
import play.api.libs.ws.WS

import concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import models.{OAuth2Provider, OAuthProvider}
import play.api.Logger
import java.net.URLEncoder
import play.api.libs.json.Json

/**
  * User: jconley
  * Date: 12/6/13
  */
object OAuth2Controller extends Controller {

  def notFound(id: String) = Future.successful(BadRequest(Json.obj("message" -> s"Not found: $id")))

  val linkedIn = OAuth2Provider("linkedin", "77t642l6zzlhpt", "RGsdJC8G9Qz2FHKf", "BNADFASKFA1874012nbakjan95280",
                                "https://www.linkedin.com/uas/oauth2/authorization", "https://www.linkedin.com/uas/oauth2/accessToken",
                                "https://api.linkedin.com/v1/people/~?format=json&oauth2_access_token=$token")

  val dropbox = OAuth2Provider("dropbox", "pwt1ng2o4opl3d2", "us9sxm9ws0uftgc", "BNADFASKFA1874012nbakjan95280",
                              "https://www.dropbox.com/1/oauth2/authorize", "https://api.dropbox.com/1/oauth2/token",
                              "https://api.dropbox.com/1/account/info")

//  val box = OAuth2Provider("Box", "hirp12fegshx1kq5n4du4tc77fl1l991", "FbuyaMpmq82z3oNULj5wI9fVwEoYRd6A", "BNADFASKFA1874012nbakjan95280",
//                           "https://www.box.com/api/oauth2/authorize", "https://www.box.com/api/oauth2/token",
//                            "https://api.dropbox.com/1/account/info")

//  val googleDrive = OAuth2Provider("Google Drive", "AIzaSyAnWDyTgtntdubCu5vYxBUX_Uimphg9k30", "", "BNADFASKFA1874012nbakjan95280",
//    "https://accounts.google.com/o/oauth2/auth", redirectURL, "https://accounts.google.com/o/oauth2/token")

  val providers = Seq(linkedIn, dropbox)

  def index = Action { implicit req =>
    Ok(providers.mkString("\n"))
  }

  def call(id: String, url: String) = Action.async { implicit request =>
    providers.find(_.id == id).map{ provider =>
      provider.requestToken.map{ token =>
        WS.url(url).withHeaders("Authorization" -> s"Bearer $token").get.map{r =>
          Ok(r.body)
        }
      }.getOrElse(Future(Redirect(routes.OAuth2Controller.auth(id))))
    }.getOrElse(notFound(id))
  }

  def auth(id: String) = Action.async { implicit request =>
    val provider = providers.find(_.id == id).get
    request.queryString.get("code").flatMap(_.headOption).map { code =>
    //we have the auth code, let's get the access token
      WS.url(provider.accessURL(code)).post(provider.accessForm(code)).map{r =>
        (r.json \ "access_token").asOpt[String].map{ token =>
          val cookie = Cookie(provider.cookieKey, (r.json \ "access_token").as[String])
          Ok(r.json).withCookies(cookie)
        }.getOrElse{
          Ok(r.json)
        }
      }
    }.getOrElse(Future(Redirect(provider.authURL)))
  }
}