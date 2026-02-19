# Architecture

Spring Session replaces container-managed session state with an infrastructure-managed model.

Instead of storing session data inside application memory, Spring Session delegates persistence to an external storage system through a well-defined abstraction layer.

The architecture is built around interception, delegation, and storage decoupling.

---

## Architectural Goal

The primary goal of Spring Session is to:

* Decouple session state from application instances
* Enable horizontal scalability
* Provide consistent session management across distributed systems
* Integrate transparently with existing Spring applications

Spring Session does not introduce a new session programming model.

It replaces **where and how** sessions are stored.

> Container memory is replaced by infrastructure-backed persistence.

---

## High-Level Replacement Model

### Without Spring Session

Client  
→ Servlet Container  
→ In-memory `HttpSession`

Session state is tied to a single application instance.

---

### With Spring Session

Client  
→ Session Interceptor (Filter or WebSessionManager)  
→ `SessionRepository`  
→ External Store (Redis, JDBC, Hazelcast, etc.)

Session state becomes shared infrastructure.

---

## Core Components

### Session

Represents user-specific state across multiple requests.

It contains:

* Session ID
* Creation time
* Last accessed time
* Attributes
* Expiration metadata

---

### SessionRepository

Central abstraction responsible for:

* Creating sessions
* Retrieving sessions
* Saving sessions
* Deleting sessions

It defines how sessions are persisted independently of the web container.

---

### FindByIndexNameSessionRepository

Extension interface that allows querying sessions by indexed attributes.

Commonly used by Spring Security to:

* Retrieve sessions by principal name
* Perform centralized invalidation

---

### SessionRepositoryFilter (Servlet Stack)

A servlet filter that:

* Intercepts HTTP requests
* Replaces the default `HttpSession`
* Delegates persistence to `SessionRepository`

This allows existing applications to operate without code changes.

---

### WebSession & ReactiveSessionRepository (Reactive Stack)

In reactive environments:

* `WebSession` replaces `HttpSession`
* `ReactiveSessionRepository` provides non-blocking persistence

This ensures compatibility with reactive execution models.

---

## Servlet Stack Architecture

In Servlet-based applications (Spring MVC):

1. An incoming HTTP request enters the filter chain.
2. `SessionRepositoryFilter` wraps the request.
3. The default `HttpSession` implementation is replaced.
4. The session is lazily or eagerly retrieved from the external store.
5. During request completion, session updates are persisted back to the store.

Key Characteristics:

* Transparent integration
* No controller changes required
* Compatible with Spring Security
* Works with embedded or traditional servlet containers

---

## Reactive Stack Architecture

In Spring WebFlux:

* No servlet container is involved.
* `WebSession` abstraction is used.
* `ReactiveSessionRepository` handles persistence.
* Non-blocking drivers are required.

Architectural Constraint:

Using blocking storage (e.g., traditional JDBC) in reactive applications violates reactive principles and may introduce performance bottlenecks.

Reactive session persistence must be fully non-blocking.

---

## Storage Layer Model

Spring Session is storage-agnostic.

The `SessionRepository` abstraction enables multiple implementations, each with different characteristics.

Common implementations:

* Redis-backed repository
* JDBC-backed repository
* Hazelcast-backed repository

Each backend affects:

* Consistency guarantees
* Latency
* Expiration handling
* Operational complexity

---

### Expiration Strategy

Expiration is typically implemented via:

* Time-to-live (TTL) mechanisms (e.g., Redis)
* Scheduled cleanup processes (e.g., JDBC)
* Distributed in-memory expiration (e.g., Hazelcast)

Session timeout configuration must align with backend behavior.

---

## Session Lifecycle Flow

The lifecycle of a session typically follows:

1. Creation
2. Access
3. Update
4. Persistence
5. Expiration or Invalidation

During a request:

* Session data may be read from the store.
* Modifications are tracked.
* Changes are flushed back depending on configuration (flush mode).

---

### Save and Flush Behavior

Spring Session can control when session data is written:

* On every request
* Only when modified
* At specific lifecycle events

This impacts performance and consistency.

---

## Scaling Model

Spring Session enables:

* Horizontal scaling
* Rolling deployments
* Blue-green releases
* Zero-downtime restarts

Because session state is no longer bound to instance memory, application nodes can be added or removed safely.

However:

Session storage latency becomes part of the request path.

Network and backend performance now directly impact response time.

---

## Consistency and Concurrency Considerations

Distributed session management introduces new concerns:

* Concurrent updates from multiple nodes
* Lost update scenarios
* Serialization conflicts
* Eventual consistency (depending on backend)

Important:

Session persistence is not inherently transactional.

Applications must avoid storing critical transactional state inside sessions.

---

## Architectural Trade-offs

### Advantages

* Infrastructure-level scalability
* Centralized session management
* Cluster-safe authentication
* Deployment flexibility

---

### Costs

* Additional network latency
* Serialization overhead
* Operational complexity (Redis, database clusters, etc.)
* Increased distributed system complexity

---

## Architectural Guideline

If session state is required in a distributed environment:

Externalize it intentionally.

If full stateless design is possible:

Prefer eliminating server-side session state entirely.

Spring Session is a powerful tool —
but distributed state must be treated as infrastructure, not convenience.

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*