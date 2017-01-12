package sbthello

import sbt._
import Keys._
import sbt.complete.Parsers._

object HelloPlugin extends AutoPlugin {

  override def trigger = allRequirements

  object autoImport {
    lazy val apiTest = inputKey[Unit]("Runs the API integration test suite")
  }

  import autoImport._

  override def projectSettings = Seq(
    apiTestSetting
  )

  /** Allows the RSS command to take string arguments. */
  private val argsParser = (Space ~> StringBasic).*

  def apiTestSetting: Setting[_] = apiTest := {
    val args = argsParser.parsed
    val log = streams.value.log
    log.debug(s"args = $args")

  }

}