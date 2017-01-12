package controllers

import play.api.mvc._

object PostController extends Controller {

  def form = Action { implicit req =>
    req.body.asFormUrlEncoded.foreach(println)
    Ok
  }

  def json = Action { implicit req =>
    req.body.asJson.foreach(println)
    Ok
  }
}