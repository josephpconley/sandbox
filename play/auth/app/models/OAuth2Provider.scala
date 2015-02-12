package models

import play.api.libs.oauth.{RequestToken, ConsumerKey, ServiceInfo, OAuth}
import play.api.mvc.RequestHeader
import java.net.URLEncoder

/**
 * User: jconley
 * Date: 2/12/14
 */
case class OAuth2Provider(id: String, clientId: String, clientSecret: String, state: String,
                          authorizeUrl: String, accessTokenUrl: String, homeUrl: String){
  lazy val authURL = s"$authorizeUrl?response_type=code&client_id=$clientId&state=$state&redirect_uri=$redirectUrl"

  lazy val redirectUrl = s"http://localhost:9000/oauth2/auth?id=$id"

  def accessMap(code: String) = Map(
    "grant_type" -> "authorization_code",
    "redirect_uri" -> redirectUrl,
    "client_id" -> clientId,
    "client_secret" -> clientSecret,
    "code" -> code
  )

  def accessForm(code: String) = accessMap(code).map(kv => kv._1 -> Seq(kv._2))

  def accessURL(code: String) = accessTokenUrl + "?" + accessMap(code).map(kv => kv._1 + "=" + kv._2).mkString("&")

  val cookieKey = id + clientId + clientSecret

  def requestToken(implicit rh: RequestHeader) = rh.cookies.get(cookieKey).map{ c =>
    c.value
  }
}