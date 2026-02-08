# Spring Modulith – Ecosystem & Related Projects

Spring Modulith does not exist in isolation. It is designed to work seamlessly with the broader Spring ecosystem, leveraging existing Spring projects to build well-structured, modular monoliths with strong architectural boundaries.

This document lists the most relevant projects, tools, and technologies that integrate naturally with Spring Modulith and commonly appear in real-world Modulith-based systems.

---

## Core Spring Projects

### Spring Boot
**Foundation of most Spring Modulith applications**

Spring Modulith is built on top of Spring Boot and relies on its auto-configuration, dependency management, and application lifecycle.

**How it integrates:**
- Bootstraps the application context
- Simplifies configuration of modules
- Enables production-ready features via Actuator

**Link:**  
https://spring.io/projects/spring-boot

---

### Spring Framework
**Underlying dependency injection and application model**

Spring Modulith builds directly on core Spring Framework concepts such as:
- ApplicationContext
- Bean lifecycle
- Events
- AOP

**Why it matters:**
- Modulith boundaries are enforced at the package and bean level
- Module communication is implemented using standard Spring mechanisms

**Link:**  
https://spring.io/projects/spring-framework

---

## Eventing & Messaging

### Spring Events (ApplicationEventPublisher)
**Primary mechanism for module-to-module communication**

Spring Modulith strongly encourages the use of events instead of direct dependencies between modules.

**Typical use cases:**
- Domain events
- Integration events
- Decoupled workflows

**Link:**  
https://docs.spring.io/spring-framework/reference/core/beans/context-introduction.html#context-functionality-events

---

### Spring for Apache Kafka
**Asynchronous communication beyond the monolith**

While Spring Modulith focuses on modular monoliths, it integrates well with Kafka when:
- Modules evolve into external services
- Events need to be published outside the application

**Link:**  
https://spring.io/projects/spring-kafka

---

## Persistence & Data Access

### Spring Data
**Consistent data access across modules**

Spring Data provides repository abstractions that are commonly scoped to individual modules.

**Best practices with Modulith:**
- One persistence model per module
- Avoid sharing repositories across modules
- Use module-local aggregates

**Link:**  
https://spring.io/projects/spring-data

---

### Spring Data JDBC / JPA
**Persistence strategies inside modules**

Spring Modulith works equally well with:
- JPA (Hibernate)
- JDBC-based persistence
- Mixed persistence strategies per module

**Links:**
- https://spring.io/projects/spring-data-jpa
- https://spring.io/projects/spring-data-jdbc

---

## Testing & Verification

### Spring Modulith Test
**Architecture and module boundary testing**

Spring Modulith provides dedicated test support to:
- Verify module dependencies
- Detect illegal cross-module access
- Generate module diagrams

**Key features:**
- Module dependency verification
- Architectural tests
- Documentation generation

**Link:**  
https://docs.spring.io/spring-modulith/reference/testing.html

---

### Spring Boot Test
**Integration and slice testing**

Used alongside Modulith tests for:
- Context loading
- Module-level integration tests
- End-to-end scenarios

**Link:**  
https://spring.io/projects/spring-boot#overview

---

## Observability & Operations

### Spring Boot Actuator
**Operational visibility of modular applications**

Actuator complements Spring Modulith by exposing:
- Health checks
- Metrics
- Application info

**Common use cases:**
- Monitoring module-heavy applications
- Production diagnostics

**Link:**  
https://spring.io/projects/spring-boot#actuator

---

### Micrometer
**Metrics and monitoring**

Micrometer integrates naturally with Spring Boot and can be used to:
- Measure module-level performance
- Track event throughput
- Feed observability platforms

**Link:**  
https://micrometer.io/

---

## Documentation & Visualization

### Spring Modulith Documentation Support
**Architecture diagrams and documentation generation**

Spring Modulith can generate:
- Module dependency diagrams
- PlantUML visualizations
- Architecture documentation from code

**Why it matters:**
- Architecture stays close to implementation
- Reduces documentation drift

**Link:**  
https://docs.spring.io/spring-modulith/reference/documentation.html

---

### PlantUML
**Visual representation of module relationships**

Often used together with Spring Modulith to:
- Visualize module dependencies
- Share architecture with teams

**Link:**  
https://plantuml.com/

---

## Security & Infrastructure

### Spring Security
**Security within modular boundaries**

Spring Modulith works well with Spring Security when:
- Each module owns its security rules
- Authentication is centralized
- Authorization is module-aware

**Link:**  
https://spring.io/projects/spring-security

---

### Spring Authorization Server
**OAuth2 and OpenID Connect support**

For applications that require:
- External authentication
- Token-based security
- Future service extraction

**Link:**  
https://spring.io/projects/spring-authorization-server

---

## Build & Tooling

### Maven / Gradle
**Standard Java build tools**

Spring Modulith supports both build systems and works well with:
- Multi-module builds
- Layered dependency management

**Links:**
- https://maven.apache.org/
- https://gradle.org/

---

### ArchUnit
**Architecture rules enforcement**

Often used together with Spring Modulith for:
- Additional architectural constraints
- Package and dependency rules

**Link:**  
https://www.archunit.org/

---

## Related Architectural Concepts

While not frameworks, the following concepts are closely related to Spring Modulith usage:

- Modular Monolith Architecture
- Domain-Driven Design (DDD)
- Bounded Contexts
- Event-Driven Architecture
- Hexagonal / Clean Architecture

These ideas strongly influence how Spring Modulith applications are designed and structured.

---

## Final Notes

Spring Modulith is intentionally lightweight and opinionated. Instead of introducing new infrastructure, it amplifies the value of existing Spring projects by providing structure, verification, and guidance for modular architectures.

Its strength lies in how well it fits into the existing Spring ecosystem rather than replacing it.

---

**Next suggested documents:**
- `projects/README.md` – Real-world example projects
- `migration.md` – Migrating from layered or monolithic architectures
- `best-practices.md` – Proven patterns for large Modulith systems

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*