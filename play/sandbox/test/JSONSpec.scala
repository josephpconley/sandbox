
import com.josephpconley.jsonpath.JSONPath
import play.api.libs.json.Json
import play.api.test.{PlaySpecification, WithApplication}

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class JSONSpec extends PlaySpecification {

  "JSON" should {
    "test" in new WithApplication{
      val json = Json.parse("""
        {
         "id": 1,
         "name": "Joe",
         "tags": ["programmer", "husband", "father", "golfer"],
         "address": [
            {"id": 2, "street": "123 Main St.", "city": "Springfield", "state": "PA"},
            {"id": 3, "street": "456 Main St.", "city": "Sea Isle City", "state": "NJ", "beach": true}
         ]
        }
      """)

      println(JSONPath.query("$.id", json))
      println(JSONPath.query("$..id", json))
      println(JSONPath.query("$.address[*].city", json))
      println(JSONPath.query("$.address[?(@.beach)]", json))
    }
  }
}