FROM amazoncorretto:17.0.10-al2023 AS build
WORKDIR /jlink
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN dnf install -y unzip
RUN unzip -q app.jar
RUN jdeps \
    --ignore-missing-deps \
    --multi-release 17 \
    --print-module-deps \
    --class-path 'BOOT-INF/lib/*' \
    app.jar > jre-deps.info
RUN jlink \
    --verbose \
    --compress=2 \
    --strip-java-debug-attributes \
    --no-header-files \
    --no-man-pages \
    --add-modules $(cat jre-deps.info) \
    --output jre-min

FROM amazonlinux:2023.3.20240219.0
WORKDIR /deployments
COPY --from=build /jlink/jre-min /opt/jre-min
ENV PATH /opt/jre-min/bin:$PATH
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
