# Architecture

This document describes the architectural decisions, structure, and design principles used in the **Spring Boot REST API** project.

The goal is not to showcase every possible Spring feature, but to establish a **clean, understandable, and extensible baseline architecture** that reflects real-world Spring Boot applications.

---

## 1. Architectural Style

### RESTful Monolithic Application

This project follows a **monolithic REST architecture**, where:

- The application is deployed as a single unit
- Responsibilities are separated by layers, not by services
- HTTP/JSON is the primary communication mechanism

This approach is intentionally chosen because:

- Most Spring Boot applications start as monoliths
- It simplifies development, testing, and deployment
- It provides a strong foundation before moving to microservices

---

## 2. Layered Architecture

The application follows a **classic layered architecture**, with clear separation of concerns.

```text
Client
  ↓
Controller (Web Layer)
  ↓
Service (Business Logic)
  ↓
Repository (Persistence)
  ↓
Database
```

## 3. Layered Architecture "springboot-rest-api"
``` text
com.thecodingcabin.springbootrestapi
├── controller
├── service
├── repository
├── domain
├── dto
└── exception
```