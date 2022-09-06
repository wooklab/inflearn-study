# kafka 관련 명령어

# 1. zookeeper 실행
./bin/zookeeper-server-start.sh ./config/zookeeper.properties   # using 2181 port

# 2. kafka 실행
./bin/kafka-server-start.sh ./config/server.properties          # using 9091 port

# 3. 현재 토픽 확인
./bin/kafka-topics.sh --bootstrap-server localhost:9092 --list

# 4. 토픽 생성
./bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic quickstart-events --partitions 1

# 5. 토픽 정보 상세 조회
./bin/kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic quickstart-events

# 6. 프로듀서 실행
./bin/kafka-console-producer.sh --broker-list localhost:9092 --topic quickstart-events

# 7. 컨슈머 실행
./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic quickstart-events --from-beginning
