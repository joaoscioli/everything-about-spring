# Architecture

Spring LDAP provides an architectural abstraction layer between Spring
applications and LDAP directory services.

It is not a persistence framework. It is an integration infrastructure
component.

---

## 01 - Architectural Role

Spring LDAP acts as:

-   A directory access adapter
-   A boundary between application services and LDAP infrastructure
-   A wrapper over JNDI
-   A consistency layer aligned with the Spring programming model

It should reside in the infrastructure layer of your application.

---

## 02 - Layered Architecture Model

A typical architecture looks like this:

Application Layer ↓ Domain / Service Layer ↓ Directory Access Layer
(Spring LDAP) ↓ JNDI (Java Naming and Directory Interface) ↓ LDAP Server
(Active Directory, OpenLDAP, etc.)

### Application Layer

Contains:

-   Controllers
-   REST APIs
-   Messaging endpoints

This layer must not depend on LDAP details.

### Domain / Service Layer

Contains:

-   Business rules
-   Authentication orchestration
-   Identity-related workflows

Should depend on abstractions (interfaces), not directly on
LdapTemplate.

### Directory Access Layer

Implemented using:

-   LdapTemplate
-   ContextSource
-   Custom mappers
-   Optional ODM layer

This layer isolates:

-   DN construction
-   Search filters
-   Attribute mapping
-   Exception handling

This is where Spring LDAP lives.

### JNDI Layer

Low-level API responsible for:

-   Connections
-   Context management
-   Bind operations
-   Search execution

Spring LDAP wraps and manages this layer to eliminate boilerplate.

### LDAP Server

External infrastructure providing:

-   Hierarchical directory data
-   Identity storage
-   Organizational structures

Examples include:

-   Active Directory
-   OpenLDAP
-   Corporate IAM systems

---

## 03 - Execution Flow Example

Example: User lookup by username.

Application Service ↓ UserDirectoryService ↓ LdapTemplate.search() ↓
ContextSource.getReadOnlyContext() ↓ JNDI Search ↓ LDAP Server ↓
SearchResult → Attribute Mapping ↓ Domain Object

Spring LDAP manages:

-   Context lifecycle
-   Exception translation
-   Result mapping

---

## 04 - Integration with Spring Security

When used for authentication, the flow becomes:

AuthenticationManager ↓ LdapAuthenticationProvider ↓ Spring LDAP ↓ LDAP
Server (Bind or Compare)

In this case, Spring LDAP becomes part of the security boundary of the
system.

It participates directly in identity verification.

---

## 05 - Architectural Constraints of LDAP

Architects must understand that LDAP is:

-   Hierarchical (tree-based)
-   Filter-driven (not SQL-based)
-   Optimized for read-heavy workloads
-   Not designed for transactional domain data

This affects:

-   Data modeling
-   Query strategies
-   Performance expectations

You cannot design it like a relational database.

---

## 06 - Recommended Architectural Patterns

### Use as an Integration Adapter

Wrap Spring LDAP inside:

-   UserDirectoryRepository
-   LdapUserGateway
-   IdentityProviderAdapter

Expose only domain objects to the rest of the system.

### Isolate DN Logic

DN construction should not leak into business services.

Use:

-   Builders
-   Configuration-based base DNs
-   Dedicated mapping utilities

### Do Not

-   Inject LdapTemplate directly into controllers
-   Couple business rules to LDAP schema
-   Expose LDAP-specific models outside infrastructure

---

## 07 - Transaction Model Consideration

LDAP does not provide:

-   ACID transactions comparable to relational databases

Spring LDAP offers limited transaction support, but:

It should not be treated as a transactional persistence engine.

---

## 08 - Deployment & Infrastructure Positioning

In enterprise environments, LDAP servers are:

-   Centrally managed
-   Shared across multiple systems
-   High-availability configured

Your application must treat LDAP as:

-   An external infrastructure dependency
-   A network-bound service
-   A potentially latency-sensitive component

Connection pooling and proper timeout configuration are critical.

---

## Architectural Summary

Spring LDAP is:

-   An infrastructure adapter
-   A wrapper over JNDI
-   A directory integration layer

It belongs in the infrastructure boundary of your system.

It is not:

-   A database
-   A domain model engine
-   A general persistence framework

Understanding its architectural position prevents misuse and fragile
system design.

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*