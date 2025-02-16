FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/LoanApplication-0.0.1-SNAPSHOT.jar /app/LoanApplication.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "LoanApplication.jar"]