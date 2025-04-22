# Dockerfile for Spring Boot Todo List App

# --- Stage 1: Build Stage ---
# Use a base image with Maven and JDK 21 to build the application
FROM maven:3.9-eclipse-temurin-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml first to leverage Docker layer caching for dependencies
COPY pom.xml .

# Download dependencies (optional, but can speed up subsequent builds if pom.xml hasn't changed)
# RUN mvn dependency:go-offline -B

# Copy the entire project source code
COPY src ./src

# Build the application JAR, skipping tests (tests will run in CI pipeline)
# The JAR will be created in /app/target/
RUN mvn clean package -DskipTests -B

# --- Stage 2: Runtime Stage ---
# Use a lightweight Java Runtime Environment (JRE) base image
FROM eclipse-temurin:21-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the executable JAR file built in the 'build' stage
# Use a wildcard to match the version number
COPY --from=build /app/target/testing-*.jar app.jar

# Expose the port the application runs on (defined in application.yml or default 8080)
EXPOSE 8080

# Define the command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]

