# WebTechWithJava Projects

This repository contains several Java-based Spring Boot web applications developed during my exploration of backend web technologies. Each project in this folder showcases different aspects of building RESTful services, securing APIs, and rendering dynamic web content. The stack used across all projects includes:

- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **Thymeleaf (for dynamic HTML pages)**
- **JWT Authentication**
- **Maven**

---

## 📁 Projects Overview

### 🎵 `musicplayer` – Minimalistic Web Music Player

A feature-light but functional music streaming platform where users can:
- **Register and log in** securely with JWT tokens.
- **Browse and search** through available music.
- **Play songs directly** in-browser using a built-in HTML5 audio player.
- **Access static assets** like music files and cover images.
- Features a **minimalistic UI** with logout support and protected music streaming endpoints.

**Stack**: Spring Boot, JWT, HTML5, JS, CSS

---

### 🔐 `mobile-app-ws` & `mobile-app-ws2` – Secure User REST APIs (Trial Projects)

These two projects are exploratory trials inspired by the *AppsDeveloperBlog* tutorials. They cover:
- RESTful user registration and login.
- Password encryption and secure storage.
- Stateless JWT-based authentication and authorization.
- Basic service-layer architecture with DTOs and repositories.

**Stack**: Spring Boot, Spring Security, JWT, REST API, Maven

---

### 🌐 `springmvc-ws` – Web MVC + REST with Extended Features (Trial Project)

This project is a more complete version that includes:
- Secure login, password reset flow, and email templates.
- Address management tied to user profiles.
- Exception handling, error responses, and custom global handlers.
- Full REST + MVC setup with DTO mapping, services, and persistence layers.

**Extras**:
- Swagger integration for API testing
- Amazon SES (mocked setup) for email

**Stack**: Spring Boot, Spring MVC, Spring Security, JPA, Swagger

---

## 🧱 General Architecture & Approach

Each project follows a layered architecture with the following structure:
Controller → Service → DTO → Repository → Entity


Security is enforced via **Spring Security with JWT** (in applicable projects). Static resources and templates are placed in the standard `/resources/static` and `/resources/templates` folders.

---

## 🚀 How to Run

1. Ensure Java & Maven are installed.
2. Clone the repository.
3. Navigate into any project folder, then run:
   ```bash
   mvn spring-boot:run
Visit http://localhost:8080 in your browser.

👨‍💻 Author
George Malimba Billa-Yandanbon
Built during practical sessions for learning web development with Java and Spring Boot.