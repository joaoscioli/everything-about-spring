# Spring Security – Project Examples

This folder contains **hands-on projects** designed to demonstrate Spring Security concepts incrementally, from fundamentals to advanced, real-world security architectures.

Each project is **self-contained**, focused on a specific security concern, and aligned with production-grade practices.

---

## Project Philosophy

The goal of these projects is **not** to teach Spring Security configuration line by line, but to:

* Show *why* certain security decisions exist
* Demonstrate *where* Spring Security fits architecturally
* Expose common trade-offs and pitfalls
* Mirror real backend security setups

Projects are ordered from **foundational → advanced**.

---

## Basic Authentication API

**Folder:** `01-basic-auth-api`

### Goal

Understand the **minimum Spring Security setup** and the core authentication flow.

### Features

* HTTP Basic Authentication
* In-memory users
* Secured and public endpoints

### Concepts Covered

* Security filter chain
* Authentication vs Authorization
* `AuthenticationManager`
* `SecurityContext`

### When to Use

* Internal tools
* Proof of concepts
* Learning the security pipeline

---

## Form Login Web Application

**Folder:** `02-form-login-web`

### Goal

Demonstrate Spring Security in a **traditional web MVC application**.

### Features

* Custom login page
* Session-based authentication
* Role-based authorization

### Concepts Covered

* Session management
* CSRF protection
* Login flows
* Logout handling

### When to Use

* Admin panels
* Legacy web applications
* Server-rendered apps

---

## REST API with JWT Authentication

**Folder:** `03-jwt-auth-api`

### Goal

Build a **stateless REST API** using JWT for authentication.

### Features

* Login endpoint issuing JWT
* Stateless security
* Token validation filter

### Concepts Covered

* Stateless authentication
* Custom security filters
* Token-based identity
* SecurityContext propagation

### When to Use

* Mobile backends
* SPA backends
* Microservices

---

## OAuth2 Resource Server

**Folder:** `04-oauth2-resource-server`

### Goal

Secure APIs using **OAuth2 and JWT issued by an external Identity Provider**.

### Features

* OAuth2 Resource Server
* JWT validation via JWK
* Scope-based authorization

### Concepts Covered

* OAuth2 roles
* Access tokens
* Authorization scopes
* Externalized identity

### When to Use

* Enterprise systems
* Systems with SSO
* Integration with Auth0, Keycloak, Cognito

---

## Method-Level Security

**Folder:** `05-method-security`

### Goal

Apply security rules **inside the domain and service layers**.

### Features

* `@PreAuthorize`
* `@PostAuthorize`
* Custom permission evaluators

### Concepts Covered

* Expression-based authorization
* Domain-level security
* Defense in depth

### When to Use

* Complex business rules
* Multi-tenant systems
* Fine-grained access control

---

## Multi-Tenant Security

**Folder:** `06-multi-tenant-security`

### Goal

Demonstrate tenant isolation at the security level.

### Features

* Tenant resolution from token
* Tenant-aware authorization
* Data isolation strategy

### Concepts Covered

* Tenant context
* Authorization per tenant
* Security + data boundaries

### When to Use

* SaaS platforms
* B2B systems
* Shared infrastructure apps

---

## Microservices Security (Gateway + Services)

**Folder:** `07-microservices-security`

### Goal

Secure a **distributed system** using a centralized authentication strategy.

### Features

* API Gateway authentication
* Downstream service authorization
* Token propagation

### Concepts Covered

* Zero trust
* Gateway patterns
* Service-to-service security

### When to Use

* Microservice architectures
* Cloud-native systems
* Platform engineering

---

## Security Testing

**Folder:** `08-security-testing`

### Goal

Test security rules **without fragile integration tests**.

### Features

* Mock authentication
* Authorization testing
* Security context simulation

### Concepts Covered

* `@WithMockUser`
* Test slices
* Security regression prevention

### When to Use

* Any production system
* Regulated environments
* CI/CD pipelines

---

## Optional Advanced Projects

These projects are recommended after mastering the core ones:

* **Custom Authentication Provider**
* **Attribute-Based Access Control (ABAC)**
* **Policy-based authorization (OPA integration)**
* **Zero-trust internal APIs**

---

## How to Navigate

Each project contains:

* `README.md` → What & why
* `architecture.md` → Security design
* `security-flow.md` → Request lifecycle
* Source code with minimal boilerplate

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*
