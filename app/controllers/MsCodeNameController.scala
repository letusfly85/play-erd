package controllers

import play.api.mvc.{Action, Controller}
import play.api.data._
import play.api.data.Forms._
import models.MsCodeName

object MsCodeNameController extends Controller{

  def ms_code_names = Action {
    Ok(views.html.ms_code_names(MsCodeName.list))
  }

}
