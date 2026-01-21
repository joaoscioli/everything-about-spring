# Spring Boot – Core Concepts

This document describes the **core concepts and mental models** behind Spring Boot.
Rather than focusing on APIs or annotations, it explains *how to think in Spring Boot* and *why the framework behaves the way it does*.

Understanding these concepts is essential to using Spring Boot effectively at an advanced level.

---

## 1. Spring Boot Is an Opinionated Platform

Spring Boot is not a neutral toolkit.
It is an **opinionated platform** designed to accelerate application development by making informed architectural decisions on your behalf.

These opinions are based on:

* Industry best practices
* Production experience from the Spring team
* Common architectural patterns in modern Java applications

Spring Boot works best when you **follow its opinions**.
You can override them, but fighting the defaults usually leads to unnecessary complexity.

> Spring Boot removes decision fatigue by choosing sensible defaults.

---

## 2. Convention Over Configuration

Spring Boot embraces the principle of **Convention over Configuration**.

Instead of requiring explicit configuration for every component, Spring Boot:

* Assumes conventional package structures
* Infers behavior from class names and locations
* Applies default configurations automatically

Explicit configuration is still available and encouraged **only when deviating from the convention**.

This approach leads to:

* Less boilerplate
* Faster onboarding
* More readable projects

---

## 3. Classpath-Driven Behavior

In Spring Boot, the **classpath is a primary driver of behavior**.

The presence or absence of dependencies determines:

* Which auto-configurations are activated
* Which beans are created
* Which features are enabled

Adding a dependency is not just a technical action, it is an **architectural signal**.

> In Spring Boot, dependencies define capabilities, not just libraries.

---

## 4. Auto-Configuration as Conditional Assembly

Auto-configuration is often perceived as magic, but it is not.

Spring Boot auto-configuration is a **conditional assembly mechanism**:

* Configurations are applied only if specific conditions are met
* Conditions include classpath checks, existing beans, properties, and environment state

Every auto-configuration effectively answers:

> “Should this capability be enabled in this application?”

This keeps the system flexible while preserving strong defaults.

---

## 5. Starters as Capability Descriptors

Spring Boot starters are not frameworks and not features.

They are **capability descriptors**.

Each starter communicates intent:

* `spring-boot-starter-web` → This application serves HTTP requests
* `spring-boot-starter-data-jpa` → This application uses relational persistence
* `spring-boot-starter-security` → This application requires security concerns

Starters simplify dependency management and enforce coherent dependency graphs.

---

## 6. Externalized Configuration

Spring Boot strongly enforces **externalized configuration**.

Key principles:

* The application artifact should be immutable
* Configuration must change across environments
* Code should not contain environment-specific values

Spring Boot supports layered configuration from multiple sources:

* Properties and YAML files
* Environment variables
* Command-line arguments
* External configuration servers

> Build once, configure many times.

---

## 7. Production-Ready by Default

Spring Boot assumes that applications are meant to **run in production**.

Production concerns are not optional:

* Health checks
* Metrics
* Logging
* Observability
* Graceful shutdown

These capabilities are built into the platform and exposed through Spring Boot Actuator.

> In Spring Boot, production readiness is a first-class concern.

---

## 8. Explicit Overrides Over Implicit Hacks

Spring Boot is designed to be extensible and customizable.

However, it strongly favors:

* Explicit configuration
* Clear bean definitions
* Documented extension points

Overriding behavior should be done through:

* Custom configuration classes
* Property overrides
* Conditional beans

Not through:

* Reflection hacks
* Copying internal code
* Relying on undocumented behavior

> Spring Boot rewards clarity, not cleverness.

---

## 9. When Spring Boot Is Not the Right Tool

Spring Boot is powerful, but not universal.

It may not be ideal for:

* Ultra-minimal runtime environments
* Framework-level libraries
* Extremely constrained systems
* Applications requiring full manual wiring

Understanding when *not* to use Spring Boot is a sign of architectural maturity.

---

## 10. Mental Model Summary

To think effectively in Spring Boot:

* Spring Boot assembles, it does not invent
* Defaults are deliberate
* The classpath drives behavior
* Configuration is layered and externalized
* Architecture comes before annotations

Mastering these concepts allows you to move from *using Spring Boot* to *designing with Spring Boot*.

---

**Part of the *Everything About Spring Framework* series.**
