# Core Concepts

This document introduces the **fundamental concepts of Spring Security**.  
It focuses on *mental models*, not configuration or code.

Understanding these concepts is essential to avoid misconfiguration, security gaps, and incorrect assumptions when working with Spring Security.

---

## Authentication vs Authorization

**Authentication** answers the question:
> *Who are you?*

It is the process of verifying the identity of a user, system, or client.

**Authorization** answers the question:
> *What are you allowed to do?*

It determines whether an authenticated principal has permission to access a specific resource or execute an operation.

Authentication must always occur **before** authorization.  
Confusing these two concepts is one of the most common causes of insecure systems.

> Authentication establishes identity. Authorization enforces access.

---

## Principal

A **Principal** represents the authenticated entity interacting with the system.

This entity is not always a human user. A principal may represent:
- A system or service
- An external client
- An application using a token
- A user authenticated via an identity provider

Spring Security treats the principal as an abstract identity, independent of how authentication was performed.

---

## Credentials

**Credentials** are the proof used to verify a principal’s identity during authentication.

Examples include:
- Passwords
- Tokens
- Certificates
- One-time codes

Credentials are used **only during authentication**.  
After authentication is complete, credentials should no longer be required or exposed.

> Credentials prove identity — they are not permissions.

---

## GrantedAuthority

A **GrantedAuthority** represents a permission assigned to a principal.

Spring Security performs authorization checks based on authorities, not directly on users or roles.

Authorities are typically:
- Strings representing permissions
- Fine-grained and expressive
- Evaluated during authorization decisions

They form the foundation of access control in Spring Security.

---

## Roles vs Authorities

In Spring Security, **roles are a convention**, not a separate security model.

Key distinctions:
- A role is a specialized type of authority
- Roles typically follow the `ROLE_` naming convention
- Authorities are more flexible and fine-grained
- Roles are usually coarse-grained groupings

> All roles are authorities, but not all authorities are roles.

Relying exclusively on roles often leads to rigid and less expressive security models.

---

## SecurityContext

The **SecurityContext** holds all security-related information for the current execution.

It typically contains:
- The authenticated principal
- Granted authorities
- Authentication details

The security context exists **per execution flow**, not globally.  
It defines *who* is acting and *with which permissions* at a given moment.

---

## SecurityContextHolder

The **SecurityContextHolder** is responsible for storing and accessing the current `SecurityContext`.

By default, the context is:
- Bound to the current thread
- Available throughout the execution lifecycle

This design has important implications for:
- Asynchronous execution
- Reactive programming
- Thread switching

Understanding where the security context lives is critical for secure system behavior.

---

## Filter Chain

Spring Security enforces security through a **filter-based architecture**.

Incoming requests pass through a chain of security filters **before** reaching application logic.

Key characteristics:
- Filters are ordered
- Each filter has a focused responsibility
- Security decisions occur early in the request lifecycle

> Security is enforced at the boundary, not inside controllers.

---

## Web Security vs Method Security

Spring Security supports security enforcement at multiple levels.

**Web Security**
- Applied at the HTTP and endpoint level
- Protects URLs and request patterns

**Method Security**
- Applied at the service or domain layer
- Protects business operations directly

Relying only on web-level security is insufficient for complex systems.

> Security at the edge is not enough.

---

## Session-based vs Token-based Security

**Session-based security**
- Stores authentication state on the server
- Relies on server-managed sessions
- Common in traditional web applications

**Token-based security**
- Carries authentication state on the client
- Each request contains the necessary credentials
- Common in APIs and distributed systems

Both approaches can be secure when used correctly, but they serve different architectural needs.

---

## Stateless vs Stateful Security

These concepts describe where security state is maintained.

**Stateful security**
- The server maintains authentication state
- Often paired with sessions

**Stateless security**
- No server-side authentication state
- Each request is self-contained
- Common in REST APIs and microservices

Stateless security improves scalability but requires careful design.

---

## CSRF (Cross-Site Request Forgery)

CSRF is an attack where a malicious site causes a user’s browser to perform unwanted actions on another site.

Key points:
- It exploits implicit trust in browser-based credentials
- It primarily affects session-based applications
- It is less relevant for stateless APIs

CSRF protection exists to defend users, not servers.

---

## CORS (Cross-Origin Resource Sharing)

CORS is a **browser security policy**, not a backend security mechanism.

It controls:
- Which origins are allowed to access resources
- Which methods and headers are permitted

Spring Security participates in CORS handling, but CORS itself is enforced by browsers.

> CORS protects users, not servers.

---

## OAuth2

OAuth2 is an **authorization framework**, not an authentication protocol.

It enables:
- Delegated authorization
- Secure access to resources using tokens
- Separation between identity and access control

OAuth2 focuses on *what a client is allowed to access*, not *who the user is*.

---

## OpenID Connect (OIDC)

OpenID Connect (OIDC) is an **identity layer built on top of OAuth2**.

It adds:
- Standardized authentication
- User identity information
- ID tokens

OIDC enables modern authentication scenarios such as single sign-on and federated identity.

---

## Resource Server vs Authorization Server

These roles define responsibilities in token-based security systems.

**Authorization Server**
- Authenticates users or clients
- Issues tokens
- Manages identity and consent

**Resource Server**
- Hosts protected resources
- Validates incoming tokens
- Enforces access control

Separating these responsibilities is fundamental to scalable and secure architectures.

---

## Summary

Spring Security is built on a precise set of concepts that define:
- Identity
- Permissions
- Context
- Enforcement boundaries

Misunderstanding these concepts leads to fragile and insecure systems.

Understanding them enables:
- Correct configuration
- Predictable behavior
- Secure architectural decisions

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
