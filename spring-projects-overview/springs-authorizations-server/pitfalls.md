# Pitfalls

Spring Authorization Server provides the core building blocks required to implement OAuth 2.1 and OpenID Connect authorization servers.

However, **OAuth is a security protocol, not a convenience feature**.  
Most problems when using Spring Authorization Server do not come from the framework itself, but from **incorrect architectural assumptions and protocol misuse**.

This document outlines the most common pitfalls teams encounter when designing, implementing, and operating an Authorization Server.

---

## Treating the Authorization Server as a Regular Application

A frequent mistake is treating the Authorization Server like any other Spring Boot service.

An Authorization Server is a **security boundary**.  
It issues credentials that grant access across multiple systems.

Common consequences of this mistake include:

- Insufficient isolation from business services
- Weak operational security controls
- Underestimated blast radius in case of compromise

> An Authorization Server is infrastructure, not business logic.

---

## Mixing Authorization Server and Resource Server Responsibilities

Combining token issuance and protected APIs in the same application is a well-known anti-pattern.

Typical issues include:

- Business APIs deployed alongside token endpoints
- Authorization logic mixed with domain logic
- Difficulty auditing and monitoring security-related events

Recommended separation:

- **Authorization Server** → issues and manages tokens
- **Resource Server** → validates tokens and protects APIs

This separation reduces attack surface and improves security governance.

---

## Incorrect Grant Type Usage

OAuth grant types represent **trust relationships**.  
Using the wrong flow often results in insecure systems.

Common mistakes:

- Using deprecated flows such as Resource Owner Password Credentials
- Using Client Credentials for end-user authentication
- Skipping PKCE in Authorization Code flows
- Treating all clients as equally trusted

OAuth 2.1 intentionally restricts grant types to encourage safer defaults.

> Choosing a grant type is a security decision, not a technical shortcut.

---

## Underestimating Token Design Decisions

Tokens are **credentials**, not data containers.

Common pitfalls include:

- Embedding sensitive or excessive data in JWTs
- Using long-lived access tokens
- Confusing opaque tokens with self-contained JWTs
- Ignoring token revocation strategies

Poor token design increases the impact of token leakage and limits future flexibility.

---

## Weak Client Registration and Management

OAuth clients are **security identities** and require lifecycle management.

Frequent issues:

- Hardcoded client registrations
- Shared clients across multiple applications
- Secrets stored in plaintext
- No rotation or expiration policy

Without proper client governance, revoking access becomes operationally expensive and error-prone.

---

## Poor Key Management and Rotation

Cryptographic keys are the foundation of trust in OAuth systems.

Common problems include:

- Static signing keys used indefinitely
- No support for key rotation
- Missing or incorrect `kid` usage in JWKs
- Confusing signing keys with encryption keys

A compromised signing key compromises **every issued token**.

Key management must be treated as a first-class operational concern.

---

## Ignoring Operational and Compliance Concerns

Authorization Servers are often subject to strict compliance and audit requirements.

Pitfalls include:

- Missing audit logs for token issuance and revocation
- No visibility into authentication failures
- Lack of rate limiting and abuse detection
- No monitoring of key or client usage

OAuth infrastructure is frequently within the scope of:

- GDPR / LGPD
- SOC 2
- ISO 27001
- Internal security audits

---

## Assuming Spring Authorization Server Is a Plug-and-Play Solution

Spring Authorization Server is a **framework**, not a turnkey identity product.

It intentionally:

- Exposes extension points instead of fixed policies
- Avoids opinionated identity models
- Requires explicit security decisions

Teams expecting an out-of-the-box IAM solution often implement insecure defaults.

> Spring Authorization Server provides primitives, not policies.

---

## Misunderstanding OAuth vs Authentication

OAuth is not an authentication protocol.

Common misunderstandings:

- Treating OAuth tokens as proof of user identity
- Implementing login flows without OpenID Connect
- Mixing authentication and authorization concepts

OAuth handles **delegation**.  
OpenID Connect adds **identity** on top of OAuth.

Confusing these layers leads to fragile and insecure implementations.

---

## Skipping Threat Modeling

Many OAuth implementations fail due to lack of threat modeling.

Commonly ignored risks:

- Token leakage through logs or URLs
- Replay attacks
- Overly permissive scopes
- Insecure redirect URI handling

OAuth systems operate across multiple trust boundaries and require explicit threat analysis.

> OAuth must be designed with an adversarial mindset.

---

## Summary

Spring Authorization Server enables secure token-based systems, but it does not remove the need for:

- Protocol knowledge
- Architectural discipline
- Security ownership

Most pitfalls arise not from missing features, but from **misunderstanding what OAuth is designed to solve**.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
