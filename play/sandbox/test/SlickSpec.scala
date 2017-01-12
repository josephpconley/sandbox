import org.specs2.mutable._

import models._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.test.WithApplication
import scala.slick.driver.H2Driver.simple._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class SlickSpec extends Specification {

  "Slick" should {
    "computed column" in new WithApplication{
      val q = TableQuery[ProductTable]
      println(q.ddl.createStatements.mkString(","))

      1 + 1 must beEqualTo(2)
    }
  }
}