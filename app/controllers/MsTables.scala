package controllers

import play.api.mvc.{Action, Controller}

object MsTables extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

}
