package slick

import slick.backend.StaticDatabaseConfig
import slick.driver.PostgresDriver.api._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

@StaticDatabaseConfig("file:src/main/resources/application.conf#tsql")
object SlickApp extends App {
  val db = Database.forConfig("pg")
  try {
    val query = TableQuery[Test]
    val ops = DBIO.seq(
      query.result,
      tsql"insert into test (bad) values ('Joey')"
    )

    val res = Await.result(db.run(ops), Duration.Inf)
    println(res)
  } finally {
    db.close
  }

}

class Test(tag: Tag) extends Table[(Int, String)](tag, "test") {
  def id = column[Int]("id", O.PrimaryKey) // This is the primary key column
  def name = column[String]("name")
  def * = (id, name)
}

