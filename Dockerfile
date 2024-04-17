FROM amazoncorretto:17-alpine-jdk

COPY target/com.plataforma.comunitaria-1.0.jar app.jar

ENTRYPOINT ["java" , "-jar" , "/app.jar"]