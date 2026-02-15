# Pitfalls

Spring for Apache Kafka provides a powerful abstraction over Kafka’s distributed log model.

However, it does not eliminate distributed system complexity.

This document highlights common architectural and operational mistakes, explains why they happen, and provides guidance for avoiding them.

---

# Delivery Semantics Misunderstandings

## Mistake

Assuming Kafka provides exactly-once delivery by default.

## Why It Happens

Kafka is often described as “reliable” and “durable,” leading teams to assume strong delivery guarantees out of the box.

## Consequence

Applications may:

* Process duplicate events unexpectedly
* Fail to implement idempotent logic
* Rely on guarantees that are not configured

## Recommendation

Understand that:

* Kafka defaults to **at-least-once delivery**.
* Exactly-once semantics require:
    * Idempotent producers
    * Transactions enabled
    * Proper consumer isolation configuration

> Exactly-once is a configuration outcome — not a default behavior.

---

# Offset Management Mistakes

## Mistake

Committing offsets before processing is safely completed.

## Why It Happens

Teams misunderstand acknowledgment modes or use auto-commit without understanding its implications.

## Consequence

* Message loss (at-most-once behavior)
* Silent processing gaps
* Inconsistent system state

## Recommendation

Align offset strategy with reliability requirements:

* Use manual acknowledgment when necessary.
* Avoid committing offsets before business logic completes.
* Test failure scenarios explicitly.

Offset management defines delivery guarantees.

---

# Concurrency & Partition Misconceptions

## Mistake

Increasing consumer concurrency without increasing partitions.

## Why It Happens

Concurrency is often mistaken for unlimited parallelism.

## Consequence

* No actual throughput improvement
* Misconfigured scaling expectations
* Wasted resources

## Recommendation

Understand that:

* Parallelism is limited by partition count.
* Each partition is consumed by only one thread per consumer group.

> Partitions define your scaling ceiling.

Design partition strategy intentionally.

---

# Error Handling Anti-Patterns

## Mistake

Handling exceptions directly inside listener methods without structured retry or DLT configuration.

## Why It Happens

Developers rely on try/catch instead of container-level error strategies.

## Consequence

* Infinite retry loops
* Blocked partitions
* Poison messages stalling consumption
* Hidden production failures

## Recommendation

Use:

* DefaultErrorHandler
* Backoff strategies
* Dead Letter Topics

Centralize failure handling rather than scattering exception logic.

---

# Transaction Misuse

## Mistake

Enabling Kafka transactions without understanding their scope and cost.

## Why It Happens

Teams attempt to achieve exactly-once processing without analyzing architectural impact.

## Consequence

* Increased latency
* Higher operational complexity
* False assumption of distributed transaction coordination

Important clarification:

Kafka transactions do not automatically coordinate with database transactions.

## Recommendation

Use transactions only when required by business guarantees.

Often, idempotent processing is simpler and more robust than strict transactional coordination.

---

# Serialization & Schema Problems

## Mistake

Using unversioned JSON payloads without schema evolution strategy.

## Why It Happens

JSON is easy to adopt and requires no upfront structure.

## Consequence

* Breaking changes across services
* Runtime deserialization failures
* Tight coupling between producers and consumers

## Recommendation

Adopt a versioning strategy:

* Backward-compatible schema changes
* Schema registry (if applicable)
* Explicit event contracts

Event evolution must be intentional.

---

# Blocking & Performance Issues

## Mistake

Performing long-running or blocking operations inside the listener thread.

Examples:

* External API calls
* Heavy database operations
* File I/O

## Why It Happens

Listeners are treated like regular service methods.

## Consequence

* Increasing consumer lag
* Poll loop delays
* Rebalancing due to missed heartbeats
* Throughput degradation

## Recommendation

Consider:

* Delegating heavy work to asynchronous components
* Using worker pools
* Keeping listener logic lightweight

Listener threads should not become bottlenecks.

---

# Rebalancing Surprises

## Mistake

Ignoring the effects of consumer group rebalancing.

## Why It Happens

Rebalancing is automatic and often invisible during development.

## Consequence

* Duplicate message processing
* Interrupted in-flight operations
* Inconsistent state during redeployments

## Recommendation

Design for reprocessing:

* Make consumers idempotent
* Handle graceful shutdown correctly
* Test scaling and redeployment scenarios

Rebalancing is normal behavior, not an error condition.

---

# Observability Blind Spots

## Mistake

Deploying Kafka consumers without monitoring lag, retries, or error rates.

## Why It Happens

Kafka may appear to “just work” during initial testing.

## Consequence

* Hidden processing delays
* Silent retry loops
* Undetected Dead Letter accumulation
* Late discovery of system degradation

## Recommendation

Monitor:

* Consumer lag
* Retry metrics
* DLT volume
* Processing latency

Event-driven systems require strong observability discipline.

---

# Architectural Overreach

## Mistake

Using Kafka for all communication patterns.

## Why It Happens

Event-driven architecture is often perceived as universally superior.

## Consequence

* Unnecessary operational complexity
* Increased debugging difficulty
* Over-engineered systems

Kafka is powerful, but not always necessary.

## Recommendation

Use Kafka when:

* Decoupling across services is required
* High throughput is necessary
* Event replay provides real value
* Asynchronous communication is essential

Avoid introducing Kafka as architectural decoration.

> Event-driven architecture should solve a real scalability or decoupling problem — not exist for its own sake.

---

# Final Perspective

Spring for Apache Kafka provides structure and consistency.

However:

* It does not remove distributed system challenges.
* It does not guarantee correctness by default.
* It does not replace architectural responsibility.

Successful systems require:

* Clear delivery guarantees
* Intentional partition strategy
* Idempotent processing
* Observability discipline
* Realistic scaling expectations

Understanding these pitfalls transforms Kafka from a risk factor into a strategic advantage.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
