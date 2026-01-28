# Spring Data — Architecture

This document describes the **internal architecture** of Spring Data.

It focuses on *how Spring Data is structured, how its components interact, and where responsibilities begin and end*. The content is intentionally **store-agnostic** and avoids application-level configuration or tutorials.

---

##  Big Picture: Where Spring Data Fits

Spring Data operates as an **intermediate abstraction layer** between application code and concrete persistence technologies.

At a high level:

* Application and domain layers depend on **repository interfaces**
* Spring Data provides runtime implementations of those repositories
* Actual data access is delegated to **store-specific engines** (JPA providers, database drivers, NoSQL clients)

Spring Data does not own:

* Database connections
* Transaction boundaries
* Query execution engines

Instead, it **orchestrates interactions** between Spring, the persistence store, and the domain model.

> Spring Data orchestrates persistence — it does not own it.

---

## Spring Data Commons — The Foundation

The architectural core of Spring Data lives in the `spring-data-commons` module.

This module defines **shared contracts and infrastructure** used by all Spring Data store implementations.

Key responsibilities include:

* Repository interfaces and base contracts
* Query method abstraction and metadata
* Mapping context abstractions
* Auditing infrastructure
* Repository discovery and registration

Store-specific modules build on top of these contracts rather than redefining them.

> Commons defines behavior; store modules define integration.

---

## Repository Infrastructure (Factories & Proxies)

Spring Data repositories are **runtime constructs**, not static implementations.

The creation process follows a consistent pattern:

1. Repository interfaces are detected during application context initialization
2. A `RepositoryFactory` is selected based on the store module
3. A proxy implementation is generated at runtime
4. Method calls are intercepted and delegated to appropriate execution paths

This proxy-based design allows Spring Data to:

* Combine multiple behaviors transparently
* Inject cross-cutting concerns
* Route method calls dynamically

> Repositories exist at runtime as proxies, not concrete classes.

---

## Query Execution Pipeline

Every repository method invocation passes through a **query execution pipeline**.

Conceptually, Spring Data performs the following steps:

* Classify the repository method

    * Query derivation
    * Explicit query annotation
    * Custom repository implementation
* Parse and validate query metadata
* Translate the query into a store-specific representation
* Execute the query using the underlying engine
* Map raw results back to domain types or projections

This pipeline is deterministic and rule-based, not heuristic.

> Query methods are classified, not guessed.

---

##  Store-Specific Modules

Each Spring Data store module implements the contracts defined in Spring Data Commons.

Responsibilities of store modules include:

* Mapping domain models to persistence representations
* Translating abstract queries into store-native queries
* Integrating with store-specific transaction semantics
* Handling result materialization

While the programming model remains consistent, **behavior and capabilities vary** across stores.

Examples of variation include:

* Entity lifecycle management
* Query expressiveness
* Consistency guarantees
* Transaction support

> Abstraction is consistent; behavior is not identical.

---

## Integration with Spring Core

Spring Data is tightly integrated with the Spring Framework by design.

Key integration points include:

* Dependency injection and bean lifecycle management
* Application context scanning and bootstrap
* AOP-based proxying and interception
* Exception translation

Spring Data repositories are managed Spring beans and fully participate in the container lifecycle.

> Spring Data is coupled to the Spring container by design, not by accident.

---

## Transaction Management Integration

Spring Data delegates transaction management to **Spring’s transaction abstraction**.

Important architectural boundaries:

* Repositories do not open, commit, or roll back transactions
* Transaction scope is defined externally (typically at the service layer)
* Store modules adapt to the active transaction context

This separation ensures that:

* Transaction semantics remain consistent across repositories
* Data access logic stays focused on persistence concerns

> Transactions surround repositories, not the other way around.

---

## Extension Points

Spring Data is designed to be **extensible through contracts**, not internal modification.

Supported extension mechanisms include:

* Custom repository implementations
* Repository fragments
* Custom query strategies
* Integration with lower-level data access APIs

These extension points allow Spring Data to coexist with custom persistence logic without breaking architectural boundaries.

> Spring Data is extensible by contract, not by hacking.

---

##  Architectural Trade-offs

Spring Data intentionally optimizes for **consistency and developer productivity**.

This comes with trade-offs:

* Proxy-based indirection adds runtime complexity
* Debugging generated implementations can be non-trivial
* Abstractions may leak in advanced scenarios
* Not all store features are uniformly exposed

Understanding these trade-offs is essential for responsible use.

> Convenience always comes with architectural cost.

---

## Summary

Spring Data is architected as:

* A shared abstraction layer (Commons)
* Runtime-generated repository implementations
* Store-specific integration modules
* Deep Spring Framework integration

It provides a **cohesive and extensible architecture** that balances abstraction with control.

Mastering this architecture enables developers to:

* Debug Spring Data behavior confidently
* Extend repositories safely
* Make informed architectural decisions

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*
