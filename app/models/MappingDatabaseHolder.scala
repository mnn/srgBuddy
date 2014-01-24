package models

import java.text.SimpleDateFormat
import tk.monnef.mcmapper.{McMapper, MappingDatabaseSearchable}
import tk.monnef.mcmapper.McMapper._
import java.util.Date

object MappingDatabaseHolder {

  private case class DatabaseHolder(db: MappingDatabaseSearchable, createdOn: String)

  private val dateFormatter = new SimpleDateFormat("k:mm:ss d. M. yyyy")

  private val innerDb: DatabaseHolder = {
    val a = McMapper.load("conf/mcp/811/")
    DatabaseHolder(MappingDatabaseSearchable(a), dateFormatter.format(new Date()))
  }

  def db: MappingDatabaseSearchable = innerDb.db

  def dbCreatedOn: String = innerDb.createdOn
}
