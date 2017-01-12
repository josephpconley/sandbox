package controllers

import _root_.models.Foo
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.i18n.Messages
import play.api.libs.iteratee.{Enumeratee, Enumerator, Iteratee}
import scala.concurrent.Future

import play.api.libs.concurrent.Execution
import Execution.Implicits._
import java.io.File
import play.api.db.DB

object Application extends Controller {

  def building = Action {
    Ok(Json.obj("id" -> 1))
  }

  def index = Action {
    Ok(views.html.index(Some(Foo("Joe Conley", 1, 3.5))))
  }

  def test = Action{
    val json =
      """{
        |  "name": "Alice",
        |  "hobby": ["skiing", "biking", "joe"]
        |}
      """.stripMargin

    val hobbyReads = Reads.pattern("skiing|biking".r)

//    val reads = (
//      (__ \ "name").read[String] and
//      (__ \ "hobby").read(Reads.seq[String](hobbyReads))
//    ) tupled

//    val coastTransformer = (
//      (__ \ "name").json.pick[JsString] and
//      (__ \ "hobby").json.pick[JsArray]
//    ) join

//    reads.reads(Json.parse(json)).fold(
//      invalid => BadRequest(JsError.toFlatJson(invalid)),
//      valid => Ok("got it")
//    )

    val result = "TEST"
    Ok(result.toString)
  }

  def console = Action {

    //*
    /* CONSUMERS - Iteratee
     */


//    val consume: Iteratee[String,String] = {
//      Iteratee.fold[String,String]("") { (result, chunk) => result ++ chunk }
//    }
    val consume = Iteratee.consume[String]()

    val printlnIteratee = Iteratee.foreach[String](s => println(s))
//    printlnIteratee.run.onSuccess{case x => println("YO " + x)}

    /*
    * PRODUCERS - Enumerator
    */

    val enumerateUsers: Enumerator[String] = {
      Enumerator("Guillaume", "Sadek", "Peter", "Erwan")
    }

    val newIteratee: Future[Iteratee[String,String]] = enumerateUsers(consume)

    // We use flatMap since newIteratee is a promise,
    // and run itself return a promise
//    val eventuallyResult: Future[String] = newIteratee.flatMap(i => i.run)
    val eventuallyResult: Future[String] = Iteratee.flatten(newIteratee).run

    //Eventually print the result
//    eventuallyResult.onSuccess { case x => println(x) }

    val colors = Enumerator("Red","Blue","Green")
    val moreColors = Enumerator("Grey","Orange","Yellow")
    val combinedEnumerator = colors.andThen(moreColors)
    val eventuallyIteratee = combinedEnumerator(consume)
//    Iteratee.flatten(eventuallyIteratee).run.onSuccess{case x => println(x)}


    val fileEnumerator: Enumerator[Array[Byte]] = Enumerator.fromFile(new File("logs/application.log"))
    val printFile = Iteratee.foreach[Array[Byte]](a => {
      println("****************NEW STRING*************")
      println(new String(a))
    })
//    Iteratee.flatten(fileEnumerator(printFile)).run

    /*
    * ADAPTER - Enumeratees
    */

    //adapt the iteratee
    val sum: Iteratee[Int,Int] = Iteratee.fold[Int,Int](0){ (s,e) => s + e }

    val strings: Enumerator[String] = Enumerator("1","2","3","4")

    //create am Enumeratee using the map method on Enumeratee
    val toInt: Enumeratee[String,Int] = Enumeratee.map[String]{ s => s.toInt }

    val adaptedIteratee: Iteratee[String,Int] = toInt.transform(sum)

//    Iteratee.flatten(strings(adaptedIteratee)).run.onSuccess{case x => println(x)}

    //can also adapt the Enumerator
    val adaptedEnumerator: Enumerator[Int] = strings.through(toInt)
//    Iteratee.flatten(adaptedEnumerator(sum)).run.onSuccess{case x => println(x)}


    val numbers = Enumerator(1,2,3,4,5,6,7,8,9,10)
    val onlyOdds = Enumeratee.filter[Int](i => i % 2 != 0)
//    Iteratee.flatten(numbers.through(onlyOdds)(sum)).run.onSuccess{case x => println(x)}



    Ok
  }

  val screenshots =
    """
      |{
      |  "screenshots": [
      |    {
      |      "browser": "chrome",
      |      "yo": "pending",
      |      "url": "http://localhost/mirror/52ea1b22e4b0e133507b209b",
      |      "browser_version": "26.0",
      |      "os_version": "7",
      |      "id": "92342eed1fd14c354d9365cbbd3e35ea1fc45df2",
      |      "os": "Windows"
      |    }
      |  ],
      |  "wait_time": 5,
      |  "callback_url": "http://localhost/screenshot/accept/52ea1b22e4b0e133507b209b",
      |  "quality": "compressed",
      |  "job_id": "ce991c0c3d140b5a78859b28cf391fd99c63ff98",
      |  "win_res": "1024x768",
      |  "orientation": "portrait",
      |  "mac_res": "1024x768"
      |}
    """.stripMargin

  case class JobInfo(job_id: String, state: String)

  object JobInfo {
//    implicit val fmt = Json.format[JobInfo]

    implicit val jobReads: Reads[JobInfo] = (
      (__ \ "job_id").read[String] and
      ((__ \ "screenshots")(0) \ "state").read[String]
    )(JobInfo.apply _)
  }

  def json = Action { implicit req =>
//    val jobInfo = Json.parse(screenshots).as[JobInfo]
//    println(jobInfo)

    Json.parse(screenshots).validate[JobInfo].fold(
      valid => {
        println(valid)
      },
      invalid => {
        println(invalid)
      }
    )
    Ok
  }

  import play.api.data._
  import play.api.data.Forms._

  case class ExerciseName(exerciseName:String)
  case class WorkoutSet(exerciseName:ExerciseName, number:Int)
  case class WorkoutSets(sets:List[WorkoutSet])

  val setsForm:Form[WorkoutSets] = Form(
    mapping(
      "sets" -> list(
        mapping(
          "exerciseName" -> mapping("exerciseName" -> nonEmptyText)(ExerciseName.apply)(ExerciseName.unapply),
          "workoutSet" -> number(min=1,max=20)
        )(WorkoutSet.apply)(WorkoutSet.unapply)
      )
    )(WorkoutSets.apply)(WorkoutSets.unapply)
  )
}