# Spring LDAP – Projects

This directory contains practical, architecture-oriented projects designed to demonstrate real-world usage of Spring LDAP in enterprise systems.

These are not simple CRUD examples.
They focus on integration boundaries, identity modeling, and infrastructure isolation.

---

## Available Projects

---

### 01 - ldap-authentication-service

An authentication microservice that validates user credentials against LDAP.

Focus areas:

- Spring Security integration
- Bind authentication
- Group-based role resolution
- Clean architecture separation

---

### 02 - ldap-user-synchronizer

A scheduled synchronization service that imports LDAP users into a relational database.

Focus areas:

- SUBTREE search strategies
- Schema decoupling
- Idempotent synchronization
- Error handling and retries

---

### 03 - ldap-organization-browser-api

A REST API that exposes organizational hierarchies stored in LDAP.

Focus areas:

- Hierarchical navigation
- Efficient search scope usage
- DTO mapping strategies
- Performance considerations

---

### 04 - ldap-identity-gateway

An identity integration adapter that isolates LDAP schema from the domain layer.

Focus areas:

- Anti-corruption layer
- Adapter pattern
- Replaceable identity provider design
- Infrastructure boundary isolation

---

## Design Philosophy

All projects in this directory aim to demonstrate:

- Correct architectural placement of Spring LDAP
- Infrastructure boundary enforcement
- Proper handling of hierarchical directory data
- Enterprise-ready integration patterns

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*