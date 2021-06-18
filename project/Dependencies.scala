import sbt._

object Dependencies {
  object Versions {
    val scala212 = "2.12.14"
    val scala213 = "2.13.6"

    val trace4cats = "0.12.0-RC1+156-7ea07b63"

    val circe = "0.14.1"
    val http4s = "0.23.0-RC1"
    val logback = "1.2.3"
  }

  lazy val trace4catsKernel = "io.janstenpickle"  %% "trace4cats-kernel"  % Versions.trace4cats
  lazy val trace4catsTestkit = "io.janstenpickle" %% "trace4cats-testkit" % Versions.trace4cats

  lazy val circeGeneric = "io.circe"        %% "circe-generic-extras" % Versions.circe
  lazy val http4sCirce = "org.http4s"       %% "http4s-circe"         % Versions.http4s
  lazy val http4sBlazeClient = "org.http4s" %% "http4s-blaze-client"  % Versions.http4s
  lazy val logback = "ch.qos.logback"        % "logback-classic"      % Versions.logback
}