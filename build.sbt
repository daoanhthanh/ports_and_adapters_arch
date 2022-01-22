import Dependencies._

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "vn.flinters.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "vn.flinters.binders._"

lazy val commonSettings = Seq(
   libraryDependencies ++= Seq(
      guice, //
      specs2 % Test,
      jdbc,
      _playTest // TODO should in root or in all?
   ),
   Test / parallelExecution := true,
   Test / fork := true,
   Compile / scalaSource := baseDirectory.value / "src" / "main" / "scala",
   //    Compile / TwirlKeys.compileTemplates / sourceDirectories := (Compile / unmanagedSourceDirectories).value,
   Test / scalaSource := baseDirectory.value / "src" / "test" / "scala",
   Compile / resourceDirectory := baseDirectory.value / "src" / "main" / "resources",
   Test / resourceDirectory := baseDirectory.value / "src" / "test" / "resources",
   resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/",
   name := "thanh_da_long_nt_bbs_BE",
   organization := "vn.flinters",
   version := "0.0.1-M1",
   scalaVersion := "2.13.8"


)

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .aggregate(application, domain, port, utilities)
  .dependsOn(application, domain, port, utilities)
  .settings(
     commonSettings,
     Global / onChangedBuildSource := ReloadOnSourceChanges,
     PlayKeys.playDefaultPort := 9090,
     PlayKeys.fileWatchService := play.dev.filewatch.FileWatchService.jdk7(play.sbt.run.toLoggerProxy(sLog.value))
  
  
  )


lazy val application = (project in file("./app/application"))
  .enablePlugins(PlayScala)
  .dependsOn(domain, utilities)
  .settings(
     commonSettings
  )


lazy val domain = (project in file("./app/domain"))
  .enablePlugins(PlayScala)
  .dependsOn(utilities)
  .settings(commonSettings)

lazy val port = (project in file("./app/port"))
  .enablePlugins(PlayScala)
  .aggregate(portWebService, portDatabase, portHttp)
  .dependsOn(portWebService, portDatabase, portHttp)
  .settings(commonSettings)

lazy val portWebService = (project in file("./app/port/primary/webService"))
  .enablePlugins(PlayScala)
  .dependsOn(application, utilities)
  .settings(
     commonSettings,
     name := "webService",
     libraryDependencies ++= Seq(
        jsonFormatter
     )
  )

lazy val portDatabase = (project in file("./app/port/secondary/database"))
  .enablePlugins(PlayScala)
  //  .enablePlugins(ScalikejdbcPlugin)
  .dependsOn(utilities, application)
  .settings(
     commonSettings,
     libraryDependencies ++= portDatabaseDependencies
  )

lazy val portHttp = (project in file("./app/port/secondary/http"))
  .enablePlugins(PlayScala)
  .dependsOn(utilities, application)
  .settings(commonSettings)


lazy val utilities = (project in file("./app/utilities"))
  .enablePlugins(PlayScala)
  .settings(
     commonSettings
  )
