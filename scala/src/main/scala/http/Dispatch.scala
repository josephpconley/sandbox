package http

import dispatch.Http
import org.apache.http.client.utils.URIBuilder
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global

object Dispatch extends App {

  val url = "https://maps.googleapis.com/maps/api/geocode/json"
  val address = "430 Foulke Ln, Springfield, Pennsylvania, United States"

  val queryParams = Map("sensor" -> Seq("false"), "address" -> Seq(address))
  val queryParamsArray = Array("sensor" -> "false", "address" -> address)

  val b = new URIBuilder
  b.setScheme("https")
  b.setHost("maps.googleapis.com")
  b.setPath("/maps/api/geocode/json")
//  b.addParameter("sensor", "false")
//  b.addParameter("address", address)
  b.addParameter("philip", "fry")

  val burl = b.build().toString
  val req = dispatch.url(burl)
//  val req = withHeadersAndQueryParams(burl, Array(), Array("philip" -> "bender"))
  println(req.url)

//  val req = dispatch.url(url).setQueryParameters(queryParams)
//  val req = withHeadersAndQueryParams(url, Array(), queryParamsArray)

  for {
    response <- Http(req.GET)
  } yield {
    println(response.getResponseBody)
  }

  private def withHeadersAndQueryParams(
      url: String,
      headerParams: Array[(String, String)],
      queryParams: Array[(String, String)]) = {
    def compress(array: Array[(String, String)]): Map[String, Seq[String]] = {
      val defaultValue = Map.empty[String, Seq[String]].withDefaultValue(Seq.empty[String])
      array.foldLeft(defaultValue) {
        case (acc, (key, value)) => acc updated (key, value +: acc(key))
      }
    }

    dispatch.url(url).setHeaders(compress(headerParams)).setQueryParameters(compress(queryParams))
  }
}