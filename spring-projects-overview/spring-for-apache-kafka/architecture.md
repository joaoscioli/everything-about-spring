# Architecture

Spring for Apache Kafka provides a structured integration layer between Spring applications and the Apache Kafka broker cluster.

It does not replace Kafka’s client API.  
Instead, it wraps and manages the Kafka client within the Spring container lifecycle, offering consistent configuration, threading, transactions, and error handling.

This document explains how the system works internally and how responsibilities are divided.

---

## High-Level Architecture

At a high level, the architecture can be visualized as:
````text
Application Code
↓
Spring Kafka Abstractions
↓
Kafka Client (Producer / Consumer)
↓
Kafka Broker Cluster
````

Spring for Apache Kafka sits between the application layer and the Kafka Java client, coordinating:

* Bean lifecycle management
* Listener container execution
* Threading and concurrency
* Offset management
* Transaction coordination
* Error handling

The Kafka broker cluster remains responsible for:

* Message storage
* Partition leadership
* Replication
* Durability guarantees
* Consumer group coordination

---

## Core Components

Spring for Apache Kafka is structured around producer and consumer abstractions.

---

### Producer Side

The producer flow is built around the following components:

* **ProducerFactory**
* **KafkaTemplate**
* **Serializer**
* **Transaction Manager (optional)**

#### Flow

1. The application calls `KafkaTemplate`.
2. `KafkaTemplate` delegates to a `ProducerFactory`.
3. The factory creates or retrieves a `KafkaProducer`.
4. The producer serializes the record.
5. The record is sent to the Kafka broker.

`KafkaTemplate` simplifies:

* Asynchronous sends
* Callbacks
* Transactions
* Partition targeting

It does not change Kafka’s delivery semantics — it coordinates them within Spring.

---

### Consumer Side

The consumer model is more complex and centers around listener containers.

Core components:

* **ConsumerFactory**
* **KafkaMessageListenerContainer**
* **ConcurrentMessageListenerContainer**
* **MessageConverter**
* **ErrorHandler / DefaultErrorHandler**

When using `@KafkaListener`, Spring automatically:

1. Creates a listener container.
2. Creates a Kafka consumer instance.
3. Starts a poll loop.
4. Dispatches records to the listener method.

The listener itself is not the consumer — it is invoked by a managed container.

---

## Listener Container Model

The listener container is the heart of the architecture.

It is responsible for:

* Polling the Kafka broker
* Managing consumer threads
* Invoking the listener method
* Coordinating offset commits
* Handling failures

Two primary container implementations exist:

### KafkaMessageListenerContainer

* Single-threaded
* One consumer instance
* Simpler execution model

### ConcurrentMessageListenerContainer

* Multiple consumer threads
* Parallel consumption
* Scales with partition count

The container abstracts Kafka’s poll loop and integrates it into the Spring lifecycle.

---

## Threading & Concurrency Model

Concurrency in Spring Kafka is controlled at the container level.

Key rules:

* Each consumer thread handles one or more partitions.
* Parallelism is limited by the number of partitions.
* Increasing concurrency beyond partition count provides no additional parallelism.

Rebalancing occurs when:

* A new consumer joins the group
* A consumer leaves or crashes
* Topic partition count changes

During rebalancing:

* Partition assignments change
* In-flight processing may be interrupted
* Ordering remains guaranteed within each partition

Concurrency must be configured with awareness of partition strategy.

---

## Offset Management

Offset management determines how the system tracks message consumption.

Kafka tracks offsets per consumer group and partition.

Spring supports multiple acknowledgment modes:

* **RECORD** – commit after each record
* **BATCH** – commit after each poll batch
* **MANUAL** – application explicitly acknowledges
* **MANUAL_IMMEDIATE** – commit immediately upon acknowledgment

Important distinction:

Offset commit does not equal successful business processing unless coordinated carefully.

Incorrect configuration may cause:

* Duplicate processing
* Message loss
* Processing gaps

Offset strategy must align with reliability requirements.

---

## Transaction Model

Spring Kafka supports Kafka transactions for stronger delivery guarantees.

Transactions coordinate:

* Message production
* Consumer offset commits

When properly configured:

1. Producer starts a transaction.
2. Messages are sent.
3. Offsets are committed within the same transaction.
4. Transaction is committed atomically.

This enables exactly-once semantics under specific broker and client configurations.

However:

* Exactly-once requires broker support.
* Idempotent producers must be enabled.
* Consumer isolation level must be configured correctly.

Transactions increase reliability but also operational complexity.

---

## Error Handling & Retry Flow

When a listener throws an exception:

1. The container intercepts the failure.
2. The configured error handler decides the strategy.
3. The record may be retried.
4. The record may be redirected to a Dead Letter Topic (DLT).

Spring provides:

* DefaultErrorHandler
* Backoff policies
* Retry topic configuration
* Dead Letter publishing recoverers

Retries may cause reprocessing and must be configured with idempotent logic in mind.

---

## Partitioning & Ordering Guarantees

Kafka guarantees ordering only within a single partition.

Spring does not alter this behavior.

Important principles:

* Message key determines partition.
* Ordering is preserved per partition.
* No global ordering exists across partitions.
* Rebalancing does not break partition-level ordering.

Designing partition strategy is an architectural decision, not a framework feature.

---

## Observability Integration

Spring Kafka integrates with:

* Micrometer metrics
* Distributed tracing systems
* Spring Boot Actuator

Metrics commonly exposed:

* Consumer lag
* Record processing time
* Send latency
* Error counts

Observability is critical for detecting:

* Lag accumulation
* Stalled consumers
* Retry loops
* Backpressure conditions

---

## Interaction with the Kafka Broker

Spring for Apache Kafka does not manage the Kafka cluster.

The broker remains responsible for:

* Replication
* Leader election
* Persistence
* Log compaction
* Consumer group coordination

Spring manages only:

* Client configuration
* Listener execution
* Offset coordination
* Application-level error handling

Understanding this boundary is essential for correct system design.

---

## Architectural Flow Example

### Producer Flow

1. Application publishes event via `KafkaTemplate`.
2. Producer serializes record.
3. Broker appends record to partition log.
4. Broker replicates record.
5. Send callback completes.

---

### Consumer Flow

1. Listener container polls broker.
2. Broker returns batch of records.
3. Container invokes listener method.
4. Business logic executes.
5. Offset is committed (based on configuration).

If an exception occurs:

* Error handler determines retry or redirection.
* Offset commit strategy adjusts accordingly.

---

## Architectural Perspective

Spring for Apache Kafka provides structure around Kafka’s distributed model.

It standardizes:

* Producer configuration
* Consumer execution
* Offset control
* Transaction boundaries
* Error handling

But it does not remove distributed system complexity.

Effective use requires understanding:

* Partitioning strategies
* Consumer group behavior
* Offset semantics
* Failure scenarios
* Idempotent processing

Spring abstracts the mechanics —  
it does not abstract the architectural consequences.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*


