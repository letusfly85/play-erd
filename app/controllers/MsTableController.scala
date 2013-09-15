package controllers

import play.api.mvc.{Action, Controller}

object MsTableController extends Controller {

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

}
