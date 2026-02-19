# Concepts

Spring Session is not primarily about storing user data.

It is about managing state in distributed systems.

Understanding its core concepts requires understanding how state behaves across instances, networks, and deployments.

---

## Session State in Distributed Systems

HTTP is a stateless protocol.

Sessions introduce state by associating multiple requests with a single logical user interaction.

In single-instance systems, session state lives in application memory.

In distributed systems:

* Multiple instances handle requests.
* Load balancers distribute traffic.
* Instances may restart independently.
* Deployments happen without downtime.

In this environment, state placement becomes an architectural decision.

> Where state lives determines how the system scales.

Spring Session externalizes session state so it becomes infrastructure-managed instead of instance-bound.

---

## HttpSession vs Infrastructure-Backed Session

### Traditional HttpSession

* Stored in servlet container memory
* Tied to a single instance
* Lost on restart
* Requires sticky sessions for clustering

---

### Infrastructure-Backed Session

* Stored in Redis, JDBC, or other backends
* Shared across multiple instances
* Survives restarts
* Enables true horizontal scaling

Spring Session replaces the storage mechanism without changing the programming model.

Controllers still interact with `HttpSession` (Servlet) or `WebSession` (Reactive).

---

## SessionRepository Abstraction

At the core of Spring Session is the `SessionRepository` abstraction.

It defines how sessions are:

* Created
* Retrieved
* Updated
* Deleted

This abstraction:

* Decouples storage from application logic
* Enables pluggable backends
* Allows custom implementations
* Simplifies testing strategies

The application does not depend on Redis or JDBC directly —
it depends on the repository contract.

---

## Session Expiration Model

Session expiration is critical in distributed environments.

Spring Session supports:

* Idle timeout (most common)
* Explicit invalidation
* Backend-driven TTL

Expiration involves coordination between:

* Application configuration
* Backend storage TTL policies
* Access patterns

Mismatch between application timeout and backend expiration can cause:

* Unexpected logouts
* “Zombie” sessions
* Security inconsistencies

Expiration must be treated as a system-level concern.

---

## Session Serialization

Session attributes must be serializable.

Because sessions are stored externally, data must:

* Be serialized
* Travel over the network
* Be deserialized on access

This introduces important implications:

* Non-serializable objects cause runtime failures.
* Class changes between deployments may break deserialization.
* Large objects increase network and storage overhead.

> Session data becomes part of your distributed contract.

Storing complex domain objects inside sessions increases coupling and operational risk.

---

## Indexed Sessions

Some session repositories implement indexed lookups via:

`FindByIndexNameSessionRepository`

This enables querying sessions by attributes such as:

* Principal name (username)
* Custom indexed attributes

Common use cases:

* Global logout
* Forced session invalidation
* Administrative session management
* Concurrent session control

Indexed sessions are especially important when integrated with Spring Security.

---

## Security Context Persistence

Spring Security stores authentication data in the session via the `SecurityContext`.

With Spring Session:

* Authentication becomes cluster-safe.
* Users remain authenticated across instances.
* Session invalidation propagates across nodes.

This enables:

* Centralized logout
* Concurrent session limits
* Distributed authentication consistency

However:

If using fully stateless JWT authentication, distributed sessions may be unnecessary.

Mixing stateful and stateless models without a clear strategy increases complexity.

---

## Session Consistency Model

Distributed session storage introduces consistency challenges.

Multiple nodes may:

* Read the same session concurrently.
* Update attributes simultaneously.
* Overwrite changes unintentionally.

Session updates are not transactional by default.

Depending on the backend:

* Writes may be eventually consistent.
* Race conditions may occur.
* Last-write-wins behavior may apply.

Applications should avoid storing:

* Critical transactional state
* Business invariants
* Financial calculations

Sessions are best used for identity and lightweight conversational state.

---

## Stateful vs Stateless Architecture

Spring Session supports stateful distributed systems.

### Stateful Architecture

* Server stores user state.
* Simpler mental model.
* Easier integration with traditional authentication.
* Requires session storage infrastructure.

---

### Stateless Architecture (JWT)

* Client carries authentication state.
* No server-side session storage.
* Simpler scaling model.
* Harder centralized invalidation.

Choosing between stateful and stateless design is an architectural decision —
not just a technical preference.

Spring Session is appropriate when stateful behavior is required at scale.

---

## Reactive Session Model

In reactive applications:

* `WebSession` replaces `HttpSession`.
* `ReactiveSessionRepository` is used.
* Non-blocking drivers are required.

Reactive session persistence must:

* Avoid blocking calls.
* Respect backpressure.
* Integrate with reactive pipelines.

Using blocking storage in reactive systems introduces performance bottlenecks and defeats the reactive execution model.

---

## Conceptual Guideline

Spring Session introduces distributed state.

Distributed state introduces distributed complexity.

Use sessions for:

* Authentication context
* Lightweight conversational data
* Controlled user-specific metadata

Avoid using sessions as:

* Business data stores
* Transaction buffers
* Cache substitutes for domain entities

When designing systems:

If state is required —
externalize it intentionally.

If state is not required —
prefer stateless architecture.

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*