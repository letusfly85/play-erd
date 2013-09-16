package utils

import com.typesafe.config.ConfigFactory

object AppProperties {

  val config = ConfigFactory.load()
  val issuesUrl: String = config.getString("issues.url")

}
