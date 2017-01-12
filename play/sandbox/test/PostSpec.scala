import play.api.libs.json.Json
import play.api.test.{FakeRequest, PlaySpecification, WithApplication}

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class PostSpec extends PlaySpecification {

  "POST examples" should {

    "form" in new WithApplication{
      val post = route(FakeRequest(POST, "/post/form").withFormUrlEncodedBody(
        "name" -> "Joe",
        "gender" -> "M",
        "age" -> "29"
      )).get

      status(post) must beEqualTo(OK)
    }

    "json" in new WithApplication{
      val post = route(FakeRequest(POST, "/post/json").withJsonBody(
        Json.obj(
          "name" -> "Joe",
          "gender" -> "M",
          "age" -> "29"
        )
      )).get

      status(post) must beEqualTo(OK)
    }
  }
}