# Spring AMQP — Architecture

This document describes the **architectural structure** of Spring AMQP, explaining how it fits between application code and the AMQP broker, and how responsibilities are distributed across layers.

The goal is to provide a **mental model** of how Spring AMQP works internally, not a usage guide.

---

## High-Level Architecture Overview

Spring AMQP sits between the application and the AMQP broker, orchestrating messaging infrastructure using Spring abstractions while delegating protocol-level work to the native AMQP client.

````text
Application Code
↓
Spring AMQP Abstractions
↓
AMQP Client Library (RabbitMQ Java Client)
↓
AMQP Broker (RabbitMQ)
````

Spring AMQP **does not implement the AMQP protocol** and **does not replace the broker**.  
Its role is to integrate AMQP messaging into the Spring programming and lifecycle model.

---

## Core Architectural Layers

Spring AMQP is structured around four distinct layers, each with clear responsibilities.

---

### Application Layer

The application layer contains domain and business logic.

Characteristics:

* Does not manage connections or channels
* Does not control threading or consumption loops
* Interacts with messaging via:
    * `RabbitTemplate`
    * `@RabbitListener`

This layer remains agnostic of broker mechanics and protocol details.

---

### Spring AMQP Layer

This layer provides abstractions and infrastructure services.

Responsibilities:

* Connection and channel lifecycle management
* Message conversion
* Listener container orchestration
* Error handling and retries
* Transaction integration

Spring AMQP translates Spring-level semantics into AMQP operations.

---

### AMQP Client Layer

This layer is provided by the **RabbitMQ Java Client**.

Responsibilities:

* TCP connections
* AMQP channels
* Protocol framing
* Network I/O

Spring AMQP builds on top of this client and does not bypass it.

---

### Broker Layer

The broker is responsible for:

* Exchanges
* Queues
* Bindings
* Message routing and persistence

Spring AMQP assumes the broker is externally managed and operational.

---

## Message Production Architecture (Outbound Flow)

Message publishing follows a structured pipeline:

1. Application code invokes `RabbitTemplate`
2. A message converter transforms the payload
3. Spring AMQP obtains a channel from the connection factory
4. The message is published to an exchange
5. The broker routes the message to bound queues

Key architectural points:

* Channels are not thread-safe
* Spring manages channel reuse
* Publisher confirms and returns are optional but recommended
* Transactions may wrap publish operations when configured

---

## Message Consumption Architecture (Inbound Flow)

Message consumption is driven by **listener containers**, not by application code.

Common container types:

* `SimpleMessageListenerContainer`
* `DirectMessageListenerContainer`

Consumption flow:

1. The container creates one or more consumers
2. Each consumer operates on:
    * a dedicated thread
    * a single AMQP channel
3. Messages are delivered from the broker
4. Payloads are converted into domain objects
5. Listener methods are invoked
6. Acknowledgement decisions are applied

The application code is **passive** and reacts to message delivery.

---

## Concurrency and Threading Model

Concurrency in Spring AMQP is primarily controlled at the listener container level.

Key characteristics:

* One consumer typically maps to one channel
* One channel is bound to one thread
* Increasing concurrency increases parallelism but reduces ordering guarantees

Important implications:

* Message ordering is only preserved per queue and per consumer
* High concurrency may increase broker load
* Thread count does not guarantee higher throughput

Concurrency tuning must consider both **application processing time** and **broker capacity**.

---

## Error Handling Architecture

Errors can occur at multiple stages:

* Message conversion
* Listener execution
* Infrastructure interaction

Spring AMQP handles errors through:

* Error handlers
* Retry interceptors
* Message rejection or requeueing
* Dead-letter exchanges (DLQ)

Key principle:

> The AMQP broker does not understand Java exceptions.  
> Spring AMQP converts failures into protocol-level decisions.

---

## Acknowledgement and Delivery Guarantees

Spring AMQP supports multiple acknowledgement strategies:

* Automatic acknowledgement
* Manual acknowledgement
* Negative acknowledgement (nack)

Delivery characteristics:

* At-least-once delivery is the default guarantee
* Message duplication is possible
* Exactly-once delivery is not guaranteed

Idempotent message processing is a **business responsibility**, not a framework feature.

---

## Transactional Boundaries

Spring AMQP integrates with Spring’s transaction model but with important limitations.

Supported scenarios:

* Local database transactions
* Channel-level transactions

Important constraints:

* Acknowledgement and business logic may not share the same atomic boundary
* Failures after acknowledgement cannot be rolled back
* Distributed transactions are not supported

Transactional design must be explicit and conservative.

---

## Observability and Operational Architecture

Spring AMQP integrates with Spring’s observability ecosystem.

Common capabilities:

* Metrics via Micrometer
* Structured logging
* Health indicators (via Spring Boot)
* Tracing integration when supported

Operational visibility is critical for diagnosing message flow issues and backpressure.

---

## Architectural Boundaries and Non-Goals

Spring AMQP intentionally avoids certain responsibilities.

It is **not**:

* An event streaming platform
* A message broker
* An event store
* A distributed transaction coordinator

Spring AMQP focuses on **reliable AMQP integration**, not messaging system design.

---

## Summary

Spring AMQP provides a clear architectural separation between:

* Business logic
* Messaging infrastructure
* Protocol-level communication

It embraces AMQP semantics while aligning them with the Spring ecosystem.

Understanding these architectural boundaries is essential for building reliable and scalable messaging systems.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*

