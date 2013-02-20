import sbt._
import sbt.Keys._
import com.github.retronym.SbtOneJar

object Resolvers {
  val sonatypeRepo = "Sonatype Release" at "http://oss.sonatype.org/content/repositories/releases/"
  val jbossRepo = "JBoss" at "http://repository.jboss.org/nexus/content/groups/public/"
  val akkaRepo = "Akka" at "http://repo.akka.io/repository/"
}

object Dependencies {

  
  val slf4jSimple = "org.slf4j" % "slf4j-simple" % "1.6.2"
  val slf4jSimpleTest = slf4jSimple % "test"

  val akka = "se.scalablesolutions.akka" % "akka-actor" % "1.2"
  val akkaHttp = "se.scalablesolutions.akka" % "akka-http" % "1.2"
  val akkaAmqp = "se.scalablesolutions.akka" % "akka-amqp" % "1.2"

  val asyncHttp = "com.ning" % "async-http-client" % "1.6.5"

  val jsoup = "org.jsoup" % "jsoup" % "1.6.1"

  val casbahCore = "com.mongodb.casbah" %% "casbah-core" % "2.1.5-1"

  val signpost = "oauth.signpost" % "signpost-core" % "1.2"
  val signpostHttp = "oauth.signpost" % "signpost-commonshttp4" % "1.2"
  val httpclient = "org.apache.httpcomponents" % "httpclient" % "4.2"
  val commonsio = "commons-io" % "commons-io" % "2.4"

  val jaxbApi = "javax.xml" % "jaxb-api" % "2.1"
  val jaxbImpl = "javax.xml" % "jaxb-impl" % "2.1"

  val jacksonCore = "com.fasterxml.jackson.core" % "jackson-core" % "2.1.3"
  val jacksonModuleScala = "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.1.3"

  val scopt = "com.github.scopt" %% "scopt" % "2.1.0"
  val scallop = "org.rogach" %% "scallop" % "0.6.4"

  val luceneCore = "org.apache.lucene" % "lucene-core" % "3.6.1"
  val luceneAnalyzer = "org.apache.lucene" % "lucene-analyzers" % "3.6.1"

  val casbah = "org.mongodb" %% "casbah" % "2.5.0"

  val elasticsearch = "org.elasticsearch" % "elasticsearch" % "0.19.8"

  val scalatest = "org.scalatest" %% "scalatest" % "1.9" % "test"
  val scalaMock = "org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test"
  val fongo = "com.foursquare" % "fongo" % "1.0.3" % "test"


}

object BuildSettings {
  import Dependencies._
  import Resolvers._

  val buildOrganization = "com.ejrav"
  val buildVersion = "0.1"
  val buildScalaVersion = "2.10.0"

  val globalSettings = Seq(
    organization := buildOrganization,
    version := buildVersion,
    scalaVersion := buildScalaVersion,
    scalacOptions += "-deprecation",
    fork in test := true,
    libraryDependencies ++= Seq(slf4jSimpleTest, scalatest),
    resolvers := Seq(jbossRepo, akkaRepo, sonatypeRepo))

  val projectSettings = Defaults.defaultSettings ++ globalSettings
}

object MongoIndexerBuild extends Build {
  import Dependencies._
  import Resolvers._
  import BuildSettings._

  override lazy val settings = super.settings ++ globalSettings

  lazy val mongoindexer = Project(
    id = "mongoindexer",
    base = file("."),
    settings = projectSettings
      ++ Seq(libraryDependencies ++= Seq(slf4jSimple,signpost, signpostHttp, httpclient, commonsio,
          scalatest, scalaMock, fongo,
          scallop, jacksonCore, jacksonModuleScala,
          luceneCore, luceneAnalyzer, casbah, elasticsearch))++ SbtOneJar.oneJarSettings).settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)


}
