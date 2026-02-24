# Architecture — Spring Web Flow

Spring Web Flow is architected as a **state machine execution engine layered on top of Spring MVC**.

It intercepts HTTP requests, delegates control to a Flow Executor, and manages user interaction as a deterministic state transition model.

Unlike traditional MVC, where controllers manage navigation imperatively, Web Flow centralizes navigation logic inside a flow definition.

---

## Architectural Positioning

Spring Web Flow sits between:

- Spring MVC (request dispatching)
- View technologies (Thymeleaf, JSP, etc.)

It replaces controller-driven navigation with a **flow-driven execution model**.

High-level layering:

Client  
↓  
DispatcherServlet  
↓  
FlowHandlerMapping  
↓  
FlowExecutor  
↓  
FlowDefinition (State Machine)  
↓  
View Rendering

---

## Request Processing Model

1. A request reaches `DispatcherServlet`.
2. `FlowHandlerMapping` determines whether a flow should handle it.
3. `FlowExecutor` retrieves or creates a `FlowExecution`.
4. The engine processes:
    - Current state
    - Event triggering
    - Transition resolution
5. A new view is rendered or the flow ends.

Every request either:

- Starts a new flow execution
- Continues an existing one
- Terminates a flow

---

## Core Engine Components

### FlowDefinition

Defines:

- States
- Transitions
- Entry/exit actions
- Exception handling rules

Usually declared in XML.

---

### FlowExecutor

Central orchestration component.

Responsible for:

- Launching flows
- Resuming executions
- Managing lifecycle
- Coordinating state transitions

---

### FlowExecution

Represents a runtime instance of a flow.

Contains:

- Current active state
- Scoped data
- Execution status

Each user interaction creates or resumes a FlowExecution.

---

### FlowExecutionRepository

Stores active flow executions.

Strategies include:

- Server-side session storage
- Serialized client-side storage (less common)
- Custom persistence strategies

---

## State Machine Model

Spring Web Flow uses a deterministic state machine model.

State types:

- View State
- Action State
- Decision State
- Subflow State
- End State

Transitions are event-driven.

The engine guarantees that only valid transitions occur from a given state.

---

## Scope Architecture

Web Flow extends traditional Spring scopes.

Hierarchy:

- Request Scope → single HTTP request
- Flash Scope → across redirect
- View Scope → tied to a rendered view
- Flow Scope → tied to a flow execution
- Conversation Scope → spans multiple flows

This layered scope model enables precise state management.

---

## Security Integration

When integrated with Spring Security:

- States can be secured
- Transitions can require authorization
- Flow-level security policies can be applied

Security checks occur during transition resolution.

---

## Persistence & Serialization

FlowExecution state is typically stored in:

- HTTP session

It may be serialized to:

- Support session replication
- Enable clustered environments

However, serialization increases:

- Memory usage
- Replication overhead

---

## Integration with Spring MVC

Spring Web Flow does not replace Spring MVC.

Instead:

- DispatcherServlet remains the entry point
- View resolvers remain active
- Controllers may still coexist

Web Flow handles structured navigation, while MVC handles request dispatching and rendering.

---

## Scalability Characteristics

Spring Web Flow is inherently:

- Stateful
- Session-dependent

This affects:

- Horizontal scaling
- Cloud-native deployment models
- Stateless service design

Scaling strategies may require:

- Sticky sessions
- Distributed session replication
- Custom execution repositories

---

## Architectural Strengths

- Explicit navigation model
- Strong control over interaction lifecycle
- Clear separation between flow logic and business services
- Deterministic state transitions

---

## Architectural Constraints

- Increased server memory footprint
- Coupling to server-side rendering
- Limited suitability for REST or reactive systems
- XML-centric configuration

---

## Architectural Summary

Spring Web Flow transforms web interaction into:

> A managed state machine executed within the Spring MVC infrastructure.

It provides strong guarantees around navigation correctness and conversational state management, at the cost of increased statefulness and architectural rigidity.

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*
