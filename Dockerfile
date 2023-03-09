FROM maven:3.6.3 AS maven
LABEL MAINTAINER="marcelonavarro11md@gmail.com"

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn package

FROM openjdk:17-alpine

ARG JAR_FILE=spring-boot-api-tutorial.jar

WORKDIR /opt/app

# Copy the spring-boot-api-tutorial.jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","utfprapps-api.jar"]