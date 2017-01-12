name := "slick"

version := "1.0"

scalaVersion := "2.11.4"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.zaxxer" % "HikariCP-java6" % "2.3.8",
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "postgresql" % "postgresql" % "9.1-901.jdbc4"
)