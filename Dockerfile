# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .

# Copy the entire source code
COPY src ./src

# Make gradlew executable
RUN chmod +x gradlew

# Build the Spring Boot app
RUN ./gradlew clean build -x test

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "build/libs/raghuu-0.0.1-SNAPSHOT.jar"]
