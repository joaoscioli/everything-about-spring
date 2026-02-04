# Architecture

This document describes the **architectural model promoted by Spring Modulith**, focusing on how applications are structured, how module boundaries are enforced, and how the system evolves over time.

Spring Modulith does not change the runtime topology of an application.  
Instead, it introduces **explicit architectural structure inside a monolithic deployment**.

---

## 1. Architectural Overview

A Spring Modulith application is deployed as a **single, monolithic unit**, typically running as one JVM process.

Internally, however, the application is composed of **well-defined application modules** that represent cohesive business capabilities.

This leads to a clear separation between:

* **Physical architecture** — a single deployable unit
* **Logical architecture** — multiple autonomous modules with enforced boundaries

> A Spring Modulith application is physically monolithic but logically modular.

This distinction allows teams to preserve operational simplicity while gaining architectural clarity.

---

## 2. Modular Monolith Architecture

A modular monolith is not merely a monolith with organized packages.

It is an architectural approach where:

* Modules are explicit architectural units
* Dependencies between modules are intentional and controlled
* Boundaries are validated automatically

In contrast:

* **Traditional monoliths** rely on conventions and discipline to maintain structure
* **Spring Modulith** enforces structure through tooling and validation
* **Microservices** extend modularity into runtime distribution

Spring Modulith brings architectural rigor to monolithic systems without introducing network boundaries.

---

## 3. Application Modules

An **Application Module** is the fundamental architectural building block in Spring Modulith.

Each module:

* Represents a distinct business capability or domain concept
* Encapsulates its internal state and behavior
* Exposes a clearly defined public API
* Hides internal implementation details from other modules

Modules are not layers and should not be organized around technical concerns such as `util`, `common`, or `shared`.

> Modules should model the business, not the framework.

This alignment with business concepts supports Domain-Driven Design and long-term maintainability.

---

## 4. Module Boundaries and Dependency Rules

Spring Modulith introduces **explicit rules for module dependencies**.

These rules ensure that:

* Dependencies are directional and intentional
* Cyclic dependencies between modules are prohibited
* Internal types cannot be accessed from outside the module
* Only exposed APIs may be referenced by other modules

If a dependency violates these rules, it is detected during testing or build execution.

> If a dependency is not explicitly allowed, it is considered an architectural violation.

This transforms architecture from documentation into an enforceable constraint.

---

## 5. Module Interaction Patterns

Modules collaborate using well-defined interaction patterns.

### Direct Invocation

Direct method calls between modules are permitted when:

* The interaction is synchronous
* The dependency is explicitly allowed
* The call targets a module’s public API

This approach is suitable for simple queries and tightly scoped operations.

---

### Event-Based Collaboration

Event-based communication is the preferred collaboration mechanism between modules.

Using published application events:

* Reduces direct coupling
* Preserves module autonomy
* Enables asynchronous processing
* Improves future extractability

Events represent **facts that have occurred**, not commands or requests.

> Events describe what happened, not what should happen next.

---

## 6. Published Events as Architectural Contracts

Published events act as **stable contracts between modules**.

Key characteristics include:

* Producers are unaware of consumers
* Consumers react independently
* Events cross module boundaries intentionally

This decoupling allows modules to evolve independently while maintaining consistent system behavior.

Events complement, rather than replace, proper domain modeling and transactional boundaries.

---

## 7. Package Structure and Code Organization

Package structure plays a critical role in expressing architectural intent.

In a Spring Modulith application:

* Each module resides in its own root package
* Internal packages remain inaccessible from the outside
* Public APIs are clearly identifiable

A common internal structure may include:

* `domain` — core business logic
* `application` — use cases and orchestration
* `infrastructure` — technical integrations

> Package structure is an architectural tool, not a cosmetic choice.

Clear organization enables automated analysis and boundary enforcement.

---

## 8. Architectural Validation and Enforcement

Spring Modulith treats architecture as a **testable artifact**.

Through dedicated test support, the framework can:

* Analyze module structure
* Detect illegal dependencies
* Validate architectural constraints automatically

Violations fail fast, preventing architectural erosion over time.

This approach replaces manual reviews and tribal knowledge with objective verification.

---

## 9. Evolution and Scalability Paths

Spring Modulith supports long-term system evolution.

Because modules are:

* Clearly bounded
* Loosely coupled
* Explicitly integrated

They can evolve independently or, if necessary, be extracted into separate deployable units.

> Spring Modulith reduces the cost of architectural change over time.

This makes it possible to defer distribution decisions until they are justified by real system demands.

---

## Conclusion

Spring Modulith does not impose microservices, nor does it promote architectural stagnation.

It provides a disciplined foundation for building **clear, modular, and evolvable systems** within a monolithic deployment model.

By enforcing boundaries and validating dependencies, Spring Modulith enables teams to scale software **without sacrificing simplicity**.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*