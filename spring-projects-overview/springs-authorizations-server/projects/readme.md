# Projects

This directory contains **practical, architecture-focused projects** demonstrating real-world usage of **Spring Authorization Server**.

These projects are not tutorials.  
They are designed to showcase **correct OAuth 2.1 and OpenID Connect usage**, clear separation of responsibilities, and production-oriented security decisions.

---

## Project Goals

The projects in this module aim to demonstrate:

- Proper Authorization Server isolation
- Correct OAuth 2.1 grant type usage
- OpenID Connect identity flows
- Secure token issuance and validation
- Enterprise-ready security architecture

---

## Available Projects

### Basic Authorization Server

**Folder:** `basic-authorization-server`

Minimal OAuth 2.1 Authorization Server implementation.

**Focus:**
- Authorization Server fundamentals
- Authorization Code + PKCE
- Client registration
- Token issuance
- JWK publishing

---

### Authorization Server with OpenID Connect

**Folder:** `authorization-server-with-oidc`

Adds OpenID Connect support on top of OAuth 2.1.

**Focus:**
- OAuth vs OIDC
- ID Tokens
- User identity propagation
- UserInfo endpoint

---

### Resource Server Protected by JWT

**Folder:** `resource-server-jwt`

A standalone API protected by JWTs issued by the Authorization Server.

**Focus:**
- Resource Server responsibilities
- JWT validation
- Scope-based access control
- Stateless security

---

### Backend-for-Frontend (BFF) Authentication Flow

**Folder:** `bff-authentication-flow`

Implements a secure authentication flow for frontend applications using a BFF.

**Focus:**
- Authorization Code + PKCE
- Token protection
- Session management
- OIDC integration

---

### Machine-to-Machine Authentication

**Folder:** `machine-to-machine-auth`

Demonstrates service-to-service authentication using OAuth 2.1.

**Focus:**
- Client Credentials flow
- Machine identity
- Technical scopes
- Zero user context

---

### Multi-Client OAuth Architecture

**Folder:** `multi-client-oauth-architecture`

A complete OAuth ecosystem with multiple client types.

**Focus:**
- Multiple grant types
- Client isolation
- Trust boundaries
- Authorization governance

---

## Architectural Principles

Across all projects, the following principles apply:

- Authorization Servers are treated as infrastructure
- OAuth is used strictly according to specification
- Security decisions are explicit and documented
- Business logic is never coupled to token issuance

---

## Notes

These projects are intentionally designed to reflect **real production scenarios**, not simplified demos.

Understanding why each architectural decision was made is more important than the code itself.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
