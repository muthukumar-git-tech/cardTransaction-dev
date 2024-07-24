FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/Transaction-Routine-0.0.1-SNAPSHOT.jar Transaction-Routine.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/Transaction-Routine.jar"]