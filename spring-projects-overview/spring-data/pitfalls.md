# Pitfalls

Spring Data significantly simplifies data access, but its abstraction can also hide important details. This document highlights common pitfalls encountered in real-world projects, explaining **why they happen**, **their impact**, and **how to avoid them**.

---

## 1. Misunderstanding Repositories

**The Pitfall**
Treating `JpaRepository` or other Spring Data repositories as generic DAOs and placing business logic inside them.

**Why It Happens**
Repositories feel convenient and powerful, which leads developers to gradually move logic into query methods.

**Impact**

* Tight coupling between business rules and persistence
* Harder unit testing
* Poor separation of concerns

**How to Avoid**

* Keep repositories focused on data access only
* Place business rules in the service or domain layer

---

## 2. The N+1 Query Problem

**The Pitfall**
Fetching a list of entities and lazily accessing related entities, triggering one query per record.

**Why It Happens**
Lazy loading is implicit and often invisible without SQL logging.

**Impact**

* Massive performance degradation
* Unexpected load on the database

**How to Avoid**

* Enable SQL logging in development
* Use `fetch join`, `@EntityGraph`, or tailored queries

---

## 3. Overusing Derived Query Methods

**The Pitfall**
Creating extremely long method names based on field combinations.

**Why It Happens**
Derived queries are quick to write and require no JPQL.

**Impact**

* Poor readability
* Bloated repository interfaces
* Difficult refactoring

**How to Avoid**

* Prefer `@Query` for complex queries
* Use Specifications or QueryDSL selectively

---

## 4. Lazy vs Eager Loading Misuse

**The Pitfall**
Switching everything to `EAGER` to fix `LazyInitializationException`, or blindly using `LAZY` everywhere.

**Why It Happens**
Misunderstanding the persistence context lifecycle.

**Impact**

* Memory overhead
* Unnecessary queries

**How to Avoid**

* Keep associations `LAZY` by default
* Load data explicitly when needed

---

## 5. Pagination and Sorting Pitfalls

**The Pitfall**
Paginating or sorting large datasets without proper database indexes.

**Why It Happens**
`PageRequest` makes pagination feel "free".

**Impact**

* Full table scans
* Slow response times

**How to Avoid**

* Always index sortable and filterable columns
* Validate pagination queries with `EXPLAIN`

---

## 6. Transactions Misuse

**The Pitfall**
Using `@Transactional` everywhere or not using it at all.

**Why It Happens**
Lack of understanding of transaction propagation and boundaries.

**Impact**

* Deadlocks and unnecessary locks
* Data inconsistency

**How to Avoid**

* Define transactions at the service layer
* Understand propagation and isolation levels

---

## 7. Entity Design Mistakes

**The Pitfall**
Designing entities that are either too large or completely anemic.

**Why It Happens**
Entities are often confused with DTOs.

**Impact**

* High coupling
* Painful schema evolution

**How to Avoid**

* Keep entities focused on persistence concerns
* Use DTOs for API communication

---

## 8. Ignoring Database Indexes

**The Pitfall**
Relying on JPA abstractions and ignoring database execution plans.

**Why It Happens**
The database feels distant behind the ORM.

**Impact**

* Slow queries
* Increased infrastructure cost

**How to Avoid**

* Analyze queries with execution plans
* Define indexes explicitly

---

## 9. Overusing Specifications and Criteria APIs

**The Pitfall**
Creating highly generic and complex Specifications that are hard to read and debug.

**Why It Happens**
Desire for maximum flexibility.

**Impact**

* Reduced maintainability
* Complex debugging

**How to Avoid**

* Use Specifications only for truly dynamic queries
* Prefer simpler, explicit queries when possible

---

## 10. Mixing Domain Logic with Persistence

**The Pitfall**
Embedding business rules directly in JPQL or database queries.

**Why It Happens**
Convenience and lack of clear layering.

**Impact**

* Fragile domain model
* Hard-to-change business rules

**How to Avoid**

* Keep domain logic in the service or domain layer
* Keep queries focused on data retrieval

---

## 11. NoSQL-Specific Pitfalls

**The Pitfall**
Using NoSQL databases as if they were relational.

**Why It Happens**
Prior experience limited to SQL-based systems.

**Impact**

* Poor performance
* Data duplication issues

**How to Avoid**

* Model data around access patterns
* Embrace denormalization when appropriate

---

## 12. Testing Pitfalls

**The Pitfall**
Either testing everything with a real database or mocking the persistence layer entirely.

**Why It Happens**
Unclear testing strategy.

**Impact**

* Slow test suites
* Low confidence in results

**How to Avoid**

* Combine repository integration tests with service-level unit tests
* Use Testcontainers when possible

---

## Final Thoughts

Spring Data is a powerful abstraction, but abstraction does not remove responsibility. Understanding what happens under the hood is essential to building scalable, maintainable, and production-ready systems.

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*
