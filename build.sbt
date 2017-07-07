name := "BowlingKata"

version := "1.0"

scalaVersion := "2.12.1"

val versions = new {
  val scalatest = "3.0.1"
}

libraryDependencies += "org.scalatest" %% "scalatest" % versions.scalatest % "test"