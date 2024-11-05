포스팅에 자세한 설명이 있습니다.
https://blog.naver.com/6134aon/223630231280

스크립트로 도커 컴포즈 실행하도록 자동화되있음.

# 클러스터 생성 스크립트
chmod 777 docker.sh
./docker.sh

# 도커 컴포즈 다운
docker-compose down

# 도커 컴포즈로 클러스터 생성하는 명령어
docker-compose --env-file config up -d

# 네임 노드 진입 명령어
docker exec -it hadoop-namenode-1 /bin/bash

# 테스트 스크립트
chmod 777 wordcount_test.sh

