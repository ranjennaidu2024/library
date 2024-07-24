# Use a base image with Java 17
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the target directory to the container
COPY target/library-0.0.1-SNAPSHOT.jar library.jar

# Expose port 8081
EXPOSE 8081

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "library.jar"]
