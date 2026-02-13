# Concepts — Spring Cloud Data Flow

This document defines the core concepts of Spring Cloud Data Flow (SCDF).

It serves as a formal vocabulary reference for understanding how the platform models, composes, deploys, and manages distributed data pipelines.

---

## 01 - Application

An **Application** in SCDF is a Spring Boot executable unit that performs data processing logic.

Characteristics:

* Packaged as an executable JAR or container image
* Registered in the App Registry
* Deployable to a runtime platform
* Independent and self-contained

SCDF does not generate applications.  
It orchestrates pre-built applications.

Applications are categorized as:

* Source
* Processor
* Sink
* Task

---

## 02 - Stream

A **Stream** is a logical definition of a continuous data pipeline.

It represents:

* A composition of stream applications
* A long-running event-driven process
* A topology connected through messaging middleware

A stream is not a single application.  
It is a declarative composition of multiple applications.

Streams remain active until explicitly undeployed.

---

## 03 - Source

A **Source** is a stream application that produces events.

Responsibilities:

* Generates or ingests data
* Publishes messages to the middleware
* Acts as the entry point of a stream

Examples include data ingestion from:

* HTTP endpoints
* Messaging systems
* Databases
* File systems

---

## 04 - Processor

A **Processor** is a stream application that consumes, transforms, and republishes events.

Responsibilities:

* Reads from middleware
* Applies transformation logic
* Emits processed data

Processors enable pipeline enrichment, filtering, or aggregation.

---

## 05 - Sink

A **Sink** is a stream application that consumes events and performs terminal processing.

Responsibilities:

* Reads from middleware
* Persists data
* Sends data to external systems
* Finalizes the pipeline

Sinks represent the end of a stream topology.

---

## 06 - Task

A **Task** is a short-lived application that executes once and terminates.

Characteristics:

* Run-to-completion
* Triggered manually or scheduled
* Often batch-oriented
* Generates execution metadata

Tasks differ from streams in that they are not continuously running processes.

---

## 07 - Composed Task

A **Composed Task** is a workflow that orchestrates multiple tasks.

Features:

* Sequential or parallel execution
* Conditional transitions
* Execution tracking across steps

Composed tasks enable multi-step batch workflows.

---

## 08 - Data Flow Server

The **Data Flow Server** is the central orchestration component.

Responsibilities:

* Application registration
* Stream definition management
* Task launching
* Deployment coordination
* Execution monitoring

It does not process business data.

---

## 09 - Skipper

**Spring Cloud Skipper** manages stream lifecycle and versioning.

Responsibilities:

* Stream release management
* Upgrades
* Rollbacks
* Release history tracking

Skipper applies only to streams.

---

## 10 - App Registry

The **App Registry** maintains metadata about available applications.

It maps:

Logical application names  
→  
Physical artifacts (Maven coordinates or container images)

This registry enables SCDF to deploy applications dynamically.

---

## 11 - Stream Definition

A **Stream Definition** is a declarative description of a stream topology.

It:

* Describes logical composition
* Defines application order
* Specifies bindings

It does not describe infrastructure or scaling.

---

## 12 - Deployment

**Deployment** is the process of translating a logical definition into runtime instances.

It involves:

* Allocating infrastructure resources
* Creating application instances
* Binding middleware connections
* Injecting configuration

Deployment is handled via Spring Cloud Deployer.

---

## 13 - Platform

A **Platform** is the runtime environment where applications execute.

Supported platforms include:

* Kubernetes
* Cloud Foundry
* Local environments

The platform determines:

* Scaling behavior
* Resource isolation
* Container orchestration

---

## 14 - Middleware

**Middleware** refers to the messaging system connecting stream applications.

Supported systems:

* Apache Kafka
* RabbitMQ

Middleware enables:

* Decoupling
* Backpressure handling
* Horizontal scaling
* Event durability

SCDF depends on external middleware and does not implement it internally.

---

## 15 - Release

A **Release** is a deployed version of a stream managed by Skipper.

It includes:

* Application versions
* Configuration properties
* Deployment state

Releases allow controlled upgrades and rollbacks.

---

## 16 - Execution Metadata

Execution metadata records operational details about tasks and streams.

Includes:

* Execution status
* Start and end times
* Exit codes
* Deployment information

Metadata is stored in a relational database.

---

## 17 - Logical Topology

A **Logical Topology** describes the abstract structure of a stream or composed task.

It defines:

* Application order
* Data flow direction
* Composition rules

It is infrastructure-independent.

---

## 18 - Physical Deployment

A **Physical Deployment** is the runtime instantiation of a logical topology.

It includes:

* Containers or pods
* Runtime instances
* Middleware bindings
* Platform-level scaling

This separation enables infrastructure abstraction.

---

## Summary

Spring Cloud Data Flow is built around a clear conceptual separation between:

* Logical definitions
* Deployable applications
* Orchestration control
* Messaging infrastructure
* Runtime platforms

Understanding these concepts is essential before designing or operating distributed data pipelines with SCDF.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
