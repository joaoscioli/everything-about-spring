# Concepts — Spring for GraphQL

Spring for GraphQL is built on top of **GraphQL Java** and integrates it into the Spring ecosystem.  
To use it effectively, it is essential to understand both **GraphQL core concepts** and how they map to Spring abstractions.

This document focuses on the conceptual model required to design, implement, and maintain GraphQL APIs using Spring.

---

# 01 - GraphQL Execution Model

GraphQL operates differently from REST.

Instead of multiple endpoints, GraphQL exposes a **single endpoint** that executes operations defined in a schema.

There are three operation types:

- **Query** → Read data
- **Mutation** → Modify data
- **Subscription** → Stream data updates

Execution is:

- Schema-driven
- Hierarchical
- Field-based

Each field in a query is resolved independently and may trigger nested resolution steps.

Unlike REST, the client defines:

- What fields are returned
- How deep relationships are traversed
- The exact shape of the response

---

# 02 - Schema as the Contract

The GraphQL schema is the **central contract** of the API.

It defines:

- Types
- Fields
- Input types
- Enums
- Interfaces
- Unions
- Scalars
- Nullability rules

In Spring for GraphQL, schemas are typically defined in `.graphqls` files.

The schema determines:

- What operations exist
- What data can be requested
- What arguments are required
- What types are returned

### Key Principle

**The schema comes first. Implementation follows.**

This is different from REST, where controllers often drive API shape.

---

# 03 - Resolver Model in Spring

Resolvers implement fields declared in the schema.

Spring provides annotation-based mapping similar to Spring MVC:

- `@QueryMapping` → Root query resolver
- `@MutationMapping` → Root mutation resolver
- `@SchemaMapping` → Field resolver
- `@BatchMapping` → Batch field resolver

### Root Resolvers

Handle top-level operations:
```text
type Query {
users: [User]
}
```

### Field Resolvers

Resolve nested fields:
```text
type User {
id: ID
posts: [Post]
}
```

Each field can have its own resolver.

### Batch Resolvers

Used to resolve multiple field instances together to avoid N+1 problems.

---

# 04 - Field-Level Resolution

GraphQL executes field-by-field.

If a query requests:

- Users
- Each user's posts
- Each post's comments

Resolution happens recursively.

This creates a powerful but potentially expensive execution model.

Understanding field-level resolution is critical for performance design.

---

# 05 - Data Fetching Strategy

At runtime, each field maps to a **DataFetcher**.

Spring abstracts this through annotated methods.

### The N+1 Problem

Because fields are resolved independently, a query like:

- Fetch 100 users
- Fetch posts for each user

May generate:

- 1 query for users
- 100 queries for posts

This is known as the **N+1 problem**.

### DataLoader

Spring integrates DataLoader to:

- Batch requests
- Cache results per request
- Reduce database round-trips

DataLoader operates per request and is a core optimization strategy.

---

# 06 - Execution Context

Each GraphQL request has an execution context.

This context may contain:

- Authentication details
- Request metadata
- DataLoader registry
- Custom contextual information

Spring propagates:

- SecurityContext
- Request-scoped beans
- Reactor context (in reactive mode)

Resolvers can access contextual information when needed.

---

# 07 - Reactive Execution Model

Spring for GraphQL supports both blocking and reactive execution.

### Blocking Model (Spring MVC)

Resolvers return:

- Plain objects

Execution uses thread-per-request model.

### Reactive Model (WebFlux)

Resolvers may return:

- `Mono<T>`
- `Flux<T>`

This enables:

- Non-blocking I/O
- Backpressure
- Streaming responses
- Subscriptions

Reactive execution is especially important for:

- Real-time updates
- High concurrency systems
- Streaming data APIs

---

# 08 - Error Handling Model

GraphQL error handling differs significantly from REST.

In REST:

- HTTP status codes represent errors.

In GraphQL:

- HTTP status is usually 200.
- Errors are returned inside the response payload.
- Partial responses are allowed.

Example conceptual structure:
```text
{
"data": { ... },
"errors": [ ... ]
}
```

Spring allows customization of:

- Exception resolvers
- Error mapping
- Error extensions

Understanding this model is critical for proper API design.

---

# 09 - Validation & Nullability

GraphQL enforces strict validation.

Before execution:

- Query is parsed
- Schema validation occurs
- Type validation is applied

Nullability is explicit:

- `String` → nullable
- `String!` → non-null

If a non-null field fails to resolve, parent fields may also become null depending on schema structure.

Schema nullability directly impacts error propagation.

---

# 10 - Field-Level Authorization

Spring Security integrates at multiple levels.

Authorization can occur:

- At HTTP level
- At method level (`@PreAuthorize`)
- Inside resolver logic

GraphQL allows:

- Fine-grained authorization per field
- Different access rules within the same query

Because clients define query structure, field-level security becomes essential.

---

# 11 - Contract Evolution

GraphQL typically uses a **single endpoint without versioning**.

Instead of versioning, APIs evolve through:

- Field deprecation
- Backward-compatible additions
- Non-breaking schema changes

Example:
```text
oldField: String @deprecated(reason: "Use newField instead")
```

This allows gradual evolution without breaking clients.

---

# 12 - Strong Typing & Introspection

GraphQL provides:

- Strong type enforcement
- Introspection capabilities
- Schema discoverability

Clients can:

- Query the schema
- Generate typed SDKs
- Validate queries before runtime

This promotes tighter frontend-backend integration.

---

# Mental Model Summary

To work effectively with Spring for GraphQL, developers must understand:

- Schema-first design
- Field-by-field execution
- Resolver responsibility
- DataLoader batching strategy
- Error propagation model
- Nullability rules
- Reactive vs blocking execution
- Field-level authorization
- Contract evolution without versioning

GraphQL is not “REST with a single endpoint”.

It is a different execution paradigm that requires deliberate schema modeling and careful data-fetching design.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*

