lazy val commonSettings = Seq(
  Compile / compile / javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
  libraryDependencies ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, _)) =>
        Seq(compilerPlugin(Dependencies.kindProjector), compilerPlugin(Dependencies.betterMonadicFor))
      case _ => Seq.empty
    }
  },
  scalacOptions := {
    val opts = scalacOptions.value
    val wconf = "-Wconf:any:wv"
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, _)) => opts :+ wconf
      case _ => opts
    }
  },
  Test / fork := true,
  resolvers += Resolver.sonatypeRepo("releases"),
)

lazy val noPublishSettings =
  commonSettings ++ Seq(publish := {}, publishArtifact := false, publishTo := None, publish / skip := true)

lazy val publishSettings = commonSettings ++ Seq(
  publishMavenStyle := true,
  pomIncludeRepository := { _ =>
    false
  },
  Test / publishArtifact := false
)

lazy val root = (project in file("."))
  .settings(noPublishSettings)
  .settings(name := "Trace4Cats Jaeger Integration Test")
  .aggregate(`jaeger-integration-test`)

lazy val `jaeger-integration-test` =
  (project in file("modules/jaeger-integration-test"))
    .settings(publishSettings)
    .settings(
      name := "trace4cats-jaeger-integration-test",
      libraryDependencies ++= Seq(
        Dependencies.trace4catsKernel,
        Dependencies.trace4catsTestkit,
        Dependencies.http4sCirce,
        Dependencies.circeGeneric,
        Dependencies.http4sCirce,
        Dependencies.http4sBlazeClient,
        Dependencies.logback
      )
    )
