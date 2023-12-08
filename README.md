# springboot-kotlin-crud-demo

## 프로젝트 설명
이 프로젝트는 Spring Boot, Kotlin, MongoDB를 사용하여 간단한 Member CRUD REST API를 구현합니다.

MongoDB는 Docker Compose를 통해 로컬 환경에서 실행되며. 엔드포인트는 JUnit5를 이용하여 테스트 코드로 검증하였습니다.


## Run
### 1. MongoDB 실행
```shell
docker-compose up -d
```

### 2. 애플리케이션 실행
```shell
./gradlew bootRun

or 

./gradlew build
java -jar build/libs/kotlin.jar
```

### 3. 엔드포인트 테스트
- GET /members: 모든 멤버 조회
- POST /members: 새 멤버 추가
- PATCH /members/{id}: 특정 멤버 수정
- DELETE /members/{id}: 특정 멤버 삭제

## Reference
https://spring.io/guides/tutorials/spring-boot-kotlin

