package json

case class JsNumber(i: Number)
case class JsBigDecimal(i: BigDecimal)

object JsNumberPerfTest extends App {

  val count = 1000000

  //=============================================
  //JsNumber
  //=============================================
  //With Int, 42ms
  {
    var i: Int = 0
    var start = System.currentTimeMillis
    i = 0
    while (i < count) { JsNumber(i); i += 1 }
    println(System.currentTimeMillis - start)
  }

  //With Double, 22ms
  {
    var i: Double = 0
    var start = System.currentTimeMillis
    i = 0
    while (i < count) { JsNumber(i); i += 1 }
    println(System.currentTimeMillis - start)
  }

  //With Long, 22ms
  {
    var i: Long = 0
    var start = System.currentTimeMillis
    i = 0
    while (i < count) { JsNumber(i); i += 1 }
    println(System.currentTimeMillis - start)
  }

  //=============================================
  //JsBigDecimal
  //=============================================

  //With Int, 73ms
  {
    var i: Int = 0
    var start = System.currentTimeMillis
    i = 0
    while (i < count) { JsBigDecimal(i); i += 1 }
    println(System.currentTimeMillis - start)
  }

  //With Double, 270ms
  {
    var i: Double = 0
    var start = System.currentTimeMillis
    i = 0
    while (i < count) { JsBigDecimal(i); i += 1 }
    println(System.currentTimeMillis - start)
  }

  //With Long, 41ms
  {
    var i: Long = 0
    var start = System.currentTimeMillis
    i = 0
    while (i < count) { JsBigDecimal(i); i += 1 }
    println(System.currentTimeMillis - start)
  }

}