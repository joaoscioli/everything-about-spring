# Projects — Spring for Apache Kafka

## Overview

This directory contains practical, production-oriented projects designed to demonstrate how Spring for Apache Kafka is used in real-world distributed systems.

These are not "hello world" examples.

Each project focuses on architectural patterns, scalability concerns, resiliency strategies, and event-driven design principles commonly found in modern enterprise systems.

The goal is to move beyond configuration and into architectural reasoning.

---

# Purpose of These Projects

Kafka is not just a messaging system.

It is an architectural decision.

The projects in this module are designed to demonstrate:

- Event-driven architecture
- Distributed system coordination
- Consumer group scaling
- Partition strategy design
- Retry and Dead Letter Topic (DLT) patterns
- Idempotency handling
- Transactional messaging
- Observability and monitoring concerns

Each project isolates a different dimension of Kafka-based system design.

---

# Project List

---

## 1 - Order Processing System

**Focus:** Event-driven microservices communication

Simulates an e-commerce system where services communicate exclusively via Kafka events:

- Order Service publishes events
- Payment Service processes transactions
- Inventory Service updates stock
- Notification Service sends confirmations

### Concepts Demonstrated

- Producer and consumer configuration
- Consumer groups
- Event choreography
- Idempotent processing
- Retry and dead-letter topics
- Distributed consistency patterns

This project demonstrates how loosely coupled services coordinate without direct synchronous calls.

---

## 2 - Event-Driven Inventory System

**Focus:** Partitioning and state consistency

A stock management system driven entirely by Kafka events such as:

- OrderCreated
- OrderCancelled
- StockReplenished

### Concepts Demonstrated

- Partition key strategy (e.g., product ID)
- Parallel consumer configuration
- Manual offset management
- Ordering guarantees within partitions
- Event replay to rebuild projections

This project highlights the importance of partition-aware architecture.

---

## 3 - Payment Transaction Processing Pipeline

**Focus:** High-throughput and transactional messaging

Simulates a financial transaction pipeline with fraud detection and validation stages.

### Concepts Demonstrated

- Idempotent producers
- Kafka transactions
- Exactly-once semantics
- High concurrency tuning
- Consumer lag monitoring

This project demonstrates how Kafka can support financial-grade reliability requirements.

---

## 4 - Log Aggregation & Streaming System

**Focus:** Streaming data ingestion

Multiple services publish structured logs into Kafka. A processing service consumes and transforms logs for analytics or storage.

### Concepts Demonstrated

- High-volume ingestion
- Batch consumption tuning
- Retention policies
- Compression strategies
- Stream processing preparation

This project shows how Kafka can act as a central event hub for observability platforms.

---

## 5 - Retry & Dead Letter Strategy Demo

**Focus:** Failure handling and resiliency

Demonstrates advanced error-handling patterns:

- Retry topics
- Exponential backoff
- Dead Letter Topics (DLT)
- Recoverable vs non-recoverable exception handling

### Concepts Demonstrated

- DefaultErrorHandler configuration
- Backoff strategies
- Failure routing
- Operational observability of failed events

This project emphasizes that distributed systems must be designed around failure.

---

# Architectural Patterns Covered

Across these projects, the following patterns are explored:

- Event choreography
- Consumer scaling via partitions
- Idempotency in distributed systems
- Eventually consistent workflows
- Exactly-once semantics (with proper configuration)
- Retry and recovery pipelines
- Asynchronous microservice coordination

---

# How to Approach These Projects

Recommended progression:

1. Start with Retry & Dead Letter Strategy Demo
2. Move to Event-Driven Inventory
3. Then implement Order Processing
4. Explore Payment Transaction Pipeline
5. Finish with Log Aggregation System

This progression moves from operational resilience → partition strategy → full microservice coordination → high-throughput design.

---

# Technical Requirements

To run these projects, you will need:

- Java 17+
- Spring Boot
- Apache Kafka (local or containerized)
- Docker (recommended for local Kafka cluster)
- Optional: Prometheus & Grafana for monitoring

---

# What These Projects Prepare You For

These projects prepare you to:

- Design scalable event-driven systems
- Discuss Kafka partitioning strategies in interviews
- Implement production-grade retry mechanisms
- Reason about distributed consistency
- Operate Kafka-backed microservices

This module is aligned with real-world enterprise use cases and distributed architecture interviews.

---

# Final Note

Kafka should not be used by default.

It should be used intentionally.

These projects are designed to teach not only how to use Spring for Apache Kafka — but when and why to use it.

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*
