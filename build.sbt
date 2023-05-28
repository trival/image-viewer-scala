val scalaVer = "3.3.0-RC6"

val zioVersion = "2.0.13"
val calibanVersion = "2.2.1"

ThisBuild / scalaVersion := scalaVer
ThisBuild / organization := "trival.xyz"

lazy val localBackend = project
  .in(file("local-backend"))
  .enablePlugins(RevolverPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % zioVersion,
      "dev.zio" %% "zio-json" % "0.5.0",
      "dev.zio" %% "zio-http" % "3.0.0-RC1",
      "com.github.ghostdogpr" %% "caliban" % calibanVersion,
      "com.github.ghostdogpr" %% "caliban-zio-http" % calibanVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-json-zio" % "1.2.11", // ZIO JSON for Caliban
      "com.lihaoyi" %% "os-lib" % "0.9.1",
      "com.lihaoyi" %% "utest" % "0.8.1" % Test,
      "dev.zio" %% "zio-test" % zioVersion % Test,
      "dev.zio" %% "zio-test-sbt" % zioVersion % Test,
      "dev.zio" %% "zio-test-magnolia" % zioVersion % Test,
    ),
  )
  .settings(testFrameworks += new TestFramework("utest.runner.Framework"))
  .settings(
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
  )
