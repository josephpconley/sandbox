package elasticsearch

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s._
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.{ImmutableSettings, Settings}
import org.elasticsearch.common.transport.InetSocketTransportAddress

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
//
//  val categories: Seq[Long] = Seq(78, 107)
//  filterList += termsFilter("categories.id", categories:_*) execution "and"
//
//  filterList += existsFilter("reviews")

//  filterList += existsFilter("offers")

//    filterList += queryFilter(stringQuery("for") field "offers.description")

//  filterList += queryFilter(stringQuery("offer") field "offers.title")

//  filterList += geoDistance("contactInfo.geoPoint").point(40, -105).distance("200.5mi")

//  val keywordFields = Seq("description", "servicesDescription", "reviews.text", "offers.title", "offers.description")
//  filterList += queryFilter(multiMatchQuery("door") fields keywordFields)
  
//  filterList += (rangeFilter("recentGrade") gte "3")
//
//  filterList += regexFilter("contactInfo.phoneNumber", "30344.*")

  val filter = filteredQuery filter bool {
    must(filterList.toList:_*)
  }
  val query = search in "search_v1" -> "serviceProvider" query filter start 0 limit 5
  val res = Await.result(es.execute(query), Duration.Inf)

  println(res)

  res.getHits.hits().toIterable.foreach { h =>
    println(h.getSource)
  }
}