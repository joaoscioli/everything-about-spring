# When to Use Spring Modulith

Spring Modulith is a Spring project focused on **structuring and governing the internal architecture of a monolithic application**.

It does not change how applications are deployed or executed. Instead, it helps teams design **clear, enforceable module boundaries** inside a single Spring Boot application.

This document explains **when Spring Modulith is the right choice**, **when it is unnecessary**, and **how it fits into an evolutionary architecture strategy**.

---

## When Spring Modulith Is a Good Fit

Spring Modulith is most valuable when an application is still a monolith, but its **internal complexity is becoming difficult to manage**.

You should consider Spring Modulith if your system:

* Is deployed as a **single application**, but contains multiple business domains
* Has grown beyond a simple CRUD or single responsibility
* Suffers from **implicit and accidental coupling** between packages
* Lacks clear ownership boundaries inside the codebase
* Requires architectural rules that go beyond conventions

Common signals that indicate the need for Spring Modulith:

* Developers avoid changing code outside their area
* Package-by-layer no longer reflects business structure
* Circular dependencies start to emerge
* Tests require loading large portions of the application
* Architectural decisions exist only in documentation, not in code

> Spring Modulith addresses architectural complexity **without introducing distributed system complexity**.

---

## Typical Use Cases

Spring Modulith is commonly used in:

* **Growing monolithic applications** expected to live for years
* **Enterprise backends** with multiple domains or subdomains
* **Domain-Driven Design (DDD)**-oriented systems
* Applications that require **strong transactional consistency**
* Teams that want **microservice-like discipline** without microservices

It is particularly effective when:

* The application is packaged and deployed as a single artifact
* All modules share the same database
* In-process communication is preferred over network calls

Spring Modulith improves **maintainability, clarity, and evolvability**, not scalability through distribution.

---

## When Spring Modulith Is Not Necessary

Spring Modulith introduces architectural concepts and constraints that may be unnecessary for small or short-lived systems.

It may be excessive if:

* The application is small and stable
* There is a single, well-defined business domain
* The codebase is maintained by one or two developers
* The project is an early-stage MVP or a disposable prototype

In these scenarios, **plain Spring Boot with good package discipline** is usually sufficient.

> Not every monolith needs to be modularized formally.

---

## Spring Modulith vs Plain Spring Boot

Spring Boot and Spring Modulith solve different problems.

Spring Boot focuses on:

* Application startup and runtime
* Auto-configuration and dependency management
* Deployment and operational concerns

Spring Modulith focuses on:

* Internal architecture
* Module boundaries
* Dependency rules between parts of the system

Spring Modulith **does not replace Spring Boot**.
It builds on top of it, adding architectural structure where Spring Boot intentionally remains flexible.

Use Spring Modulith when:

* Package structure alone is no longer enough
* You want architectural decisions to be enforceable by the framework

---

## Spring Modulith vs Microservices

Spring Modulith is often misunderstood as a stepping stone to microservices. While it can support that evolution, it is not its primary purpose.

Spring Modulith:

* Single deployment unit
* In-process communication
* Strong consistency
* Low operational complexity

Microservices:

* Multiple deployment units
* Network-based communication
* Eventual consistency
* High operational and cognitive overhead

> Spring Modulith often solves problems that teams mistakenly try to solve with microservices.

In many systems, a well-structured modular monolith is simpler, faster to evolve, and easier to operate than a distributed architecture.

---

## Evolutionary Architecture Perspective

Spring Modulith fits naturally into an evolutionary architecture approach.

A healthy progression often looks like:

1. Simple monolithic application
2. Modular monolith with Spring Modulith
3. Optional extraction of selected modules into services

By making module boundaries explicit early, Spring Modulith:

* Clarifies domain ownership
* Reduces accidental coupling
* Makes future extraction safer and cheaper

Importantly, Spring Modulith is not a mandatory step toward microservices.
For many systems, it is the **final and optimal architectural state**.

---

## Decision Checklist

Spring Modulith is a good choice if you can answer "yes" to several of the following:

* [ ] Does the application contain multiple business domains?
* [ ] Is architectural coupling slowing down development?
* [ ] Do you want stronger structure without distributed complexity?
* [ ] Is the system expected to evolve over many years?

If most answers are "no", introducing Spring Modulith may add unnecessary complexity.

---

## Summary

Use Spring Modulith when:

* A monolith is growing in complexity
* Clear and enforceable module boundaries are required
* Microservices would introduce more problems than they solve

Avoid Spring Modulith when:

* The system is trivial or short-lived
* Architectural discipline can be maintained informally

> Build one application. Structure it deliberately. Evolve only when the problem demands it.

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*
