# Use Ubuntu latest
FROM ubuntu:latest

# Update package repository and install necessary packages
RUN apt-get update && \
    apt-get install -y \
    openjdk-21-jdk \
    maven

# Set working directory
WORKDIR /app

# Copy the entire project directory into the Docker container
COPY . .

# Build the Spring Boot application using Maven
RUN mvn -f pom.xml clean package

# Expose the port the app runs on
EXPOSE 8080

# Define the command to run your application
ENTRYPOINT ["mvn", "spring-boot:run"]