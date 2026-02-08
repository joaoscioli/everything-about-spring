# Spring Integration Concepts

Spring Integration is built around a small and well-defined set of concepts that form a **shared architectural vocabulary** for message-based integration.

Understanding these concepts is essential to reason about integration flows, design decisions, and trade-offs when building integration-heavy systems.

---

## Message

A **Message** is the fundamental unit of communication in Spring Integration.

It consists of:

* A payload — the data being transported
* Headers — metadata associated with the message

Messages are treated as **immutable objects**, which enables safe concurrent processing, reliable routing, and consistent error handling.

A message represents a technical communication unit, not a domain event or business command.

---

## Message Headers

**Message headers** carry metadata that influences how a message is processed and routed.

Common uses include:

* Correlation identifiers
* Routing hints
* Content type information
* Error context

Headers should describe *how* a message is handled, not *what* the business logic decides.

Overloading headers with domain rules leads to tightly coupled and fragile flows.

---

## MessageChannel

A **MessageChannel** defines how messages move between components.

Channels are architectural contracts that specify delivery semantics, not simple conduits.

They determine:

* Synchronous or asynchronous communication
* Point-to-point or publish–subscribe behavior
* Buffering and decoupling characteristics

> Channels define how messages move, not what they contain.

### Common Channel Types

* **DirectChannel** — synchronous, point-to-point delivery
* **QueueChannel** — buffered, decoupled communication
* **PublishSubscribeChannel** — fan-out to multiple subscribers

---

## Endpoint

An **Endpoint** represents a connection point between an integration flow and external code or systems.

Endpoints define where messages:

* Enter the system
* Leave the system
* Interact with application components

Architecturally, endpoints establish clear boundaries between integration infrastructure and external concerns.

---

## MessageHandler

A **MessageHandler** processes messages within a flow.

Handlers are designed to:

* Perform a single, focused responsibility
* Be stateless whenever possible
* Operate independently of transport concerns

Examples of handler roles include transformation, filtering, and routing.

---

## Adapter

An **Adapter** bridges an external system or protocol to the internal messaging model.

Adapters isolate transport-specific logic and translate external interactions into messages.

### Adapter Types

* **Inbound adapters** — convert external input into messages
* **Outbound adapters** — send messages to external systems

> Adapters decouple protocol concerns from integration logic.

---

## Gateway

A **Gateway** provides request–reply semantics on top of a message-based system.

Gateways allow synchronous interaction while preserving internal message-driven architecture.

They are useful when integrating with components that require immediate responses.

Gateways should be used selectively, as they introduce coupling and potential blocking behavior.

---

## Transformer

A **Transformer** changes the structure or representation of a message payload.

Transformers focus on:

* Data shape
* Format conversion
* Structural adaptation

They should not implement complex business rules or decision-making logic.

---

## Filter

A **Filter** performs a binary decision on a message.

Messages are either:

* Allowed to continue through the flow
* Discarded or redirected

Filters are commonly used for validation, eligibility checks, or noise reduction.

---

## Router

A **Router** determines the path a message should take within a flow.

Routing decisions are typically based on:

* Message headers
* Payload characteristics
* External metadata

Routers enable dynamic flow paths without hard-coded dependencies between components.

---

## Splitter

A **Splitter** divides a single message into multiple messages.

This is commonly used when a message contains a collection or composite structure that must be processed independently.

Splitters increase parallelism but also introduce coordination complexity.

---

## Aggregator

An **Aggregator** combines multiple related messages into a single message.

Aggregation requires:

* Correlation identifiers
* State management
* Completion conditions

Aggregators are stateful by nature and must be designed carefully to avoid resource leaks and incomplete aggregation scenarios.

---

## Integration Flow

An **Integration Flow** represents a complete message pipeline from ingress to egress.

Flows are:

* Declarative
* Composable
* Independently testable

An integration flow models infrastructure behavior and should not be confused with a business workflow.

---

## Polling vs Event-Driven Consumers

Spring Integration supports two primary consumption models:

* **Polling consumers** actively retrieve messages at intervals
* **Event-driven consumers** react to incoming messages

Polling provides control over load but introduces latency, while event-driven models offer responsiveness at the cost of tighter coupling to the message source.

---

## Error Channel

An **Error Channel** is a dedicated path for handling failures.

Errors are represented as messages, allowing them to be:

* Routed
* Logged
* Retried
* Persisted

This approach ensures that failure handling is explicit and observable.

---

## Enterprise Integration Patterns

Spring Integration is heavily inspired by **Enterprise Integration Patterns (EIP)**.

EIP provides a shared vocabulary and proven solutions for common integration challenges.

By aligning its components with these patterns, Spring Integration enables designs that are both expressive and predictable.

---

## Summary

Spring Integration concepts form a **language for integration architecture**.

They encourage thinking in terms of:

* Message flows
* Explicit boundaries
* Composable responsibilities

Mastering these concepts allows teams to design integration solutions that are resilient, understandable, and adaptable over time.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
