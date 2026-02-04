# Concepts

This document defines the **core concepts used by Spring Modulith**.  
Its purpose is to establish a shared vocabulary for discussing architecture, design decisions, and module boundaries in modular monolith applications.

Understanding these concepts is essential before working with code.

---

## 1. Modular Monolith

A **modular monolith** is a single, deployable application that is internally structured as a set of well-defined, autonomous modules.

Key characteristics include:

* A single runtime and deployment unit
* Clear logical separation between modules
* Explicit boundaries enforced by tooling

A modular monolith is not a collection of microservices packaged together, nor is it a traditional monolith with informal package organization.

> A modular monolith is physically unified but logically decomposed.

---

## 2. Application Module

An **Application Module** is the fundamental architectural unit in Spring Modulith.

An application module:

* Represents a cohesive business capability
* Encapsulates state and behavior
* Exposes a deliberate public API
* Hides its internal implementation

Modules are architectural constructs, not technical layers.

> A module models a business concept, not a framework concern.

Common misconceptions include treating modules as:
* Layers (e.g. controller, service, repository)
* Shared utility containers
* Arbitrary package groupings

---

## 3. Module Boundary

A **module boundary** defines the limits of visibility and interaction for a module.

Boundaries control:

* Which types are accessible from outside the module
* Which dependencies are allowed
* How collaboration with other modules occurs

Boundaries are not conventions; they are enforceable architectural rules.

> A boundary defines what is allowed, not merely what is possible.

---

## 4. Public API vs Internal Implementation

Each module exposes a **public API** that defines how other modules may interact with it.

The public API:
* Consists of explicitly exposed types
* Acts as a stable contract
* Should evolve carefully

Internal implementation:
* Is hidden from other modules
* Can change without external impact
* Contains technical and domain details

Reusing internal types across module boundaries introduces tight coupling and undermines modularity.

---

## 5. Dependency Direction

**Dependency direction** expresses the intended flow of control and knowledge between modules.

In Spring Modulith:

* Dependencies must be explicit
* Cyclic dependencies are prohibited
* Direction must reflect architectural intent

Dependencies should point toward stable abstractions, not volatile implementations.

> Dependency direction encodes architectural decisions in the codebase.

---

## 6. Named Interfaces

**Named Interfaces** define the explicit contracts a module exposes to others.

They:

* Declare which packages or types are accessible externally
* Make module APIs visible and intentional
* Serve as architectural documentation

Named Interfaces help prevent accidental exposure of internal implementation details.

A common pitfall is exposing too much, which weakens encapsulation and increases coupling.

---

## 7. Published Events

**Published Events** are used for collaboration between modules.

They represent:

* Facts that have occurred within a module
* Significant domain-level changes

Events are not commands and should not be used to request behavior from other modules.

> Events describe what happened, not what should happen next.

Producers publish events without knowledge of consumers, preserving module autonomy.

---

## 8. Module Interaction Styles

Spring Modulith supports two primary interaction styles between modules.

### Direct Invocation

* Synchronous communication
* Performed through a module’s public API
* Suitable for simple queries and deterministic operations

### Event-Based Interaction

* Asynchronous or decoupled communication
* Implemented through published events
* Preferred for cross-module collaboration

Choosing the appropriate interaction style is a key architectural decision.

---

## 9. Architectural Validation

Spring Modulith treats architecture as a **verifiable artifact**.

Through automated analysis and tests, it can:

* Detect illegal dependencies
* Identify boundary violations
* Prevent architectural erosion

Architecture is no longer enforced solely through reviews or documentation.

> In Spring Modulith, architecture fails fast.

---

## Conclusion

Spring Modulith relies on clear concepts and shared understanding.

Without explicit definitions and a common vocabulary, architectural tooling cannot compensate for poor design decisions.

These concepts form the foundation for building **maintainable, modular, and evolvable monolithic systems**.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*