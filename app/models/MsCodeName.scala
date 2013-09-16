package models

import play.api.db._
import play.api.Play.current

import anorm.SqlParser._
import anorm._
import java.sql.Connection
import anorm.~

case class MsCodeName(codeId: String, logicalCodeName: String, physicalCodeName: String, logicalTableName: String, physicalTableName: String)

object MsCodeName {

  val ms_code_name = {
    get[String]("PK_CODE_ID") ~
    get[Option[String]]("LOGICAL_CODE_NAME") ~
    get[Option[String]]("PHYSICAL_CODE_NAME") ~
    get[String]("LOGICAL_TABLE_NAME") ~
    get[String]("PHYSICAL_TABLE_NAME") map  {
      case codeId ~ logicalCodeName ~ physicalCodeName ~ logicalTableName ~ physicalTableName =>
        MsCodeName(codeId, logicalCodeName.getOrElse(""), physicalCodeName.getOrElse(""), logicalTableName, physicalTableName)
    }
  }

  def list: List[MsCodeName] = {
    DB.withConnection { implicit conn: Connection =>
      SQL("SELECT * FROM MS_CODE_NAMES").as(ms_code_name *)
    }
  }
}
