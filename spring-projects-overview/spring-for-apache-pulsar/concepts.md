# Core Concepts

Spring for Apache Pulsar builds on top of Apache Pulsar’s distributed messaging model while aligning it with Spring’s programming abstractions.

Understanding this module requires understanding both:

• Pulsar’s distributed log architecture  
• Spring’s messaging and container model

This document focuses on the essential mental models.

---

## Topic

A **topic** is the fundamental message channel in Pulsar.

It represents:

* A distributed append-only log
* A logical message stream
* A durable storage abstraction

Topics may be:

* Non-partitioned
* Partitioned (for horizontal scalability)

Spring does not abstract topics away — they are explicit in configuration.

---

## Partitioned Topics

Partitioned topics split a logical topic into multiple partitions.

Why this matters:

* Increases parallelism
* Improves throughput
* Enables horizontal scaling of consumers

Architectural implication:

Ordering is only guaranteed **within a partition**, not across partitions.

Designing event-driven systems requires awareness of this constraint.

---

## Subscription

A **subscription** defines how consumers receive messages from a topic.

It is not just a consumer — it defines distribution semantics.

Supported subscription types:

### Exclusive
Only one consumer allowed.

• Guarantees ordering  
• Simple failure model

---

### Shared
Multiple consumers share messages in round-robin.

• High parallelism  
• No strict ordering

---

### Failover
Multiple consumers exist, but only one is active.

• Active-passive model  
• Maintains ordering

---

### Key_Shared
Messages with the same key go to the same consumer.

• Balances ordering and parallelism  
• Ideal for entity-based routing

---

Choosing the correct subscription type is an architectural decision, not a configuration detail.

---

## Producer

A **producer** publishes messages to a topic.

In Spring, producers are accessed through:

`PulsarTemplate`

Responsibilities:

* Serialization
* Routing
* Async or sync publishing
* Schema enforcement

Producers may:

* Use batching
* Enable compression
* Define routing strategies

---

## Consumer

A **consumer** subscribes to a topic through a subscription.

In Spring, consumers are typically defined using:

`@PulsarListener`

The listener container manages:

* Consumer lifecycle
* Threading
* Acknowledgment
* Error handling

The consumer is stateful in the context of its subscription.

---

## Schema

Pulsar has built-in schema management.

Schemas define:

* Data structure
* Serialization format
* Compatibility rules

Common schema types:

* JSON
* Avro
* Protobuf
* Key/Value

Schema evolution requires compatibility strategies:

* Backward
* Forward
* Full

Spring integrates schema handling into message conversion automatically.

---

## Message

A Pulsar message consists of:

* Payload
* Key (optional)
* Properties (headers)
* Metadata (event time, publish time)

Spring maps:

* Payload → Method parameter
* Properties → Headers
* Metadata → Optional injection

Messages are immutable once persisted.

---

## Acknowledgment

Acknowledgment defines when a message is considered successfully processed.

Modes may include:

* Automatic acknowledgment
* Manual acknowledgment

If a message is not acknowledged:

• It may be redelivered  
• It may move to a dead letter topic

By default, delivery is **at-least-once**.

---

## Delivery Semantics

Pulsar guarantees:

At-least-once delivery.

Implication:

Applications must design for **idempotency**.

Exactly-once is not a broker guarantee — it must be engineered through:

* Idempotent consumers
* Deduplication strategies
* Transactional patterns (where applicable)

---

## Dead Letter Topic (DLT)

Failed messages can be routed to a Dead Letter Topic.

Used when:

* Max retries exceeded
* Processing consistently fails

DLTs are essential for:

* Observability
* Recovery workflows
* Manual intervention pipelines

Ignoring DLT strategy leads to silent message loss or infinite retry loops.

---

## Backpressure

Backpressure controls message consumption rate.

If consumers process slower than producers publish:

• Memory pressure increases  
• Redelivery cycles intensify  
• System instability emerges

Proper tuning involves:

* Listener concurrency
* Consumer receiver queue size
* Processing time optimization

---

## Multi-Tenancy

Apache Pulsar supports:

* Tenants
* Namespaces
* Topic isolation

Spring applications typically interact at the topic level but must respect namespace strategy defined at infrastructure level.

This enables:

* Logical isolation
* Governance
* Access control

---

## Observability

Spring integrates Pulsar with:

* Micrometer
* Spring Boot Actuator

Important metrics:

* Consumer lag
* Throughput
* Error rates
* Processing latency

Event-driven systems require observability-first design.

---

## Mental Model Summary

Spring for Apache Pulsar is not just about sending and receiving messages.

It is about designing:

• Distributed log-driven systems  
• Fault-tolerant consumer architectures  
• Schema-evolving event streams  
• Horizontally scalable pipelines

To use it effectively, developers must understand:

* Topic partitioning
* Subscription semantics
* Delivery guarantees
* Idempotent processing strategies
* Concurrency tuning

Without these concepts, messaging systems become unstable, inconsistent, and difficult to scale.

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*