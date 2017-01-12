import org.specs2.mutable._

import models._
import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends Specification {

  implicit val fooWritesAlt: Writes[Foo] = (
    (__ \ "a").write[String] and
      (__ \ "b").write[Int]
    ) (unlift({(f: Foo) => Some((f.a, f.b))}))

  "Application" should {
    "foo" in {
      val f = Foo("a", 2, 0.0)
      println(fooWritesAlt.writes(f))
      println(Json.writes[Foo].writes(f))
      f.a must beEqualTo("a")
    }

    "hobby" in {
      val json = Json.parse("""{"id":"123", "name":"Joe", "hobbyId":"abc", "hobbyName":"programming"}""")
      println(Json.fromJson[PersonWithHobby](json))

      true must beTrue
    }

//    "bar" in {
//      implicit val barReads: Reads[Bar] = (
//        (__ \ "name").read[String] and
//        (__ \ "isDead").readNullable[Int]
//      )(Bar.applyOpt _)
//      val bar = Bar("Joe")
//      println(Json.writes[Bar].writes(bar))

//      val barJson = """{"name": "Joe"}"""
//      println(Json.parse(barJson).validate[Bar])
//    }



//    "send 404 on a bad request" in {
//      running(FakeApplication()) {
//        route(FakeRequest(GET, "/boum")) must beNone
//      }
//    }
//
//    "render the index page" in {
//      running(FakeApplication()) {
//        val home = route(FakeRequest(GET, "/")).get
//
//        status(home) must equalTo(OK)
//        contentType(home) must beSome.which(_ == "text/html")
//        contentAsString(home) must contain ("Your new application is ready.")
//      }
//    }
  }
}