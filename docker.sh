#!/bin/sh
docker-compose down
#docker build -t myhadoop:1 .
docker-compose --env-file config up -d
docker ps

# 네임노드에 HADOOP_MAPRED_HOME=/opt/hadoop 으로 설정되어 있는지 확인해 봄
docker exec hadoop-namenode-1 cat /opt/hadoop/etc/hadoop/mapred-site.xml

# 하둡에 자바 연동 된지 확인
docker exec hadoop-namenode-1 ls $HADOOP_CLASSPATH
docker exec hadoop-namenode-1 java -version
docker exec hadoop-namenode-1 javac -version

# 볼륨 연동 확인
docker exec hadoop-namenode-1 ls /opt/hadoop/work
