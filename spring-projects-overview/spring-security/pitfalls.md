# Common Pitfalls

This document highlights **common mistakes and misconceptions** when working with Spring Security.

These pitfalls are not caused by lack of features, but by **incorrect mental models**.  
Most security issues arise not from what is missing, but from what is misunderstood.

---

## Treating Spring Security as a Configuration Problem

A frequent mistake is treating Spring Security as something that can be “enabled” or “disabled” through configuration alone.

Security is not a switch.  
It is a **system-level design concern** that affects architecture, request flow, and responsibility boundaries.

When security is treated as configuration, it becomes fragile, implicit, and difficult to reason about.

---

## Relying Blindly on Auto-Configuration

Spring Boot provides sensible security defaults, but relying on them without understanding their implications is dangerous.

Auto-configuration:
- Makes initial decisions
- Does not replace security design
- Should be understood, not blindly accepted

Security that “works by default” often stops working when requirements evolve.

---

## Confusing Authentication with Authorization

Authentication answers *who you are*.  
Authorization answers *what you are allowed to do*.

A common pitfall is assuming that a successfully authenticated user is automatically authorized to perform actions.

This confusion often results in systems where:
- Login is enforced
- Access control is weak or nonexistent

Authentication without proper authorization is not security.

---

## Securing Only Controllers

Many applications enforce security only at the HTTP or controller level.

This approach assumes that:
- Controllers are the only entry point
- Internal method calls are always safe

In reality, controllers are not trust boundaries.  
Business logic must also be protected.

---

## Ignoring Method-Level Security

Failing to apply security at the method level leaves core business operations exposed.

Method-level security:
- Protects services and domain logic
- Enforces rules regardless of how methods are invoked
- Prevents internal bypasses

Relying solely on URL-based security creates false confidence.

---

## Overusing Roles

Using roles as the only authorization mechanism leads to rigid and coarse-grained security models.

Common symptoms include:
- Excessive use of `ADMIN` roles
- Authorization logic that lacks expressiveness
- Difficulty evolving access rules

Roles are useful, but they are not sufficient for complex systems.

---

## Hardcoding Security Rules

Embedding security rules directly in application logic creates tight coupling and poor maintainability.

Hardcoded rules:
- Are difficult to audit
- Are easy to break accidentally
- Spread authorization logic across the codebase

Security rules should be centralized and declarative whenever possible.

---

## Misunderstanding the Filter Chain

Spring Security relies on an ordered chain of filters.

A common mistake is ignoring:
- Filter order
- Filter responsibility
- The fact that filters may short-circuit requests

Misunderstanding the filter chain leads to unpredictable behavior and subtle security bugs.

---

## Disabling CSRF Without Understanding the Impact

CSRF protection is often disabled to “make things work”.

This is usually done without understanding:
- What CSRF protects against
- When it is relevant
- Which architectures require it

Disabling CSRF blindly trades convenience for vulnerability.

---

## Misconfiguring CORS

CORS is frequently misunderstood as a backend security mechanism.

In reality:
- CORS is enforced by browsers
- It protects users, not servers
- Overly permissive CORS configurations expose unnecessary risk

CORS misconfiguration is often used as a quick fix for frontend integration problems.

---

## Treating JWT as a Silver Bullet

JWTs are often seen as a universal solution for authentication and authorization.

In practice:
- Tokens introduce revocation challenges
- Token leakage is hard to mitigate
- Token validation does not replace access control logic

JWT is a tool, not a security strategy.

---

## Mixing Security and Business Logic

Embedding security checks directly into business code creates tight coupling and reduces clarity.

Examples include:
- Conditional logic based on user roles
- Permission checks scattered across services

This approach makes security:
- Hard to audit
- Hard to evolve
- Hard to reason about

Security should guard business logic, not live inside it.

---

## Ignoring Security Context Propagation

Spring Security relies on contextual security state.

In asynchronous or reactive flows, failing to propagate the security context correctly results in:
- Missing authentication information
- Inconsistent authorization decisions
- Hard-to-debug issues

Security context propagation must be considered part of system design.

---

## Assuming Security Is Stateless by Default

Many developers assume that modern security is inherently stateless.

In reality:
- Statelessness is an architectural choice
- Spring Security supports both models
- Mixing stateful and stateless assumptions leads to bugs

Understanding where security state lives is essential.

---

## Underestimating the Learning Curve

Spring Security appears simple on the surface, but its depth is often underestimated.

Common behaviors include:
- Copying configuration from tutorials
- Disabling features without understanding them
- Treating security as an afterthought

> Spring Security is simple in principle, but deep in execution.

---

## Summary

Most Spring Security pitfalls stem from **incorrect assumptions**, not missing features.

Avoiding these pitfalls requires:
- Understanding core concepts
- Respecting the security architecture
- Treating security as a first-class system concern

Security that is not understood cannot be trusted.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
