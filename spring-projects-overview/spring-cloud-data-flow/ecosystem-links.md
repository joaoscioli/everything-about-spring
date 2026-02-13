# Ecosystem Links — Spring Cloud Data Flow

Spring Cloud Data Flow (SCDF) is a cloud-native orchestration platform for data pipelines.  
It sits within the Spring ecosystem as a central **orchestration layer**, connecting Spring applications, messaging middleware, deployment platforms, and observability tools.

This document outlines SCDF's relationships and dependencies across the ecosystem.

---

## 01 - Core Spring Foundations

### Spring Boot
- Provides the runtime for all stream and task applications
- Enables auto-configuration, embedded servers, and application packaging
- SCDF does **not replace** Spring Boot; it orchestrates Spring Boot apps

### Spring Framework
- Offers the core programming model
- Provides dependency injection, configuration, and essential abstractions
- Foundation for building all SCDF applications

---

## 02 - Messaging & Streaming Layer

### Spring Cloud Stream
- Abstracts messaging middleware
- Provides bindings for source, processor, and sink applications
- Connects SCDF stream applications to Kafka or RabbitMQ
- Essential for declarative stream definitions

### Apache Kafka / RabbitMQ
- Provide reliable, scalable messaging infrastructure
- Enable event-driven communication between stream applications
- SCDF depends on them for actual message transport
- SCDF does **not implement messaging**; it leverages middleware

---

## 03 - Batch & Task Layer

### Spring Batch
- Provides a structured model for batch jobs
- Supports step-based execution and job metadata
- SCDF uses Spring Batch for task applications, especially composed tasks

---

## 04 - Deployment & Infrastructure Layer

### Spring Cloud Deployer
- Abstracts the deployment platform
- Supports multiple runtimes (Kubernetes, Cloud Foundry, Local)
- Translates logical stream/task definitions into platform-specific deployments

### Kubernetes
- Orchestrates containerized applications
- Provides scaling, isolation, and lifecycle management
- SCDF integrates with Kubernetes but does not replace it

### Cloud Foundry
- Alternative deployment platform
- Supports SCDF deployment and scaling in a PaaS environment

---

## 05 - Observability & Monitoring

### Spring Boot Actuator
- Exposes health checks, metrics, and operational endpoints
- Integral for monitoring stream and task applications

### Micrometer
- Standardizes metrics collection
- Integrates with Prometheus, Grafana, and other monitoring tools
- Provides observability for deployed pipelines

---

## 06 - Versioning & Lifecycle

### Spring Cloud Skipper
- Manages stream lifecycle, upgrades, and rollbacks
- Tracks release history
- Essential for controlled versioning of long-running streams

---

## 07 - Configuration & Distributed Systems

### Spring Cloud Config
- Centralized configuration management
- Supports profiles and secrets
- Often used in conjunction with SCDF deployments

### Spring Cloud Core
- Provides patterns for discovery, distributed configuration, and resilience
- Supports orchestration of applications without replacing SCDF

---

## 08 - What SCDF Does Not Replace

SCDF is a **glue layer for orchestration** and does not replace:

* Kubernetes or Cloud Foundry (runtime management)
* Kafka or RabbitMQ (messaging infrastructure)
* Airflow or other workflow schedulers (for general orchestration outside Spring)
* CI/CD pipelines (build and deployment automation)
* Observability stack (monitoring, logging, tracing)

SCDF assumes these tools exist and integrates with them to provide a cohesive orchestration platform.

---

## Summary

SCDF positions itself as a **cloud-native orchestration and management layer** within the Spring ecosystem.  
It leverages existing Spring projects, messaging middleware, and deployment platforms, while providing:

* Declarative pipeline definitions
* Stream versioning and lifecycle management
* Task orchestration and composability
* Observability integration

Understanding its ecosystem links is crucial for designing resilient, scalable, and maintainable data pipelines.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
