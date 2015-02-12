package models

import play.api.libs.json.Writes
import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * User: jconley
 * Date: 12/4/13
 */
case class Foo(a: String, b: Int, c: Double)

case class Bar(name:String, id:Int = 0)

object Bar{
  def applyOpt(name: String, id: Option[Int]): Bar = id.map(i => Bar(name, i)).getOrElse(Bar(name))
}

case class Item(id:Option[Int], name:String, description:Option[String], count:Int=0)

object Item{
  def applyOpt(id:Option[Int], name:String, description:Option[String], count:Option[Int]): Item = count.map{c =>
    Item(id, name, description, c)
  }.getOrElse{
    Item(id, name, description)
  }

  implicit val reads: Reads[Item] = (
    (__ \ "id").readNullable[Int] and
    (__ \ "name").read[String] and
    (__ \ "description").readNullable[String] and
    (__ \ "count").readNullable[Int]
  )(Item.applyOpt _)
}

case class Hobby(id: String, name: String)
case class PersonWithHobby(id: String, name: String, hobby: Hobby)

object PersonWithHobby{
  implicit val personFormat: Format[PersonWithHobby] = (
    (__ \ "id").format[String] and
    (__ \ "name").format[String] and
      (
        (__ \ "hobbyId").format[String] and
        (__ \ "hobbyName").format[String]
      )(Hobby.apply, unlift(Hobby.unapply))
  )(PersonWithHobby.apply, unlift(PersonWithHobby.unapply))
}
