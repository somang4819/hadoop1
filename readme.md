
docker-compose down

chmod 777 docker.sh
./docker.sh

docker-compose --env-file config up -d

docker exec -it hadoop-namenode-1 /bin/bash

chmod 777 wordcount_test.sh

docker exec hadoop-namenode-1 ls /opt/hadoop/work# hadoop1
