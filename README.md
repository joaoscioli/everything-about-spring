![thecodingcabin_pixel_banner](https://github.com/user-attachments/assets/dad054e9-275e-428b-a670-027c3cd21d97)
## 🪵 The Coding Cabin

Built with care, depth, and long-term thinking.
Welcome to the cabin.

# Everything About Spring Framework

> A practical, advanced, and up-to-date guide to the **official Spring ecosystem**, based entirely on **active Spring projects**.

This repository is part of **The Coding Cabin** initiative and aims to provide a **deep technical understanding** of Spring Framework, Spring Boot, and the broader Spring portfolio used in real-world, production-grade systems.

---

## 🎯 Purpose

* Map and explain **all active Spring projects**
* Provide **concise summaries + practical examples**
* Serve as a **long-term reference** for modern Spring development
* Support advanced topics such as:

  * Microservices
  * Cloud-native architectures
  * Security
  * Observability
  * Modular monoliths
  * Event-driven systems
  * AI integration

This is **not** a beginner tutorial.
It is designed for developers who already work with Spring and want to **master the ecosystem**.

---

## 🌱 Spring Projects Overview (Active)

### Spring Boot (4.0.1)

Opinionated framework for building Spring applications quickly and consistently.

**Use cases**

* REST APIs
* Microservices
* Batch jobs
* Cloud-native applications

**Example**

```java
@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
```

---

### Spring Framework (7.0.3)

The core foundation of the Spring ecosystem.

**Key features**

* Dependency Injection (IoC)
* Transaction management
* Web frameworks (MVC & WebFlux)
* AOP
* Messaging

**Example**

```java
@Component
public class PaymentService {
}
```

---

### Spring Data (2025.1.2)

Unified programming model for data access.

**Supports**

* JPA / JDBC
* MongoDB
* Redis
* Elasticsearch
* Neo4j
* LDAP

**Example**

```java
public interface UserRepository extends JpaRepository<User, Long> {
}
```

---

### Spring Cloud (2025.1.0)

Tools for building distributed systems and microservices.

**Patterns**

* Service discovery
* Configuration management
* Circuit breakers
* API gateways

**Example**

```yaml
spring:
  cloud:
    config:
      uri: http://config-server:8888
```

---

### Spring Cloud Data Flow (2.11.5)

Orchestration platform for **data microservices** and streaming pipelines.

**Use cases**

* ETL pipelines
* Streaming data processing
* Batch workflows

---

### Spring Security (7.0.2)

Authentication and authorization framework.

**Supports**

* JWT
* OAuth2
* OpenID Connect
* Method-level security

**Example**

```java
@PreAuthorize("hasRole('ADMIN')")
public void deleteUser() {}
```

---

### Spring Authorization Server (1.5.5)

Framework for building OAuth2 and OpenID Connect authorization servers.

**Use cases**

* Identity Providers
* Centralized authentication
* Token issuance

---

### Spring for GraphQL (2.0.1)

Spring integration with GraphQL Java.

**Use cases**

* GraphQL APIs
* Schema-first development

**Example**

```java
@QueryMapping
public Book bookById(@Argument Long id) { }
```

---

### Spring Session (4.0.1)

Externalized session management.

**Backends**

* Redis
* JDBC
* Hazelcast

**Use cases**

* Stateless applications
* Horizontal scalability

---

### Spring Integration (7.0.1)

Implementation of **Enterprise Integration Patterns (EIP)**.

**Use cases**

* Messaging
* File integration
* System-to-system communication

---

### Spring HATEOAS (3.0.2)

Support for hypermedia-driven REST APIs.

**Example**

```java
EntityModel<User> model = EntityModel.of(user);
```

---

### Spring Modulith (2.0.1)

Guidance and tooling for **modular monoliths**.

**Focus**

* Domain-driven modules
* Clear boundaries
* Evolution toward microservices

---

### Spring REST Docs (4.0.0)

API documentation generated from tests.

**Benefits**

* Always up to date
* Test-driven documentation

---

### Spring AI (1.1.2)

Framework for AI engineering in Spring applications.

**Focus**

* LLM integration
* Prompt templates
* Vector databases
* Enterprise AI workflows

---

### Spring Batch (6.0.1)

High-volume batch processing.

**Use cases**

* Data migration
* Scheduled jobs
* Large-scale processing

---

### Spring AMQP (4.0.1)

Messaging abstraction for AMQP brokers (RabbitMQ).

---

### Spring for Apache Kafka (4.0.1)

Spring-native support for Kafka producers and consumers.

---

### Spring LDAP (4.0.1)

LDAP integration using Spring templates.

**Use cases**

* Authentication
* Enterprise directories

---

### Spring for Apache Pulsar (2.0.1)

Messaging support for Apache Pulsar.

---

### Spring Shell (3.4.1)

CLI applications built with Spring.

**Use cases**

* Internal tools
* Admin consoles

---

### Spring Statemachine (4.0.1)

State machine framework.

**Use cases**

* Workflow engines
* Business process modeling

---

### Spring Web Flow (4.0.0)

Stateful web flows with controlled navigation.

---

### Spring Web Services (5.0.0)

Contract-first SOAP web services.

---

## 🧱 Repository Structure (High Level)

```text
modules/
├── spring-boot/
├── spring-framework/
├── spring-data/
├── spring-security/
├── spring-cloud/
├── spring-ai/
└── ...
```

Each module contains:

* Conceptual notes
* Practical examples
* Architectural diagrams
* Production considerations

---

## 🚀 Who This Repository Is For

* Software Engineers
* Backend Developers
* Architects
* Developers preparing for international roles
* Anyone who wants **real mastery of Spring**

---

## 📌 Philosophy

> Learn Spring not as a framework, but as an **ecosystem for building systems**.

---
