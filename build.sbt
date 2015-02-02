import play.PlayScala
import NativePackagerKeys._

scalaVersion := "2.11.1"

name := "sample-play-app"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  cache,
  filters,
  "com.google.inject" % "guice" % "3.0",
  "javax.inject" % "javax.inject" % "1",
  "org.reactivemongo" %% "play2-reactivemongo" % "0.10.5.0.akka23",
  "org.webjars" % "bootstrap" % "2.3.1",
  "org.webjars" % "requirejs" % "2.1.11-1",
  "org.mockito" % "mockito-core" % "1.9.5" % "test")

dockerExposedPorts in Docker := Seq(9000)

maintainer := "krenzaslv"