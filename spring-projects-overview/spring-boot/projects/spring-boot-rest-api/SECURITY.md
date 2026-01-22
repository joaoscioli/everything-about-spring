# Spring Boot REST API

This project is a foundational Spring Boot REST API designed to demonstrate best practices, clean structure, and core concepts of building RESTful services with **Spring Boot 3** and **Spring Framework 6**.

It serves as a baseline project for learning, reference, and future extensions within the **Everything About Spring Framework** series by *The Coding Cabin*.

---

## 🎯 Project Goals

- Provide a clean and understandable REST API structure
- Demonstrate standard Spring Boot layering
- Use modern Spring Boot defaults and conventions
- Avoid unnecessary complexity while remaining production-oriented
- Serve as a reusable starting point for other Spring Boot projects

---

## 🧱 Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Web (Spring MVC)
- Spring Data JPA
- Hibernate
- H2 (in-memory database, for development)
- Maven

---

## 📁 Project Structure

```text
src/main/java/com/thecodingcabin/springbootrestapi
├── controller     # REST controllers (HTTP layer)
├── service        # Business logic
├── repository     # Data access layer
├── domain         # JPA entities
├── dto            # Data Transfer Objects
├── exception      # Custom exceptions and handlers
└── config         # Application and framework configuration
