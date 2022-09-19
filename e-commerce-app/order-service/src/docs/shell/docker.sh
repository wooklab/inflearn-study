# docker 의 exit된 모든 컨테이너 초기화
$ docker system prune

# prune 하기전에 확인하고 prune 이후 확인
$ docker container ls -a

# docker network 보기 (초기 설정은 3가지 bridge/host/none)
$ docker network ls

# network 생성하기
$ docker network create --gateway 172.18.0.1 --subnet 172.18.0.0/16 ecommerce-network

# network 상세내용
$ docker network inspect ecommerce-network



# 다운로드 받은 이미지 확인
$ docker images
$ docker ps -a

# rabbitmq 인스턴스화 하기
# docker run과 동시에 rabbitmq management tag 가 없으면 다운
$ docker run -d --name rabbitmq --network ecommerce-network \
             -p 15672:15672 -p 5672:5672 -p 5671:5671 -p 4369:4369 \
             -e RABBITMQ_DEFAULT_USER=guest \
             -e RABBITMQ_DEFAULT_PASS=guest \
             rabbitmq:managem




# docker 이미지 만들기
$ mvn clean compile package -DskipTests=true
# docker 파일 빌드 및 허브에 업로드하기
$ docker build --tag inwook9/config-service:1.0 .


# docker 에서 config-service 실행하기
$ docker run -d -p 8888:8888 --network ecommerce-network \
             -e "spring.rabbitmq.host=rabbitmq" \
             -e "spring.profiles.active=default" \
             --name config-service inwook9/config-service:1.0