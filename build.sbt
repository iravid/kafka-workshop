import Dependencies._

ThisBuild / scalaVersion     := "2.12.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.iravid"
ThisBuild / organizationName := "kafka"

lazy val root = (project in file("."))
  .settings(
    name := "Kafka Workshop",
    libraryDependencies ++= Seq(
      "org.apache.kafka"                 % "kafka-clients"              % "2.3.1",
      "org.apache.kafka"                 % "kafka-streams"              % "2.3.1",
      "org.apache.kafka"                 %% "kafka-streams-scala"       % "2.3.1",
      "org.apache.kafka"                 % "kafka-streams-test-utils"   % "2.3.1",
      "io.circe"                         %% "circe-core"             % "0.12.3",
      "io.circe"                         %% "circe-parser"           % "0.12.3",
      "io.circe"                         %% "circe-generic"          % "0.12.3",
      "com.typesafe.akka"                %% "akka-stream"            % "2.5.26",
      "com.typesafe.akka"                %% "akka-stream-kafka"      % "1.0.4",
      "com.typesafe.akka"                %% "akka-http"              % "10.1.10",
      "de.heikoseeberger"                %% "akka-http-circe"        % "1.29.1",
      "io.prometheus"                    % "simpleclient"            % "0.6.0",
      "io.prometheus"                    % "simpleclient_hotspot"    % "0.6.0",
      "io.prometheus"                    % "simpleclient_httpserver" % "0.6.0",
      "org.apache.logging.log4j"         % "log4j-api"               % "2.11.0",
      "org.apache.logging.log4j"         % "log4j-core"              % "2.11.0",
      "org.apache.logging.log4j"         %% "log4j-api-scala"        % "11.0",
      "org.apache.logging.log4j"         % "log4j-slf4j-impl"        % "2.11.0"
    ),
    fork in run := true,
    cancelable in Global := false
  )
