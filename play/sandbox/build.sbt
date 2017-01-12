name := "sandbox"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  cache,
  jdbc,
  anorm,
  "com.typesafe.play" % "play-slick_2.10" % "0.6.0.1",
  "com.typesafe.play" %% "play-slick" % "0.4.0",
  "com.josephpconley" %% "play-jsonpath" % "1.0",
  "com.wordnik"  % "swagger-play2_2.10" % "1.3.3"
)

play.Project.playScalaSettings