FROM eclipse-temurin:17-jdk

WORKDIR /app

# Gradle wrapper 포함한 전체 프로젝트 복사
COPY . .

# 의존성 캐시 활용을 위해 먼저 wrapper만 복사
RUN ./gradlew build -x test

# 빌드 결과물 실행
ENTRYPOINT ["java", "-jar", "build/libs/app.jar"]
