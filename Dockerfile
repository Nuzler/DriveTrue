# Start with Java 17 JDK base image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy everything to the container
COPY . .

# Build the app
RUN ./mvnw clean package -DskipTests

# 🔧 Make mvnw executable
RUN chmod +x mvnw

# Expose port (Spring Boot default)
EXPOSE 8080

# Run the JAR
CMD ["java", "-jar", "target/DriveTrueApp-0.0.1-SNAPSHOT.jar"]