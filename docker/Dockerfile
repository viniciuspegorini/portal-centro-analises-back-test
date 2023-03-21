FROM openjdk:8-jdk-alpine
LABEL MAINTAINER="marcelonavarro11md@gmail.com"
EXPOSE 8080
ARG JAR_FILE=target/spring-boot-web.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]