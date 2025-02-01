FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY target/assignment-0.0.1-SNAPSHOT.jar gse-cart-contact.jar
ENTRYPOINT ["java","-jar","/gse-cart-contact.jar"]