package models

import play.api.libs.oauth.{RequestToken, ConsumerKey, ServiceInfo, OAuth}
import play.api.mvc.{RequestHeader, Cookies}

/**
 * User: jconley
 * Date: 2/12/14
 */
case class OAuthProvider(id: String, appKey: String, appSecret: String, 
                         requestTokenUrl: String, accessTokenUrl: String, authorizationUrl: String,
                         homeUrl: String){

  lazy val oauth = OAuth(ServiceInfo(requestTokenUrl, accessTokenUrl, authorizationUrl, consumerKey), false)
  lazy val consumerKey = ConsumerKey(appKey, appSecret)
  lazy val cookieKey = id + appKey

  def requestToken(implicit rh: RequestHeader) = rh.cookies.get(cookieKey).map{ c =>
    val cValue = c.value.split(",")
    RequestToken(cValue(0), cValue(1))
  }

  def retrieveAccessToken(verifier: String)(implicit rh: RequestHeader) = oauth.retrieveAccessToken(requestToken(rh).get, verifier)
}


