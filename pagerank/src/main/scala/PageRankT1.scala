import org.apache.spark.sql.SparkSession

object PageRankT1{
    def main(args: Array[String]) {
        val spark = SparkSession.builder.appName("Page Rank Task 1").getOrCreate()

        println(s"Page rank task 1 running")

        val separator = "\t"
        val lines = spark.read.textFile("hdfs://128.104.222.128:9000/user/input/web-BerkStan.txt")
            .filter( line => !(line.contains("#")) ).rdd
        val data = lines.map{ s =>
            val parts = s.split(separator)
            (parts(0), parts(1))
        }.distinct().groupByKey()
        var ranks = data.mapValues(v => 1.0)

        for (i <- 1 to 10) {
            val pageContributions = data.join(ranks).values.flatMap{ case (urls, rank) =>
                val size = urls.size
                urls.map(url => (url, rank / size))
            }
            ranks = pageContributions.reduceByKey(_+_).mapValues(0.15 + 0.85 * _)
        }

        ranks.saveAsTextFile("hdfs://128.104.222.128:9000/user/output/pr1")

        spark.stop()
    }
}
