# Architecture

Spring Batch is not just a scheduling tool or a job runner.

It is a **stateful execution engine** designed to provide reliable, transactional, restartable, and scalable batch processing.

This document explains how Spring Batch works internally and the architectural decisions behind its design.

---

## Architectural Overview

At a high level, Spring Batch is composed of:

- A **Job execution model**
- A **Step orchestration layer**
- A **Chunk processing engine**
- A **Metadata repository**
- A **Transaction management integration layer**

Conceptually:
````text
Job
├── Step 1 (Chunk-Oriented)
│ ├── ItemReader
│ ├── ItemProcessor
│ └── ItemWriter
│
└── Step 2 (Tasklet)
````

The framework acts as an execution coordinator that:

1. Launches jobs
2. Orchestrates steps
3. Manages transactions
4. Persists execution metadata
5. Controls failure and restart behavior

Spring Batch is fundamentally **execution-state driven**.

---

## Core Execution Model

### Job

A **Job** represents a logical batch process.

Key characteristics:

- Identified by a name
- Parameterized via `JobParameters`
- Can have multiple executions
- Composed of one or more Steps

Important distinction:

- **JobInstance** → Logical definition + parameters
- **JobExecution** → A specific runtime attempt
- **JobParameters** → Identify uniqueness of a JobInstance

This separation allows:

- Restarting failed jobs
- Preventing duplicate executions
- Tracking historical runs

---

### Step

A **Step** is a unit of work inside a Job.

Each Step:

- Has its own transaction boundaries
- Can be chunk-oriented or tasklet-based
- Produces its own execution metadata
- Has independent success/failure status

Steps may be:

- Sequential
- Conditional
- Parallel (if configured)

Architecturally, the Step is the primary transactional boundary.

---

## Chunk Processing Architecture

Chunk-oriented processing is the default and most powerful model.

The execution cycle:
````text
repeat until data exhausted:
read()
process()
buffer
if buffer size == commit interval:
write()
commit transaction
````

### Internal Mechanics

- Items are read one at a time.
- Processed items are temporarily buffered.
- When the buffer reaches the configured commit interval:
    - The writer is invoked.
    - The transaction is committed.
    - Metadata is updated.

### Why Chunking Exists

Chunking provides:

- Controlled memory usage
- Explicit transaction boundaries
- Restart precision
- Fault isolation
- Optimized database interaction

A chunk is not just a batch size.

It defines:

- Transaction scope
- Recovery boundary
- Performance tuning parameter

---

## Job Repository & Metadata Layer

The **JobRepository** is the core infrastructure component that enables restartability.

It stores:

- JobInstance
- JobExecution
- StepExecution
- ExecutionContext

### ExecutionContext

A key-value store persisted between executions.

Used for:

- Storing cursor positions
- Tracking processed offsets
- Maintaining state between restarts

Architectural implication:

> The JobRepository transforms Spring Batch from a stateless processor into a stateful, recoverable execution engine.

Without it, restartability would not be possible.

---

## Transaction Management Model

Spring Batch does not implement its own transaction engine.

It delegates transaction control to the **Spring Framework TransactionManager**.

### Transaction Boundaries

- Each Step defines a transaction strategy.
- In chunk-oriented steps:
    - Each chunk is executed inside a transaction.
    - Commit interval defines transaction size.

### Failure Handling

Within a chunk:

- If an exception occurs:
    - The transaction rolls back.
    - Retry or skip logic may apply.
- Metadata is updated only after successful commit.

This guarantees:

- Data consistency
- Atomic chunk processing
- Predictable recovery

---

## Fault Tolerance Architecture

Spring Batch supports:

- Retry policies
- Skip policies
- Exception classification
- Rollback control

These mechanisms operate within the chunk transaction model.

Architectural principle:

> Fault handling is declarative and infrastructure-driven, not business-logic driven.

This keeps business processing code clean and deterministic.

---

## Scalability Model

Spring Batch supports multiple scaling strategies.

### 1. Multi-Threaded Step

- Parallel processing within a single step
- Shared JobRepository
- Suitable for CPU-bound workloads

---

### 2. Parallel Steps

- Independent steps running concurrently
- Useful for unrelated processing branches

---

### 3. Partitioning

- Splits a Step into multiple partitions
- Each partition processes a subset of data
- Can run locally or distributed

Best for large datasets divided by key ranges.

---

### 4. Remote Chunking

- Reading occurs centrally
- Processing/writing occurs remotely
- Uses messaging middleware

Best for distributed, heavy processing workloads.

---

## End-to-End Execution Flow

1. `JobLauncher` starts execution.
2. `JobRepository` creates a JobExecution record.
3. First Step begins.
4. Chunk loop runs:
    - Read
    - Process
    - Buffer
    - Write
    - Commit
5. StepExecution metadata updated.
6. Next Step executes.
7. Final Job status persisted.

If failure occurs:

- Execution state is saved.
- Restart resumes from last committed chunk.

---

## Design Principles Behind Spring Batch

Spring Batch is built on several architectural principles:

### Separation of Concerns

- Business logic lives in Reader/Processor/Writer.
- Execution logic lives in the framework.

---

### Stateful Execution

Execution metadata is externalized and persisted.

This enables:

- Restartability
- Auditability
- Historical tracking

---

### Declarative Infrastructure

Retry, skip, transaction, and scaling policies are configured declaratively.

---

### Idempotent Processing Model

Design assumes:

- Processing may be retried
- Chunks may roll back
- Systems must handle re-execution safely

---

### Infrastructure Isolation

Core engine components are independent of:

- Specific databases
- Specific file formats
- Specific messaging providers

Adapters handle integration.

---

## Architectural Summary

Spring Batch is a:

- Stateful execution engine
- Transaction-aware processing framework
- Metadata-driven orchestration system

It provides:

- Deterministic execution
- Reliable recovery
- Controlled scaling
- Infrastructure abstraction

Understanding Spring Batch architecture requires understanding:

- Execution state management
- Chunk transaction semantics
- Metadata persistence
- Restart boundaries
- Scaling trade-offs

Spring Batch is not merely about running jobs.

It is about building **resilient, production-grade data processing systems**.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*


