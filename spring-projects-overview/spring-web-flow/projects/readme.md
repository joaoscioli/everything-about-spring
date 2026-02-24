# Projects — Spring Web Flow

This directory contains practical examples designed to demonstrate when and how Spring Web Flow provides architectural value.

These projects focus on:

- Multi-step interactions
- Deterministic navigation
- Conversational state management
- Explicit state-machine modeling

They are intentionally designed to highlight scenarios where traditional controller-based MVC becomes difficult to maintain.

---

## Project Philosophy

Spring Web Flow should not be used for simple CRUD applications.

It shines when:

- Interaction spans multiple requests
- Navigation rules are strict
- Conditional branching is complex
- Server-side conversational state is required

Each project increases in complexity and architectural depth.

---

## 01 - Multi-Step Registration Wizard

A structured user registration process divided into multiple validated steps.

### Demonstrates:

- View states
- Flow scope
- Validation gating
- Controlled back navigation
- Deterministic transitions

Best starting point for understanding flow mechanics.

---

## 02 - E-commerce Checkout Flow

A checkout process with branching logic and validation.

### Demonstrates:

- Decision states
- Action states
- Transactional boundaries
- Guard conditions
- Error transitions

Highlights deterministic navigation control.

---

## 03 - Loan Application System

A branching approval workflow with risk evaluation.

### Demonstrates:

- Conditional transitions
- Subflows
- Complex decision modeling
- Service orchestration integration

Shows how flows manage structured business processes.

---

## 04 - Insurance Claim Workflow

A long-running conversational process with document handling.

### Demonstrates:

- Conversation scope
- Flow state persistence
- Manual review branching
- Multi-step state accumulation

Represents real-world enterprise interaction modeling.

---

## 05 - Administrative Console with Subflows

A modular flow-based administrative interface.

### Demonstrates:

- Subflows
- Flow composition
- Reusable interaction modules
- Separation of navigation concerns

Shows architectural scalability of flow design.

---

## How to Approach These Projects

Start from the simplest project and focus on:

- Understanding the state machine model
- Observing flow execution lifecycle
- Analyzing scope usage
- Identifying deterministic transitions

The goal is not UI complexity.

The goal is understanding structured conversational modeling.

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*
