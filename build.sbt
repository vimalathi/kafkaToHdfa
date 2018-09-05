name := "kafkaToHdfs"

version := "0.1"

scalaVersion := "2.10.5"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core
//libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.0"

//gpu enabler
//libraryDependencies += "com.ibm" %% "gpu-enabler_2.10" % "1.0.0"

//spark streaming
//libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka_2.10" % "1.6.0"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.10" % "1.6.0"

//spark core
libraryDependencies += "org.apache.spark" % "spark-core_2.10" % "1.6.0"

libraryDependencies += "org.apache.spark" % "spark-streaming-kafka_2.10" % "1.6.0"
