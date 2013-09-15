package controllers

import play.api._
import play.api.mvc._
import models.MsTable

import play.api.data._
import play.api.data.Forms._

object Application extends Controller {

  /*
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  */

  def index = Action {
    Redirect(routes.Application.ms_tables)
  }

  val msTableForm = Form(
    "physicalTableName" -> nonEmptyText
  )

  def ms_tables = Action {
    Ok(views.html.ms_tables(MsTable.list, msTableForm))
  }

  def new_ms_tables =  Action { implicit request =>
    msTableForm.bindFromRequest.fold(
      errors => BadRequest(views.html.ms_tables(MsTable.list, errors)),
      physicalTableName => {
        MsTable.create(physicalTableName)
        Redirect(routes.Application.ms_tables)
      }
    )
  }

  def tasks     = TODO
  
}