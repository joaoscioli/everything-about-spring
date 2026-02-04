# Pitfalls

This document describes the **most common pitfalls when adopting Spring Modulith**, why they occur, and how to avoid them.

Spring Modulith provides strong architectural tooling, but it cannot compensate for poor modeling or shortcuts in design decisions.

---

## 1. Treating Modules as Technical Layers

### Description

Organizing modules around technical layers such as `web`, `service`, `repository`, or `infrastructure`.

### Why It Happens

* Familiarity with layered architecture
* Misunderstanding the purpose of application modules
* Attempting a mechanical migration from existing codebases

### Consequences

* High coupling between modules
* Bidirectional dependencies
* No meaningful architectural improvement

> A layered modulith is still a layered monolith.

### How to Avoid

Define modules around **business capabilities or domain concepts**, not technical responsibilities.

---

## 2. Creating a “Common” or “Shared” Module

### Description

Introducing a generic `common`, `shared`, or `core-utils` module used by all others.

### Why It Happens

* Desire to avoid code duplication
* Pressure to centralize utilities
* Short-term convenience

### Consequences

* Global coupling point
* Inverted dependency direction
* Increased change ripple effects

> A shared module often becomes a dumping ground.

### How to Avoid

Allow limited duplication or extract **stable abstractions** only when their purpose is clear and justified.

---

## 3. Bypassing Boundaries via Dependency Injection

### Description

Injecting internal beans from other modules directly, bypassing public APIs.

### Why It Happens

* All beans live in the same Spring application context
* Dependency injection makes access trivial

### Consequences

* Violated encapsulation
* Implicit and undocumented dependencies
* Fragile tests and runtime behavior

> Dependency injection should respect boundaries, not erase them.

### How to Avoid

Expose explicit module APIs and restrict injection to those contracts only.

---

## 4. Overusing Direct Module Calls

### Description

Relying exclusively on synchronous method calls for cross-module communication.

### Why It Happens

* Direct calls feel simpler and more familiar
* Event-based collaboration requires better modeling

### Consequences

* Temporal coupling between modules
* Reduced autonomy
* Higher cost of future refactoring or extraction

### How to Avoid

Prefer **event-based collaboration** for cross-module interactions and reserve direct calls for well-defined queries.

---

## 5. Misusing Events as Commands

### Description

Publishing events with the expectation that specific actions will occur in other modules.

### Why It Happens

* Confusion between events and commands
* Experience with distributed messaging systems

### Consequences

* Hidden control flow
* Implicit contracts
* Difficult debugging and maintenance

> Events are facts, not instructions.

### How to Avoid

Publish events only to signal that something has happened, never to request behavior.

---

## 6. Ignoring Architectural Validation

### Description

Disabling or ignoring architectural validation tests provided by Spring Modulith.

### Why It Happens

* Perceived slowdown in development
* Architecture viewed as documentation rather than code

### Consequences

* Gradual architectural erosion
* Boundary violations going unnoticed
* Loss of modular integrity over time

> Architecture that is not validated will eventually be violated.

### How to Avoid

Treat architectural validation as a first-class test concern and enforce it in CI pipelines.

---

## 7. Designing Modules Around Code Instead of the Domain

### Description

Defining modules based on existing code structure rather than domain boundaries.

### Why It Happens

* Fear of refactoring
* Limited understanding of the business domain
* Incremental migration without architectural intent

### Consequences

* Artificial module boundaries
* Weak encapsulation
* Low architectural value

### How to Avoid

Start from **domain modeling** and align modules with business capabilities, even if refactoring is required.

---

## 8. Assuming Spring Modulith Implies Microservices

### Description

Believing that adopting Spring Modulith automatically prepares the system for microservices.

### Why It Happens

* Confusion between modularity and distribution
* Organizational pressure toward microservices

### Consequences

* Premature infrastructure decisions
* Misaligned architectural expectations
* Increased system complexity

> Modular does not mean distributed.

### How to Avoid

Treat distribution as a separate, deliberate decision based on concrete system needs.

---

## Conclusion

Spring Modulith is a powerful architectural tool, but its effectiveness depends on disciplined usage.

Most pitfalls arise from shortcuts, misplaced abstractions, or misunderstandings of core concepts.

Used correctly, Spring Modulith helps teams maintain **clear boundaries, controlled dependencies, and long-term evolvability** within a monolithic system.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*