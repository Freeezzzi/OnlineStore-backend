FROM openjdk:8-jdk-alpine
ARG JAR_FILE=backend.jar
RUN mkdir -p /app
COPY ${JAR_FILE} /app/backend-app.jar
ENTRYPOINT ["java","-jar","/app/backend-app.jar"]