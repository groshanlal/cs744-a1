/* Sort.scala */
import org.apache.spark.sql.SparkSession

object SortApp {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("Sort").getOrCreate()
    println(args(0))
    println(args(1))
    val df = spark.read.format("csv")
        .option("inferSchema", "true")
        .option("header", "true")
        //.load("hdfs://128.105.144.28:9000/user/input/export.csv")
        .load(args(0))
    df.orderBy("cca2", "timestamp")
        .write.format("csv")
        .mode("overwrite")
        .option("header", "true")
        //.save("hdfs://128.105.144.28:9000/user/output/sorted")
        .save(args(1))
    println(s"Sorting Done")
    spark.stop()
  }
}
