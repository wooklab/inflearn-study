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
             rabbitmq:management




# docker 이미지 만들기
$ mvn clean compile package -DskipTests=true
# docker 파일 빌드
$ docker build --tag inwook9/config-service:1.0 .
$ docker build --tag inwook9/discovery-service:1.0 .
$ docker build --tag inwook9/apigateway-service:1.0 .
# docker image 업로드하기
$ docker push inwook9/config-service:1.0

# docker 에서 config-service 실행하기
$ docker run -d -p 8888:8888 --network ecommerce-network \
             -e "spring.rabbitmq.host=rabbitmq" \
             -e "spring.profiles.active=default" \
             --name config-service inwook9/config-service:1.0

# docker 에서 discovery-service 실행하기
$ docker run -d -p 8761:8761 --network ecommerce-network \
             -e "spring.cloud.config.uri=http://config-service:8888" \
             --name discovery-service inwook9/discovery-service:1.0

# docker 에서 apigateway-service 실행하기
$ docker run -d -p 8000:8000 --network ecommerce-network \
             -e "spring.cloud.config.uri=http://config-service:8888" \
             -e "spring.rabbitmq.host=rabbitmq" \
             -e "eureka.client.serviceUrl.defaultZone=http://discovery-service:8761/eureka/" \
             --name apigateway-service inwook9/apigateway-service:1.0



# docker 에서 mariadb 이미지 만들기 (m1 mac 기준, 테이블 저장 경로: /opt/homebrew/var/mysql)
$ docker build -t inwook9/my-mariadb:1.0 .
$ docker run -d -p 3306:3306 --network ecommerce-network \
             --name mariadb inwook9/my-mariadb:1.0
# mariadb 접속 후 설정
$ docker exec -it mariadb /bin/bash
$ mysql -hlocalhost -uroot -p # 127.0.0.1 사용못함
$> use mydb;
$> grant all privileges on *.* to 'root'@'%' identified by 'test1357';
$> flush privileges;
$> exit
$ mysql -h127.0.0.1 -uroot -p # 이제 가능


# kafka docker-compose 실행 (아래 수행 전 docker-compose-single-broker.yml 파일 참조)
$ docker-compose -f docker-compose-single-broker.yml up -d


# zipkin 다운 & 수행
$ docker run -d -p 9411:9411 \
             --network ecommerce-network \
             --name zipkin openzipkin/zipkin

# 모너티러이 다운 & 수행
$ docker run -d -p 9090:9090 \
             --network ecommerce-network \
             --name prometheus \
             -v /path/to/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus
$ docker run -d -p 3000:3000 \
             --network ecommerce-network \
            --name grafana grafana/grafana