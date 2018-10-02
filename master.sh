#Run the scripts in following order:

#To Install Scala Build Tools:
bash install_sbt.sh

#To set up the inputs on HDFS:
bash hdfs_structure.sh

#To run the sort:
bash run_sort.sh
#Output will be stored on HDFS at /user/output/sorted

#To run the pagerank on BerkStan(without any optimization):
bash run_pgrank_unopt_BS.sh
#Output will be stored on HDFS at /user/output/BerkStan

#To run the pagerank on en-Wiki(with custom RDD Partitioner):
bash run_pgrank_part_BS.sh
#Output will be stored on HDFS at /user/output/wikiout

#To run the pagerank on en-Wiki(with persisting in memory):
bash run_pgrank_cache_BS.sh
#Output will be stored on HDFS at /user/output/wikiout

#To run the pagerank on en-Wiki(without any optimization):
bash run_pgrank_unopt_Wiki.sh
#Output will be stored on HDFS at /user/output/BerkStan

#To run the pagerank on en-Wiki(with custom RDD Partitioner):
bash run_pgrank_part_Wiki.sh
#Output will be stored on HDFS at /user/output/wikiout

#To run the pagerank on en-Wiki(with persisting in memory):
bash run_pgrank_cache_Wiki.sh
#Output will be stored on HDFS at /user/output/wikiout
