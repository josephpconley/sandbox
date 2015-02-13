package scalapuzzlers

import _root_.scala.concurrent.duration.Duration
import scala.concurrent.duration.Duration

/**
 * User: jconley
 * Date: 6/12/2014
 */
object Puzzle30 extends App{
  import reflect._
  import concurrent._
  import ExecutionContext.Implicits._

  class Expect[A: ClassTag](msgTemplate: String, f: Future[A]) {
    def announce() = f onSuccess {
      case result: A => println(msgTemplate format result)
      case _ => println("Future ended with unexpected result.")
    }
  }

  val one = future("boy")
  val two = future(1 + 1)

  new Expect("It's a %s!", one).announce()
  new Expect("1 + 1 = %d", two).announce()

  Await.ready(one, Duration.Inf)
  Await.ready(two, Duration.Inf)

  /*
  Namely, the future method, defined in the concurrent package object, is just a convenience method for the factory
  method of class Future, which has the following signature:
    def apply[T](body: => T)(implicit execctx: ExecutionContext): Future[T]

  Note that the body, which denotes the asynchronous computation, is a byname
  parameter. Behind the scenes, to perform lazy evaluation, the compiler
  passes such parameters as Function0 objects.

  you can observe that the runtime type of future(1 + 1) is not Int, but its boxed version, java.lang.Integer
  since that expression is of type java.lang.Integer and type parameter A is inferred as Int.
   */
}
