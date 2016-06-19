package scala

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by root on 16-6-19. 参考：http://bbs.tianya.cn/post-414-57216-1.shtml
  */

object SparkScalaWordCountTwo {
  def main(args: Array[String]): Unit = {
    //这三行语句创建了一个Spark上下文，并且运行时这个Application的名字就叫WordCount
    val conf = new SparkConf()
    conf.setAppName("WorkCount")
    val sc = new SparkContext(conf)

    /*这两行语句从hdfs文件中创建了叫lines的RDD，它的每个元素就对应文件中的每一行
    有了RDD我们就可以通过它提供的各种API来完成需要的业务功能*/
    val file = "hdfs://127.0.0.1:9000/file.txt"
    val lines = sc.textFile(file)

    /*flatMap是RDD众多转换中的一种，它的功能是把源RDD中的元素映射成目的RDD中的0个
     或者多个元素。上面语句把以文本行为元素的RDD转换成了以单个单词为元素的RDD*/
    val words = lines.flatMap(_.split("\\s+"))

    //countByValue就是一个“动作”，它的功能是统计RDD中每个元素出现的次数，得到一个元素及其出现次数的Map
    val wordCount = words.countByValue()

    println(wordCount)
  }
}