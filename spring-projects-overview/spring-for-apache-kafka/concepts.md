# Concepts

Spring for Apache Kafka builds on top of Apache Kafka’s distributed log model and integrates it into the Spring programming model.

To use it effectively, developers must understand both:

1. Core Kafka concepts (infrastructure-level)
2. Spring Kafka abstractions (application-level)

This document defines the essential concepts and their architectural implications.

---

# Kafka Core Concepts

These concepts exist independently of Spring.

---

## Topic

A **topic** is a logical stream of records.

It represents a category of events or messages.

**Architectural implication:**  
Topics are not queues. Multiple consumers can read the same topic independently using different consumer groups.

---

## Partition

A **partition** is an ordered, append-only log within a topic.

Each topic is divided into one or more partitions.

**Architectural implication:**

* Ordering is guaranteed only within a partition.
* Parallelism is limited by the number of partitions.
* Partition strategy directly affects scalability and ordering guarantees.

---

## Offset

An **offset** is the sequential position of a record within a partition.

Offsets are tracked per consumer group.

**Architectural implication:**

Offset management defines delivery guarantees.  
Incorrect offset handling can lead to message loss or duplication.

---

## Producer

A **producer** writes records to a topic.

Producers decide:

* Target topic
* Message key
* Optional partition (explicit or derived from key)

**Architectural implication:**

Partition selection determines ordering and load distribution.

---

## Consumer

A **consumer** reads records from one or more partitions.

Consumers poll the broker for new records.

**Architectural implication:**

Consumers are pull-based.  
Processing speed directly affects lag.

---

## Consumer Group

A **consumer group** is a set of consumers sharing the same `group.id`.

Kafka distributes partitions across consumers in the group.

**Architectural implication:**

* Each partition is consumed by only one consumer in a group.
* Adding consumers increases parallelism only if partitions are available.
* Rebalancing occurs when group membership changes.

---

## Broker

A **broker** is a Kafka server node.

The cluster consists of multiple brokers handling:

* Storage
* Replication
* Partition leadership
* Group coordination

Spring does not manage brokers — it interacts with them.

---

## Record

A **record** (message) consists of:

* Key
* Value
* Headers
* Timestamp

**Architectural implication:**

Headers can carry metadata such as tracing or retry information.

---

## Retention

Kafka stores records for a configurable period or size.

Records are not deleted after consumption.

**Architectural implication:**

Consumers can replay events if offsets are reset.

---

## Log Compaction

Log compaction retains only the latest record for each key.

**Architectural implication:**

Useful for state reconstruction and event sourcing patterns.

---

# Spring Kafka Abstractions

These concepts are introduced by Spring to simplify integration.

---

## KafkaTemplate

High-level abstraction for sending messages.

It wraps the Kafka producer and integrates with:

* Spring configuration
* Transactions
* Observability

**Implication:**  
Standardizes producer usage within Spring applications.

---

## ProducerFactory

Creates and manages KafkaProducer instances.

**Implication:**  
Controls producer configuration and lifecycle.

---

## ConsumerFactory

Creates KafkaConsumer instances used by listener containers.

**Implication:**  
Centralizes consumer configuration.

---

## @KafkaListener

Annotation-based model for consuming messages.

Spring automatically:

* Creates listener containers
* Manages consumer threads
* Dispatches records to the annotated method

**Implication:**  
The method is not the consumer — it is invoked by a managed container.

---

## Listener Container

Component responsible for:

* Poll loop
* Thread management
* Offset commits
* Error handling

Two main types:

* KafkaMessageListenerContainer (single-threaded)
* ConcurrentMessageListenerContainer (multi-threaded)

**Implication:**  
Understanding the container is essential for concurrency and reliability design.

---

## MessageConverter

Converts Kafka records into application-level objects.

Commonly used for:

* JSON deserialization
* Type mapping

**Implication:**  
Serialization strategy must remain compatible across services.

---

## Error Handler (DefaultErrorHandler)

Handles exceptions thrown by listeners.

Responsible for:

* Retry strategies
* Backoff policies
* Dead Letter publishing

**Implication:**  
Centralizes failure strategy instead of scattering try/catch logic.

---

## Retry Topic

A separate topic used for delayed reprocessing of failed records.

**Implication:**  
Enables structured retries without blocking the main consumer flow.

---

## Dead Letter Topic (DLT)

Destination for messages that cannot be successfully processed.

**Implication:**  
Prevents infinite retry loops and isolates poison messages.

---

# Delivery Semantics & Guarantees

Delivery semantics define how reliably messages are processed.

---

## At-Least-Once Delivery

Default Kafka behavior.

Messages may be processed more than once.

**Implication:**  
Consumers must implement idempotent logic.

---

## At-Most-Once Delivery

Offsets are committed before processing.

Messages may be lost if processing fails.

**Implication:**  
Suitable only when occasional loss is acceptable.

---

## Exactly-Once Semantics

Ensures records are processed once without duplication.

Requires:

* Idempotent producers
* Transactions enabled
* Proper consumer isolation configuration

**Implication:**  
Increases system complexity and must be justified by business requirements.

---

## Idempotence

Ability to process the same message multiple times without side effects.

**Implication:**  
Often more practical than strict exactly-once guarantees.

---

# Reliability & Failure Concepts

---

## Rebalancing

Occurs when:

* Consumers join or leave a group
* Partition count changes

Partitions are reassigned across consumers.

**Implication:**  
In-flight processing may be interrupted.  
Listeners must tolerate reprocessing.

---

## Consumer Lag

Difference between the latest offset and the committed offset.

**Implication:**  
Lag indicates processing delay and potential bottlenecks.

---

## Backoff

Delay strategy between retry attempts.

**Implication:**  
Prevents tight retry loops that overload the system.

---

## Poison Message

A message that consistently fails processing.

**Implication:**  
Must be redirected to DLT to avoid infinite retries.

---

# Concurrency & Scaling Concepts

---

## Parallelism

True parallelism is limited by partition count.

**Implication:**  
Adding more consumer threads than partitions does not increase throughput.

---

## Throughput

Dependent on:

* Partition count
* Consumer concurrency
* Processing time
* Broker performance

Scaling requires coordinated changes at infrastructure and application levels.

---

## Ordering Guarantee

Kafka guarantees ordering only within a partition.

**Implication:**  
Global ordering across partitions is not supported.

---

## Blocking Listener

Long-running processing inside a listener thread.

**Implication:**

* Increases lag
* Reduces throughput
* May cause rebalancing if poll loop stalls

Asynchronous delegation may be required for heavy workloads.

---

# Conceptual Perspective

Spring for Apache Kafka provides structure and consistency around Kafka’s distributed log model.

However, it does not eliminate:

* Distributed system complexity
* Partition strategy decisions
* Delivery guarantee trade-offs
* Failure handling challenges

Understanding these concepts is essential for building reliable and scalable event-driven systems.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
