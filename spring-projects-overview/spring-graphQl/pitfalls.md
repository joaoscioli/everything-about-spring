# Pitfalls — Spring for GraphQL

GraphQL introduces a powerful and flexible execution model.

However, this flexibility also introduces new categories of architectural, performance, and security risks.

This document highlights the most common pitfalls when using Spring for GraphQL in production systems.

---

# 01 - Ignoring the N+1 Problem

One of the most common and damaging mistakes.

Because GraphQL resolves fields individually, a query like:

- Fetch 100 users
- Fetch posts for each user

May result in:

- 1 database query for users
- 100 additional queries for posts

This is known as the **N+1 problem**.

### Why It Happens

Resolvers often call repositories directly without batching.

### Consequences

- Performance degradation
- Increased database load
- Slow response times
- Production instability under load

### Prevention

- Use `@BatchMapping`
- Configure DataLoader
- Design data-fetching strategies intentionally

Ignoring N+1 is the fastest way to break a GraphQL system at scale.

---

# 02 - Treating GraphQL Like REST

GraphQL is not REST with a single endpoint.

Common mistakes:

- Returning large DTOs regardless of selected fields
- Ignoring field-level granularity
- Designing queries that mimic REST endpoints
- Creating multiple operations for static data shapes

GraphQL requires:

- Schema-driven thinking
- Field-level resolution awareness
- Flexible response modeling

Failing to shift mental models results in underutilized GraphQL and unnecessary complexity.

---

# 03 - Putting Business Logic Inside Resolvers

Resolvers should coordinate — not implement business rules.

Common mistake:

- Complex validation logic inside resolver methods
- Transaction management inside resolvers
- Cross-service orchestration embedded in field resolution

### Consequences

- Tight coupling
- Hard-to-test code
- Difficult maintenance
- Hidden transaction boundaries

Best practice:

Resolvers delegate to application services.  
Business logic belongs in the service layer.

---

# 04 - No Query Complexity or Depth Control

GraphQL allows deeply nested queries.

Without restrictions, clients may request:

- Deeply recursive relationships
- Massive object graphs
- Expensive joins

### Risks

- Denial-of-Service attacks
- Memory exhaustion
- Database overload
- Unpredictable performance

Production systems should consider:

- Query depth limiting
- Query complexity analysis
- Operation cost estimation
- Timeouts and safeguards

GraphQL flexibility requires defensive execution strategies.

---

# 05 - Missing Field-Level Authorization

Authenticating a user is not enough.

GraphQL allows clients to request multiple fields in a single operation.

If field-level checks are not implemented:

- Sensitive data may leak
- Authorization boundaries may collapse

Common mistake:

- Securing only the HTTP endpoint
- Ignoring resolver-level security

Spring Security should be applied:

- At method level
- At resolver level
- Where sensitive fields exist

GraphQL increases the need for fine-grained authorization.

---

# 06 - Misunderstanding Nullability

GraphQL nullability rules affect error propagation.

Example:

- `String` → nullable
- `String!` → non-null

If a non-null field fails:

- The parent field may become null
- Entire query sections may collapse

Common mistakes:

- Overusing non-null (`!`)
- Not understanding propagation behavior
- Designing fragile schema contracts

Schema nullability decisions directly impact runtime behavior.

---

# 07 - Overusing GraphQL for Simple APIs

GraphQL introduces:

- Schema complexity
- Resolver design overhead
- Performance tuning requirements

For simple CRUD APIs with:

- Stable requirements
- Minimal nesting
- Predictable response shapes

REST may be more appropriate.

GraphQL is powerful — but not always necessary.

Architectural choices should match system complexity.

---

# 08 - Lack of Observability

GraphQL queries vary in shape and cost.

Without proper monitoring:

- Slow queries go unnoticed
- Expensive operations are not identified
- Performance bottlenecks remain hidden

Common oversight:

- Not tracking query metrics
- Not measuring resolver timing
- Ignoring error patterns

Production systems should integrate:

- Metrics collection
- Execution timing
- Query analysis
- Structured logging

GraphQL requires more observability discipline than REST.

---

# 09 - Exposing Internal Domain Models Directly

Mapping JPA entities directly into the GraphQL schema may:

- Expose internal fields unintentionally
- Break encapsulation
- Create tight coupling between persistence and API

This reduces:

- Flexibility
- Evolution capability
- Domain isolation

Better practice:

- Use DTOs or dedicated API models
- Maintain separation between domain and schema

GraphQL schema is a public contract — not a mirror of the database.

---

# 10 - Ignoring Subscription Scalability

GraphQL subscriptions require:

- Persistent connections
- Event-driven infrastructure
- Reactive execution model

Common mistakes:

- Using subscriptions without proper messaging infrastructure
- Not planning horizontal scaling
- Ignoring backpressure

Subscriptions are powerful but introduce operational complexity.

They should be backed by:

- Messaging systems
- Event streams
- Scalable WebSocket handling

---

# 11 - Not Managing Schema Evolution Carefully

GraphQL usually operates under a single endpoint without versioning.

Common mistake:

- Removing fields abruptly
- Changing field types
- Breaking backward compatibility

Instead:

- Deprecate fields
- Add new fields safely
- Maintain backward compatibility

Schema evolution must be deliberate and conservative.

---

# Final Considerations

Spring for GraphQL is powerful, but it shifts responsibility toward:

- Schema design
- Data-fetching discipline
- Security precision
- Performance awareness
- Observability maturity

Most production issues arise not from framework limitations, but from:

- Poor schema modeling
- Lack of batching
- Missing authorization checks
- Ignoring execution complexity

GraphQL is flexible by design.  
That flexibility demands careful architectural decisions.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
