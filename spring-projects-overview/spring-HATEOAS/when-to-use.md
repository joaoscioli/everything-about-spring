# When to Use

Spring HATEOAS is not required to build REST APIs.

It exists to support the **hypermedia constraint** of REST, which introduces additional structure and complexity.  
Adopting it should be a deliberate architectural decision — not a default choice.

This document outlines when Spring HATEOAS provides meaningful value and when it may introduce unnecessary overhead.

---

## 1. Strategic Considerations

Before adopting Spring HATEOAS, consider:

- Is the API expected to evolve independently from its clients?
- Is long-term compatibility a priority?
- Is discoverability important?
- Is the API treated as a product?

Hypermedia improves evolvability and decoupling, but it increases:

- Payload size
- Cognitive complexity
- Design responsibility

Spring HATEOAS is most valuable in systems where API maturity matters more than minimalism.

---

## 2. When Spring HATEOAS Is a Good Fit

### 2.1 Public APIs

For APIs consumed by:

- External partners
- Third-party developers
- Independent teams
- Public internet clients

Hypermedia provides:

- Reduced client-server coupling
- Safer evolution
- Runtime discoverability
- Better forward compatibility

Public APIs benefit significantly from navigable representations.

---

### 2.2 Long-Lived APIs

If the API is expected to:

- Exist for years
- Evolve continuously
- Support backward compatibility

Hypermedia allows:

- Adding new links without breaking clients
- Gradual capability exposure
- Reduced versioning pressure

This is particularly useful in enterprise platforms.

---

### 2.3 APIs with Independent Clients

When:

- Mobile apps are maintained separately
- Multiple client applications consume the same API
- Clients update at different cadences

Hypermedia reduces reliance on hardcoded URLs and assumptions.

This enables safer independent evolution.

---

### 2.4 Discoverable Systems

If discoverability is a design goal:

- Developer experience is important
- APIs should be self-navigable
- Dynamic capability exposure is desirable

Spring HATEOAS helps model APIs as state machines rather than RPC endpoints.

---

### 2.5 API-First Organizations

Organizations that:

- Treat APIs as products
- Invest in API governance
- Define clear semantic contracts
- Emphasize long-term maintainability

are strong candidates for hypermedia-driven design.

---

## 3. When Spring HATEOAS May Not Be Appropriate

### 3.1 Internal Microservices

For internal service-to-service communication where:

- Services are small and short-lived
- Teams coordinate changes closely
- Deployments are synchronized

Hypermedia may introduce unnecessary complexity.

In many microservice architectures, simpler JSON contracts are sufficient.

---

### 3.2 Tight Frontend-Backend Coupling

If:

- The frontend and backend are deployed together
- The same team maintains both
- URL structure is centrally controlled

The benefit of runtime link discovery is reduced.

In such cases, static endpoint agreements may be adequate.

---

### 3.3 Simple CRUD Applications

For:

- Administrative tools
- Internal dashboards
- Small applications
- Short-lived systems

Hypermedia often becomes overengineering.

If the system is primarily CRUD with minimal evolution needs, the added abstraction may not justify the cost.

---

### 3.4 Performance-Critical Minimal APIs

Hypermedia adds:

- Additional fields (`_links`, `_embedded`)
- Serialization overhead
- Larger payload sizes

In systems where:

- Latency is extremely critical
- Bandwidth must be minimized
- Responses must be as small as possible

hypermedia may not align with system constraints.

---

## 4. Cost vs Benefit Analysis

### Costs

- Increased design complexity
- Learning curve for developers
- Larger response payloads
- Need for stable and well-designed relation types
- Additional serialization concerns

### Benefits

- Reduced coupling
- Safer API evolution
- Discoverability
- Additive capability expansion
- Architectural maturity

The decision should weigh long-term maintainability against short-term simplicity.

---

## 5. Adoption Guidelines

If adopting Spring HATEOAS:

- Start with self links and basic navigation.
- Define consistent relation naming conventions.
- Choose media types intentionally.
- Separate domain models from representation models.
- Introduce affordances only when necessary.

Avoid:

- Adding links without semantic meaning.
- Treating hypermedia as decoration.
- Mixing DTO logic with hypermedia concerns carelessly.

Hypermedia should express application state transitions — not merely replicate endpoint paths.

---

## 6. Decision Summary

Use Spring HATEOAS when:

- Your API is a long-term contract.
- Clients evolve independently.
- Discoverability matters.
- The API is treated as a product.

Avoid Spring HATEOAS when:

- The API is an internal transport layer.
- Tight coordination eliminates evolution risk.
- Simplicity outweighs flexibility.

Spring HATEOAS is a powerful architectural tool —  
but like any tool, it should be applied intentionally and strategically.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*