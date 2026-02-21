# Architecture

Spring for Apache Pulsar is designed as a **layered integration architecture** that bridges the Spring programming model with the Apache Pulsar distributed messaging system.

It does not replace the native Pulsar client.  
Instead, it wraps and orchestrates it using Spring infrastructure patterns.

---

## Architectural Position

At a high level, the architecture can be visualized as:

Application Layer  
↓  
Spring Messaging Abstractions  
↓  
Spring Pulsar Infrastructure  
↓  
Apache Pulsar Java Client  
↓  
Pulsar Broker Cluster

Spring for Apache Pulsar acts as the **integration boundary** between business code and the Pulsar client API.

---

## Core Architectural Components

### 01 - PulsarTemplate

The `PulsarTemplate` is the primary abstraction for message publishing.

Responsibilities:

* Wraps Pulsar Producer APIs
* Handles serialization via configured schemas
* Applies message converters
* Simplifies async and sync publishing
* Integrates with Spring Retry (optional)

It plays a similar role to `KafkaTemplate` in Spring Kafka.

---

### 02 - Listener Container Infrastructure

Message consumption is handled through **listener containers**, not directly through the Pulsar client.

Main responsibilities:

* Consumer lifecycle management
* Thread and concurrency control
* Message dispatching
* Acknowledgment handling
* Error handling and retry
* Dead letter routing

Listener containers isolate business logic from infrastructure concerns.

---

### 03 - @PulsarListener Processing Pipeline

When a method is annotated with `@PulsarListener`, Spring:

1. Detects the annotation during context initialization
2. Registers a listener endpoint
3. Creates a listener container
4. Subscribes to the configured topic
5. Dispatches messages to the method

The runtime pipeline becomes:

Broker → Consumer → Listener Container → Message Conversion → Method Invocation → Acknowledgment

---

### 04 - Schema & Message Conversion Layer

Spring integrates with Pulsar’s schema system.

The architecture supports:

* JSON
* Avro
* Protobuf
* Key/Value schemas
* Custom schema implementations

The conversion layer:

* Maps payloads to strongly-typed method parameters
* Handles serialization/deserialization
* Supports header extraction

Schema configuration can be explicit or inferred.

---

### 05 - Boot Auto-Configuration Layer

When used with Spring Boot, the framework provides:

* PulsarClient bean
* Producer configuration
* Consumer configuration
* ListenerContainerFactory
* PulsarTemplate

Auto-configuration activates based on:

* Classpath detection
* Configuration properties
* Application context conditions

This ensures convention-over-configuration while allowing full override.

---

## Message Flow (Producer to Consumer)

### Publishing Flow

1. Application calls PulsarTemplate.send()
2. Message converted to target schema
3. Producer sends to broker
4. Broker persists message (BookKeeper storage)
5. Acknowledgment returned to producer

---

### Consumption Flow

1. Listener container polls from broker
2. Message received from subscription
3. Payload converted to target type
4. @PulsarListener method invoked
5. Acknowledgment strategy applied
6. On failure → retry or Dead Letter Topic

---

## Concurrency Model

Concurrency is controlled at the listener container level.

Configurable aspects:

* Number of concurrent consumers
* Subscription type
* Acknowledgment mode
* Batch consumption (if enabled)

Spring does not abstract away Pulsar’s subscription model — it exposes it as configuration.

Improper concurrency tuning may cause:

* Broker overload
* Memory pressure
* Consumer starvation

---

## Subscription Architecture

Spring exposes Pulsar subscription types directly:

* Exclusive
* Shared
* Failover
* Key_Shared

Each subscription type changes how listener containers distribute messages.

Architecture decisions around subscription model directly affect:

* Scalability
* Ordering guarantees
* Fault tolerance

---

## Error Handling Architecture

Error handling is layered:
````text
Listener Method  
↓  
Error Handler  
↓  
Retry (optional)  
↓  
Dead Letter Topic
````
Spring allows:

* Custom error handlers
* Retry templates
* Dead letter topic configuration

By default, Pulsar provides **at-least-once delivery**, so idempotency is an architectural concern.

---

## Distributed System Considerations

Spring for Apache Pulsar inherits Pulsar’s distributed characteristics:

* Partitioned topics
* Geo-replication
* Multi-tenant isolation
* Persistent log storage

Spring does not manage cluster topology — it delegates this entirely to Pulsar.

---

## Deployment Model

Applications using Spring for Apache Pulsar are typically:

* Spring Boot applications
* Containerized (Docker)
* Deployed on Kubernetes
* Connected to managed Pulsar clusters

The messaging infrastructure remains external to the application runtime.

---

## Architectural Philosophy

Spring for Apache Pulsar follows three design principles:

1. Infrastructure transparency  
   Developers can access the native Pulsar client if needed.

2. Convention over configuration  
   Sensible defaults provided via Boot.

3. Separation of concerns  
   Business logic remains independent of messaging infrastructure.

---

## Summary

Spring for Apache Pulsar is not a broker replacement and not a stream processing engine.

It is a **structured integration layer** that:

* Encapsulates Pulsar client complexity
* Aligns Pulsar with Spring programming patterns
* Preserves distributed messaging semantics
* Enables production-grade event-driven systems

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*