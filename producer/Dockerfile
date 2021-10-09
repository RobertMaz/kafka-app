FROM openjdk:8
RUN mkdir /app
COPY target/producer-0.0.1-SNAPSHOT.jar /app/application.jar
ENTRYPOINT ["java", "-jar", "/app/application.jar"]