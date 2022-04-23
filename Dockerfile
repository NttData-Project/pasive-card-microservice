FROM openjdk:11
VOLUME /tmp
EXPOSE 8032
ADD ./target/product-microservice-0.0.1-SNAPSHOT.jar pasive-card-microservice.jar
ENTRYPOINT ["java","-jar","pasive-card-microservice.jar"]