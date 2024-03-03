FROM amazoncorretto:17.0.10-al2023
WORKDIR /deployments
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
