package controllers

import play.api.mvc.{Action, Controller}

import play.api.data._
import play.api.data.Forms._

object MsTablesController extends Controller {

  case class MsTables(name: String, mail: Option[String], age: Int)

  val msTablesForm = Form(
    mapping(
      "name" -> text,
      "mail" -> optional(email),
      "age"  -> number
    )(MsTables.apply)(MsTables.unapply)
  )

  def index = Action {
    Ok(<p>Hello World!</p>).as(HTML)


  }

  def test1 = Action {
    BadRequest("Bad Request!")
  }

  def test2 = {
    Redirect("http://www.google.com")
  }

  def hello(name: String) = Action { request =>
    val params: Map[String, Seq[String]] = request.queryString
    val name = params("name").head
    println(name)

    Ok(<h1>Hello {name}!</h1>).as(HTML)
  }


  def register = Action { implicit request =>
    msTablesForm.bindFormRequest.fold(
      errors => BadRequest(views.html.form(errors)),

      msTables => {
        Ok(views.html.result(msTables))
      }
    )
  }

}
