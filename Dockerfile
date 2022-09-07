FROM amazoncorretto:11-alpine
ARG JSON_FILE=output.json
ARG JAR_FILE=target/crud-0.0.1-SNAPSHOT.jar
ADD ${JSON_FILE} output.json
ADD ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]