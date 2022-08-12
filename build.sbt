scalaVersion := "3.1.3"
organization := "trival.xyz"
name := "trival-image-viewer"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % "2.0.0",
  "com.github.ghostdogpr" %% "caliban" % "2.0.1",
  "com.github.ghostdogpr" %% "caliban-zio-http" % "2.0.1",
  "io.d11" %% "zhttp" % "2.0.0-RC10"
)

// scalacOptions ++= {
//   Seq("-explain")
// }
