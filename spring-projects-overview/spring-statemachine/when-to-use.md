# When to Use Spring Statemachine

This document provides architectural guidance on when Spring Statemachine should — and should not — be introduced into a system.

Spring Statemachine is a powerful behavioral modeling tool. However, it introduces structural and cognitive overhead. Its adoption should be justified by domain complexity, not by technical curiosity.

---

## 1. Use Spring Statemachine When

### 1.1 The Domain Is State-Driven

Adopt Spring Statemachine when state is part of the business language.

Examples of state-driven domains:

- Order lifecycle management
- Subscription management
- Approval and review processes
- Payment processing flows
- Device lifecycle management

If business stakeholders speak in terms of states and transitions, a finite state machine is a natural modeling fit.

---

### 1.2 Transitions Represent Core Business Rules

Use it when transitions depend on:

- Conditional rules
- Authorization checks
- Contextual data
- Multi-step validation
- Domain invariants

When transition logic becomes complex and scattered across services, a state machine centralizes and formalizes that behavior.

---

### 1.3 Multiple Actors Trigger State Changes

A state machine is beneficial when state changes can be triggered by:

- End users
- Background jobs
- External systems
- Message brokers
- Scheduled tasks

Centralized transition control reduces inconsistency across multiple entry points.

---

### 1.4 Long-Running Processes

Consider it when processes:

- Span hours, days, or weeks
- Require persistence
- Must survive restarts
- Need resumable execution

Explicit state modeling improves recoverability and clarity in long-lived workflows.

---

### 1.5 Complex Lifecycle Management

FSMs are particularly effective when managing rich lifecycles, such as:

- Multi-phase order fulfillment
- Subscription upgrades and downgrades
- Multi-stage approval chains
- IoT device activation and deactivation

If the lifecycle has branching paths and conditional behavior, FSM adds structure.

---

### 1.6 You Need Formal Behavioral Modeling

Adopt Spring Statemachine when:

- You need explicit transition definitions
- Behavior must be testable at the transition level
- Modeling clarity is more important than implementation simplicity
- Domain correctness outweighs raw implementation speed

FSM makes implicit behavior explicit.

---

## 2. Avoid Spring Statemachine When

### 2.1 Simple Status Flags Are Enough

If the system only requires:

- ACTIVE / INACTIVE
- ENABLED / DISABLED
- OPEN / CLOSED

A state machine is unnecessary overhead.

---

### 2.2 The Process Is Linear and Predictable

If the flow is strictly:

A → B → C → DONE

With no branching, guards, or parallel paths, simple procedural logic is sufficient.

---

### 2.3 Few Transitions Exist

If there are only two or three trivial transitions, an FSM adds complexity without meaningful benefit.

---

### 2.4 Conditional Logic Is Straightforward

If behavior can be expressed cleanly with:

- Simple conditionals
- Small, cohesive methods
- Minimal branching

Then imperative logic may be more readable and maintainable.

---

### 2.5 The Team Lacks FSM Modeling Experience

Poorly modeled state machines can:

- Obscure domain logic
- Introduce state explosion
- Increase debugging difficulty
- Reduce clarity

FSM requires disciplined modeling.

---

## 3. FSM vs Alternatives

### 3.1 FSM vs If/Else Logic

**FSM Advantages**

- Centralized transition logic
- Explicit modeling
- Easier reasoning about allowed transitions
- Improved testability

**If/Else Advantages**

- Simpler for small cases
- Lower cognitive overhead
- Faster implementation

Choose FSM when behavior complexity scales beyond simple conditionals.

---

### 3.2 FSM vs Workflow Engines

| Finite State Machine | Workflow Engine |
|----------------------|-----------------|
| State-centric        | Process-centric |
| Embedded in code     | Often model-driven (e.g., BPMN) |
| Lightweight runtime  | External orchestration engine |
| Developer-controlled | Often business-controlled |

Use FSM when:

- Behavior is system-driven
- Human task orchestration is minimal
- Embedded domain modeling is preferred

Use workflow engines when:

- Visual modeling is required
- Human approvals dominate
- Cross-system orchestration is primary

---

### 3.3 FSM vs Saga Pattern

FSM and Saga solve different problems.

**FSM**

- Models lifecycle within a bounded context
- Controls state transitions
- Maintains domain consistency

**Saga**

- Coordinates distributed transactions
- Manages eventual consistency across services

FSM can be used inside a service participating in a saga, but it does not replace distributed orchestration.

---

## 4. Architectural Decision Checklist

Consider adopting Spring Statemachine if several of the following are true:

- State transitions are core business rules
- Multiple actors can change state
- Rules depend on contextual data
- Behavior is hard to reason about with conditionals
- Persistence of state is required
- Parallel or hierarchical behavior exists
- Domain language includes explicit lifecycle stages

If most answers are “no,” simpler solutions may be preferable.

---

## 5. Cost and Trade-offs

Introducing a state machine adds:

- Additional abstraction layers
- Modeling effort
- Learning curve
- Increased debugging complexity
- Potential state explosion if poorly designed

The benefits must outweigh the architectural cost.

FSM should solve essential domain complexity — not create accidental complexity.

---

## 6. Strategic Architectural Fit

Spring Statemachine fits best in:

- Domain-driven design environments
- Modular monoliths
- Bounded contexts with rich lifecycles
- Systems requiring strict transition control

It is not a default architectural component.

It is a targeted tool for domains where behavior is inherently stateful and transitions define business correctness.

---

## Final Guidance

Adopt Spring Statemachine when:

- State transitions define domain truth.
- Correctness depends on controlled transitions.
- Explicit modeling improves system clarity.

Avoid it when:

- Simplicity is sufficient.
- Lifecycle complexity is low.
- Conditionals remain readable and maintainable.

Architectural discipline means choosing structure only when the domain demands it.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
