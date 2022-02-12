FROM openjdk:12-alpine
VOLUME /app
EXPOSE 8080
ADD target/PracticeProject-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]