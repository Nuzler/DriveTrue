# Start with Java 17 JDK base image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy everything to the container
COPY . .

# ✅ Make mvnw executable BEFORE using it
RUN chmod +x mvnw

# ✅ Now build the app
RUN ./mvnw clean package -DskipTests

# Expose Spring Boot port
EXPOSE 8080

# Run the JAR
CMD ["java", "-jar", "target/DriveTrueApp-0.0.1-SNAPSHOT.jar"]