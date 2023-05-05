FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/ca

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN sed -i 's/\r$//' mvnw

RUN /bin/sh mvnw package -DskipTests

FROM openjdk:17-jdk-alpine
COPY --from=build /workspace/ca/target/apioficina-1.jar apioficina.jar
ENTRYPOINT ["java", "-jar", "apioficina.jar"]