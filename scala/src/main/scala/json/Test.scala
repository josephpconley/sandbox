package json

import play.api.libs.json._
import scala.util._

object Test {
  def checkExtendedSiteInfo(sessionKey: String): Try[JsValue] = {
    val decryptedKey = "key".split(";")
    for {
      siteInfo <- Try(decryptedKey(5))
      siteInfoJs <- Try(Json.parse(siteInfo))
    } yield siteInfoJs
  }
}
