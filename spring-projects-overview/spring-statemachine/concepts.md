# Concepts

This document describes the core theoretical and modeling concepts behind Spring Statemachine.

Spring Statemachine is based on **Finite State Machine (FSM)** theory and UML state machine semantics. Understanding these concepts is essential for modeling state-driven domains effectively.

---

## 1. Finite State Machine (FSM)

A Finite State Machine (FSM) is a mathematical model of computation used to represent behavior.

Formally, a deterministic FSM can be defined as:

FSM = (S, E, T, s₀)

Where:

- **S** → Finite set of states
- **E** → Set of events
- **T** → Transition function
- **s₀** → Initial state

An FSM operates by:

1. Being in a current state
2. Receiving an event
3. Evaluating transitions
4. Moving to a new state (if a valid transition exists)

FSMs are:

- Event-driven
- Deterministic (in well-defined configurations)
- State-centric

Spring Statemachine implements this model in an application runtime context.

---

## 2. States

A **State** represents a stable condition of the system between transitions.

A system remains in a state until an event triggers a valid transition.

### Types of States

- **Initial State** → Entry point of the machine
- **Simple State** → Standard operational state
- **Composite State** → Contains nested substates
- **Final State** → Terminal condition
- **Choice / Junction** → Decision nodes for branching
- **History State** → Remembers previous substate

States may define:

- Entry actions
- Exit actions
- Internal transitions

A state should represent a meaningful domain condition — not merely a technical flag.

---

## 3. Events

An **Event** is a stimulus that may trigger a transition.

Events can be:

- External (user action, message, API call)
- Internal (system-generated)
- Timed (triggered after delay)

Events do not guarantee transitions — they initiate evaluation.

In well-modeled systems, events reflect domain language.

---

## 4. Transitions

A **Transition** defines movement from one state to another.

Conceptually:

Transition = (source, event, guard, action, target)

Components:

- **Source state**
- **Event**
- **Optional guard**
- **Optional action**
- **Target state**

### Types of Transitions

- **External** → Exits source and enters target
- **Internal** → Executes action without leaving state
- **Local** → Moves within hierarchical boundaries

Transitions define the allowed behavior of the system.

If no valid transition matches an event, the machine remains in the current state.

---

## 5. Guards

A **Guard** is a boolean condition evaluated before a transition is executed.

- If `true` → transition proceeds
- If `false` → transition is rejected

Guards should:

- Be side-effect free
- Represent domain rules
- Avoid performing business logic

They act as conditional gates.

---

## 6. Actions

An **Action** is executable logic associated with:

- Entering a state
- Exiting a state
- Executing a transition

Types:

- **Entry action**
- **Exit action**
- **Transition action**

Actions may:

- Perform side effects
- Trigger external systems
- Update extended state

Actions represent behavioral consequences — not state definitions.

---

## 7. Extended State

Traditional FSMs only track the current state.

Spring Statemachine introduces **Extended State**, which allows contextual variables to be stored alongside the machine.

Extended state:

- Maintains contextual data
- Stores counters, flags, metadata
- Supports richer decision logic

Conceptually:

- **State** → Macro-level system condition
- **Extended State** → Contextual memory

This avoids artificial state multiplication.

---

## 8. Hierarchical State Machines (HSM)

A Hierarchical State Machine (HSM) allows states to contain substates.

Benefits:

- Reduces duplication
- Encapsulates behavior
- Improves readability
- Prevents state explosion

In HSM:

- Parent state defines shared behavior
- Child states refine specialization

Hierarchy is essential for modeling complex domains.

---

## 9. Orthogonal Regions (Parallel States)

Orthogonal regions allow multiple states to be active simultaneously.

Each region:

- Has independent transitions
- Processes events concurrently

This enables modeling:

- Concurrent workflows
- Multi-aspect behavior
- Independent sub-processes

Parallel modeling must be used carefully to avoid unintended interaction complexity.

---

## 10. History States

History states allow a composite state to remember its last active substate.

Types:

- **Shallow History** → Remembers immediate child
- **Deep History** → Remembers full nested hierarchy

Useful for:

- Paused workflows
- Recovery after interruption
- Resuming complex processes

History improves resilience and user experience in long-running systems.

---

## 11. Determinism and Conflict Resolution

A well-designed FSM should be deterministic.

Determinism means:

- For a given state and event, only one transition is valid.

Conflicts may arise when:

- Multiple transitions match the same event
- Guards overlap

Proper modeling requires:

- Clear transition priorities
- Non-overlapping guards
- Explicit branching using choice states

Ambiguity increases system unpredictability.

---

## 12. The State Explosion Problem

State explosion occurs when:

- Too many states are created
- Combinations of flags are encoded as distinct states
- Domain modeling lacks abstraction

Example issue:

Representing every combination of:
- Payment status
- Shipping status
- Approval status

Hierarchy and extended state reduce this problem by:

- Encapsulating behavior
- Separating macro-state from contextual data

Proper abstraction prevents exponential growth.

---

## 13. FSM vs Imperative Logic

Imperative logic (if/else or switch statements):

- Implicit behavior
- Hard to visualize
- Scattered transition rules
- Difficult to maintain

FSM modeling:

- Explicit transitions
- Visualizable behavior
- Centralized rule definition
- Easier reasoning about correctness

FSM introduces structural discipline to state-driven domains.

---

## 14. FSM vs Workflow Modeling

FSM and workflow engines serve different purposes.

| Finite State Machine | Workflow Engine |
|----------------------|-----------------|
| State-centric        | Process-centric |
| Embedded in application code | Often model-driven (BPMN) |
| Lightweight runtime  | External orchestration |
| Deterministic transitions | Human task orchestration |

FSM is ideal when:

- Behavior is driven by system state
- Transitions are core domain rules
- Embedding inside application logic is desired

Workflow engines are better when:

- Human interaction dominates
- Visual process modeling is required
- Cross-system orchestration is central

---

## 15. Conceptual Modeling Guidelines

When modeling with FSM:

- States should represent domain truth
- Avoid technical states (e.g., TEMP_FLAG_SET)
- Keep guards simple and pure
- Keep actions cohesive
- Use hierarchy to reduce duplication
- Use extended state instead of artificial states

State machines should clarify domain behavior — not obscure it.

---

Spring Statemachine applies these formal concepts in a Spring-managed runtime environment, enabling structured modeling of complex state-driven systems.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
