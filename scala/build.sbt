name := "scala-sandbox"

version := "1.0"

scalaVersion := "2.10.4"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.10.4",
  "org.scala-lang" % "scala-swing" % "2.10.4",
  "com.sksamuel.elastic4s" % "elastic4s_2.11" % "1.3.4",
  "com.typesafe.play" %% "play-json" % "2.3.4",
  "org.specs2" %% "specs2-core" % "2.4.9" % "test"
)