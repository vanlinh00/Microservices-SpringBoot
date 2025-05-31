# Sử dụng image OpenJDK 17 bản nhẹ làm image cơ sở
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc bên trong container là /app
WORKDIR /app

# Copy file jar của ứng dụng pickup-service từ thư mục target trên máy host vào container,
# và đổi tên thành app.jar trong thư mục /app
COPY target/pickup-service.jar app.jar

# Khi container chạy, thực thi lệnh: java -jar app.jar để chạy ứng dụng Java pickup-service
ENTRYPOINT ["java", "-jar", "app.jar"]
