# When to Use — Spring Cloud Data Flow

Spring Cloud Data Flow (SCDF) is a cloud-native orchestration platform designed for managing **data pipelines and task workflows**.  
This document outlines when SCDF should be adopted and when it may be unnecessary, providing guidance for architectural and operational decision-making.

---

## 01 - Data Pipeline Orchestration

SCDF is ideal when pipelines are **core to your system**:

* Orchestrates multiple Spring Boot applications (sources, processors, sinks)
* Combines long-running streams with short-lived tasks
* Requires versioned deployments, upgrades, and rollback capabilities

Use cases include:

* Distributed ETL pipelines
* Event-driven processing pipelines
* Multi-step batch workflows

SCDF ensures consistent and declarative management of complex pipelines.

---

## 02 - Event-Driven, Long-Running Streams

SCDF excels in **streaming-first architectures** where:

* Applications run continuously
* Multiple services consume and produce events
* Resilience and horizontal scalability are required

It is particularly suitable when using **Kafka or RabbitMQ** for decoupled, high-throughput message-driven processing.

---

## 03 - Composed and Complex Task Orchestration

SCDF is recommended when tasks need:

* Sequential or parallel execution
* Conditional branching or loops
* Execution tracking and audit history

Composed tasks enable workflow orchestration that would be cumbersome to manage manually.

---

## 04 - Multi-Platform Deployment

SCDF abstracts deployment across different runtime platforms:

* Kubernetes
* Cloud Foundry
* Local environments

This is ideal when **the same pipelines must run in multiple environments**, providing portability, scaling, and isolation independently from the pipeline definition.

---

## 05 - Declarative Pipeline Management

SCDF enables **declarative, versioned management** of pipelines:

* Logical topology is separated from physical deployment
* Pipelines can be versioned, upgraded, and rolled back
* Changes can be applied consistently across environments

This is critical for teams managing **long-lived or evolving pipelines**.

---

## 06 - Observability & Operational Insights

SCDF provides centralized visibility over distributed pipelines:

* Monitors control plane, data plane, and middleware
* Integrates with metrics, health checks, and logging
* Supports Prometheus, Grafana, and other observability tools

Ideal when pipelines require **operational transparency and real-time monitoring**.

---

## 07 - When NOT to Use SCDF

SCDF may be **overkill** for simple workloads or immature teams:

* Single Spring Boot applications or simple batch jobs
* Short-lived, low-volume processes
* Teams lacking experience with middleware, runtime platforms, or observability tools
* Synchronous microservices or lightweight REST APIs

Using SCDF in these scenarios adds unnecessary complexity and operational overhead.

---

## Summary

Spring Cloud Data Flow is recommended when:

* **Data pipelines are central** to your system
* **Event-driven, streaming-first architecture** is needed
* **Complex workflows and task orchestration** are required
* **Declarative management and versioning** are important
* **Multi-platform deployment** is necessary
* Teams have sufficient **operational maturity**

Avoid SCDF when pipelines are **simple, short-lived, or low-volume**, or when **middleware and runtime complexity outweigh benefits**.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
