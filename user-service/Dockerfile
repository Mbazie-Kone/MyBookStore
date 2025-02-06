# Use the OpenJDK 21 base image
FROM openjdk:21-jdk-slim

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean;

# Working directory inside the container
WORKDIR /app

# Copy the pom.xml file and download the dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copy all project contents into the container
COPY . ./

# Compile the project
RUN mvn package -DskipTests

# Exposes the port the microservice runs on (8081)
EXPOSE 8081

# Command to run the jar
ENTRYPOINT ["java", "-jar", "target/user-service-1.0.jar"]
