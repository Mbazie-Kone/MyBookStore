# Use the OpenJDK 21 base image
FROM openjdk:21-jdk-slim

# Working directory inside the container
WORKDIR /app

# Copy the pom.xml file and download the dependencies
COPY pom.xml ./
RUN ./mvnw dependency:go-offline -B

# Copy all project contents into the container
COPY . ./

# Compile the project
RUN ./mvnw package -DskipTests

# Exposes the port the microservice runs on (8080)
EXPOSE 8080

# 
Command to run the jar
ENTRYPOINT ["java", "-jar", "target/api-gateway-1.0.jar"]