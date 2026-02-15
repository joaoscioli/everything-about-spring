# Architecture — Spring for GraphQL

Spring for GraphQL provides the architectural bridge between **GraphQL Java** and the **Spring ecosystem**.

It does not implement GraphQL itself.  
Instead, it integrates the GraphQL Java engine into Spring’s:

- Dependency Injection model
- Web stack (MVC & WebFlux)
- Security infrastructure
- Observability system
- Data access abstractions

This document explains how the runtime is structured and how requests flow through the system.

---

# 01 - Architectural Overview

At a high level, a Spring for GraphQL application follows this layered architecture:

```text
Client
↓
HTTP / WebSocket Transport (Spring MVC or WebFlux)
↓
GraphQL HTTP Handler
↓
GraphQL Java Engine
↓
DataFetchers (Resolvers)
↓
Application Services / Repositories / External Systems
```

Spring’s responsibility is to:

- Adapt HTTP/WebSocket transport to GraphQL execution
- Wire GraphQL Java into the Spring container
- Manage resolver discovery and lifecycle
- Integrate cross-cutting concerns (security, metrics, etc.)

---

# 02 - Runtime Layers

## Layer 1 — Transport Layer

Responsible for receiving client requests.

Supports:

- HTTP POST `/graphql`
- WebSocket (subscriptions)
- Spring MVC (blocking)
- Spring WebFlux (reactive)

Responsibilities:

- Deserialize GraphQL request payload
- Forward execution to the GraphQL engine
- Serialize execution result into JSON

This layer is transport-oriented and does not execute business logic.

---

## Layer 2 — GraphQL Execution Engine

Powered by **GraphQL Java**.

Responsibilities:

- Parse query
- Validate against schema
- Apply execution strategy
- Trigger field resolution
- Aggregate response

Spring for GraphQL configures and exposes:

- `GraphQlSource`
- Execution strategies
- Instrumentation
- Exception resolvers

The execution engine is schema-driven and operates field by field.

---

## Layer 3 — Resolver Layer

Resolvers connect schema fields to application logic.

In Spring, resolvers are typically declared using:

- `@QueryMapping`
- `@MutationMapping`
- `@SchemaMapping`
- `@BatchMapping`

Responsibilities:

- Map schema operations to methods
- Access services and repositories
- Return domain or DTO objects
- Participate in request-scoped execution context

Resolvers are discovered via Spring’s component scanning and managed as beans.

---

## Layer 4 — Application & Data Layer

This layer contains:

- Services
- Spring Data repositories
- External API clients
- Messaging systems
- Domain logic

Spring for GraphQL does not enforce a specific architecture here.

However, it encourages:

- Clear service boundaries
- Transactional consistency
- Separation between resolver and business logic

Resolvers should delegate business rules to services, not implement them directly.

---

# 03 - Schema-Driven Architecture

GraphQL applications are **contract-first**.

The `.graphqls` schema is the central contract.

### REST Model
```text
Controller → Endpoint → DTO
```


### GraphQL Model
```text
Schema → Resolver → Data
```


The schema defines:

- Types
- Queries
- Mutations
- Subscriptions
- Relationships between types

Resolvers implement fields declared in the schema.

This inversion (schema first, implementation second) is a fundamental architectural difference from REST.

---

#  04 - Request Lifecycle

A typical request flows as follows:

1. Client sends a GraphQL query.
2. Spring HTTP handler receives the request.
3. Request is forwarded to `GraphQlSource`.
4. GraphQL Java parses and validates the query.
5. Execution strategy resolves root operation.
6. Field resolvers are invoked recursively.
7. Data is collected and assembled.
8. Response is serialized to JSON.
9. HTTP response is returned.

Important:

Field resolution is hierarchical and may trigger nested resolver calls.

---

# 05 - Field Execution Model

GraphQL executes field-by-field.

Example:

If a query requests:

- A list of users
- Each user's posts

Execution happens as:

1. Resolve `users`
2. For each user:
    - Resolve `posts`

Without optimization, this may lead to the **N+1 problem**.

---

# 06 - DataLoader & Batching Strategy

To mitigate N+1, Spring integrates **DataLoader** support.

DataLoader provides:

- Batching
- Per-request caching
- Deferred resolution

Architectural behavior:

- Multiple field resolutions are grouped
- Single batched query is executed
- Results are distributed back to fields

DataLoader operates within request scope.

This is critical for high-performance GraphQL APIs.

---

# 07 - Reactive vs Blocking Execution Model

Spring for GraphQL supports both:

## Blocking (Spring MVC)

- Resolver returns `Object`
- Traditional thread-per-request model

## Reactive (WebFlux)

Resolvers may return:

- `Mono<T>`
- `Flux<T>`

Execution model becomes non-blocking and supports:

- Backpressure
- Asynchronous data fetching
- Streaming responses

Subscriptions require reactive infrastructure.

The execution engine adapts to both models transparently.

---

# 08 - Security Integration Architecture

Security integrates at multiple levels.

## Transport Level

- Spring Security filter chain
- Authentication
- Token validation
- Session handling

## Resolver Level

- `@PreAuthorize`
- `@PostAuthorize`
- Method-level security

## Field-Level Authorization

Resolvers can enforce fine-grained rules per field.

Security context is propagated through the execution context.

---

# 09 - Observability & Instrumentation

Spring for GraphQL integrates with:

- Micrometer
- Spring Boot Actuator

Architecture supports:

- Query metrics
- Resolver timing
- Error metrics
- Tracing instrumentation

GraphQL Java provides instrumentation hooks that Spring bridges into observability systems.

---

# 10 - Extension Points

Spring for GraphQL exposes customization hooks:

- Custom `GraphQlSource` configuration
- Execution strategies
- Instrumentation
- Exception resolvers
- Schema wiring
- DataLoader registry configuration

These extension points allow advanced tuning of:

- Performance
- Error handling
- Execution semantics

---

# Architectural Characteristics

Spring for GraphQL systems are typically:

- Schema-driven
- Resolver-based
- Layered
- Transport-agnostic
- Extensible
- Observability-ready
- Security-integrated

They require deliberate design around:

- Schema modeling
- Field granularity
- Data fetching strategy
- Authorization boundaries
- Performance tuning

---

# Conclusion

Spring for GraphQL is not merely an HTTP adapter.

It is a **structured integration layer** that embeds GraphQL execution into the Spring runtime.

Understanding its architecture requires understanding:

- GraphQL Java execution
- Spring dependency injection
- Request-scoped batching
- Reactive vs blocking execution
- Cross-cutting concern integration

Proper architectural decisions around schema design and data loading directly impact scalability and maintainability.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*