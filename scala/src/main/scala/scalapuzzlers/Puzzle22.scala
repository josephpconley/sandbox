package scalapuzzlers

import _root_.scala.collection

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle22 extends App{
  import collection.JavaConverters._

  def javaMap: java.util.Map[String, java.lang.Integer] = {
    val map = new java.util.HashMap[String, java.lang.Integer]()
    map.put("key", null)
    map
  }

  val map1 = javaMap.asScala
  val map2 = map1.asInstanceOf[collection.Map[String, Int]]

//  println(map2("key") == null)
//  println(map2("key") == 0)
}