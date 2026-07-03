# Web Tech with Java (STS)

A collection of **Spring Boot** learning projects — REST APIs, JWT security, Thymeleaf MVC, and a minimal music player. Built while exploring backend web technologies in Spring Tool Suite.

![Java](https://img.shields.io/badge/Java-17+-007396?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=flat-square&logo=springboot&logoColor=white)

## Stack (all projects)

- Java 17+
- Spring Boot 3.x
- Spring Security + JWT
- Thymeleaf (server-rendered HTML)
- Maven

## Projects

### `musicplayer/` — Web Music Player

Minimal music streaming UI with Spring Security, JWT sessions, MySQL + JPA, and Thymeleaf templates. Close cousin of [GeeManMusic](https://github.com/malimba/GeeManMusic).

```bash
cd musicplayer && mvn spring-boot:run
```

### `mobil-app-ws/` & `mobile-app-ws2/` — JWT REST APIs

Two iterations of a secured REST backend — token issuance, protected routes, and API-first design without a heavy frontend.

```bash
cd mobil-app-ws && mvn spring-boot:run
```

### `springmvc-ws` — Full MVC + Swagger

Server-rendered MVC application with OpenAPI/Swagger documentation — useful reference for traditional Spring web apps.

## Common run pattern

```bash
cd <project-folder>
mvn spring-boot:run
# default http://localhost:8080
```

## Notes

- Folder `mobil-app-ws` is intentionally spelled that way (historic typo preserved).
- Each subproject has its own `pom.xml` and can run independently.

---

[malimba](https://github.com/malimba)
