FROM openjdk:11-slim as build

#maintainer sachin
MAINTAINER SACHIN

#add applcation jar to container
COPY target/cards-0.0.1-SNAPSHOT.jar cards-0.0.1-SNAPSHOT.jar

#execute application
ENTRYPOINT ["java", "-jar", "cards-0.0.1-SNAPSHOT.jar"]
