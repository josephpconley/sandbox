package parser

import org.parboiled.scala._
import org.parboiled.errors.{ErrorUtils, ParsingException}

/**
 * <p>A complete JSON parser producing an AST representation of the parsed JSON source.</p>
 * <p>The syntactical grammar is identical to the JsonParser0 example, however this parser adds the parser
 * actions required to build an AST during the parsing run.</p>
 * <p>You might also want to look at the functionally equivalent JsonParser2 class in this example. It makes
 * most of the type information explicit that is left out in JsonParser1 and can make the parsers workings
 * easier to understand for first-time users </p>
 */
class JsonParser extends Parser {

  /**
   * These case classes form the nodes of the AST.
   */
  sealed abstract class AstNode{
    def render: String
  }
  case class ObjectNode(members: List[MemberNode]) extends AstNode{
    def render = "{ " + members.map(_.render).mkString(",\n") + "}"
  }
  case class MemberNode(key: String, value: AstNode) extends AstNode {
    def render = StringNode(key).render + ": " + value.render
  }
  case class ArrayNode(elements: List[AstNode]) extends AstNode {
    def render = "[" + elements.map(_.render).mkString(",\n") + "]"
  }
  case class StringNode(text: String) extends AstNode {
    def render = "\"" + text + "\""
  }
  case class NumberNode(value: BigDecimal) extends AstNode {
    def render = value.toString()
  }
  case class TmplNode(tmpl: String) extends AstNode {
    def render = s"{{$tmpl}}"
  }
  case object True extends AstNode {
    def render = "true"
  }
  case object False extends AstNode {
    def render = "false"
  }
  case object Null extends AstNode {
    def render = "null"
  }

  // the root rule
  def Json = rule { WhiteSpace ~ (JsonObject | JsonArray) ~ EOI }

  def JsonObject: Rule1[ObjectNode] = rule {
    "{ " ~ zeroOrMore(Pair, separator = ", ") ~ "} " ~~> ObjectNode
  }

  def Pair = rule {
    JsonString ~~> (_.text) ~ ": " ~ Value ~~> MemberNode
  }

  def Value: Rule1[AstNode] = rule {
    JsonTmpl | JsonString | JsonNumber | JsonObject | JsonArray | JsonTrue | JsonFalse | JsonNull
  }

  def JsonTmpl = rule { "{{" ~ oneOrMore(TmplChar) ~> TmplNode ~ "}} " }

  def JsonString = rule { "\"" ~ zeroOrMore(Character) ~> StringNode ~ "\" " }

  def JsonNumber = rule {
    group(Integer ~ optional(Frac ~ optional(Exp))) ~> (s => NumberNode(BigDecimal(s))) ~ WhiteSpace
  }

  def JsonArray = rule { "[ " ~ zeroOrMore(Value, separator = ", ") ~ "] " ~~> ArrayNode }

  def Character = rule { EscapedChar | NormalChar }

  def EscapedChar = rule { "\\" ~ (anyOf("\"\\/bfnrt") | Unicode) }

  def NormalChar = rule { !anyOf("\"\\") ~ ANY }

  def TmplChar = rule { !anyOf("\"\\{}") ~ ANY}

  def Unicode = rule { "u" ~ HexDigit ~ HexDigit ~ HexDigit ~ HexDigit }

  def Integer = rule { optional("-") ~ (("1" - "9") ~ Digits | Digit) }

  def Digits = rule { oneOrMore(Digit) }

  def Digit = rule { "0" - "9" }

  def HexDigit = rule { "0" - "9" | "a" - "f" | "A" - "Z" }

  def Frac = rule { "." ~ Digits }

  def Exp = rule { ignoreCase("e") ~ optional(anyOf("+-")) ~ Digits }

  def JsonTrue = rule { "true " ~ push(True) }

  def JsonFalse = rule { "false " ~ push(False) }

  def JsonNull = rule { "null " ~ push(Null) }

  def WhiteSpace: Rule0 = rule { zeroOrMore(anyOf(" \n\r\t\f")) }

  /**
   * We redefine the default string-to-rule conversion to also match trailing whitespace if the string ends with
   * a blank, this keeps the rules free from most whitespace matching clutter
   */
  override implicit def toRule(string: String) =
    if (string.endsWith(" "))
      str(string.trim) ~ WhiteSpace
    else
      str(string)

  /**
   * The main parsing method. Uses a ReportingParseRunner (which only reports the first error) for simplicity.
   */
  def parseJson(json: String): AstNode = {
    val parsingResult = ReportingParseRunner(Json).run(json)
    parsingResult.result match {
      case Some(astRoot) => astRoot
      case None => throw new ParsingException("Invalid JSON source:\n" +
          ErrorUtils.printParseErrors(parsingResult))
    }
  }

}

object JSON extends App {

  val parser = new JsonParser
  val res = parser.parseJson(
    """
      |{
      |   "id": 1,
      |   "name": "Joe",
      |   "addressId": {{test}}
      |}
    """.stripMargin)

  println(res.render)
}
