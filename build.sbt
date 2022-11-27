val scalaVer = "3.2.1"

val zioVersion = "2.0.4"
val calibanVersion = "2.0.1"

ThisBuild / scalaVersion := scalaVer
ThisBuild / organization := "trival.xyz"

lazy val localBackend = project
  .in(file("local-backend"))
  .enablePlugins(RevolverPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % zioVersion,
      "dev.zio" %% "zio-json" % "0.3.0",
      "io.d11" %% "zhttp" % "2.0.0-RC10",
      "com.github.ghostdogpr" %% "caliban" % calibanVersion,
      "com.github.ghostdogpr" %% "caliban-zio-http" % calibanVersion,
      "com.lihaoyi" %% "os-lib" % "0.8.1",
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
