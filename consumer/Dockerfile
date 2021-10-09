FROM openjdk:8
RUN mkdir /app
COPY target/*.jar /app/application.jar
ENTRYPOINT ["java", "-jar", "/app/application.jar"]