# Spring AMQP — Common Pitfalls

This document outlines the most common architectural and operational mistakes made when designing systems with Spring AMQP and RabbitMQ.

Messaging increases decoupling in time, but it also increases **system complexity**.  
Understanding these pitfalls is essential for building reliable production systems.

---

## Treating RabbitMQ Like Kafka

### The Mistake

Using RabbitMQ as if it were an event streaming platform:

* Expecting long-term message retention
* Designing for unlimited replay
* Treating queues like immutable logs

### Why It Happens

Both Kafka and RabbitMQ are message brokers, but they solve different problems.

### Impact

* Incorrect architectural assumptions
* Operational overload
* Misaligned scalability expectations

RabbitMQ is optimized for **message routing and delivery**, not log-based event streaming.

---

## Ignoring Idempotency

### The Mistake

Assuming that each message will be processed exactly once.

### Reality

Spring AMQP supports **at-least-once delivery**.  
Message duplication is normal and expected.

### Impact

* Duplicate payments
* Repeated side effects
* Inconsistent state transitions

Idempotency must be implemented at the **business layer**, not assumed at the infrastructure level.

---

## Overusing Automatic Acknowledgement

### The Mistake

Enabling automatic acknowledgement without understanding its implications.

### Impact

* Silent message loss
* No recovery after processing failure
* Reduced system reliability

Acknowledgement strategy should reflect business guarantees, not convenience.

---

## Excessive Queue Proliferation

### The Mistake

Creating a new queue for every feature, team, or minor variation.

### Impact

* Operational complexity
* Monitoring fragmentation
* Difficult lifecycle management

Queues represent **real coupling** in the system and should be designed intentionally.

---

## Putting Business Logic in Message Converters

### The Mistake

Embedding domain logic in message converters.

### Impact

* Tight coupling between transport and business logic
* Difficult testing
* Hidden side effects

Converters should handle **format translation only**, not business decisions.

---

## Ignoring Concurrency and Backpressure

### The Mistake

Increasing listener concurrency without understanding system limits.

### Why It Happens

More threads are often assumed to mean more performance.

### Impact

* Broker saturation
* CPU exhaustion
* Thread contention
* Reduced throughput

Concurrency tuning must consider:

* Processing time per message
* Broker capacity
* Network latency
* Downstream dependencies

More consumers do not automatically increase throughput.

---

## Not Designing a Dead-Letter Strategy

### The Mistake

Ignoring dead-letter exchanges (DLX) and dead-letter queues (DLQ).

### Impact

* Messages stuck in retry loops
* Silent data loss
* Undiagnosed failures

Dead-letter handling should be explicitly designed as part of the system’s failure strategy.

DLQ is not a trash bin — it is a diagnostic and recovery mechanism.

---

## Mixing Command and Event Semantics

### The Mistake

Publishing events expecting a specific response.  
Or sending commands as broadcasts.

### Impact

* Hidden coupling
* Confusing message flows
* Hard-to-reason-about system behavior

Commands and events have distinct semantics and should be treated differently.

---

## Tight Coupling Through Serialization

### The Mistake

Sharing domain classes across services for message serialization.

### Impact

* Coordinated deployments
* Versioning difficulties
* Reduced system autonomy

Message contracts must evolve independently of internal domain models.

---

## Treating Messaging as a Silver Bullet

### The Mistake

Using messaging for every type of communication.

### Impact

* Increased latency
* Higher operational complexity
* Harder debugging

Not every integration requires asynchronous messaging.  
Sometimes, synchronous HTTP or direct method calls are more appropriate.

---

## Ignoring Observability

### The Mistake

Deploying messaging systems without monitoring:

* Consumer lag
* Retry rates
* Dead-letter volumes

### Impact

* Failures go unnoticed
* Backpressure accumulates silently
* Production incidents escalate quickly

Messaging systems require active observability.

---

## Final Thought

Spring AMQP provides powerful abstractions for AMQP-based messaging.

However:

* It does not eliminate distributed systems complexity.
* It does not guarantee exactly-once delivery.
* It does not replace architectural discipline.

Reliable messaging systems require deliberate design, clear contracts, and operational awareness.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
