# Architecture

Spring HATEOAS operates strictly at the **representation layer** of the Spring Web stack.  
It does not manage request routing, HTTP handling, or controller execution. Instead, it enriches response bodies with hypermedia controls before serialization.

---

## 1. Architectural Role in the Spring Ecosystem

Spring HATEOAS sits on top of:

- Spring Web MVC
- Spring WebFlux

It enhances the response model returned by controllers by:

- Wrapping domain data in representation models
- Attaching hypermedia links
- Supporting hypermedia media types
- Integrating with content negotiation

> Spring HATEOAS does not replace Spring MVC or WebFlux — it augments their response handling layer.

---

## 2. Hypermedia Layer on Top of Spring Web

The runtime flow in a typical Spring MVC application:
````text
HTTP Request
↓
DispatcherServlet
↓
@Controller
↓
Return RepresentationModel
↓
HttpMessageConverter
↓
Hypermedia Serialization (HAL, etc.)
↓
HTTP Response
````

Spring HATEOAS integrates between:

- The controller return value
- The HTTP message conversion phase

Hypermedia is applied **at serialization time**, not at routing time.

---

## 3. Core Abstractions

Spring HATEOAS is built around a small but powerful abstraction model.

---

### 3.1 RepresentationModel Hierarchy

The foundation is the `RepresentationModel` class.

Hierarchy:
````text
RepresentationModel
├── EntityModel<T>
├── CollectionModel<T>
└── PagedModel<T>
````

#### RepresentationModel

- Base abstraction
- Holds hypermedia links
- Does not require domain data

#### EntityModel<T>

- Wraps a single domain object
- Adds links related to that resource

#### CollectionModel<T>

- Represents a collection of resources
- Includes collection-level links

#### PagedModel<T>

- Extends collection representation
- Adds pagination metadata
- Integrates with Spring Data pagination

Important architectural distinction:

> Domain models are not representation models.  
> Hypermedia concerns are separated from core business objects.

---

### 3.2 Link Model

The `Link` abstraction represents a hypermedia control.

A link contains:

- `rel` (relation type)
- `href`
- Optional templating
- Optional affordances

Relation types (rels) are part of the public contract of the API and must be treated as stable identifiers.

---

### 3.3 Link Builders

Spring HATEOAS provides fluent APIs for constructing links dynamically.

Two main builders:

- `WebMvcLinkBuilder`
- `WebFluxLinkBuilder`

These builders:

- Reference controller methods
- Resolve mappings dynamically
- Avoid hardcoded URL strings
- Integrate with Spring’s mapping infrastructure

Link generation leverages:

- `HandlerMethod`
- URI component builders
- Controller method metadata

This ensures links remain consistent even if mappings change.

---

### 3.4 Affordances

Affordances describe possible actions that can be performed on a resource.

They may include:

- HTTP method
- Expected input structure
- Metadata about operations

Affordances enable richer client-side capability discovery beyond simple link navigation.

---

## 4. Integration with Spring MVC

In a Servlet-based application:

- Controllers return `RepresentationModel` instances
- Spring MVC resolves the return value
- `MappingJackson2HttpMessageConverter` serializes the response
- Hypermedia modules customize JSON rendering

Spring HATEOAS integrates using:

- Handler method inspection
- Servlet URI resolution
- Content negotiation via `Accept` headers

When used with Spring Boot, hypermedia configuration is typically auto-configured.

---

## 5. Integration with Spring WebFlux

In reactive environments:

- Controllers return reactive types (e.g., `Mono<EntityModel<T>>`)
- Serialization remains non-blocking
- `WebFluxLinkBuilder` adapts to reactive execution context

Key differences from MVC:

- Uses `ServerWebExchange`
- Fully non-blocking request lifecycle
- Same conceptual model, different runtime mechanics

The representation layer remains consistent across both programming models.

---

## 6. Serialization Architecture

Hypermedia rendering is performed during JSON serialization.

---

### 6.1 Jackson Modules

Spring HATEOAS provides custom Jackson modules that:

- Serialize `_links`
- Serialize `_embedded`
- Respect media type specifications
- Customize property rendering

Typical HAL structure:
````json
{
 "id": 1,
 "name": "Order",
   "_links": {
   "self": { "href": "/orders/1" }
   }
}
````

Serialization is controlled by:

- Media type configuration
- Registered Jackson modules
- Content negotiation strategy

---

### 6.2 Media Type Support

Supported hypermedia media types include:

- `application/hal+json`
- HAL-FORMS
- Collection+JSON

Content negotiation determines which media type is used based on:

- `Accept` header
- Configured message converters

---

## 7. Runtime Flow

End-to-end runtime process:

1. Controller handles request.
2. Controller returns `EntityModel`, `CollectionModel`, or `PagedModel`.
3. Spring resolves return value.
4. Appropriate `HttpMessageConverter` is selected.
5. Hypermedia serializer renders links and embedded resources.
6. HTTP response is written.

Hypermedia enrichment occurs during step 5.

---

## 8. Extension Points

Spring HATEOAS is extensible through:

- Custom `RepresentationModelAssembler`
- Custom `LinkRelationProvider`
- Custom media type configuration
- Conditional link exposure
- Integration with Spring Security for authorization-aware links

These extension points allow:

- Consistent link construction patterns
- Stable relation naming strategies
- Domain-specific hypermedia semantics

---

## Architectural Summary

Spring HATEOAS:

- Operates at the representation layer
- Is independent of business logic
- Integrates with both MVC and WebFlux
- Applies hypermedia at serialization time
- Remains optional and composable

It transforms REST responses from static JSON payloads into **navigable hypermedia documents** while preserving Spring’s programming model.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*


