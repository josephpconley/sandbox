/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle27 extends App{
  def objFromJava: Object = "string"
  def stringFromJava: String = null
  def printLengthIfString(a: AnyRef): Unit = a match {
    case str: String => println(s"String of length ${str.length}")
    case _ => println("Not a string")
  }
  printLengthIfString(objFromJava)
  printLengthIfString(stringFromJava)

  /*
  Pattern matching is done at RUNTIME
  At runtime, the javaString is of type Null

  Whenever dealing with a result of some Java method call, it
  is an imperative to wrap the result into an Option before
  using it the first time.
   */
}
