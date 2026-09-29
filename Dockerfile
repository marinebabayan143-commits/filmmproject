FROM maven:3.9-eclipse-temurin-22 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:22-jre

WORKDIR /app

COPY --from=build /app/target/filmmproject-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8082} --server.address=0.0.0.0"]