package slick

import slick.driver.PostgresDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global

object SlickApp extends App {
  val db = Database.forConfig("pg")
  try {
    val query = TableQuery[Test]
    db.run(query.result).map { rows =>
      rows foreach println
    }
  } finally {
    db.close
  }
}

class Test(tag: Tag) extends Table[(Int, String)](tag, "test") {
  def id = column[Int]("id", O.PrimaryKey) // This is the primary key column
  def name = column[String]("name")
  def * = (id, name)
}

