# Use Java 17 official image
FROM eclipse-temurin:17-jdk as builder

WORKDIR /app
COPY . .

# Use Maven wrapper or regular Maven
RUN ./mvnw clean package -DskipTests

# Actual runtime image
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy only the built jar from the builder image
COPY --from=builder /app/target/*.jar app.jar

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
