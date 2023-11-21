FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/crm-0.0.1-SNAPSHOT.jar crm-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "crm-0.0.1-SNAPSHOT.jar"]