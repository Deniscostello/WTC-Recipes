FROM openjdk:17-jdk-alpine
ENV PROFILE=docker
WORKDIR /app
COPY target/ScrapedFood-0.0.1-SNAPSHOT.jar /app
COPY env.properties /app
EXPOSE 8084
CMD ["java", "-jar", "ScrapedFood-0.0.1-SNAPSHOT.jar"]


