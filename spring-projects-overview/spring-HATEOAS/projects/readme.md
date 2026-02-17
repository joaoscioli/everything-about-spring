# Projects

This folder contains practical Spring-based projects designed to demonstrate real-world use cases, architectural patterns, and ecosystem integrations.

The goal of this directory is to provide:

- Hands-on examples
- Production-style architecture
- Incremental complexity
- Coverage of the Spring ecosystem
- Enterprise-ready patterns

Each project focuses on a specific concept or module while following clean architecture principles and best practices.

---

# Structure

Each project is organized as:
```text
/projects
/project-name
README.md
architecture.md
run.md
src/
```

Every project contains:

- Clear purpose
- Defined architecture
- Documented features
- Execution instructions
- Learning objectives

---

# Project Roadmap

The projects are ordered by complexity and ecosystem coverage.

---

## 01 - REST API with JPA

Covers:

- :contentReference[oaicite:0]{index=0}
- :contentReference[oaicite:1]{index=1}
- :contentReference[oaicite:2]{index=2}

Focus:

- CRUD operations
- DTO pattern
- Validation
- Exception handling
- Pagination
- H2 or PostgreSQL
- Layered architecture

Goal:

Build a clean and professional REST API foundation.

---

## 02 - Secure JWT API

Covers:

- :contentReference[oaicite:3]{index=3}

Focus:

- Authentication
- Authorization
- JWT token generation
- Role-based access control
- Method-level security

Goal:

Demonstrate secure API design using stateless authentication.

---

## 03 - Microservice with Config Server

Covers:

- :contentReference[oaicite:4]{index=4}
- :contentReference[oaicite:5]{index=5}

Focus:

- Centralized configuration
- Multiple services
- Externalized properties
- Environment profiles

Goal:

Show microservice configuration management.

---

## 04 - Reactive API with WebFlux

Covers:

- :contentReference[oaicite:6]{index=6}

Focus:

- Reactive programming
- Mono and Flux
- Non-blocking endpoints
- Backpressure concepts

Goal:

Demonstrate reactive stack fundamentals.

---

## 05 - Kafka Producer & Consumer

Covers:

- :contentReference[oaicite:7]{index=7}

Focus:

- Producer
- Consumer
- Retry strategies
- Dead Letter Topics
- Event-driven architecture

Goal:

Demonstrate asynchronous communication.

---

## 06 - Clean Architecture + DDD

Focus:

- Ports and Adapters
- Domain isolation
- Use cases
- Application services
- Infrastructure separation

Goal:

Show enterprise-grade architecture patterns.

---

## 07 - Batch Processing

Covers:

- :contentReference[oaicite:8]{index=8}

Focus:

- Job configuration
- Step processing
- Chunk processing
- File reading and writing

Goal:

Demonstrate batch data processing.

---

## 08 - Redis Cache Demo

Covers:

- :contentReference[oaicite:9]{index=9}

Focus:

- Caching strategies
- @Cacheable
- Cache invalidation
- Distributed cache setup

Goal:

Demonstrate performance optimization through caching.

---

## 09 - API Gateway

Covers:

- :contentReference[oaicite:10]{index=10}

Focus:

- Routing
- Filters
- Rate limiting
- Microservice gateway patterns

Goal:

Demonstrate API gateway design in distributed systems.

---

## 10 - Observability Demo

Covers:

- :contentReference[oaicite:11]{index=11}
- :contentReference[oaicite:12]{index=12}

Focus:

- Health checks
- Metrics
- Monitoring endpoints
- Production readiness

Goal:

Show how to instrument and monitor Spring applications.

---

# Design Principles

All projects follow:

- Constructor injection
- Layered or clean architecture
- Proper validation
- Exception handling
- Clear package organization
- Testability

---

# Learning Path

These projects are designed to:

1. Build foundational knowledge
2. Introduce security concepts
3. Expand to distributed systems
4. Explore reactive programming
5. Demonstrate production-level practices

---

# Contribution Strategy

Future additions may include:

- Dockerized deployments
- Kubernetes examples
- OAuth2 Authorization Server
- GraphQL integration
- Event sourcing examples

---

# Objective

This directory is intended to serve as:

- A learning laboratory
- A professional reference
- A Spring ecosystem showcase
- A portfolio of production-style implementations

Each project is self-contained but aligned with the overall ecosystem structure.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*