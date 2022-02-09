FROM openjdk:12-alpine
VOLUME /main-app
ADD target/PracticeProject-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]