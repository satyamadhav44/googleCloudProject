# Stage 1: Build the application
# Use a full JDK image for compiling
FROM openjdk:17-jdk-slim AS builder

# Install Maven
RUN apt-get update && apt-get install -y maven
#Set working directory
WORKDIR /app
#Copy the whole project to given directory
COPY . /app
#run the maven build command
RUN mvn clean install
# Copy the generated JAR file into the container
RUN cp /app/target/*.jar /app/upload_app.jar
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "upload_app.jar"]