name := "hello"

version := "1.0.0"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-deprecation", "-feature")

// scalacOptions += "-Ylog-classpath"

resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/maven-releases/"

libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.4"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

libraryDependencies += "junit" % "junit" % "4.12" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.2" % "test"

libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

testOptions in Test += Tests.Argument("-oF")

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")
