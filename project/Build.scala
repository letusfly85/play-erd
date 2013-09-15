import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-erd"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    jdbc,
    anorm
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
    scalaVersion := "2.10.0"
  )//.dependsOn(RootProject( uri("git://github.com/freekh/play-slick.git") ))

}
