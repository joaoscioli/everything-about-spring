# Concepts

Spring HATEOAS is grounded in the architectural principles of REST, particularly the **hypermedia constraint**.  
Understanding these concepts is essential before applying the framework effectively.

This document focuses on the theoretical and semantic foundations behind hypermedia-driven APIs.

---

## 1. REST and Architectural Constraints

REST (Representational State Transfer) is an architectural style defined by Roy Fielding.  
It is based on a set of constraints:

- Client–Server separation
- Stateless communication
- Cacheability
- Layered system
- Uniform interface
- **Hypermedia as the Engine of Application State (HATEOAS)**

Many APIs labeled as “REST” implement only HTTP + JSON CRUD operations while ignoring the hypermedia constraint.

True REST requires that:

> Clients discover available actions dynamically through representations provided by the server.

---

## 2. HATEOAS (Hypermedia As The Engine Of Application State)

HATEOAS means that the client interacts with the application entirely through hypermedia controls provided dynamically by the server.

Instead of constructing URLs manually, the client:

- Follows links provided in responses
- Uses relation types (`rel`) to understand semantics
- Transitions between application states via discovered actions

A hypermedia API behaves more like a website than a remote procedure call endpoint:

- The server drives navigation
- The client follows links
- The state machine is embedded in representations

Without hypermedia:

- Clients hardcode URLs
- Tight coupling increases
- API evolution becomes fragile

---

## 3. Resource vs Representation

A critical distinction in REST:

- **Resource** → An abstract concept (e.g., “Order”, “User”)
- **Representation** → A serialized form of a resource at a point in time

Spring HATEOAS operates at the **representation layer**, not at the domain model layer.

A representation may contain:

- Resource data
- Hypermedia links
- Embedded resources
- Metadata

This separation ensures that business models remain independent of transport and hypermedia concerns.

---

## 4. Hypermedia Controls

Hypermedia controls allow clients to navigate and interact with resources.

They are the core mechanism that enables HATEOAS.

---

### 4.1 Links

A link typically contains:

- `rel` (relation type)
- `href` (target URI)
- Optional templating
- Optional metadata

Common link types:

- `self` → Canonical resource URI
- `next` / `prev` → Pagination navigation
- Related resource links
- Action links (update, delete, etc.)

Links replace hardcoded endpoint knowledge in the client.

---

### 4.2 Link Relations (rel)

The `rel` attribute defines the semantic meaning of a link.

Important properties:

- It describes *what* the link represents, not *where* it points
- It is part of the public contract of the API
- It should remain stable across versions

Relation types may be:

- Standardized (e.g., IANA link relations)
- Domain-specific custom relations

The URL may change.  
The `rel` should not.

---

### 4.3 Embedded Resources

Embedded resources allow related resources to be included inside a representation.

Example structure (HAL-style):
````json
{
    "id": 1,
    "name": "Order",
        "_embedded": {
        "items": [...]
        }
}
````

Benefits:

- Reduces additional HTTP round trips
- Improves efficiency for common access patterns

Trade-off:

- Larger payload size
- Increased serialization complexity

---

## 5. Representation Models

Spring HATEOAS formalizes hypermedia representations using structured models.

These are not simple DTOs — they are hypermedia-aware containers.

---

### 5.1 RepresentationModel

- Base abstraction
- Contains a collection of links
- May exist without domain data

---

### 5.2 EntityModel<T>

- Wraps a single domain object
- Attaches links relevant to that specific entity

---

### 5.3 CollectionModel<T>

- Represents a collection of resources
- Includes collection-level links
- May include embedded entity models

---

### 5.4 PagedModel<T>

- Extends collection representation
- Adds pagination metadata
- Supports page navigation links

These models formalize the concept that:

> Data and navigation controls belong in the same representation.

---

## 6. Affordances

Affordances describe possible actions that can be performed on a resource.

They may include:

- Supported HTTP methods
- Expected input structures
- Operation metadata

Affordances enhance discoverability by allowing clients to understand:

- What actions are available
- How to invoke them
- What input is required

They move the API closer to a true application state machine.

---

## 7. Media Types

Hypermedia requires specific media types to define structure and semantics.

The media type determines how:

- Links are represented
- Embedded resources are structured
- Metadata is conveyed

---

### 7.1 HAL (Hypertext Application Language)

- Uses `_links` and `_embedded`
- JSON-based
- Lightweight and widely adopted

---

### 7.2 HAL-FORMS

- Extends HAL
- Supports form metadata
- Enables richer affordance descriptions

---

### 7.3 Collection+JSON

- Focused on collections
- Structured around queries and templates
- Less common but standardized

Choosing a media type is a contract decision.

---

## 8. Discoverability and API Evolution

One of the primary benefits of hypermedia is controlled API evolution.

With hypermedia:

- New links can be added without breaking clients
- Clients ignore unknown links safely
- Navigation logic resides in the server

This reduces:

- Versioning pressure
- Tight client-server coupling
- Hardcoded URL dependencies

API evolution becomes additive rather than disruptive.

---

## 9. REST Without Hypermedia vs With Hypermedia

| Without HATEOAS | With HATEOAS |
|-----------------|--------------|
| Hardcoded URLs | Navigable links |
| RPC-style interactions | State-machine navigation |
| Tight coupling | Loose coupling |
| Aggressive versioning | Evolvable API |
| Static documentation dependency | Runtime discoverability |

Hypermedia changes how systems evolve and how clients integrate.

---

## Conceptual Summary

Spring HATEOAS is not about adding extra JSON fields.

It is about embracing the full REST constraint model and designing APIs as **navigable systems**.

Hypermedia is not decoration —  
it is the mechanism that turns HTTP APIs into true RESTful applications.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*