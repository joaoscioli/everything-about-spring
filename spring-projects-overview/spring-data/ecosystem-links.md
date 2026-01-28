# Spring Data — Ecosystem Links

This document provides a **curated map of the Spring Data ecosystem**.

Rather than teaching usage or configuration, it explains **which Spring Data modules exist, how they relate to each other, and where each one fits conceptually**.

---

## Spring Data Commons (The Core)

**Spring Data Commons** is the foundational module of the entire Spring Data family.

Key characteristics:

* Not used directly by applications
* Defines shared abstractions and infrastructure
* Required by all store-specific Spring Data modules

It provides:

* Repository base interfaces
* Query abstraction and metadata
* Mapping context infrastructure
* Auditing support

> Every Spring Data module stands on Spring Data Commons.

---

## Relational Data Modules

Relational database support in Spring Data is divided by **persistence model**, not by database vendor.

### JPA-Based Modules

#### Spring Data JPA

Integrates Spring Data with the Java Persistence API (JPA).

* Relies on a JPA provider (e.g., Hibernate)
* Supports ORM features such as entity lifecycle and persistence context
* Suitable for complex domain models and rich object graphs

#### Spring Data R2DBC

Provides **reactive relational data access**.

* Non-blocking, reactive programming model
* No traditional JPA persistence context
* Suitable for high-concurrency, reactive systems

---

### JDBC-Based Modules

#### Spring Data JDBC

Offers a simpler, more explicit relational mapping model.

* No persistence context or lazy loading
* Focuses on aggregate roots
* Encourages explicit SQL control

Spring Data JDBC trades ORM convenience for **predictability and simplicity**.

---

## NoSQL Data Modules

Spring Data supports multiple NoSQL technologies, grouped by **data model** rather than vendor.

### Document Stores

#### Spring Data MongoDB

Designed for document-oriented databases.

* Flexible schema
* Nested document support
* Commonly used for content-heavy or evolving data models

---

### Key-Value Stores

#### Spring Data Redis

Integrates Redis as a key-value or in-memory data store.

* High-performance access
* Commonly used for caching, sessions, and fast lookup data
* Limited querying capabilities compared to document or relational stores

---

### Column & Wide-Column Stores

#### Spring Data Cassandra

Targets wide-column databases.

* Optimized for high write throughput
* Eventual consistency model
* Query patterns defined upfront by data modeling

---

### Graph Databases

#### Spring Data Neo4j

Supports graph-oriented data models.

* Nodes and relationships as first-class concepts
* Suitable for highly connected data
* Querying focused on graph traversal

---

### Search Engines

#### Spring Data Elasticsearch

Integrates search engines into Spring applications.

* Full-text search capabilities
* Index-based querying
* Often used alongside a primary data store

---

## Reactive Data Access

Reactive support in Spring Data focuses on **execution model**, not domain semantics.

Key points:

* Reactive repositories use non-blocking I/O
* Backpressure-aware data access
* Suitable for reactive pipelines and event-driven systems

Reactive programming changes *how* data is accessed, not *what* the domain represents.

> Reactive data access changes execution, not domain logic.

---

## Supporting & Integration Modules

Some Spring Data modules provide **cross-cutting capabilities** rather than data storage.

### Spring Data REST

* Exposes repositories as RESTful endpoints
* Convention-driven API generation
* Suitable for rapid prototyping and internal APIs

---

### Spring Data Envers

* Integrates auditing and versioning
* Tracks entity changes over time
* Built on top of Hibernate Envers

---

### Querydsl Integration

* Type-safe query construction
* Advanced query customization
* Optional integration with multiple Spring Data modules

---

## Integration with Spring Boot

Spring Boot acts as the **assembly and runtime layer** for Spring Data.

* Starter dependencies bundle required modules
* Auto-configuration sets up repositories and data sources
* Opinionated defaults reduce setup overhead

> Spring Boot assembles Spring Data; Spring Data defines behavior.

---

## Official Documentation

Spring Data documentation is organized by module.

### Main Entry Point

* [https://docs.spring.io/spring-data/](https://docs.spring.io/spring-data/)

From there, each module provides:

* Reference documentation
* API documentation
* Module-specific guides

---

## Summary

The Spring Data ecosystem is:

* Modular by design
* Unified by common abstractions
* Flexible across persistence technologies

Understanding how these modules relate enables developers to:

* Choose the right data access strategy
* Avoid mismatched abstractions
* Navigate the Spring Data landscape confidently

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*
