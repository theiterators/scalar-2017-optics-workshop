scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "org.typelevel"              %% "cats"              % "0.9.0",
  "com.github.julien-truffaut" %% "monocle-core"      % "1.4.0",
  "com.github.julien-truffaut" %% "monocle-macro"     % "1.4.0",
  "com.lihaoyi"                %% "pprint"            % "0.4.4",
  "org.specs2"                 %% "specs2-core"       % "3.8.9" % "test",
  "org.specs2"                 %% "specs2-scalacheck" % "3.8.9" % "test",
  "org.typelevel"              %% "discipline"        % "0.7.3" % "test",
  "com.github.julien-truffaut" %% "monocle-law"       % "1.4.0" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3")

addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.0" cross CrossVersion.full)

