# Menggunakan image base yang berisi JDK
FROM openjdk:17-alpine

# Menyalin JAR aplikasi Spring Boot ke dalam container
COPY target/demo-api.jar /app.jar

# Menjalankan aplikasi ketika container dijalankan
CMD ["java", "-jar", "/app.jar"]
