import sys

print("test")
for i in sys.stdin:
    print( i[0])    

    # if item ==0 or item ==1 or item==2:
    #     cnt+=1




# hadoop fs -mkdir /output

# # 확인 hadoop jar /opt/hadoop/share/hadoop/tools/lib/hadoop-streaming-3.4.0.jar -input /input -output /output -mapper 'python map.py' -file /opt/hadoop/work/map.py -reducer 'python reduce.py' -file /opt/hadoop/work/reduce.py

# hadoop fs -ls /output

# hadoop fs -mkdir /input

# # 테스트용 데이터 파일 입력
# hadoop fs -put /opt/hadoop/work/data/data.csv /input

# # 기존 아웃풋 폴더 삭제
# hadoop fs -rm -r hdfs://namenode/output
# hadoop fs -rm -r hdfs://namenode/input