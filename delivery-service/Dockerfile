# Sử dụng image OpenJDK 17 bản nhẹ làm image cơ sở
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc bên trong container là /app
WORKDIR /app

#Coppy file jar vào image
COPY target/delivery-service.jar app.jar

# Khi container chạy, thực thi lệnh: java -jar app.jar để chạy ứng dụng Java
ENTRYPOINT ["java", "-jar", "app.jar"]
