# Spring Cloud — Ecosystem Links

This document provides a **curated overview of the Spring Cloud ecosystem**.  
Spring Cloud is not a single framework, but a **collection of projects** that address different concerns in distributed systems.

Not all projects have the same relevance today.  
This document highlights **current, legacy, and deprecated components** to support informed architectural decisions.

---

## How to Read This Document

Projects are grouped by **architectural responsibility**, not by popularity or age.

Each project includes:
* A short description of its purpose
* Its current status:
    * **Active** — recommended for new systems
    * **Maintenance** — supported but not evolving significantly
    * **Deprecated / Legacy** — not recommended for new systems

Links point to **official Spring documentation**.

---

## Spring Cloud Core

### Spring Cloud Commons
**Status:** Active

Provides shared abstractions and utilities used across Spring Cloud projects, including:
* Service discovery interfaces
* Load balancing abstractions
* Common configuration patterns

This module is rarely used directly but forms the **foundation of the ecosystem**.

---

### Spring Cloud Context
**Status:** Active

Manages the **bootstrap context** and lifecycle of externalized configuration, including:
* Early initialization
* Context refresh mechanisms
* Configuration propagation

It enables configuration to be resolved **before the main application context starts**.

---

## Configuration & Secrets

### Spring Cloud Config
**Status:** Active

Centralizes application configuration outside application binaries, supporting:
* Git-backed configuration repositories
* Environment-specific configuration
* Secure configuration distribution

Best suited for:
* Multi-environment systems
* Centralized configuration governance

In Kubernetes-native environments, platform configuration mechanisms may be preferable.

---

## Service Discovery

### Spring Cloud Netflix Eureka
**Status:** Maintenance / Legacy

A service registry originally popularized by Netflix OSS.

While still functional, Eureka is increasingly replaced by:
* Platform-native discovery (e.g., Kubernetes Services)
* Cloud provider-managed solutions

Not recommended for new cloud-native systems.

---

### Platform-Native Discovery
**Status:** Preferred

Modern architectures rely on the platform to manage service discovery, with Spring Cloud acting as an **integration layer** rather than the source of truth.

---

## Client Communication

### Spring Cloud OpenFeign
**Status:** Active

Provides declarative HTTP clients for service-to-service communication, enabling:
* Interface-driven contracts
* Integrated serialization and error handling

Feign does not eliminate the need for:
* Explicit timeouts
* Resilience policies
* Observability integration

---

## Resilience & Fault Tolerance

### Spring Cloud Circuit Breaker
**Status:** Active

Defines a **common abstraction layer** for resilience mechanisms such as circuit breakers and retries.

It decouples application code from specific resilience implementations.

---

### Resilience4j
**Status:** Active

A lightweight, functional resilience library providing:
* Circuit breakers
* Retries
* Rate limiters
* Bulkheads

Often used as the underlying implementation for Spring Cloud Circuit Breaker.

---

## API Gateway & Edge

### Spring Cloud Gateway
**Status:** Active

A reactive, non-blocking API Gateway designed for modern distributed systems.

Common responsibilities include:
* Routing and filtering
* Security enforcement
* Traffic shaping

It replaces older gateway solutions and aligns with cloud-native networking models.

---

### Netflix Zuul
**Status:** Deprecated

An older gateway solution from the Netflix OSS stack.

Deprecated in favor of Spring Cloud Gateway and modern edge solutions.

---

## Observability & Tracing

### Spring Cloud Sleuth
**Status:** Deprecated

Previously provided distributed tracing and context propagation.

Its functionality has been superseded by Micrometer-based tracing.

---

### Micrometer Tracing
**Status:** Active

Provides standardized metrics and tracing integration aligned with:
* OpenTelemetry
* Modern observability platforms

Micrometer is now the **recommended observability foundation** for Spring-based systems.

---

## Platform & Cloud Integrations

Spring Cloud provides adapters for integrating with specific platforms and cloud providers.

### Spring Cloud Kubernetes
**Status:** Active

Integrates Spring applications with Kubernetes-native features such as:
* ConfigMaps and Secrets
* Service discovery
* Lifecycle events

---

### Spring Cloud AWS
**Status:** Active

Integrates Spring applications with AWS services, including:
* Parameter Store
* Secrets Manager
* Messaging services

---

### Spring Cloud Azure
**Status:** Active

Provides integration with Azure-native services and configuration sources.

---

### Spring Cloud GCP
**Status:** Active

Offers integrations with Google Cloud Platform services and infrastructure.

---

## Legacy & Maintenance Projects

Several Spring Cloud projects reflect architectural patterns popularized during the early microservices era, especially those originating from Netflix OSS.

While historically important, these components are generally **not recommended for new systems** due to:
* Platform evolution
* Kubernetes-native alternatives
* Improved infrastructure tooling

---

## Deprecated & Historical Projects

Spring Cloud has evolved alongside the cloud ecosystem.

Some projects have been deprecated not due to failure, but because:
* Their responsibilities moved to the platform
* Better abstractions emerged
* Operational models changed

Understanding this evolution helps avoid **outdated architectural assumptions**.

---

## Final Notes

Spring Cloud should be approached as a **toolkit**, not a checklist.

Effective use requires:
* Awareness of project maturity
* Alignment with infrastructure platforms
* Conscious avoidance of legacy patterns

> **The most important Spring Cloud decision is often what *not* to use.**

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
