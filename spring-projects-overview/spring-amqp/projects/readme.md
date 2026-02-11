# Spring Integration – Projects

This directory contains practical projects designed to demonstrate real-world usage of **Spring Integration** and Enterprise Integration Patterns (EIP).

These projects are intentionally structured to simulate production-grade integration scenarios.

---

# Projects Overview

| Project | Description | Main Concepts |
|----------|------------|---------------|
| File Processing Pipeline | Monitor files, validate, transform and publish events | File Adapter, Transformer, Filter, Kafka |
| Order Processing Orchestrator | End-to-end order workflow orchestration | Routing, Aggregator, Correlation |
| Multi-Protocol Bridge | Kafka ↔ REST ↔ RabbitMQ bridge | Protocol Bridging, Retry |
| Email Notification Engine | Event-driven email notification service | Router, Mail Adapter |
| Legacy System Poller | FTP polling + fixed-width parsing | Splitter, Aggregator |
| Audit Logger | Event-driven audit system | Publish-Subscribe, WireTap |

---

# File Processing Pipeline

## Scenario
A system that monitors a directory for CSV files, validates data, transforms it into domain objects, and publishes events to Kafka.

## Features
- File Inbound Adapter
- CSV parsing
- Validation filter
- Message transformation
- Error Channel
- Kafka outbound adapter

## Architecture Flow
````text
File → Transformer → Filter → Service → Kafka
````

## Why This Matters
Demonstrates classic batch-to-event integration.

---

# Order Processing Orchestrator

## Scenario
Receives orders via REST and orchestrates:

- Inventory validation
- Payment processing
- Notification dispatch

## Features
- HTTP Inbound Gateway
- Content-based Router
- Aggregator
- Correlation IDs
- Dead Letter Channel
- Retry Advice

## Architecture Flow
````text
HTTP → Router → Inventory
→ Payment
→ Aggregator → Notification
````

## Why This Matters
Shows orchestration patterns in distributed systems.

---

# Multi-Protocol Bridge

## Scenario
Consumes events from Kafka, transforms payload, calls external REST API, and publishes the result to RabbitMQ.

## Features
- Kafka Inbound Adapter
- REST outbound gateway
- RabbitMQ outbound adapter
- Retry with exponential backoff
- Error handling

## Architecture Flow
````text
Kafka → Transformer → REST → RabbitMQ
````

## Why This Matters
Represents real enterprise integration use cases.

---

# Email Notification Engine

## Scenario
Receives domain events and sends email notifications based on routing rules.

## Features
- Router
- Filter
- SMTP Adapter
- Template rendering
- Async channels

## Architecture Flow
````text
Queue → Router → Mail Service
````

## Why This Matters
Demonstrates event-driven communication.

---

# Legacy System Poller

## Scenario
Polls an FTP server for fixed-width files, parses them, persists to database, and emits events.

## Features
- FTP Inbound Adapter
- Fixed-width parsing
- Splitter
- Aggregator
- Transactional flows

## Architecture Flow
````text
FTP → Splitter → Service → DB → Event
````

## Why This Matters
Very common in corporate legacy integration scenarios.

---

# Audit Logger

## Scenario
Listens to internal application events and stores audit logs asynchronously.

## Features
- Publish-Subscribe Channel
- WireTap
- Async processing
- Database logging

## Architecture Flow
````text
Event → PubSub → Audit Service
````

## Why This Matters
Shows cross-cutting integration concerns.

---

# Design Principles Applied

All projects demonstrate:

- Enterprise Integration Patterns (EIP)
- Message-driven architecture
- Loose coupling
- Clear flow modeling
- Observability
- Error handling strategies

---

# Recommended Execution Order

If studying sequentially:

1. File Processing Pipeline
2. Email Notification Engine
3. Multi-Protocol Bridge
4. Order Processing Orchestrator
5. Legacy System Poller
6. Audit Logger

---

# Goal of This Folder

These projects are not simple demos.

They aim to demonstrate:

- Architectural thinking
- Integration design skills
- Enterprise-grade patterns
- Practical usage of Spring Integration

---

# Final Thought

Spring Integration is best learned by building integration flows.

This folder exists to turn concepts into real architecture.


