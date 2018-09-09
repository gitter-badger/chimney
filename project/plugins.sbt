addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.18")
libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.7.4"

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.1")
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.25")
addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.3.8")
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "0.6.0")
addSbtPlugin("org.portable-scala" % "sbt-scala-native-crossproject" % "0.6.0")

addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "1.5.1")

addSbtPlugin("com.lihaoyi" % "scalatex-sbt-plugin" % "0.3.11")
addSbtPlugin("com.typesafe.sbt" % "sbt-ghpages" % "0.6.2")
