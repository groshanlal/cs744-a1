import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.types.StructType
import org.apache.spark.HashPartitioner

object PageRankT5{
    def printType[T](x:T) :Unit = {println(x.getClass.toString())}

    def main(args: Array[String]) {
        val spark = SparkSession.builder
            .appName("Page Rank Task 2")
            .config("spark.driver.memory", "100g")
            .config("spark.local.dir", "/data/tmp")
            .config("spark.task.cpus", "10")
            .config("spark.executor.memory", "100g")
            .config("spark.eventLog.enabled", "true")
            .config("spark.eventLog.dir", "/data/spark-logs")
            .getOrCreate()
	
	import spark.implicits._
        println(s"Page rank task 5 running")

        val separator = "\t"
        val lines = spark.read.textFile(args(0)).map(_.toLowerCase)
            .filter{ line => 
                (line.contains("\tcategory:") || line.contains("\tCategory:") || !(line.contains(":"))) 
            }
            .filter{ line => 
                (line.split("\t").size == 2)
            }.rdd
                
        val data = lines.map{ s =>
            val parts = s.split(separator)
            (parts(0), parts(1))
        }.distinct().groupByKey()
        .partitionBy(new HashPartitioner(128))
        var ranks = data.mapValues(v => 1.0)

        for (i <- 1 to 10) {
            val pageContributions = data.join(ranks).values.flatMap{ case (urls, rank) =>
                val size = urls.size
                urls.map(url => (url, rank / size))
            }
            ranks = pageContributions.reduceByKey(_+_, numPartitions = data.getNumPartitions ).mapValues(0.15 + 0.85 * _)
            println(s"Page rank iteration $i running")
        }
        
        ranks.saveAsTextFile(args(1))
        
        spark.stop()
    }
}
