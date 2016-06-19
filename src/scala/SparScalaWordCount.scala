package scala

/**
  * Created by root on 16-6-19.
  * 参考：http://www.iteblog.com/archives/1028
  * 参考：http://www.cnblogs.com/byrhuangqiang/p/4017725.html
  */

import org.apache.spark.{SparkConf, SparkContext}



/**
  * 统计字符出现次数
  */
object SparScalaWordCount {
  def main(args: Array[String]) {
    if (args.length < 1) {
      System.err.println("Usage: <file>")
      System.exit(1)
    }

    val conf = new SparkConf()
    val sc = new SparkContext(conf)
    val line = sc.textFile(args(0))

    line.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_).collect().foreach(println)

    sc.stop()
  }
}
