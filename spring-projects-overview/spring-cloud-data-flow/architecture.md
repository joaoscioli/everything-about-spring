# Architecture — Spring Cloud Data Flow

Spring Cloud Data Flow (SCDF) is architected as a **control plane for distributed data processing systems**, orchestrating stream and task-based applications deployed to cloud-native runtime platforms.

It separates:

* Control responsibilities  
  from
* Data processing responsibilities  
  from
* Infrastructure concerns

Understanding this separation is essential to designing systems with SCDF effectively.

---

## 01 - Architectural Overview

At a high level, Spring Cloud Data Flow consists of:

* A **Control Plane**
* A **Data Plane**
* A **Messaging Layer**
* A **Deployment Platform**
* A **Metadata Store**

SCDF does not process business data itself.  
Instead, it coordinates applications that do.

The architecture can be conceptualized as:

Control Plane → Orchestrates → Data Plane Applications → Connected via Middleware → Deployed to Runtime Platform

---

## 02 - Control Plane

The control plane is responsible for orchestration and lifecycle management.

### Data Flow Server

The Data Flow Server is the central coordinating component.

Responsibilities include:

* Registering stream and task applications
* Defining logical stream topologies
* Launching and managing tasks
* Deploying applications to target platforms
* Monitoring execution state
* Managing configuration properties

It does **not** process data streams directly.  
It manages applications that do.

---

### Spring Cloud Skipper

Skipper is responsible for stream lifecycle management.

Responsibilities:

* Versioning stream definitions
* Upgrading running streams
* Rolling back to previous versions
* Managing release history

Skipper applies only to **stream applications**, not tasks.

---

## 03 - Data Plane

The data plane consists of the actual processing applications.

These are typically Spring Boot applications built using:

* Spring Cloud Stream (for streaming)
* Spring Batch (for batch processing)
* Custom Spring Boot logic

SCDF treats these applications as deployable units.

---

### Stream Applications

Stream applications are:

* Long-running
* Event-driven
* Connected via messaging middleware

They follow common roles:

* **Source** → Produces events
* **Processor** → Transforms events
* **Sink** → Consumes and persists events

Each role is an independent deployable application.

---

### Task Applications

Task applications are:

* Short-lived
* Run-to-completion processes
* Typically batch-oriented

They may:

* Execute ETL logic
* Perform data migrations
* Run scheduled jobs
* Execute composed workflows

Tasks generate execution metadata stored in the metadata database.

---

## 04 - Messaging Layer

SCDF relies on external messaging systems.

Supported middleware includes:

* Apache Kafka
* RabbitMQ

The messaging system:

* Connects stream applications
* Enables decoupling
* Supports horizontal scaling
* Determines partitioning and consumer group behavior

SCDF does not implement messaging.  
It delegates this responsibility to the middleware.

---

## 05 - Deployment Layer

SCDF abstracts the runtime environment using Spring Cloud Deployer.

Supported platforms include:

* Kubernetes
* Cloud Foundry
* Local development environments

The deployment layer is responsible for:

* Creating runtime instances (e.g., containers or pods)
* Injecting configuration
* Managing scaling parameters
* Handling lifecycle events

Each stream or task application is deployed as an independent runtime unit.

---

## 06 - Logical vs Physical Topology

A key architectural distinction in SCDF is between **logical definitions** and **physical deployments**.

### Logical Topology

A stream is defined declaratively, for example:

source | processor | sink

This definition describes:

* Data flow direction
* Processing stages
* Logical composition

It does not describe infrastructure.

---

### Physical Topology

At deployment time, SCDF translates logical definitions into:

* Individual runtime instances
* Containerized applications
* Middleware bindings
* Scalable deployment units

Each logical component becomes a physically deployed application.

This translation layer is one of SCDF’s core architectural responsibilities.

---

## 07 - Execution Models

Spring Cloud Data Flow supports two distinct execution models.

---

### Stream Execution Model

Streams are:

* Long-running processes
* Event-driven
* Horizontally scalable
* Potentially stateful depending on middleware

Scaling is typically achieved by:

* Increasing instance counts
* Leveraging Kafka partitioning
* Using consumer groups

Streams remain active until explicitly undeployed.

---

### Task Execution Model

Tasks are:

* Ephemeral
* Run-to-completion
* Triggered manually or scheduled
* Capable of orchestration through composed tasks

Task executions generate metadata such as:

* Start time
* End time
* Exit status
* Execution parameters

Metadata is stored in a relational database.

---

## 08 - Scaling Model

Scaling in SCDF occurs at the application level.

SCDF does not scale logic — it scales instances.

Scaling mechanisms depend on:

* Middleware partitioning (Kafka topics)
* Consumer group behavior
* Deployment platform scaling configuration

For streams:

* Instance count directly affects throughput
* Partition count must align with scaling strategy

For tasks:

* Parallel execution may be configured
* Platform capabilities determine concurrency limits

Effective scaling requires understanding both middleware and runtime platform behavior.

---

## 09 - Versioning and Upgrade Model

Stream versioning is managed through Spring Cloud Skipper.

Key characteristics:

* Streams are versioned as releases
* Upgrades are declarative
* Rollbacks are supported
* Deployment state is tracked

This enables:

* Controlled evolution of pipelines
* Reduced downtime during upgrades
* Operational consistency

Tasks do not use Skipper and are versioned at the application level.

---

## 10 - Operational Architecture

SCDF integrates with operational tooling through:

* Micrometer (metrics)
* Platform-native logging
* Health checks
* Execution metadata persistence

The platform requires a relational database for:

* Stream definitions
* Task definitions
* Execution history
* Release metadata

Operational observability is distributed:

* Application metrics come from the data plane
* Orchestration state comes from the control plane
* Infrastructure metrics come from the runtime platform

---

## 11 - Failure Domains

Spring Cloud Data Flow introduces multiple failure domains.

Understanding them is critical.

### Control Plane Failure

If the Data Flow Server becomes unavailable:

* Existing streams continue running
* New deployments cannot be initiated

---

### Middleware Failure

If Kafka or RabbitMQ fails:

* Streams are interrupted
* Backpressure and data loss risks may arise depending on configuration

---

### Application Failure

If a stream application crashes:

* The runtime platform may restart it
* Consumer group rebalance may occur
* Throughput may be temporarily reduced

---

### Platform Failure

If Kubernetes or Cloud Foundry experiences issues:

* Deployment instability may occur
* Horizontal scaling may be impacted

Each domain must be monitored independently.

---

## 12 - Summary

Spring Cloud Data Flow is architected as a **distributed orchestration system for data pipelines**.

It separates:

* Orchestration (Control Plane)
* Data Processing (Data Plane)
* Messaging Infrastructure
* Deployment Infrastructure

Its architecture enables:

* Declarative pipeline composition
* Independent scaling of processing stages
* Controlled stream versioning
* Cloud-native deployment patterns

However, it introduces additional infrastructure complexity and operational responsibilities.

Spring Cloud Data Flow should be adopted when **data pipeline orchestration becomes a first-class architectural concern**, not merely a development convenience.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
