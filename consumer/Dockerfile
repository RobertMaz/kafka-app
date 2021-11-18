FROM openjdk:11
RUN mkdir /app
COPY target/*.jar /app/application.jar
ENTRYPOINT ["java", "-jar", "/app/application.jar"]