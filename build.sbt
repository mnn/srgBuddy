import play.Project._

name := "srgbuddy"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)     

play.Project.playScalaSettings

lazy val srgbuddy = project.in(file("."))
    .aggregate(mcMapperLib)
    .dependsOn(mcMapperLib)

lazy val mcMapperLib = project
