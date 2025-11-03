# Use lightweight Java runtime image
FROM eclipse-temurin:25-jre

# Set working directory inside container
WORKDIR /app

# Copy the pre-built Spring Boot jar from target/
COPY target/*.jar app.jar

# Expose port expected by Beanstalk
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

