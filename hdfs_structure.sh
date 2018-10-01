#!/bin/bash
cd ~/
hadoop fs -mkdir /user
hadoop fs -mkdir /user/input
hadoop fs -mkdir /user/output
wget http://pages.cs.wisc.edu/~shivaram/cs744-fa18/assets/export.csv
hadoop fs -copyFromLocal export.csv /user/input
hadoop fs -copyFromLocal /proj/uwmadison744-f18-PG0/test/enwiki-pages-articles /user/input
hadoop fs -rm /user/input/enwiki-pages-articles/READ*
hadoop fs -rm /user/input/enwiki-pages-articles/.*
wget https://snap.stanford.edu/data/web-BerkStan.txt.gz
gunzip web-BerkStan.txt.gz
hadoop fs -copyFromLocal web-BerkStan.txt /user/input
