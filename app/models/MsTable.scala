package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._
import java.sql.Connection

import java.math.BigDecimal

case class MsTable(tableId: BigDecimal, physicalTableName: String, revision: BigDecimal)

object MsTable {

  val ms_table = {
    get[BigDecimal]("TABLE_ID") ~
    get[String]("PHYSICAL_TABLE_NAME") ~
    get[BigDecimal]("REVISION") map  {
      case tableId ~ physicalTableName ~ revision => MsTable(tableId, physicalTableName, revision)
    }
  }

  def list: List[MsTable] = {
    DB.withConnection { implicit conn: Connection =>
      SQL("SELECT TABLE_ID, PHYSICAL_TABLE_NAME, REVISION FROM MS_TABLES").as(ms_table *)
    }
  }

  def find(physicalTableName: String): List[MsTable] ={
    println(physicalTableName)

    val result: List[MsTable] = DB.withConnection { implicit conn: Connection =>
      SQL("SELECT TABLE_ID, PHYSICAL_TABLE_NAME, REVISION FROM MS_TABLES WHERE PHYSICAL_TABLE_NAME = {physicalTableName}")
        .on("physicalTableName" -> physicalTableName).as(ms_table *)
    }

    if (!result.isEmpty) println(result.head.physicalTableName)

    result
  }
}