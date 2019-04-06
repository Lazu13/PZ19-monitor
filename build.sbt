version := "0.1"

scalaVersion := "2.12.8"

lazy val nameSettings = Seq(
  organization := "agh",
  name := "monitor",
  version := "0.1.0",
  scalaVersion := Version.scala,
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding",
    "UTF-8",
    "-feature",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xlint",
    "-Yno-adapted-args",
    "-Ypartial-unification",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-unused",
    "-Ywarn-value-discard"
  )
)

lazy val dockerSettings = Seq(
  dockerImageCreationTask := docker.value,
  dockerfile in docker := {
    val artifact: File = assembly.value
    val artifactTargetPath = s"/app/${artifact.name}"

    new Dockerfile {
      from("openjdk:8-jre")
      add(artifact, artifactTargetPath)
      entryPoint("java", "-jar", artifactTargetPath)
    }
  },
  imageNames in docker := Seq(
    ImageName(
      namespace = Some(organization.value),
      repository = name.value,
      tag = Some(version.value)
    )
  ),
  buildOptions in docker := BuildOptions(
    cache = false,
    removeIntermediateContainers = BuildOptions.Remove.Always,
    pullBaseImage = BuildOptions.Pull.Always
  ),
)

lazy val assemblySettings = Seq(
  mainClass in assembly := Some("agh.Application"),
  assemblyJarName in assembly := "monitor.jar",
)

lazy val backend = (project in file("."))
  .enablePlugins(DockerPlugin)
  .enablePlugins(DockerComposePlugin)
  .settings(
    nameSettings,
    assemblySettings,
    dockerSettings,
    libraryDependencies ++= Seq(
      Dependencies.config,
      Dependencies.pureConfig,
      Dependencies.scalaLogging,
      Dependencies.logbackClassic,
      Dependencies.akkaActor,
      Dependencies.akkaHttp
    )
  )
