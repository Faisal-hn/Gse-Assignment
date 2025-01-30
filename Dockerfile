FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY target/assignment-0.0.1-SNAPSHOT.jar spring-boot-mysql.jar
ENTRYPOINT ["java","-jar","/spring-boot-mysql.jar"]