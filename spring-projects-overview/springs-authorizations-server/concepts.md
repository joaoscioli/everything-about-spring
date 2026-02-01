# Core Concepts

Spring Authorization Server is an implementation of **OAuth 2.1 and OpenID Connect (OIDC)** built directly on top of Spring Security.

This document explains the **core concepts behind OAuth and OIDC**, focusing on how they are **modeled, enforced, and extended** within the Spring ecosystem.

The goal is not to reproduce the OAuth specifications, but to explain **how to think about authorization when building systems with Spring Authorization Server**.

---

## Authorization Server vs Resource Server

OAuth defines a strict separation of responsibilities.

### Authorization Server

The Authorization Server is responsible for:

* Authenticating principals
* Issuing access tokens
* Issuing refresh tokens
* Managing client credentials
* Enforcing authorization policies

In Spring, this role is implemented by **Spring Authorization Server**, which exposes standardized endpoints such as:

* Authorization Endpoint
* Token Endpoint
* JWK Set Endpoint
* UserInfo Endpoint (OIDC)

> Authorization Servers **issue tokens**. They do not protect business APIs.

---

### Resource Server

A Resource Server is responsible for:

* Protecting APIs and resources
* Validating incoming tokens
* Enforcing access rules based on scopes and claims

In the Spring ecosystem, Resource Servers are implemented using **Spring Security OAuth2 Resource Server** support.

> Resource Servers **consume tokens**. They never issue them.

Combining Authorization Server and Resource Server in the same application is possible but **strongly discouraged** in production systems.

---

## OAuth Roles: Client, Principal, and User

OAuth introduces multiple actors, each with a distinct role.

### Client

A Client is an application that requests access tokens.

Examples:

* Single Page Applications (SPAs)
* Mobile applications
* Backend services
* Batch jobs

In Spring Authorization Server, clients are represented by the `RegisteredClient` abstraction, which defines:

* Client ID and secret
* Allowed grant types
* Redirect URIs
* Allowed scopes

Clients are **first-class security actors** in OAuth.

---

### Principal

A Principal is the authenticated entity within the security context.

A Principal may represent:

* A human user
* A service account
* A machine identity

In Spring, principals are represented by `Authentication` objects stored in the `SecurityContext`.

---

### User

A User is a specific type of Principal that represents a human identity.

Not all OAuth flows involve users. For example:

* Client Credentials flow has **no user**
* Authorization Code flow involves a user

Understanding this distinction is critical when designing authorization rules.

---

## Authorization Grants (OAuth Flows)

Authorization grants define **how a client obtains a token**.

Spring Authorization Server implements modern OAuth 2.1 flows.

### Authorization Code (with PKCE)

This is the **recommended flow** for applications involving user interaction.

Characteristics:

* User authenticates via the Authorization Server
* Authorization code is exchanged for tokens
* PKCE protects public clients from interception attacks

Typical clients:

* SPAs
* Mobile apps
* Web applications

---

### Client Credentials

This flow is used for **machine-to-machine communication**.

Characteristics:

* No user interaction
* Client authenticates using its own credentials
* Tokens represent the client, not a user

Typical clients:

* Microservices
* Background jobs
* Internal system integrations

---

### Refresh Token

Refresh Tokens allow clients to obtain new access tokens without re-authentication.

Key considerations:

* Long-lived credentials
* Must be stored securely
* Can be revoked independently

Spring Authorization Server enforces refresh token policies at the Authorization Server level.

---

## Tokens: Access, Refresh, and ID Token

OAuth and OIDC define multiple token types, each with a specific responsibility.

### Access Token

Access Tokens are used to:

* Authorize requests to Resource Servers
* Represent granted permissions

They are commonly issued as **JWTs**, containing:

* Scopes
* Expiration
* Custom claims

Access Tokens should be treated as **short-lived credentials**.

---

### Refresh Token

Refresh Tokens are used to:

* Obtain new Access Tokens
* Extend authenticated sessions

They are never sent to Resource Servers.

Compromise of a Refresh Token implies **long-term access**.

---

### ID Token (OIDC)

ID Tokens are part of OpenID Connect and are used to:

* Represent authenticated identity
* Convey user-related claims

They are consumed by **clients**, not Resource Servers.

Using Access Tokens for authentication is a common architectural mistake.

---

## Scopes and Consent

Scopes define **what a client is allowed to access**.

They represent **contracts**, not implementation details.

Examples:

* `read:orders`
* `write:payments`
* `profile`

Authorization Servers may require explicit user consent before granting scopes.

In Spring Authorization Server:

* Scopes are defined at client registration
* User consent is tracked using `OAuth2AuthorizationConsent`

Consent handling can be customized or fully replaced when required.

---

## Client Types: Public vs Confidential

OAuth distinguishes clients based on their ability to protect credentials.

### Public Clients

Public clients cannot securely store secrets.

Examples:

* SPAs
* Mobile apps

Security relies on:

* PKCE
* Redirect URI validation
* Short-lived tokens

---

### Confidential Clients

Confidential clients can securely store secrets.

Examples:

* Backend services
* Server-rendered applications

They authenticate using:

* Client secrets
* Private keys
* Mutual TLS (advanced setups)

Choosing the wrong client type introduces serious security risks.

---

## OpenID Connect (OIDC) Layer

OAuth is an authorization protocol, not an authentication mechanism.

OpenID Connect extends OAuth by adding:

* ID Tokens
* Standard identity claims
* UserInfo endpoint

Spring Authorization Server implements OIDC as an **extension layer** on top of OAuth.

OIDC enables:

* Login flows
* Identity federation
* Single Sign-On (SSO)

Authentication logic remains centralized in the Authorization Server.

---

## Token Customization and Claims

Real-world systems rarely use default tokens.

Spring Authorization Server provides extension points for:

* Adding custom claims
* Mapping domain roles and permissions
* Supporting multi-tenancy

Token customization is commonly used to encode:

* User roles
* Tenant identifiers
* Business-specific permissions

Custom claims must be designed carefully to avoid token bloat and security leakage.

---

## Persistence Concepts

Authorization Servers manage multiple persistent artifacts:

* Registered clients
* Issued authorizations
* Consents
* Tokens (depending on strategy)

Spring Authorization Server supports multiple persistence models:

* In-memory (development only)
* JDBC
* JPA
* Custom implementations

Persistence choices directly affect:

* Scalability
* Revocation capabilities
* Operational complexity

---

## Security Boundaries and Trust Model

Authorization Servers act as **trust anchors** in distributed systems.

Key concepts include:

* Token signing keys
* JWK Sets
* Key rotation
* Trust relationships between systems

Resource Servers trust tokens **only if**:

* The issuer is trusted
* The signature is valid
* Claims match expected policies

Improper trust configuration is one of the most common causes of security vulnerabilities.

---

## Summary

Spring Authorization Server provides a **structured, standards-based model** for authorization and identity.

To use it effectively, developers must understand:

* OAuth roles and flows
* Token responsibilities
* Client trust models
* Spring Security integration points

Authorization is not a feature.
It is **infrastructure**.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
