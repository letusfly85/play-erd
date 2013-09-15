package controllers

import play.api.mvc.{Action, Controller}
import models.MsTable
import play.api.data._
import play.api.data.Forms._

object MsTableController extends Controller {

  val msTableForm = Form(
    "physicalTableName" -> nonEmptyText
  )

  def ms_tables = Action {
    Ok(views.html.ms_tables(MsTable.list, msTableForm, result = List()))
  }

  def find_ms_table =  Action { implicit request =>
    msTableForm.bindFromRequest.fold(
      errors => BadRequest(views.html.ms_tables(MsTable.list, errors, List())),
      physicalTableName => {
        val result: List[MsTable] = MsTable.find(physicalTableName)
        Ok(views.html.ms_tables(MsTable.list, msTableForm, result))
      }
    )
  }
}
