#!/bin/bash
cd ~/cs744-a1/sort
sbt clean
sbt package
export SPARK_HOME=~/spark-2.2.0-bin-hadoop2.7
export HDFS_HOME=hdfs://128.104.222.128:9000
$SPARK_HOME/bin/spark-submit --class SortApp target/scala-2.11/sort-project_2.11-1.0.jar \
$HDFS_HOME/user/input/export.csv $HDFS_HOME/user/output/sorted.csv
