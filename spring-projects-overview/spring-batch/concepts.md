# Concepts

Spring Batch is built on a well-defined set of abstractions that formalize how batch processing systems should behave.

This document defines the core concepts of Spring Batch and clarifies their responsibilities and relationships.

Understanding these concepts is essential to designing reliable, restartable, and scalable batch systems.

---

# 1 - Job Model Concepts

## Job

A **Job** represents a complete batch process.

It is:

- A logical container of one or more Steps
- Identified by a unique name
- Parameterized by JobParameters
- Executed by a JobLauncher

A Job defines *what* should run.
Its executions define *when and how* it ran.

---

## JobInstance

A **JobInstance** represents a unique logical run of a Job, identified by:
````text
Job Name + JobParameters
````

If the same Job is launched with the same parameters:

- It refers to the same JobInstance.
- A new execution attempt will be created (JobExecution).

This distinction enables restart behavior and prevents unintended duplicate processing.

---

## JobExecution

A **JobExecution** represents a single runtime attempt to execute a JobInstance.

A JobInstance may have multiple JobExecutions if:

- It fails and is restarted
- It is retried manually

Each JobExecution has:

- Status (STARTING, STARTED, FAILED, COMPLETED, STOPPED)
- Start and end timestamps
- Exit status
- Associated StepExecutions

---

## JobParameters

**JobParameters** are immutable parameters passed at job launch time.

They serve two purposes:

1. Identify the JobInstance.
2. Provide runtime configuration.

Examples:

- Processing date
- File name
- Business identifier
- Partition key

Immutability ensures reproducibility and restart consistency.

---

# 2 - Step Model Concepts

## Step

A **Step** is a unit of work inside a Job.

Characteristics:

- Executes independently
- Has its own transaction boundaries
- Produces execution metadata
- Returns a status

A Step may be:

- Chunk-oriented
- Tasklet-based

Architecturally, the Step defines the main transactional scope of a Job.

---

## StepExecution

A **StepExecution** represents a runtime attempt to execute a Step.

It records:

- Read count
- Write count
- Commit count
- Skip count
- Failure exceptions
- Execution status

Each StepExecution belongs to a JobExecution.

---

## Tasklet

A **Tasklet** is a simplified step model.

Instead of chunk processing, it executes a single task:
````text
execute() → RepeatStatus
````

Tasklets are suitable for:

- Cleanup logic
- File preparation
- Pre/post-processing tasks
- Simple database updates

They are not designed for large streaming datasets.

---

# 3 - Chunk-Oriented Processing Concepts

Chunk-oriented processing is the default and most common model.

---

## ItemReader

An **ItemReader** is responsible for reading data one item at a time.

Contract:
````text
T read()
````

Characteristics:

- Forward-only by design
- Returns null when data is exhausted
- May maintain state via ExecutionContext

Examples:

- File readers
- JDBC readers
- JPA readers
- Messaging readers

---

## ItemProcessor

An **ItemProcessor** transforms input items into output items.

Contract:
````text
O process(T item)
````

Characteristics:

- Optional component
- May return null to filter items
- Ideally stateless and deterministic

It encapsulates business transformation logic.

---

## ItemWriter

An **ItemWriter** persists a list of processed items.

Contract:
````text
void write(List<? extends T> items)
````

Characteristics:

- Receives a chunk of items
- Executed inside a transaction
- Responsible for batch persistence

Examples:

- Database writers
- File writers
- Messaging writers

---

## Chunk

A **Chunk** is a group of items processed within a single transaction.

It defines:

- The commit boundary
- The restart boundary
- The memory buffering size

Chunk ≠ arbitrary batch size  
Chunk = transactional unit of work.

---

## Commit Interval

The **Commit Interval** defines how many items are processed before a transaction commit.

It impacts:

- Performance
- Memory usage
- Restart granularity
- Database locking behavior

Choosing the correct commit interval is a key tuning decision.

---

# 4 - State & Metadata Concepts

## JobRepository

The **JobRepository** persists execution metadata.

It stores:

- JobInstance
- JobExecution
- StepExecution
- ExecutionContext

It enables:

- Restartability
- Auditing
- Execution tracking
- Concurrency control

Without the JobRepository, Spring Batch would be stateless.

---

## ExecutionContext

The **ExecutionContext** is a key-value store persisted between executions.

It can exist at:

- Job scope
- Step scope

Common uses:

- Cursor position tracking
- File offsets
- Aggregated counters
- Intermediate state

ExecutionContext enables precise restart behavior.

---

## JobExplorer

The **JobExplorer** provides read-only access to job metadata.

It is typically used for:

- Monitoring
- Administrative dashboards
- Operational queries

---

## JobLauncher

The **JobLauncher** is the entry point for starting a Job.

Responsibilities:

- Validate parameters
- Create JobExecution
- Delegate execution to the Job

It does not contain business logic.

---

# 5 - Fault Tolerance Concepts

Spring Batch provides declarative fault tolerance mechanisms.

---

## Retry

Retry attempts to reprocess a failed item within the same chunk.

Used when failures are:

- Transient
- Recoverable
- External (e.g., temporary database issue)

---

## Skip

Skip ignores a failed item and continues processing.

Used when:

- The item is invalid
- Business rules allow partial processing

Skip policies define acceptable failure thresholds.

---

## Rollback

Rollback occurs when:

- A transaction fails
- Retry policy is exhausted
- An unrecoverable exception is thrown

Rollback reverts the entire chunk.

---

## Exception Classification

Exceptions can be classified as:

- Retryable
- Skippable
- Fatal

This classification drives the fault tolerance behavior.

---

# 6 - Scaling Concepts

Spring Batch supports multiple scaling abstractions.

---

## Multi-Threaded Step

Processes items in parallel within a single Step.

---

## Parallel Flow

Executes multiple Steps concurrently.

---

## Partitioning

Divides a Step into multiple partitions that process subsets of data independently.

---

## Remote Chunking

Distributes processing or writing to remote workers using messaging.

---

# 7 - Lifecycle & Extension Concepts

## ItemStream

An **ItemStream** allows components to participate in lifecycle callbacks:

- open()
- update()
- close()

It integrates with ExecutionContext to persist state.

---

## Listeners

Spring Batch provides listener interfaces for lifecycle hooks:

- JobExecutionListener
- StepExecutionListener
- ChunkListener
- ItemReadListener
- ItemProcessListener
- ItemWriteListener

Listeners enable cross-cutting concerns such as:

- Logging
- Metrics
- Auditing
- Custom validation

---

# Conceptual Relationships

Understanding relationships between abstractions is critical.

- JobInstance = Job + JobParameters
- JobExecution belongs to JobInstance
- StepExecution belongs to JobExecution
- ExecutionContext belongs to JobExecution or StepExecution
- Chunk executes inside a Step
- Reader/Processor/Writer operate inside a Chunk
- Transaction boundaries are defined at the Step (chunk commit level)

This layered model ensures:

- Deterministic execution
- Reliable restartability
- Clear separation of concerns
- Infrastructure-managed state

---

# Conceptual Summary

Spring Batch defines a structured vocabulary for building batch systems.

Its core ideas are:

- Jobs are logical processes.
- Steps define transactional work units.
- Chunks define commit boundaries.
- Metadata enables restartability.
- Fault tolerance is declarative.
- Scaling is infrastructure-driven.

Mastering these concepts allows engineers to design:

- Reliable data pipelines
- Large-scale processing systems
- Restartable financial workflows
- Enterprise-grade batch architectures

Spring Batch is not merely an API.

It is a **formalized execution model for stateful batch systems**.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
