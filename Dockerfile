FROM openjdk:12
VOLUME /tmp
EXPOSE 8090
ADD ./target/microservice_geo-0.0.1-SNAPSHOT.jar geo-server.jar
ENTRYPOINT ["java","-jar","/geo-server.jar"]