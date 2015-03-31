package elasticsearch

import com.sksamuel.elastic4s._
import com.sksamuel.elastic4s.ElasticDsl._
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.{ImmutableSettings, Settings}
import org.elasticsearch.common.transport.InetSocketTransportAddress
import play.api.libs.json.Json

import scala.collection.mutable.ListBuffer
import scala.concurrent.Await
import scala.concurrent.duration.Duration

trait ESClient {
  val es = {
    val clusterName = "elasticsearch"
    val host = "localhost"
    val port = 9300

    val settings: Settings = ImmutableSettings.settingsBuilder().put("cluster.name", clusterName).build()
    val client = new TransportClient(settings)
    client.addTransportAddress(new InetSocketTransportAddress(host, port))

    val timeout: Long = 30000
    ElasticClient.fromClient(client, timeout)
  }
}

object ES extends App with ESClient {
  val filterList = new ListBuffer[FilterDefinition]

  filterList += termFilter("listingId", 1)

//  val categories: Seq[Long] = Seq(78, 107)
//  filterList += termsFilter("categories.id", categories:_*) execution "and"
//
//  filterList += existsFilter("reviews")

//  filterList += existsFilter("offers")

//  filterList += queryFilter(stringQuery("offer") field "offers.title")

//  filterList += geoDistance("contactInfo.geoPoint").point(40, -105).distance("200.5mi")

//  val keywordFields = Seq("description", "servicesDescription", "reviews.text", "offers.title", "offers.description")
//  filterList += queryFilter(multiMatchQuery("door") fields keywordFields)
  
//  filterList += (rangeFilter("recentGrade") gte "C")

//  val marketPostalCode = "80005"
//  filterList += or(termFilter("postalCodesServiced", marketPostalCode), termFilter("postalCodesServiced", marketPostalCode))

//
//  filterList += regexFilter("contactInfo.phoneNumber", "30344.*")
//  filterList += termFilter("contactInfo.phoneNumber", "30344")

  val filter = filteredQuery filter bool {
    must(filterList.toList:_*)
  }

  val sorts = by field "nameKeyword"

  val query = search in "search_v1" -> "serviceProvider" query filter sort sorts start 0 limit 50
  val res = Await.result(es.execute(query), Duration.Inf)

  println(res.getHits.getTotalHits)
  res.getHits.hits().foreach { h =>
    println(h.getSourceAsString)
  }
}