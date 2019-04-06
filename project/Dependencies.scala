import sbt._

object Dependencies {

  val config         = "com.typesafe"               % "config"               % Version.config
  val pureConfig     = "com.github.pureconfig"      %% "pureconfig"          % Version.pureConfig

  val scalaLogging   = "com.typesafe.scala-logging" %% "scala-logging"       % Version.scalaLogging
  val logbackClassic = "ch.qos.logback"             % "logback-classic"      % Version.logback

  val akkaActor      = "com.typesafe.akka"          %% "akka-actor"          % Version.akka
  val akkaHttp       = "com.typesafe.akka"          %% "akka-http"           % Version.akkaHttp

}

object Version {
  val scala        = "2.12.6"
  val akka         = "2.5.14"
  val akkaHttp     = "10.1.1"
  val logback      = "1.2.3"
  val scalaLogging = "3.7.2"
  val config       = "1.3.1"
  val pureConfig   = "0.9.1"
}
