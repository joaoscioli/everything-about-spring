# Ecosystem Links

This document describes how **Spring Modulith integrates with and relates to other Spring projects**.  
Spring Modulith does not replace existing infrastructure or framework components; instead, it defines how they are composed within a modular monolith architecture.

---

## 1. Spring Framework

### Role

The Spring Framework provides the core programming model for dependency injection, configuration, and application context management.

### Relationship with Spring Modulith

Spring Modulith is built entirely on top of the Spring Framework. It does not introduce a new programming model or lifecycle, but rather leverages existing Spring concepts such as:

* Dependency injection
* Application contexts
* Event publication and consumption

Spring Modulith adds architectural structure without altering how Spring applications are written or executed.

> Spring Modulith builds architectural discipline on top of the Spring Framework’s foundation.

### Architectural Considerations

Module boundaries must be respected even though all beans live within the same application context. Dependency injection should not be used to bypass architectural constraints.

---

## 2. Spring Boot

### Role

Spring Boot provides the runtime, auto-configuration, and packaging model for modern Spring applications.

### Relationship with Spring Modulith

Spring Modulith integrates seamlessly with Spring Boot and relies on it for:

* Application startup and configuration
* Dependency management via starters
* Packaging and deployment

Spring Boot determines **how the application runs**, while Spring Modulith defines **how the application is structured**.

> Spring Boot manages execution; Spring Modulith manages internal architecture.

### Architectural Considerations

Auto-configuration and component scanning must align with module boundaries to avoid accidental coupling.

---

## 3. Spring Data

### Role

Spring Data provides abstractions for data access and repository management.

### Relationship with Spring Modulith

Spring Data repositories are typically owned by a single module and represent part of that module’s internal implementation.

Spring Modulith does not restrict the use of Spring Data but encourages repository access to remain within module boundaries.

> Sharing repositories across modules is usually a design smell.

### Architectural Considerations

Cross-module data access should be mediated through module APIs or domain events rather than direct repository sharing.

---

## 4. Spring Security

### Role

Spring Security handles authentication, authorization, and access control.

### Relationship with Spring Modulith

Security concerns often span multiple modules, but they should still respect module boundaries.

Typical patterns include:

* Centralized authentication
* Authorization rules defined close to domain logic
* Security enforcement at module entry points

> Security should reinforce module boundaries, not bypass them.

### Architectural Considerations

Avoid leaking security-specific abstractions into module internals unless they are part of the domain model.

---

## 5. Spring Events

### Role

Spring Events provide an in-process event publication and subscription mechanism.

### Relationship with Spring Modulith

Spring Modulith heavily leverages Spring’s event infrastructure to enable decoupled module collaboration.

Published events:

* Cross module boundaries intentionally
* Represent domain-level facts
* Do not imply synchronous dependencies

> Spring Modulith uses events for decoupling, not for distribution.

### Architectural Considerations

Events should not be used as hidden command mechanisms or as replacements for explicit APIs.

---

## 6. Spring Test

### Role

Spring Test provides infrastructure for integration and application context testing.

### Relationship with Spring Modulith

Spring Modulith extends the testing model by allowing architecture to be validated through tests.

This enables:

* Detection of illegal dependencies
* Verification of module boundaries
* Early feedback during development

> Architecture becomes executable and verifiable.

### Architectural Considerations

Architectural tests should be treated as first-class tests and included in continuous integration pipelines.

---

## 7. Observability (Spring Boot Actuator and Micrometer)

### Role

Spring Boot Actuator and Micrometer provide operational insight into application behavior.

### Relationship with Spring Modulith

Spring Modulith does not introduce new observability tooling. Instead, it integrates with existing mechanisms to expose:

* Module-level interactions
* Event publication behavior
* Execution paths across modules

This enables deeper insight into system behavior without additional infrastructure.

### Architectural Considerations

Observability should reflect architectural intent, making module interactions visible and measurable.

---

## 8. Spring Cloud

### Role

Spring Cloud provides tools and patterns for building distributed systems.

### Relationship with Spring Modulith

Spring Modulith does not replace Spring Cloud. Instead, it prepares applications for possible future distribution.

Well-defined modules can later be extracted into separate services with reduced risk and effort.

> Spring Modulith is often a precursor to Spring Cloud, not an alternative.

### Architectural Considerations

Distribution should be a deliberate decision based on real system needs, not an assumed default.

---

## Conclusion

Spring Modulith integrates naturally with the broader Spring ecosystem.

By focusing on internal structure and boundary enforcement, it enables teams to combine Spring projects coherently while preserving architectural clarity.

The value of Spring Modulith emerges when it is used as a **structural backbone**, not as an isolated framework.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*