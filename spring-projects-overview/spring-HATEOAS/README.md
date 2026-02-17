# Spring HATEOAS

Spring HATEOAS provides utilities to implement **Hypermedia-driven REST APIs** following the HATEOAS constraint of REST.

Within the Spring ecosystem, Spring HATEOAS acts as the **hypermedia abstraction layer** on top of Spring Web infrastructure, enabling discoverable and evolvable APIs.

---

## Why Spring HATEOAS Exists

Most REST APIs in practice are implemented as simple JSON-over-HTTP CRUD endpoints.

However, the original REST architectural style — defined by Roy Fielding — includes a constraint called:

> **HATEOAS — Hypermedia As The Engine Of Application State**

Without hypermedia:

- Clients must hardcode URLs
- API evolution becomes risky
- Versioning becomes fragile
- Coupling between client and server increases

Spring HATEOAS exists to make **true REST constraints practical** within the Spring ecosystem.

---

## Core Idea

Spring HATEOAS does not change how controllers work.

Instead, it:

- Enriches responses with hypermedia links
- Provides representation models for structured API responses
- Supports hypermedia media types such as HAL
- Enables affordances (action discovery)

> **Spring HATEOAS turns static REST endpoints into navigable application state machines.**

---

## Position in the Spring Ecosystem

Spring HATEOAS builds on top of:

- Spring Framework (Spring Web MVC & WebFlux)
- Spring Boot (auto-configuration support)

It is also used internally by:

- Spring Data REST

Spring HATEOAS does **not** replace Spring MVC or WebFlux.  
It enhances them by adding hypermedia capabilities to response models.

---

## Key Capabilities

### Representation Models

Provides structured response types:

- `RepresentationModel`
- `EntityModel`
- `CollectionModel`
- `PagedModel`

These models encapsulate:

- Domain data
- Hypermedia links
- Metadata (such as pagination)

---

### Link Building API

Provides fluent APIs for constructing links:

- Controller method references
- Dynamic URI templates
- Link relations (rel types)

This ensures links remain consistent even if controller mappings change.

---

### Media Type Support

Supports hypermedia formats such as:

- HAL (`application/hal+json`)
- HAL-FORMS
- Collection+JSON

Serialization integrates with Jackson via dedicated modules.

---

### Affordances

Allows exposing possible actions on resources.

Example:

- A `GET` response may describe that `PUT` or `DELETE` operations are available.

This improves client capability discovery.

---

## Typical Use Cases

Spring HATEOAS is commonly used for:

- Public REST APIs
- Long-lived APIs requiring backward compatibility
- APIs intended for third-party consumption
- Hypermedia-driven systems
- API-first organizations

It is particularly valuable when:

- API evolution is a concern
- Clients should not hardcode endpoint URLs
- Discoverability improves usability

---

## Trade-offs

Spring HATEOAS may not be ideal when:

- Building small internal microservices
- Frontend and backend are tightly coupled
- Minimal payload size is critical
- Hypermedia adds unnecessary complexity

Hypermedia increases:

- Payload size
- Cognitive complexity
- Documentation requirements

It should be adopted intentionally, not automatically.

---

## Documentation

Official documentation maintained by the Spring team.

### Reference Documentation

- https://docs.spring.io/spring-hateoas/docs/current/reference/html/

Covers:

- Representation models
- Link building
- Media types
- Affordances
- Integration with Spring MVC and WebFlux

### API Documentation

- https://docs.spring.io/spring-hateoas/docs/current/api/

Useful for:

- Understanding model hierarchy
- Navigating link builder utilities
- Advanced customization

---

## Relationship with Other Spring Projects

- **Spring Framework** → Web infrastructure and controller model
- **Spring Boot** → Auto-configuration and dependency management
- **Spring Data REST** → Automatic repository exposure with hypermedia
- **Spring Security** → Conditional link exposure based on authorization
- **Spring REST Docs** → Documenting hypermedia-driven APIs

Spring HATEOAS plays a **structural role** in building mature REST APIs.

---

## Summary

Spring HATEOAS is not required for every REST API.

It exists to support the **full REST architectural style**, including hypermedia constraints.

To use it effectively, developers must understand:

- REST beyond CRUD
- Hypermedia design principles
- Link relation semantics
- API evolution strategies

Spring HATEOAS transforms APIs from static endpoints into **navigable, evolvable systems**.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
