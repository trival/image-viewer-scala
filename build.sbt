val scalaVer = "3.1.3"

val zioVersion = "2.0.2"
val calibanVersion = "2.0.1"

ThisBuild / scalaVersion := scalaVer
ThisBuild / organization := "trival.xyz"

lazy val backend = project
  .in(file("backend"))
  .enablePlugins(RevolverPlugin, CompileTimeCalibanServerPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % zioVersion,
      "com.github.ghostdogpr" %% "caliban" % calibanVersion,
      "com.github.ghostdogpr" %% "caliban-zio-http" % calibanVersion,
      "io.d11" %% "zhttp" % "2.0.0-RC10"
    )
  )
  .settings(
    Compile / ctCalibanServer / ctCalibanServerSettings ++=
      Seq(
        "xyz.trival.image_viewer.graphql.GraphQLApi.api" -> ClientGenerationSettings(
          packageName = "xyz.trival.image_viewer.graphql.generated",
          clientName = "ApiClient"
        )
      )
  )

lazy val frontend = project
  .in(file("frontend"))
  .enablePlugins(ScalaJSPlugin, CompileTimeCalibanClientPlugin)
  .disablePlugins(RevolverPlugin)
  .settings(scalaJSUseMainModuleInitializer := true)
  .settings(
    Compile / ctCalibanClient / ctCalibanClientsSettings := Seq(backend)
  )
