# Architecture

Spring Authorization Server is a **specialized Spring Security application** designed to act as an OAuth 2.1 and OpenID Connect (OIDC) Authorization Server.

This document describes its **architectural structure**, internal components, request flows, and extension points, focusing on **how the system works as a whole**, rather than implementation details.

---

## High-Level Architecture Overview

At a high level, Spring Authorization Server sits at the **center of a distributed security model**.

It interacts with three primary actors:

* **Clients** – applications requesting tokens
* **Users (Principals)** – authenticated identities (human or system)
* **Resource Servers** – APIs protected by issued tokens

The Authorization Server is responsible for:

* Authenticating principals
* Issuing tokens
* Managing authorization state
* Acting as a trust anchor for the system

Tokens issued by the Authorization Server become **security contracts** consumed by Resource Servers.

---

## Spring Security as the Foundation

Spring Authorization Server is built entirely on top of **Spring Security**.

It does not introduce a new security runtime. Instead, it composes:

* `SecurityFilterChain`
* `Authentication`
* `AuthenticationProvider`
* `AuthenticationManager`
* `SecurityContext`

Every OAuth request is processed using the **standard Spring Security authentication pipeline**.

> Spring Authorization Server is best understood as a carefully orchestrated set of Spring Security filter chains.

---

## Authorization Server Filter Chains

Spring Authorization Server defines **multiple specialized filter chains**, each responsible for a specific set of endpoints.

Typical endpoint groups include:

* Authorization Endpoint
* Token Endpoint
* JWK Set Endpoint
* OIDC Endpoints (UserInfo, Provider Configuration)

Each endpoint group:

* Matches specific request paths
* Uses its own authentication logic
* Delegates to dedicated `AuthenticationProvider` implementations

This separation ensures:

* Clear responsibility boundaries
* Predictable request handling
* Secure handling of different OAuth flows

---

## Core Services and Internal Components

The Authorization Server relies on a small set of **core services**, each with a focused responsibility.

### RegisteredClientRepository

Responsible for storing and retrieving OAuth clients.

Manages:

* Client identifiers and secrets
* Allowed grant types
* Redirect URIs
* Scopes
* Token settings

Clients are treated as **security principals**, not configuration artifacts.

---

### OAuth2AuthorizationService

Manages authorization state, including:

* Issued tokens
* Grant associations
* Token revocation data

This service represents the **stateful core** of the Authorization Server.

---

### OAuth2AuthorizationConsentService

Tracks user consent decisions.

Used when:

* Explicit user approval is required
* Scopes must be granted interactively

Consent is a first-class concept, not an afterthought.

---

### Token Infrastructure

Token generation and validation rely on:

* `JwtEncoder`
* `JwtDecoder`
* Cryptographic key material (JWK)

Token format and signing are **explicit architectural decisions**.

---

## Token Issuance Pipeline

Token issuance follows a well-defined pipeline.

A typical flow looks like this:

1. Request reaches an OAuth endpoint
2. A security filter extracts request parameters
3. An `Authentication` object is created
4. An `AuthenticationProvider` validates the grant
5. Authorization state is created or updated
6. Token(s) are generated
7. Claims are customized
8. Token is signed
9. Response is returned to the client

Each step is **individually extensible**, but tightly ordered.

> Token issuance is a pipeline, not a single operation.

---

## Customization and Extension Points

Spring Authorization Server is designed for **explicit customization**.

Common extension points include:

* Custom `AuthenticationProvider` implementations
* Token customizers for adding claims
* Client registration strategies
* Consent handling logic
* Persistence implementations

Customization is intentional and visible.

There are no hidden conventions or implicit magic.

---

## Persistence Architecture

Authorization Servers are inherently **stateful systems**.

Spring Authorization Server persists:

* Registered clients
* Issued authorizations
* Consent decisions
* Tokens (depending on configuration)

Persistence strategies include:

* In-memory (development only)
* JDBC
* JPA
* Custom storage solutions

Persistence choices directly affect:

* Scalability
* Token revocation
* Horizontal scaling
* Operational complexity

---

## Deployment Architecture

In production, Spring Authorization Server is typically deployed as a **standalone service**.

Common deployment characteristics:

* Dedicated runtime
* Shared database
* Centralized key management
* Network-level isolation

It integrates with:

* Resource Servers
* API Gateways
* Frontend applications
* External identity providers (optional)

The Authorization Server defines the **security perimeter** of the platform.

---

## Security and Trust Boundaries

Spring Authorization Server acts as a **trust authority**.

Key trust concepts include:

* Token signing keys
* JWK Set exposure
* Issuer identification
* Audience validation

Resource Servers trust tokens only if:

* The issuer is known
* The signature is valid
* Claims meet expected policies

Improper trust boundaries lead to systemic vulnerabilities.

---

## Summary

Spring Authorization Server is not a library.
It is **security infrastructure**.

Architecturally, it is:

* Built on Spring Security
* Composed of multiple filter chains
* Centered around token issuance and trust
* Designed for extensibility and correctness

Effective use requires:

* Understanding OAuth flows
* Respecting architectural boundaries
* Making deliberate security decisions

Authorization is not implemented.
It is **designed**.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
