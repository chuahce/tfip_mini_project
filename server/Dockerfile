# Set the base image
FROM openjdk:17-jdk-slim
# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/server-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your Spring Boot application listens on
EXPOSE 8080

# Set the entry point command to run your Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]