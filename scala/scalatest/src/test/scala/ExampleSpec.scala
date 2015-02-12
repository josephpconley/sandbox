import org.scalatest._

class ExampleSpec extends FlatSpec with Matchers {

  "test" should "test" in {
    2 + 2 should be (4)
  }
}