# Spring Cloud – Project Examples

This folder contains **incremental, real-world projects** designed to demonstrate *when* and *how* Spring Cloud should be introduced into a system.

These projects are **not isolated demos**. They form a **progressive architecture journey**, evolving from a simple setup into a distributed system with real operational concerns.

The goal is to show **decision-making**, not just feature usage.

---

## Design Philosophy

All projects in this module follow these principles:

* Incremental complexity
* Explicit architectural motivation
* Minimal tooling per step
* Production-oriented defaults
* Clear trade-offs

> You should never introduce Spring Cloud components unless a concrete problem justifies them.

---

## Project Overview

### Centralized Configuration with Spring Cloud Config

**Folder:** `01-config-server`

#### Problem

Multiple services require consistent configuration across environments, with the ability to change values without redeployment.

#### What This Project Shows

* Spring Cloud Config Server
* Git-backed configuration repository
* Environment-based configuration resolution
* Runtime configuration refresh

#### Key Concepts

* Externalized configuration
* Configuration as code
* Environment separation

#### Why It Exists

To demonstrate **when centralized configuration becomes necessary** and how to avoid configuration drift across services.

---

### Service Discovery with Eureka

**Folder:** `02-service-discovery`

#### Problem

Services need to locate each other dynamically in an environment where instances scale up and down.

#### What This Project Shows

* Eureka Server
* Service registration and discovery
* Client-side load balancing
* Instance metadata

#### Key Concepts

* Logical service names
* Dynamic topology
* Decoupled service addressing

#### Why It Exists

To explain **why hard-coded URLs do not scale** in distributed systems.

---

### Resilient Inter-Service Communication

**Folder:** `03-resilience-patterns`

#### Problem

Remote calls are unreliable and can cascade failures across services.

#### What This Project Shows

* Resilience4j integration
* Circuit breakers
* Timeouts and retries
* Fallback strategies

#### Key Concepts

* Failure isolation
* Bulkheads
* Graceful degradation

#### Why It Exists

To highlight that **network failures are the default**, not the exception.

---

### API Gateway with Spring Cloud Gateway

**Folder:** `04-api-gateway`

#### Problem

Clients should not be aware of internal service topology or cross-cutting concerns.

#### What This Project Shows

* Spring Cloud Gateway
* Routing and filtering
* Authentication delegation
* Rate limiting

#### Key Concepts

* Edge services
* Cross-cutting concerns
* API composition

#### Why It Exists

To show how gateways centralize responsibilities that do not belong in business services.

---

### Distributed Tracing and Observability

**Folder:** `05-observability`

#### Problem

Debugging failures across multiple services is difficult without correlation.

#### What This Project Shows

* Distributed tracing setup
* Correlation IDs
* Metrics propagation
* Log enrichment

#### Key Concepts

* Trace context propagation
* Observability boundaries
* Production diagnostics

#### Why It Exists

To demonstrate that **you cannot operate what you cannot observe**.

---

### Feature Flags and Dynamic Behavior

**Folder:** `06-dynamic-flags`

#### Problem

Releasing features safely in distributed systems requires runtime control.

#### What This Project Shows

* Configuration-driven feature toggles
* Environment-aware behavior
* Safe rollout strategies

#### Key Concepts

* Progressive delivery
* Runtime adaptability
* Risk mitigation

#### Why It Exists

To show how configuration becomes a **control plane**, not just static values.

---

### Failure Scenarios and Chaos Testing

**Folder:** `07-failure-scenarios`

#### Problem

Systems often fail in unexpected ways that are never tested.

#### What This Project Shows

* Simulated network failures
* Latency injection
* Partial outages

#### Key Concepts

* Chaos engineering
* Resilience validation
* Failure-first thinking

#### Why It Exists

To reinforce that **resilience must be tested, not assumed**.

---

## How to Use These Projects

Recommended approach:

1. Start with Project 01
2. Run each project locally
3. Observe behavior under failure
4. Reflect on whether the added complexity is justified

Each project builds on lessons from the previous one.

---

## What These Projects Are NOT

* Not toy examples
* Not feature showcases
* Not production templates

They are **learning instruments** designed to build architectural intuition.

---

## Final Notes

Spring Cloud should never be introduced all at once.

These projects demonstrate how to:

* Identify real distributed system problems
* Introduce Spring Cloud components deliberately
* Understand the cost of each architectural decision

> Architecture is about **choosing constraints**, not adding tools.

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*
