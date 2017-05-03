name := "scala-play2-activiti-integration"

version := "1.0"

lazy val `scalaplay2activitiintegration` = (project in file(".")).enablePlugins(PlayScala)

resolvers ++= Seq(
  "gphat" at "https://raw.github.com/gphat/mvn-repo/master/releases/",
  "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
  "Apache bleeding edge" at "http://repository.apache.org/snapshots",
  "Sonatype releases" at "http://oss.sonatype.org/content/repositories/releases",
  "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "SpinGo OSS" at "http://spingo-oss.s3.amazonaws.com/repositories/releases"
)

scalaVersion := "2.11.7"

val activitiVersion = "5.21.0"

libraryDependencies ++= Seq(
  jdbc , cache , ws   , specs2 % Test ,
  "org.activiti" % "activiti-bpmn-converter" % activitiVersion,
  "org.activiti" % "activiti-bpmn-layout" % activitiVersion,
  "org.activiti" % "activiti-bpmn-model" % activitiVersion,
  "org.activiti" % "activiti-common-rest" % activitiVersion,
  "org.activiti" % "activiti-engine" % activitiVersion,
  "org.activiti" % "activiti-image-generator" % activitiVersion,
  "org.activiti" % "activiti-process-validation" % activitiVersion,
  "org.activiti" % "activiti-rest" % activitiVersion,
  "org.mybatis" % "mybatis" % "3.1.1",
  "org.springframework" % "spring-asm" % "3.1.1.RELEASE",
  "org.springframework" % "spring-beans" % "3.1.1.RELEASE",
  "org.springframework" % "spring-context" % "3.1.1.RELEASE",
  "org.springframework" % "spring-core" % "3.1.1.RELEASE",
  "org.springframework" % "spring-expression" % "3.1.1.RELEASE",
  "org.springframework" % "spring-jdbc" % "3.1.1.RELEASE",
  "org.springframework" % "spring-tx" % "3.1.1.RELEASE")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  