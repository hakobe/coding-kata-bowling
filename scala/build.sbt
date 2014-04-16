name := "bowling"

version := "0.0.1"

scalaVersion := "2.10.3"

libraryDependencies += "org.specs2" %% "specs2" % "2.3.11" % "test"

scalacOptions in Test ++= Seq("-Yrangepos")