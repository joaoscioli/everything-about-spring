# Common Pitfalls

Spring for Apache Pulsar simplifies integration —  
but it does not remove distributed system complexity.

Most production failures are not caused by the framework,
but by incorrect architectural assumptions.

This document highlights common and costly mistakes.

---

## 01 - Ignoring Subscription Type Implications

Choosing a subscription type is not a minor configuration detail.

Each subscription model changes:

• Ordering guarantees  
• Parallelism  
• Fault tolerance behavior  
• Scalability

For example:

Using `Shared` when strict ordering is required  
→ Causes subtle data inconsistencies.

Using `Exclusive` in a horizontally scaled deployment  
→ Creates bottlenecks.

Always align subscription type with business invariants.

---

## 02 - Assuming Exactly-Once Delivery

Pulsar guarantees **at-least-once delivery** by default.

This means:

• Messages may be redelivered  
• Consumers may process duplicates

If the system is not idempotent:

• Duplicate payments  
• Duplicate state transitions  
• Corrupted aggregates

Design for idempotency from the beginning.

Exactly-once behavior must be engineered at the application level.

---

## 03 - Poor Schema Evolution Strategy

Changing message schemas without compatibility planning causes:

• Consumer deserialization failures  
• Production outages  
• Partial deployment deadlocks

Common mistakes:

• Removing required fields  
• Changing field types  
• Ignoring backward compatibility

Always define a schema evolution policy:

• Backward compatible  
• Forward compatible  
• Fully compatible

Messaging systems require versioned thinking.

---

## 04 - Overprovisioned Listener Concurrency

Increasing listener concurrency seems like a quick way to increase throughput.

But excessive concurrency can cause:

• Broker overload  
• CPU saturation  
• Memory pressure  
• Database bottlenecks downstream

Throughput tuning must consider the entire pipeline:

Producer → Broker → Consumer → Downstream System

Messaging scalability is end-to-end, not isolated.

---

## 05 - Ignoring Backpressure and Flow Control

If consumers are slower than producers:

• Receiver queues grow  
• Memory usage increases  
• Redelivery storms may occur

Spring exposes configuration options, but misuse leads to instability.

Backpressure is a design concern, not just a tuning parameter.

---

## 06 - Misconfigured Acknowledgment Mode

Incorrect acknowledgment strategy can lead to:

• Message loss  
• Infinite redelivery loops  
• Partial processing

Examples:

Acknowledging before business logic completes  
→ Data loss on failure.

Never acknowledging on exceptions  
→ Endless retries.

Acknowledgment timing must reflect true business success.

---

## 07 - Treating Messaging Like Synchronous RPC

Messaging is not REST over a queue.

Common anti-patterns:

• Blocking waiting for responses  
• Coupling services tightly through request-reply patterns  
• Expecting immediate consistency

Event-driven systems require:

• Eventual consistency  
• Asynchronous workflows  
• Compensating transactions

Misunderstanding this leads to architectural friction.

---

## 08 - Ignoring Dead Letter Topic (DLT) Strategy

Without a DLT strategy:

• Failed messages disappear into retry loops  
• Operations teams lack visibility  
• Recovery becomes manual and chaotic

A proper DLT strategy includes:

• Monitoring  
• Alerting  
• Replay workflows  
• Root cause analysis

DLT is not optional in production systems.

---

## 09 - Not Monitoring Consumer Lag

Consumer lag indicates processing delay.

If not monitored:

• Systems silently fall behind  
• SLAs are violated  
• Backlogs grow unnoticed

Observability must include:

• Throughput metrics  
• Error rates  
• Processing latency  
• Lag monitoring

Event-driven systems demand proactive observability.

---

## 10 - Overengineering Small Systems

Introducing Pulsar for:

• Small internal tools  
• Low-traffic applications  
• Single-node systems

Often increases complexity without benefit.

Distributed messaging shines at scale —  
not in trivial deployments.

---

## 11 - Underestimating Operational Complexity

Apache Pulsar introduces operational components:

• Brokers  
• BookKeeper nodes  
• Storage management  
• Replication configuration

Spring simplifies application code —
but cluster complexity remains.

Architecture decisions must consider infrastructure maturity.

---

## Strategic Lessons

Most pitfalls originate from:

• Misaligned subscription strategy  
• Lack of idempotency  
• Poor schema governance  
• Weak observability  
• Treating messaging as simple transport

Spring for Apache Pulsar provides strong abstractions,
but it does not eliminate distributed system responsibility.

---

## Final Advice

Before going to production:

✔ Validate subscription model  
✔ Design idempotent consumers  
✔ Define schema evolution rules  
✔ Configure DLT and monitoring  
✔ Load test with realistic traffic

Messaging systems fail silently —  
until they fail catastrophically.

Architectural discipline prevents that.

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*