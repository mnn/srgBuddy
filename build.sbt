import play.Project._

name := "srgbuddy"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)     

libraryDependencies += "net.sf.opencsv" % "opencsv" % "2.3"

play.Project.playScalaSettings

lazy val srgbuddy = project.in(file("."))

lazy val mcMapperLib = project
