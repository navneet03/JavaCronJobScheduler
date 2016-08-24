name := "jobSchedular"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
 "org.quartz-scheduler" % "quartz" % "2.2.2",
 "javax.mail" % "javax.mail-api" % "1.5.5",
 "com.sun.mail" % "javax.mail" % "1.5.2"
)     

play.Project.playJavaSettings
