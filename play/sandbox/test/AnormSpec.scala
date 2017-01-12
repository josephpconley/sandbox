import org.specs2.mutable._

import anorm._
import play.api.db.DB
import play.api.test.{PlaySpecification, WithApplication}
import play.api.Play.current

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class AnormSpec extends PlaySpecification {

  "Anorm" should {
    "test" in new WithApplication{
      val data = DB.withConnection { implicit c =>
        SQL("Select * from test").apply().map(row => row.asMap.map(col => col._2.toString()))
      }.toList

//      val data = DB.withConnection { implicit c =>
//        SQL("Select 1").execute()
//      }

      println(data)

      1 + 1 must beEqualTo(2)
    }
  }
}