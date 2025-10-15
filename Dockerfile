# Stage 1: Build the application
FROM eclipse-temurin:25-jdk as builder

WORKDIR /app

COPY . .


RUN chmod +x mvnw


RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENV PORT=9000
EXPOSE 9000

# Run the app
CMD ["java", "-jar", "app.jar"]
