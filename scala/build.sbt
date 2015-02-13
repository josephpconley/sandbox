name := "scala-sandbox"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.10.4",
  "org.scala-lang" % "scala-swing" % "2.10.4",
  "org.specs2" %% "specs2-core" % "2.4.9" % "test"
)