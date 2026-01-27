# Spring Framework – Projects Overview

This directory contains **hands-on projects designed to explore the Spring Framework at its core**.

These projects intentionally avoid focusing on Spring Boot conveniences and instead emphasize:
- The Spring container
- Dependency Injection mechanics
- Bean lifecycle
- Architecture and modularity
- Transactions, AOP, and configuration

Each project targets **real-world architectural concerns** commonly faced in professional and enterprise systems.

> These are not tutorials.  
> They are **explorations of how Spring actually works**.

---

## Project Philosophy

The goal of these projects is to:

- Understand Spring beyond annotations
- Use Spring as infrastructure, not architecture
- Build systems that scale in complexity, not just size
- Prepare for senior-level technical interviews
- Demonstrate architectural thinking

Most projects use:
- Spring Framework (core)
- Minimal or no Spring Boot
- Explicit configuration where appropriate

---

## Project Index

### 1️⃣ Spring Core Lifecycle Lab

📁 **`core-lifecycle-lab/`**

**Purpose:**  
Explore how the Spring container creates, manages, and destroys beans.

**Key Topics:**
- `ApplicationContext` vs `BeanFactory`
- Bean lifecycle phases
- `BeanPostProcessor`
- `FactoryBean`
- Profiles and conditional beans

**Why it matters:**  
Understanding lifecycle and wiring is essential for debugging, customization, and framework-level thinking.

---

### 2️⃣ Clean Architecture with Spring Core

📁 **`clean-architecture-core/`**

**Purpose:**  
Demonstrate how Spring adapts to architecture instead of defining it.

**Key Topics:**
- Clean / Hexagonal Architecture
- Domain layer without Spring dependencies
- Ports and adapters
- Manual bean wiring
- Dependency inversion

**Why it matters:**  
Shows architectural maturity and separation of concerns — a key senior-level skill.

---

### 3️⃣ Spring Transactions Internals

📁 **`transactions-internals/`**

**Purpose:**  
Explain how transaction management works under the hood.

**Key Topics:**
- `@Transactional` via proxies
- Self-invocation pitfalls
- Propagation and isolation
- Rollback rules
- Programmatic vs declarative transactions

**Why it matters:**  
Transaction bugs are expensive. This project teaches how to avoid them.

---

### 4️⃣ Modular Monolith with Spring Core

📁 **`modular-monolith/`**

**Purpose:**  
Build a modular system without microservices.

**Key Topics:**
- Module boundaries
- Controlled component scanning
- Domain events
- Inter-module communication
- Dependency direction enforcement

**Why it matters:**  
Modern systems often fail due to premature microservices. Modular monoliths are a powerful alternative.

---

### 5️⃣ Plugin Architecture with Spring

📁 **`plugin-architecture/`**

**Purpose:**  
Use Spring to build extensible, pluggable systems.

**Key Topics:**
- Strategy pattern with Spring
- Conditional beans
- Runtime selection of implementations
- Configuration-driven behavior
- Plugin isolation

**Why it matters:**  
Many real systems require extensibility without code changes.

---

### 6️⃣ Spring Test – Container Behavior

📁 **`test-container-behavior/`**

**Purpose:**  
Test Spring wiring and configuration integrity.

**Key Topics:**
- Context loading tests
- Profile-based testing
- Configuration validation
- Integration testing without Boot
- Avoiding over-mocking

**Why it matters:**  
Spring applications often fail at startup. These tests catch errors early.

---

### 7️⃣ Real-World AOP Use Cases

📁 **`aop-use-cases/`**

**Purpose:**  
Apply Aspect-Oriented Programming to real problems.

**Key Topics:**
- Auditing
- Metrics
- Retry logic
- Rate limiting
- Proxy chains (JDK vs CGLIB)

**Why it matters:**  
AOP is powerful but dangerous if misunderstood. This project focuses on disciplined usage.

---

### 8️⃣ Internal Framework Built with Spring

📁 **`internal-framework/`**

**Purpose:**  
Create a small internal framework on top of Spring.

**Key Topics:**
- Custom annotations
- Convention-over-configuration
- Framework-style abstractions
- Shared infrastructure modules
- Reusable components

**Why it matters:**  
This project demonstrates framework-level thinking — rare and highly valued.

---

## How to Use These Projects

Recommended order:

1. Core Lifecycle Lab
2. Clean Architecture Core
3. Transactions Internals
4. Modular Monolith
5. Plugin Architecture
6. AOP Use Cases
7. Test Container Behavior
8. Internal Framework

Each project builds on concepts from previous ones.

---

## Final Notes

These projects are designed to answer questions such as:

- *How does Spring really work?*
- *Where are the architectural boundaries?*
- *Why did Spring choose this design?*

> **Mastering Spring means mastering its container — not memorizing annotations.**

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
