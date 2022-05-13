FROM openjdk:11-jre-slim
WORKDIR /app
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ./app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

EXPOSE 8080