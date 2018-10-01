#!/bin/bash
DIR=~/cs744-a1/pagerank
cd $DIR
sbt clean
sbt package
hadoop fs -rm -r -f /user/output/*
export SPARK_HOME=~/spark-2.2.0-bin-hadoop2.7
export HDFS_HOME=hdfs://$(hostname -i):9000
$SPARK_HOME/bin/spark-submit --class PageRankT1 $DIR/target/scala-2.11/page-rank_2.11-1.0.jar \
$HDFS_HOME/user/input/test/enwiki-pages-articles/* \
$HDFS_HOME/user/output/wikiout

#$DIR/small.txt \



