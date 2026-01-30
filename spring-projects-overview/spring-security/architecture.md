# Architecture Overview

This document describes the **internal architecture of Spring Security** and how it enforces security across Spring-based applications.

It focuses on **execution flow, responsibilities, and integration points**, not on configuration or implementation details.

---

## High-Level Architecture

Spring Security is designed as a **protective layer that surrounds the application**, rather than being embedded inside business logic.

At a high level, the architecture consists of:

- Incoming requests from clients
- A security interception layer
- Application components (controllers, services, handlers)
- A contextual security state bound to execution

Spring Security operates **before application logic executes**, ensuring that authentication and authorization decisions are made early and consistently.

> Spring Security does not secure individual components in isolation — it secures the execution flow itself.

---

## Request Lifecycle with Spring Security

Every incoming request follows a well-defined lifecycle when Spring Security is present.

At a conceptual level, the flow is:

1. A request enters the application runtime
2. The request is intercepted by the security infrastructure
3. The security filter chain is invoked
4. Authentication may be performed if required
5. A security context is established or retrieved
6. Authorization checks are evaluated
7. The request is either rejected or forwarded to application logic

Security decisions occur **before** controllers, handlers, or services are executed.

If a request fails authentication or authorization, the execution is terminated immediately.

---

## Filter-Based Security Model

Spring Security enforces security through a **filter-based architecture**.

All security logic is implemented as a chain of filters that:

- Intercept requests early
- Execute in a defined order
- Each perform a focused responsibility
- May stop further processing if a decision fails

Key architectural characteristics:

- Filters are composable
- Order is critical
- Filters may depend on the outcome of previous filters
- Not all filters perform authentication

This design allows Spring Security to remain **framework-agnostic**, applying equally to traditional web applications, APIs, and reactive systems.

---

## Delegation and Entry Point

Spring Security integrates with the runtime using a delegation mechanism.

An external entry point intercepts incoming requests and delegates security handling to Spring-managed components.

This approach ensures:

- Full integration with the Spring container
- Access to dependency injection
- Centralized security orchestration

From this point onward, the request is fully governed by the Spring Security infrastructure.

---

## Authentication Flow

Authentication is the process of **establishing identity**.

Architecturally, the authentication flow involves:

1. Detection that authentication is required
2. Extraction of credentials from the request
3. Delegation to authentication components
4. Validation of credentials
5. Creation of an authenticated identity
6. Storage of authentication information in the security context

Important architectural properties:

- Authentication is explicit, not implicit
- Multiple authentication mechanisms may coexist
- Authentication logic is isolated from authorization logic

Once authentication succeeds, the system moves forward with a verified identity.

---

## Authorization Flow

Authorization determines whether an authenticated identity is **allowed to perform a specific action**.

Authorization checks occur:

- After authentication
- Before protected resources are accessed
- At both web and method execution boundaries

Architectural characteristics:

- Authorization decisions are based on granted authorities
- Decisions are deterministic and repeatable
- Authorization is evaluated at well-defined enforcement points

> Authorization is a decision-making process, not a side effect of authentication.

---

## Security Context Propagation

Spring Security maintains security information using a **contextual model**.

The security context:

- Represents the current authenticated identity
- Is bound to the execution flow
- Travels with the request through the system

By default, the context is associated with the executing thread, which has important implications for:

- Asynchronous execution
- Thread switching
- Reactive programming models

Correct context propagation is essential to ensure consistent authorization behavior throughout the application.

---

## Stateless vs Stateful Architecture

Spring Security supports both **stateful** and **stateless** security models.

In a stateful architecture:
- Authentication state is stored on the server
- The security context is retrieved from server-managed storage
- Requests rely on an existing server-side session

In a stateless architecture:
- No authentication state is stored on the server
- Each request carries its own authentication information
- The security context is reconstructed per request

While both models follow the same architectural flow, they differ significantly in how the security context is created and managed.

---

## Integration with Spring Web MVC and WebFlux

Spring Security integrates with different execution models while preserving the same conceptual architecture.

### Servlet-Based Applications (Spring Web MVC)

- Uses a blocking execution model
- Security context is thread-bound
- Filters operate on servlet requests

### Reactive Applications (Spring WebFlux)

- Uses a non-blocking execution model
- Security context is propagated reactively
- Security components are adapted to reactive streams

Despite implementation differences, the **core security principles remain identical**.

> Different execution models, same security architecture.

---

## Extension and Customization Points

Spring Security is designed to be extensible without breaking its architectural model.

Common extension points include:

- Custom security filters
- Custom authentication mechanisms
- External identity provider integration
- Method-level authorization strategies

These extensions integrate into the existing flow rather than bypassing it, preserving consistency and predictability.

---

## Architectural Principles Recap

Spring Security architecture is built on the following principles:

- Security is enforced before business logic
- Authentication and authorization are separate concerns
- Security state is contextual, not global
- Execution order matters
- Integration is centralized and consistent

---

## Summary

Spring Security is not a collection of annotations or configuration options.

It is a **security execution engine** that intercepts requests, establishes identity, evaluates permissions, and enforces access rules across the entire application lifecycle.

Understanding this architecture is essential to:

- Avoid misconfiguration
- Design secure systems
- Reason about security behavior
- Extend Spring Security responsibly

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
