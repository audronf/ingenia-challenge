FROM eclipse-temurin:21
COPY target/stations.jar /app/stations.jar
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "stations.jar"]
