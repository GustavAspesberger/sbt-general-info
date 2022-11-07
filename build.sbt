lazy val root = ( project in file (".") )
  .settings(
    name := "sbt-generalinfo",
    organization := "com.github.gustavaspesberger",
    version := IO.readLines(new File("version")).head,
    sbtPlugin := true,
    scriptedBufferLog := false,
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.7.3"
  )