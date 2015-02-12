import macros.DebugMacros._
import org.specs2.mutable._

class MacroSpec extends Specification {

  "MacroSpec" should {

    "test" in {
      val me = "joe"
      hello("joe")

      1 + 1 must beEqualTo(2)
    }
  }
}
