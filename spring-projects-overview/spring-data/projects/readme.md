# Spring Data – Practice Projects

This folder contains **hands-on projects** designed to reinforce the concepts, architecture decisions, and pitfalls discussed in the Spring Data module.

These projects are **not tutorials**. They are **learning laboratories** focused on real-world problems, trade-offs, and architectural decisions developers face in production systems.

---

## 🎯 Project Goals

Each project aims to:

* Demonstrate correct and incorrect uses of Spring Data
* Expose performance and design pitfalls
* Encourage architectural thinking, not framework memorization
* Simulate real production scenarios

---

## 1️⃣ CRUD Foundation – Clean Repository Design

### Project: `spring-data-crud-foundation`

**Focus**

* Proper repository responsibilities
* Separation between repository, service, and domain layers

**Key Concepts**

* Repository abstraction
* Transaction boundaries
* DTO vs Entity separation

**What This Project Teaches**

* How to avoid repository-as-service anti-patterns
* Where business logic should live

---

## 2️⃣ The N+1 Problem in Practice

### Project: `spring-data-n-plus-one`

**Focus**

* Lazy loading pitfalls
* Hidden query explosions

**Key Concepts**

* Fetch strategies
* `@EntityGraph`
* Fetch joins

**What This Project Teaches**

* How N+1 happens silently
* Multiple ways to fix it and their trade-offs

---

## 3️⃣ Pagination and Indexing Under Load

### Project: `spring-data-pagination-performance`

**Focus**

* Pagination on large datasets
* Database indexing strategies

**Key Concepts**

* `PageRequest`
* Sorting cost
* Execution plans

**What This Project Teaches**

* Why pagination is not free
* How indexes impact real response times

---

## 4️⃣ Derived Queries vs Explicit Queries

### Project: `spring-data-query-design`

**Focus**

* Readability vs convenience
* Long derived query method names

**Key Concepts**

* Derived queries
* `@Query`
* Specifications

**What This Project Teaches**

* When derived queries help
* When they harm maintainability

---

## 5️⃣ Transactions and Consistency

### Project: `spring-data-transaction-boundaries`

**Focus**

* Transaction misuse
* Propagation and isolation

**Key Concepts**

* `@Transactional`
* Service-layer transactions
* Rollback scenarios

**What This Project Teaches**

* How wrong transaction boundaries cause bugs
* How to design safe transactional flows

---

## 6️⃣ Hybrid Data Access Strategy

### Project: `spring-data-hybrid-access`

**Focus**

* Combining Spring Data with JDBC Template

**Key Concepts**

* Abstraction layering
* Performance optimization

**What This Project Teaches**

* When to bypass Spring Data safely
* How to mix abstractions without chaos

---

## 7️⃣ JPA vs jOOQ – Architectural Comparison

### Project: `spring-data-vs-jooq`

**Focus**

* ORM vs SQL-centric design

**Key Concepts**

* Domain modeling
* Query ownership
* Maintainability

**What This Project Teaches**

* Trade-offs between object-centric and SQL-centric approaches
* How architectural goals influence tooling

---

## 8️⃣ NoSQL Is Not SQL

### Project: `spring-data-nosql-modeling`

**Focus**

* Modeling data for NoSQL access patterns

**Key Concepts**

* Denormalization
* Query-driven modeling

**What This Project Teaches**

* Why relational thinking fails in NoSQL
* How Spring Data adapts across datastores

---

## 9️⃣ Testing Spring Data Correctly

### Project: `spring-data-testing-strategies`

**Focus**

* Testing repositories and services

**Key Concepts**

* Slice tests
* Testcontainers
* Integration vs unit tests

**What This Project Teaches**

* Balanced testing strategies
* How to avoid slow or meaningless tests

---

## 🧠 Suggested Learning Path

Recommended order:

1. CRUD Foundation
2. Query Design
3. Transactions
4. N+1 Problem
5. Pagination Performance
6. Hybrid Access
7. NoSQL Modeling
8. Testing Strategies
9. JPA vs jOOQ

---

## 📌 Final Note

These projects are intentionally opinionated.

They are designed to help developers **think like architects**, understand **why abstractions exist**, and recognize **when to bypass them responsibly**.

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*
