# 📦 Delivery System - Microservices Architecture

## 🔖 Overview

This project is a microservices-based delivery management system, developed using **Spring Boot**, **Kafka**, **Redis**, and **Docker Compose**. It includes modularized services for authentication, pickup, and delivery, each with its own responsibilities.

---

## 🧱 Project Structure

delivery-system/
│
├── .mvn/ # Maven wrapper config
├── auth-service/ # Authentication microservice
├── pickup-service/ # Pickup management microservice
├── delivery-service/ # Delivery management microservice
│
├── mvnw # Unix-based Maven wrapper
├── mvnw.cmd # Windows-based Maven wrapper
├── .gitignore # Git ignore rules
├── .gitattributes # Git attributes file
├── docker-compose.yml # Docker Compose configuration
└── README.md # Project description


---

## 📐 System Architecture

![Architecture Diagram](https://raw.githubusercontent.com/vanlinh00/delivery-system/main/docs/architecture-diagram.png)

> **Ghi chú:** Nếu bạn chưa có ảnh thật sự, bạn có thể tự tạo sơ đồ kiến trúc đơn giản rồi lưu vào thư mục `docs/` trong project với tên `architecture-diagram.png`. Ví dụ sơ đồ nên vẽ như sau:

 +---------+
  | Client  |
  +---------+
       |
 REST + JWT
       |
+----------------+
| auth-service |
+----------------+
| |
REST + JWT REST + JWT
| |
+-------------+ +------------------+
| pickup-service | <-- Kafka (event-driven) --> | delivery-service |
+-------------+ +------------------+
| Redis | | Redis |
| PostgreSQL | | MySQL |
+-------------+ +------------------+

---

## 🧩 Microservices Description

### 1. `auth-service`
- **Responsibilities**:
  - User registration & login
  - JWT token generation & validation
  - Role-based access control
- **Tech**: Spring Security, JWT, Spring Data JPA

### 2. `pickup-service`
- **Responsibilities**:
  - Pickup order creation and status updates
  - Kafka events to `delivery-service`
- **Tech**: Spring Boot, Kafka, PostgreSQL, Redis (optional cache)

### 3. `delivery-service`
- **Responsibilities**:
  - Delivery assignment and status tracking
  - Consumes Kafka events from `pickup-service`
- **Tech**: Spring Boot, Kafka, PostgreSQL

---

## ⚙️ Running the System

```bash
# Start all services
docker-compose up --build




---

## 📐 System Architecture

![Architecture Diagram](https://raw.githubusercontent.com/vanlinh00/delivery-system/main/docs/architecture-diagram.png)

> **Ghi chú:** Nếu bạn chưa có ảnh thật sự, bạn có thể tự tạo sơ đồ kiến trúc đơn giản rồi lưu vào thư mục `docs/` trong project với tên `architecture-diagram.png`. Ví dụ sơ đồ nên vẽ như sau:
>
> ```
> +-------------+       REST        +--------------+         Kafka        +----------------+
> |  Clients    |  ------------->  | auth-service |  ----------------->  | pickup-service |
> +-------------+                 +--------------+                       +----------------+
>                                    |                                        |
>                                    |        REST + JWT                     |     REST
>                                    v                                        v
>                             +----------------+                        +-------------------+
>                             | delivery-service| <------------------- |     Redis / DB     |
>                             +----------------+                        +-------------------+
> ```

---

## 🧩 Microservices Description

### 1. `auth-service`
- **Responsibilities**:
  - User registration & login
  - JWT token generation & validation
  - Role-based access control
- **Tech**: Spring Security, JWT, Spring Data JPA

### 2. `pickup-service`
- **Responsibilities**:
  - Pickup order creation and status updates
  - Kafka events to `delivery-service`
- **Tech**: Spring Boot, Kafka, PostgreSQL, Redis (optional cache)

### 3. `delivery-service`
- **Responsibilities**:
  - Delivery assignment and status tracking
  - Consumes Kafka events from `pickup-service`
- **Tech**: Spring Boot, Kafka, PostgreSQL

---

## ⚙️ Running the System

```bash
# Start all services
docker-compose up --build

This will start:

Kafka broker

Redis

PostgreSQL (if included in compose)

All three Spring Boot services

🔐 Security
All services authenticate using JWT tokens

Token is issued by auth-service

Protected endpoints require Authorization: Bearer <token> header

💬 Inter-service Communication
REST (JWT secured)

Kafka (event-driven messaging)

Kafka Topic	Producer	Consumer	Purpose
pickup.events	pickup-service	delivery-service	Notify delivery updates
user.auth	auth-service	all services	(Optional) Auth-related

📦 Tech Stack
Layer	Technology
Language	Java 17
Framework	Spring Boot, Spring Security
Messaging	Apache Kafka
DB/Cache	PostgreSQL, Redis
Orchestration	Docker Compose

📌 Future Improvements
Add API Gateway (Spring Cloud Gateway)

Add Service Discovery (Netflix Eureka)

Central config with Spring Cloud Config

Monitoring with Prometheus + Grafana

🤝 Contribution
Feel free to fork the repository and submit pull requests!

📄 License
This project is licensed under the MIT License.


---

🎯 **Gợi ý để có ảnh sơ đồ kiến trúc**:

Bạn có thể dùng công cụ vẽ sơ đồ online như:
- [https://app.diagrams.net](https://app.diagrams.net)
- [https://excalidraw.com](https://excalidraw.com)

Sau đó lưu ảnh dưới tên `architecture-diagram.png` và bỏ vào thư mục `docs/`, rồi push lên GitHub. Link ảnh trong `README` sẽ tự động hoạt động nếu đúng đường dẫn. Nếu bạn muốn, mình có thể giúp bạn tạo sơ đồ sơ khai. Muốn không?

