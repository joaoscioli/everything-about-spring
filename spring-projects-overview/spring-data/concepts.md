# Spring Data — Core Concepts

This document describes the **foundational concepts and mental models** behind Spring Data.

It is intentionally **store-agnostic** and **framework-focused**, avoiding tutorials and configuration details. The goal is to explain *how to think* when using Spring Data, not *how to write CRUD code*.

---

## 🧠 Repository Abstraction

At the heart of Spring Data lies the **Repository abstraction**.

A repository represents a **collection-like access point** to persistent data, expressed as an interface. Instead of writing concrete DAO implementations, developers declare *what* operations are needed, and Spring Data provides the *how*.

Key characteristics:

* Repositories are defined as **interfaces**, not classes
* Implementations are generated at runtime using proxies
* Repositories express **intent**, not persistence mechanics

This abstraction aligns well with architectural styles such as **DDD**, **Hexagonal Architecture**, and **Clean Architecture**, where repositories act as *ports* between the domain and infrastructure layers.

> Repositories describe *what* you need from persistence, not *how* persistence works.

---

## 🧬 Repository Hierarchy

Spring Data provides a hierarchy of repository interfaces, each adding capabilities and assumptions.

Common examples include:

* `CrudRepository` — basic create, read, update, delete operations
* `PagingAndSortingRepository` — adds pagination and sorting
* Store-specific repositories (e.g. `JpaRepository`) — add persistence-engine-specific features

Choosing a more specific repository interface means:

* More convenience
* More coupling to a specific store or persistence model

There is no “best” repository type — only the one that best fits the use case.

> The more specific the repository, the more assumptions it carries.

---

## 🔍 Query Methods & Query Derivation

Spring Data can automatically generate queries based on repository method names. This mechanism, known as **query derivation**, reduces boilerplate and improves readability for simple queries.

Characteristics:

* Based on naming conventions
* Readable and self-documenting for common access patterns
* Automatically translated into store-specific queries

However, query derivation is **not designed for complex logic**. Long or overly expressive method names are a signal that the abstraction is being stretched too far.

> Query derivation optimizes for clarity, not complexity.

---

## ✍️ Explicit Queries

When query derivation reaches its limits, Spring Data allows **explicit queries** to be defined.

Depending on the store, this may include:

* JPQL or native SQL (relational databases)
* Store-native query languages (NoSQL databases)

Explicit queries:

* Provide full control over query behavior
* Improve performance for complex scenarios
* Reduce portability across different stores

Using explicit queries is not a failure of abstraction — it is a **deliberate escape hatch**.

> Explicit queries are a feature, not a failure.

---

## 🧩 Custom Repository Implementations

Spring Data supports **custom repository implementations** for cases where neither query derivation nor explicit queries are sufficient.

This mechanism allows developers to:

* Extend repository behavior
* Encapsulate complex persistence logic
* Combine Spring Data with lower-level data access APIs

Custom repositories should remain focused on **data access concerns**. Business logic belongs in services or domain layers, not in repositories.

> Custom repositories exist to extend behavior, not to leak business logic.

---

## 🔄 Entity Lifecycle & Persistence Context (High-Level)

In many Spring Data modules, entities participate in a **lifecycle** managed by the persistence engine.

At a high level, entities may be:

* Transient (not associated with persistence)
* Managed (tracked by the persistence context)
* Detached (no longer tracked)

The persistence context affects:

* Change detection
* Write behavior
* Consistency guarantees

Although lifecycle details vary across stores, the core idea remains the same: **persistence state influences runtime behavior**, often implicitly.

> Persistence context affects behavior even when you don’t see it.

---

## 🔐 Transactions & Consistency

Spring Data repositories fully participate in **Spring’s transaction management**.

Important principles:

* Repositories do not start or manage transactions themselves
* Transaction boundaries are defined by Spring (typically at the service layer)
* Consistency guarantees depend on the underlying store

Understanding transaction propagation and scope is essential to avoid subtle bugs and data inconsistencies.

> Repositories don’t manage transactions — Spring does.

---

## 🎯 Projections & Data Shaping

Fetching entire entities is not always efficient or desirable.

Spring Data supports **projections** to shape query results according to use-case needs:

* Interface-based projections
* DTO-based projections

Benefits include:

* Reduced memory usage
* Improved query performance
* Clearer intent at query boundaries

Choosing the right projection strategy is both a **performance** and **design decision**.

> Fetch only what your use case needs.

---

## 📄 Pagination, Sorting & Streaming

Spring Data treats pagination and sorting as **first-class concerns**.

Concepts include:

* Paging vs slicing
* Sorting as part of the repository contract
* Streaming large result sets

These mechanisms are critical for scalability and should be considered early in system design.

> Pagination is a design decision, not a UI concern.

---

## 🕒 Auditing & Cross-Cutting Concerns

Many applications require tracking metadata such as:

* Creation timestamps
* Modification timestamps
* User or system actors

Spring Data provides auditing support as an **infrastructure concern**, keeping domain models focused on business behavior.

> Auditing is infrastructure, not domain logic.

---

## 📌 Final Notes

Spring Data concepts are designed to be:

* Consistent across modules
* Flexible across architectures
* Explicit about trade-offs

Understanding these mental models is essential before diving into store-specific implementations.

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*
