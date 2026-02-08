# Spring Modulith – Example Projects

This directory contains a curated set of example projects designed to demonstrate **real-world usage of Spring Modulith**.

Each project focuses on:
- Clear module boundaries
- Domain-driven modularization
- Event-based communication
- Evolvability towards distributed systems

These projects are intentionally modeled after common enterprise and SaaS domains to showcase practical architectural decisions.

---

## Project Overview

| Project | Domain | Key Concepts |
|------|------|------|
| Modular E-commerce | Retail | Bounded contexts, events |
| Banking Core System | Fintech | Domain events, consistency |
| Subscription Platform | SaaS | Billing, lifecycle |
| Order Management System | Enterprise | Workflow orchestration |
| Identity & Access Management | Security | Authorization, auditing |

---

## 1. Modular E-commerce Platform

### Domain
A classic e-commerce system built as a **modular monolith**, prepared for future service extraction.

### Modules
- `catalog` – Product catalog and pricing
- `orders` – Order creation and lifecycle
- `payments` – Payment processing
- `shipping` – Shipment handling
- `customers` – Customer profiles

### Architectural Highlights
- Orders publish domain events consumed by Payments and Shipping
- No direct module-to-module service calls
- Module-level persistence isolation

---

## 2. Banking Core System

### Domain
A simplified banking backend focusing on **account operations and financial transactions**.

### Modules
- `accounts` – Account management
- `transactions` – Money movement
- `cards` – Debit and credit cards
- `fraud` – Fraud detection
- `notifications` – Customer notifications

### Architectural Highlights
- Event-driven transaction processing
- Fraud module reacts asynchronously to transactions
- Strong consistency boundaries per module

---

## 3. Subscription & Billing Platform

### Domain
A SaaS-style subscription system handling plans, billing cycles, and payments.

### Modules
- `plans` – Subscription plans
- `subscriptions` – Customer subscriptions
- `billing` – Invoice generation
- `payments` – Payment execution
- `notifications` – Billing notifications

### Architectural Highlights
- Time-based domain events
- Clear lifecycle ownership per module
- Designed for future integration with external payment providers

---

## 4. Order Management System (OMS)

### Domain
An enterprise-grade order orchestration system commonly used in logistics and retail.

### Modules
- `order-intake` – Order ingestion
- `inventory` – Stock management
- `fulfillment` – Order fulfillment
- `billing` – Financial settlement
- `reporting` – Operational insights

### Architectural Highlights
- Event choreography between modules
- Inventory updates triggered by order events
- Reporting consumes read-only projections

---

## 5. Identity & Access Management Platform

### Domain
A centralized identity platform responsible for authentication and authorization.

### Modules
- `users` – User management
- `roles` – Role definitions
- `permissions` – Authorization rules
- `sessions` – Login sessions
- `audit` – Security auditing

### Architectural Highlights
- Integration with Spring Security
- Clear separation between authentication and authorization
- Audit module reacts to security-related events

---

## Common Design Principles

All projects follow these core principles:

- **Explicit module boundaries**
- **No shared domain models**
- **Event-driven communication**
- **Module-local persistence**
- **Architecture verified by tests**

---

## Purpose of These Projects

These examples are intended to:
- Serve as learning references
- Demonstrate Spring Modulith best practices
- Act as a foundation for real-world systems
- Showcase architectural thinking beyond CRUD applications

---

## Next Steps

Suggested improvements and extensions:
- Extract selected modules into microservices
- Add distributed messaging (Kafka)
- Introduce CQRS read models
- Apply advanced security constraints

---

Each project can be developed incrementally and used independently as a portfolio showcase.
