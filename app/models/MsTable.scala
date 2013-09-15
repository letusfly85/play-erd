package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._
import java.sql.Connection

import java.math.BigDecimal

case class MsTable(tableId: BigDecimal, logicalTableName: String, physicalTableName: String, tableComment: String, revision: BigDecimal, ticketNumber: BigDecimal)

object MsTable {

  val ms_table = {
    get[BigDecimal]("TABLE_ID") ~
    get[String]("LOGICAL_TABLE_NAME") ~
    get[String]("PHYSICAL_TABLE_NAME") ~
    get[Option[String]]("TABLE_COMMENT") ~
    get[BigDecimal]("REVISION") ~
    get[BigDecimal]("TICKET_NUMBER") map  {
      case tableId ~ logicalTableName ~ physicalTableName ~ Some(tableComment) ~ revision ~ ticketNumber =>
        MsTable(tableId, logicalTableName, physicalTableName, tableComment, revision, ticketNumber)

      case tableId ~ logicalTableName ~ physicalTableName ~ None ~ revision ~ ticketNumber =>
        MsTable(tableId, logicalTableName, physicalTableName, "", revision, ticketNumber)
    }
  }

  def list: List[MsTable] = {
    DB.withConnection { implicit conn: Connection =>
      SQL("SELECT * FROM MS_TABLES").as(ms_table *)
    }
  }

  def find(physicalTableName: String): List[MsTable] ={
    println(physicalTableName)

    val result: List[MsTable] = DB.withConnection { implicit conn: Connection =>
      SQL("SELECT * FROM MS_TABLES WHERE PHYSICAL_TABLE_NAME = {physicalTableName}")
        .on("physicalTableName" -> physicalTableName).as(ms_table *)
    }

    if (!result.isEmpty) println(result.head.physicalTableName)

    result
  }
}