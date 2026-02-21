# Projects

This directory contains practical, architecture-driven examples
demonstrating how to use Spring for Apache Pulsar in real-world scenarios.

These projects are not toy samples.

They are designed to reflect:

• Production-grade patterns  
• Distributed system challenges  
• Architectural decision trade-offs  
• Event-driven design principles

---

## Purpose of These Projects

The goal is to move beyond "how to send a message"
and focus on:

• Why to use Pulsar  
• When to use specific subscription types  
• How to design idempotent consumers  
• How to structure event-driven systems

Each project highlights a different distributed system concern.

---

## Project List

### 01 - Event-Driven Order System

A microservices-based workflow using asynchronous event coordination.

Demonstrates:

• Partitioned topics  
• Idempotent consumers  
• Dead Letter Topics  
• Subscription strategy selection  
• Eventual consistency

Focus: foundational event-driven microservices.

---

### 02 - Multi-Tenant SaaS Messaging Platform

Simulates a SaaS architecture using Pulsar’s tenant and namespace isolation.

Demonstrates:

• Multi-tenancy  
• Namespace separation  
• Tenant-aware topic resolution  
• Governance boundaries

Focus: scalable platform-level messaging architecture.

---

### 03 - Saga Orchestration with Pulsar

Implements distributed transaction coordination using event choreography.

Demonstrates:

• Saga pattern  
• Compensating transactions  
• Failure propagation via events  
• Reliable asynchronous workflows

Focus: distributed consistency without 2PC.

---

### 04 - Real-Time Analytics Pipeline

Simulates high-throughput event ingestion and processing.

Demonstrates:

• Partitioned topics  
• Horizontal consumer scaling  
• Backpressure considerations  
• Throughput tuning  
• Observability integration

Focus: streaming scalability.

---

## Architectural Themes Covered

Across these projects, you will encounter:

• Topic partitioning strategies  
• Subscription model trade-offs  
• Delivery semantics (at-least-once)  
• Idempotency patterns  
• Dead Letter Topic design  
• Observability-first messaging

The examples reflect the strengths of Apache Pulsar
while leveraging Spring’s structured programming model.

---

## Recommended Learning Path

If you are new to distributed messaging:

1. Start with Event-Driven Order System
2. Move to Saga Orchestration
3. Explore Multi-Tenant Messaging
4. Finish with Real-Time Analytics

This progression builds from basic event-driven concepts
to platform-level architecture.

---

## Technology Stack

All projects use:

• Spring Boot  
• Spring for Apache Pulsar  
• Docker (for Pulsar cluster simulation)  
• Spring Boot Actuator  
• Micrometer

Some projects may also include:

• Spring Retry  
• Spring Data  
• Integration testing with Testcontainers

---

## Final Note

These projects are intentionally architecture-focused.

They are meant to:

• Strengthen distributed system thinking  
• Prepare for production-level event-driven systems  
• Provide portfolio-ready examples  
• Demonstrate enterprise-grade Spring usage

Spring for Apache Pulsar is powerful —
but only when used with architectural discipline.

These projects aim to cultivate that discipline.