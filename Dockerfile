FROM openjdk:17-alpine
ADD target/apioficina-1.jar apioficina.jar
ENTRYPOINT ["java", "-jar", "apioficina.jar"]