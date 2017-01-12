name := "scala-sandbox"

version := "1.0"

val ver = "2.11.7"

scalaVersion := ver

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % ver,
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "com.sksamuel.elastic4s" % "elastic4s_2.11" % "1.3.4",
  "org.slf4j" % "slf4j-api" % "1.6.2",
  "org.slf4j" % "slf4j-simple" % "1.6.2",
  "org.tpolecat" %% "doobie-core" % "0.2.3",
  "com.typesafe.play" %% "play-json" % "2.3.4",
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "org.apache.httpcomponents" % "httpclient" % "4.3.6",
  "org.scalactic" %% "scalactic" % "2.2.4",
  "org.specs2" %% "specs2-core" % "2.4.9" % "test",
  "org.parboiled" %% "parboiled-scala" % "1.1.7"
)