import kafka.serializer.StringDecoder
import org.apache.hadoop.fs.Path
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka._

object airportsTopicToHdfs {
  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      System.err.println("useage: airportsTopicToHdfs <run mode> <topicName>")
      System.exit(0)
    }
    //    var executionMode = args(0)
    //    var seconds = args(0).toInt
    //    val scc: StreamingContext = if (executionMode.toLowerCase == "runlocal") {
    //      val conf = new SparkConf()
    //      conf.set("spark.broadcast.compress", "false")
    //      conf.set("spark.shuffle.compress", "false")
    //      conf.set("spark.shuffle.spill.compress", "false")
    //      val sc: SparkContext = SparkContext("local[2]", "airports topic data to hdfs", conf)
    //      new StreamingContext(sc, Seconds(seconds))
    //    }
    //    else {
    //      val conf = new SparkConf().setAppName("airports topic data to hdfs").setMaster(executionMode)
    //      val sc: SparkContext = new SparkContext(conf)
    //      new StreamingContext(sc, Seconds(seconds))
    //    }
    val conf = new SparkConf().setMaster(args(0)).setAppName("stream kafka topics to hdfs")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(5))

    val topicsName = Set(args(1).toString)
    val kafkaPrams = Map[String, String]("metadata.broker.list" -> "quickstart.cloudera:9092")

    val stream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaPrams, topicsName)
    val lines = stream.map(line => line)
    lines.print()
    lines.saveAsTextFiles("hdfs://192.168.44.128:8020/user/cloudera/flight/data/raw/")

    ssc.start()
    ssc.awaitTermination()
  }

}
