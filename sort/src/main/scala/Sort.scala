/* Sort.scala */
import org.apache.spark.sql.SparkSession

object SortApp {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("Sort").getOrCreate()
    val df = spark.read.format("csv")
        .option("inferSchema", "true")
        .option("header", "true")
        .load("hdfs://128.104.222.128:9000/user/input/export.csv")
    /*df.orderBy("cca2", "timestamp")
        .write.format("csv")
        .mode("overwrite")
        .option("header", "true")
        .save("hdfs://128.104.222.128:9000/user/output/sorted.csv")*/
    df.orderBy("cca2", "timestamp").show()
    println(s"Sorting Done")
    spark.stop()
  }
}
