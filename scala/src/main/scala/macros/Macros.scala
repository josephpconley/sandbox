package macros

import language.experimental.macros

import reflect.macros.Context
import scala.reflect.ClassTag

object Macros {
//  def hello(param: Any): Unit = macro hello_impl
//
//  def hello_impl(c: Context)(param: c.Expr[Any]): c.Expr[Unit] = {
//    import c.universe._
//    val paramRep = show(param.tree)
//    val paramRepTree = Literal(Constant(paramRep))
//    val paramRepExpr = c.Expr[String](paramRepTree)
//    reify { println(paramRepExpr.splice + " = " + param.splice) }
//  }
//
//  val EmailRegex = """^.*@.*(\.[a-z]{2,3})$""".r
//  def checkEmail(address: String) = EmailRegex.pattern.matcher(address).matches
//
//  def email(address: String): String = macro emailImpl
//
//  def emailImpl(c: Context)(address: c.Expr[String]): c.Expr[String] = {
//    import c.universe._
//
//    address.tree match {
//      case Literal(Constant(text: String)) =>
//        if (checkEmail(text)) {
//          address
//        } else {
//          c.abort(c.enclosingPosition, s"Invalid email: $text")
//        }
//      case _ => address // Not a literal, can't validate at compile time
//    }
//  }
//
//  def defaultMap[A] = macro defaultMapImpl[A]
//
//  def defaultMapImpl[A: c.WeakTypeTag](c: Context): c.Expr[String] = {
//    import c.universe._
//
//    val companioned = weakTypeOf[A].typeSymbol
//    val companionSymbol = companioned.companionSymbol
//    val companionType = companionSymbol.typeSignature
//
//    val unapply = companionType.declaration(stringToTermName("unapply"))
//    val unapplySeq = companionType.declaration(stringToTermName("unapplySeq"))
//
//    val effectiveUnapply = Seq(unapply, unapplySeq).filter(_ != NoSymbol).headOption match {
//      case None => c.abort(c.enclosingPosition, "No unapply or unapplySeq function found")
//      case Some(s) => s.asMethod
//    }
//
//    val applies =
//      companionType.declaration(stringToTermName("apply")) match {
//        case NoSymbol => c.abort(c.enclosingPosition, "No apply function found")
//        case s => s.asMethod.alternatives
//      }
//
//    val name = "apply"
//    def valueFor(p: Symbol, i: Int): Option[Any] = {
//      val defarg = companionType member newTermName(s"$name$$default$$${i+1}")
//      if (defarg != NoSymbol) {
//
//        val obj = Select(Ident(companionSymbol), newTermName(s"$name$$default$$${i+1}"))
//        val res = c.eval(c.Expr(obj))
//        Some(res)
//
//      } else {
//        None
//      }
//    }
//
//    val dMap = applies.head.asMethod.paramss.head.zipWithIndex map { p =>
//      p._1.name.toString -> valueFor(p._1, p._2)
//    } filter { f =>
//      f._2.isDefined
//    } map { p =>
//      p._1 + "=" + p._2.get
//    } mkString("&")
//
//    c.Expr[String](Literal(Constant(dMap)))
//  }
}