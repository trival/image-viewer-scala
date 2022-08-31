scalaVersion := "3.1.3"
organization := "trival.xyz"
name := "trival-image-viewer"

val zioVersion = "2.0.2"
val calibanVersion = "2.0.1"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % zioVersion,
  "com.github.ghostdogpr" %% "caliban" % calibanVersion,
  "com.github.ghostdogpr" %% "caliban-zio-http" % calibanVersion,
  "io.d11" %% "zhttp" % "2.0.0-RC10"
)

// scalacOptions ++= {
//   Seq("-explain")
// }
