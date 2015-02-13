package scalapuzzlers

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle4 extends App{
  trait A {
    val foo: Int
    val bar = 10
    println("In A: foo:" + foo + ", bar:" + bar)
  }
  class B extends A {
    val foo: Int = 25
    println("In B: foo:" + foo + ", bar:" + bar)
  }
  class C extends B {
    override val bar = 99
    println("In C: foo:" + foo + ", bar: " + bar)
  }
  new C()

  /*
  1.  Superclasses get fully initialized before subclasses.
  2. Members are initialized in the order they are declared.
  3. When a val is overriden, it can still only be initialized once.
  4. Just like abstract val, an overridden val will have a default initial value during the construction of superclasses.
   */

  /*
  define as def (method) to avoid initialization issues
  lazy vals work the same way

   */
}
