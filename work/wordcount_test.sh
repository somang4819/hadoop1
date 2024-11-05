#!/bin/bash

#네임 노드 집입
#docker exec -it hadoop-namenode-1 /bin/bash

# 아웃풋 폴더 만듬
hadoop fs -mkdir /output

# 확인
hadoop fs -ls /output

hadoop fs -mkdir /input

# 테스트용 데이터 파일 입력
hadoop fs -put /opt/hadoop/README.txt /input

# 기존 아웃풋 폴더 삭제
hadoop fs -rm -r hdfs://namenode/output

# 실행
hadoop jar /opt/hadoop/share/hadoop/mapreduce/hadoop-mapreduce-examples-3.4.0.jar wordcount /input /output

# 결과 데이터 확인
hdfs dfs -ls /output/