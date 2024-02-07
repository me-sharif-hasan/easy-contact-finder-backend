# Use a different OpenJDK version
FROM openjdk:21-jdk-oraclelinux8

# Set working directory
WORKDIR /app

# Copy the Maven executable to the container
COPY mvnw .
COPY .mvn .mvn

# Copy the project object model (POM) file to download dependencies
COPY pom.xml .

# Download dependencies for caching
RUN ./mvnw dependency:go-offline

# Copy the project source code
COPY src src

# Package the application
RUN ./mvnw package

# Expose the port that the Spring Boot application will run on
EXPOSE 5000

# Run the Spring Boot application
CMD ["java", "-jar", "target/easy-contact-finder-backend-0.0.1-SNAPSHOT.jar"]
