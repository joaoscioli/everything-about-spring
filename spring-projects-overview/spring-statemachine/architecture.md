# Architecture

This document describes the internal architecture of Spring Statemachine and how it models, executes, and manages finite state machines within a Spring-based application.

Spring Statemachine implements a runtime engine that processes events, evaluates transition rules, and maintains state consistency according to finite state machine (FSM) semantics.

---

## 1. Architectural Overview

Spring Statemachine is an **event-driven runtime engine** built on top of the Spring Framework.

At its core, it:

- Maintains a current state (or set of states)
- Receives events
- Evaluates transitions
- Applies guards
- Executes actions
- Updates state accordingly

The architecture follows UML state machine semantics and supports:

- Hierarchical states
- Parallel regions
- Conditional transitions
- Extended state variables
- Persistence and recovery

The state machine itself is a managed Spring bean and participates in the application lifecycle.

---

## 2. Core Components

### 2.1 StateMachine

The `StateMachine` is the central runtime engine.

Responsibilities:

- Holds the current state(s)
- Accepts events
- Evaluates transitions
- Manages execution flow
- Controls lifecycle (`start`, `stop`, `reset`)

A state machine may operate in:

- Synchronous mode
- Asynchronous mode
- Reactive mode

It can also be instantiated via a `StateMachineFactory` when multiple independent instances are required.

---

### 2.2 State

A `State` represents a condition in which the machine can exist.

Supported types include:

- Initial state
- Simple state
- Composite (hierarchical) state
- Final state
- Choice / Junction pseudo states
- History states

States may define:

- Entry actions (executed when entering)
- Exit actions (executed when leaving)
- Internal transitions (do not change state)

Composite states allow nested behavior modeling.

---

### 2.3 Transition

A `Transition` defines how the machine moves from one state to another.

It consists of:

- Source state
- Target state
- Triggering event
- Optional guard
- Optional action

Types of transitions:

- **External** → Exits source and enters target
- **Internal** → Does not exit the state
- **Local** → Moves within hierarchical boundaries

Transitions are evaluated when an event is processed.

---

### 2.4 Event

An `Event` triggers transition evaluation.

Characteristics:

- Can be enum-based or object-based
- May carry payload data
- Can be processed synchronously or asynchronously

Events initiate the state resolution process.

---

### 2.5 Guard

A `Guard` is a boolean condition evaluated before a transition is allowed.

If the guard returns `false`, the transition is rejected.

Guards are typically used for:

- Authorization checks
- Domain rule validation
- Conditional branching

---

### 2.6 Action

An `Action` executes during a transition.

Actions may:

- Perform side effects
- Modify external systems
- Update extended state variables

Actions execute in the following order:

1. Exit actions (source state)
2. Transition actions
3. Entry actions (target state)

---

### 2.7 Extended State

The `ExtendedState` provides contextual storage associated with the machine.

It:

- Stores variables across transitions
- Maintains runtime context
- Supports dynamic decision logic

Extended state allows the machine to carry domain data without encoding it into states.

---

## 3. Execution Model

The execution model is event-driven and deterministic.

When an event is received:

1. The machine identifies candidate transitions.
2. Guards are evaluated.
3. The first valid transition is selected.
4. Exit actions are executed.
5. Transition actions are executed.
6. Entry actions are executed.
7. Current state is updated.

If no valid transition exists, the event is ignored.

Conflict resolution follows defined precedence rules, especially in hierarchical and parallel configurations.

---

## 4. Hierarchical and Parallel States

### 4.1 Hierarchical (Composite) States

States may contain nested substates.

Characteristics:

- Encapsulate complex behavior
- Allow local transitions
- Support entry/exit boundaries

Hierarchical modeling reduces state explosion by grouping related behavior.

---

### 4.2 Regions (Parallel States)

A composite state may contain multiple regions.

Each region:

- Maintains its own active state
- Processes events independently

This allows concurrent behavior modeling within a single machine.

---

## 5. Persistence Architecture

Spring Statemachine supports persistence to enable:

- Long-running workflows
- Crash recovery
- Distributed coordination

Key components:

### StateMachineContext

Represents a serializable snapshot of:

- Current state(s)
- Extended state variables
- History information

### StateMachinePersister

Provides save/restore capabilities for a machine instance.

### RuntimePersister

Persists state changes automatically during execution.

Persistence strategies may integrate with:

- Databases
- Distributed caches
- Custom storage systems

---

## 6. Distributed State Machines

Spring Statemachine can coordinate machines across distributed systems.

Capabilities include:

- Shared state storage
- Event propagation
- Cluster coordination

This is useful in:

- Microservices
- Multi-node deployments
- High-availability systems

Distributed configuration requires consistent state synchronization mechanisms.

---

## 7. Reactive Support

Spring Statemachine supports reactive execution.

Features:

- Integration with Reactor (`Mono`, `Flux`)
- Non-blocking event processing
- Asynchronous transitions
- Backpressure-aware execution

Reactive mode aligns with reactive Spring applications and messaging pipelines.

---

## 8. Spring Integration Model

Spring Statemachine integrates directly with the Spring container.

Configuration options include:

- Java DSL configuration
- Builder API
- Annotation-based configuration

State machines are:

- Standard Spring beans
- Dependency-injectable
- Lifecycle-managed

When used with Spring Boot, auto-configuration simplifies setup.

---

## 9. FSM vs Workflow Engines

Spring Statemachine is a finite state machine engine — not a full workflow or BPM engine.

| Finite State Machine | Workflow Engine |
|----------------------|-----------------|
| State-centric        | Process-centric |
| Embedded in code     | Often model-driven (BPMN) |
| Lightweight runtime  | Heavier orchestration engine |
| Explicit transitions | Visual process diagrams |
| Domain behavior tool | Business process management tool |

FSM is appropriate when:

- Behavior is driven by state transitions
- Control flow is part of domain modeling
- Lightweight embedding is desired

Workflow engines are more suitable when:

- Human tasks dominate
- Visual modeling is required
- Long-running business orchestration is primary concern

---

## 10. Architectural Considerations

When adopting Spring Statemachine:

- Model states carefully to avoid explosion
- Avoid encoding business logic exclusively inside actions
- Keep transitions explicit and domain-aligned
- Evaluate whether FSM complexity is justified

Spring Statemachine introduces structural rigor to state-driven domains, but should be used when domain complexity requires formal behavioral modeling.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
