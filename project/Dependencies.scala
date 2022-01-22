import sbt._

object Dependencies {
   // DB dependencies
   lazy val scalikejdbcVersion = "3.5.0"
   lazy val scalikejdbc = "org.scalikejdbc" %% "scalikejdbc" % scalikejdbcVersion
   lazy val scalikejdbcTest = "org.scalikejdbc" %% "scalikejdbc-test" % scalikejdbcVersion % Test
   lazy val scalikejdbcConfig = "org.scalikejdbc" %% "scalikejdbc-config" % scalikejdbcVersion
   lazy val scalikejdbcDbapiAdapter = "org.scalikejdbc" %% "scalikejdbc-play-dbapi-adapter" % "2.8.0-scalikejdbc-3.5"
//   lazy val scalikeInit = "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.8.0-scalikejdbc-3.5"

   lazy val _playTest = "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test

   lazy val skinnyOrm = "org.skinny-framework" %% "skinny-orm" % "3.1.0"

   lazy val mysqlConnectorJava = "mysql" % "mysql-connector-java" % "8.0.25"

   lazy val jsonFormatter = "org.json4s" % "json4s-jackson_2.13" % "4.0.2"


   val portDatabaseDependencies = Seq(
      scalikejdbc,
      scalikejdbcTest,
      scalikejdbcConfig,
      scalikejdbcDbapiAdapter,
      skinnyOrm,
      mysqlConnectorJava
   )
}
