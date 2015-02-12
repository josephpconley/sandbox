package models

/**
 * User: joe
 * Date: 12/30/13
 */

import scala.slick.driver.H2Driver.simple._

case class Product(code: String, path: String, color: String)

class ProductTable(tag: Tag) extends Table[Product](tag, "PRODUCTS") {
  def code = column[String]("CODE", O.PrimaryKey)
  def path = column[String]("PATH")
  def color = column[String]("COLOR", O.DBType("VARCHAR AS SUBSTR(PATH, LENGTH(PATH) - 3)"))
  def * = (code, path, color) <> (Product.tupled, Product.unapply)
}
