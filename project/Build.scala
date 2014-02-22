import sbt._
import Keys._
import com.typesafe.sbt.SbtStartScript

object BuildSettings {
  val buildOrganization = "com.github.nmarshall23"
  val buildVersion      = "0.1.1-SNAPSHOT"
  val buildScalaVersion = "2.10.3"

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := buildOrganization,
    version      := buildVersion,
    scalaVersion := buildScalaVersion
  )
}

object Dependencies {
  val liftVersion = "2.5.1"

  val liftWeb      = "net.liftweb" %% "lift-webkit" % liftVersion % "compile"
  val liftMap      = "net.liftweb" %% "lift-mapper" % liftVersion % "compile"

  val jettyWebApp  = "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container,test"
  val javaxServlet = "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,compile" artifacts Artifact("javax.servlet", "jar", "jar")
  val scalatest = "org.scalatest" % "scalatest_2.9.0" % "1.4.1" % "test"

}

object Lift2Build extends Build {
  import Dependencies._
  import BuildSettings._

  val commonDeps = Seq (
      liftWeb,
      liftMap,
      jettyWebApp,
      javaxServlet
    )

  lazy val liftapp = Project("liftweb",
                              base = file("."),
                              settings = buildSettings ++ 
                              Seq (libraryDependencies ++= commonDeps) ++ 
                              com.earldouglas.xsbtwebplugin.WebPlugin.webSettings ++
                              SbtStartScript.startScriptForWarSettings
                              ) 

}
