# Pitfalls in the Spring Framework

## Introduction

The Spring Framework is extremely powerful and flexible. However, this
flexibility also allows bad architectural practices and
wrong decisions to go unnoticed—especially in projects that grow rapidly.

This document describes the main **common errors, technical pitfalls, and anti-patterns** found in Spring projects (including Spring Boot), focusing on architecture, transactions, persistence, performance, and testing.

> Important: Spring does not prevent bad decisions. It amplifies both good and bad architectural choices.

# 1. Architectural Pitfalls

## 1.1 Controllers with Business Rules

Problems: - Business rules coupled to the web layer - Difficult reuse - Low testability

Solution: - Move the rules to the service or domain layer.

## 1.2 Giant Services (God Services)

Classes with hundreds or thousands of lines containing: - Business logic - Validations - Orchestrations - External calls - Entity manipulation

This generates: - Low cohesion - High complexity - Difficult code to maintain

## 1.3 Entities Exposed Directly in the API

Problems: - Leakage of internal model - Problems with Lazy Loading - Exposure of sensitive fields

Solution: - Use DTOs. - Map explicitly.

## 1.4 Circular Dependencies

Problems: - Indicates incorrect architecture - Use of @Lazy as a patch

If there is a circular dependency, there is probably poorly defined responsibility.

# 2. Pitfalls of Dependency Injection

## 2.1 Field Injection

Problems: - Makes testing difficult - Violates immutability - Hides real dependencies

Prefer injection via constructor.

## 2.2 Excessive Use of @Component

Problems: - Bloated container - Domain coupled to the framework - Lack of architectural clarity

Not everything should be a Bean.

# 3. Pitfalls with Spring Boot

## 3.1 Misunderstood Auto-configuration

Common problems: - Duplicate beans - Invisible conflicts - Customizations that don't work as expected

Always understand what is being auto-configured.

## 3.2 Disorganized application.yml

Problems: - Scattered configurations - Hardcoded values ​​- Lack of appropriate profiles

Use profiles and environment variables.

# 4. Pitfalls with JPA

## 4.1 LazyInitializationException

Occurs when: - The session has already been closed - A lazy relationship is accessed outside the transaction

Correct solution: - Use fetch joins - Use DTO projections - Define clear transaction boundaries

## 4.2 N+1 Queries

Result: - 1 query + N additional queries

Solution: - JOIN FETCH - EntityGraph - Projections

## 4.3 Incorrect Equals/HashCode in Entities

Can generate: - Strange behaviors in collections - Bugs that are difficult to track


## 4.4 Excessive Use of Cascade

Can: - Accidentally delete data - Generate unexpected operations

# 5. Pitfalls with Transactions

## 5.1 @Transactional in Private Method

Spring uses a proxy.

Private methods: - Are not intercepted - Transaction is not applied

## 5.2 Self-invocation

The internal call bypasses the proxy.

Solution: - Separate responsibilities - Call via external bean

## 5.3 Very Long Transactions

Problems: - Locks - Performance degradation

# 6. Performance Pitfalls

## 6.1 Singleton Beans with Mutable State

If they have mutable state: - Concurrency problems - Unpredictable bugs

## 6.2 Poorly Configured Cache

- Cache without invalidation
- Cache without TTL
- Indiscriminate use of @Cacheable

## 6.3 Poorly Sized Connection Pool

Can cause: - Timeout - Connection exhaustion - Invisible bottlenecks

# 7. Pitfalls with Tests

## 7.1 Excessive use of @SpringBootTest

- Slow tests
- Delayed feedback

Prefer more specific tests such as @WebMvcTest and @DataJpaTest.

## 7.2 Excessive Mocks

- Weak tests
- False positives

Real integration tests are essential.

# 8. Classic Anti-patterns

- Business Logic in the Controller
- Generic Repository for Everything
- Exceptions as Normal Flow
- Premature Microservices
- Copying Configurations Without Understanding

# 9. Common "Illusions" in Spring

- "Spring solves architecture"
- "@Transactional solves consistency"
- "Spring Boot solves performance"
- "Dependency injection means automatic decoupling"
- "Annotations replace design"

# Conclusion

Spring is a powerful tool --- but it doesn't replace: - Good modeling - Well-defined architecture - Separation of responsibilities - Deep knowledge of what is being used

Frameworks accelerate development. Architecture sustains systems.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
