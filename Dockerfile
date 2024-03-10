FROM amazoncorretto:17.0.10-al2023 AS build
WORKDIR /build
COPY ./ ./
RUN ./mvnw clean package -Dmaven.test.skip=true
RUN dnf install -y unzip
RUN unzip -q examination2-app-server/target/examination2-app-server-0.0.1-SNAPSHOT.jar
RUN jdeps \
    --ignore-missing-deps \
    --multi-release 17 \
    --print-module-deps \
    --class-path 'BOOT-INF/lib/*' \
    examination2-app-server/target/examination2-app-server-0.0.1-SNAPSHOT.jar > jre-deps.info
RUN jlink \
    --verbose \
    --compress=2 \
    --strip-java-debug-attributes \
    --no-header-files \
    --no-man-pages \
    --add-modules $(cat jre-deps.info) \
    --output jre-min

FROM amazonlinux:2023.3.20240219.0
COPY --from=build /build/examination2-app-server/target/*.jar app.jar
COPY --from=build /build/jre-min /opt/jre-min
ENV JAVA_HOME /opt/jre-min
ENV PATH $JAVA_HOME/bin:$PATH
ENV JAVA_ARGS '-Djavax.net.ssl.trustStore=${JAVA_HOME}"/lib/security/cacerts" -Djavax.net.ssl.trustStorePassword="changeit"'
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=render", "app.jar"]

# FROM amazoncorretto:17.0.10-al2023
# COPY --from=build /build/examination2-app-server/target/*.jar app.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=render", "app.jar"]
