# Spring Framework Architecture

The Spring Framework is a **modular, container-centric framework** designed around clear architectural boundaries and well-defined extension points.

Its power comes not from hidden behavior, but from a **deterministic startup and composition model**.

---

## High-Level Architecture Overview

At a high level, the Spring Framework is organized around a **core container** that manages application components and their relationships.

All higher-level capabilities — such as web frameworks, data access, transactions, and security — are built **on top of this container**.

The central architectural element is the **ApplicationContext**, which coordinates:

- Bean creation and lifecycle
- Dependency resolution
- Configuration processing
- Extension mechanisms
- Cross-cutting behavior

> **Everything in Spring starts and ends in the container.**

---

## Core Container Modules

The core of the Spring Framework is split into focused modules, each with a distinct responsibility.

### spring-core
Provides low-level utilities and foundational infrastructure, including:
- Resource abstraction
- Type conversion
- Core utilities

### spring-beans
Implements the bean model, including:
- Bean definitions
- Dependency resolution
- Bean factories

### spring-context
Builds on `spring-beans` and provides:
- ApplicationContext implementations
- Event publishing
- Internationalization support
- Integration with lifecycle callbacks

### spring-expression
Supplies the Spring Expression Language (SpEL), used for:
- Configuration expressions
- Conditional wiring
- Dynamic value resolution

> **Spring is modular by design, not by packaging accident.**

---

## Application Startup Flow

Spring application startup follows a **well-defined pipeline**.

At a conceptual level, the process includes:

1. Creation of the ApplicationContext
2. Environment and property source resolution
3. Loading of bean definitions
4. Preparation of the BeanFactory
5. Registration of post-processors
6. Bean instantiation and dependency injection
7. Lifecycle callbacks and context refresh completion

Each step builds on the previous one, ensuring a predictable initialization sequence.

> **Startup is a pipeline, not a black box.**

---

## Bean Definitions vs Bean Instances

A key architectural distinction in Spring is between **bean definitions** and **bean instances**.

- A **BeanDefinition** describes how a bean should be created
- A **bean instance** is the actual object created at runtime

Spring processes and modifies bean definitions **before any objects are instantiated**.

This allows:
- Conditional configuration
- Proxy preparation
- Definition-level customization

> **Spring manages definitions first, objects later.**

---

## Dependency Resolution and Wiring

Dependency resolution in Spring is **rule-based**, not annotation-driven.

The container determines dependencies using:
- Type matching
- Qualifiers and names
- Primary candidates
- Scope compatibility

Resolution often occurs **lazily**, allowing the container to handle complex dependency graphs.

Circular dependencies are partially supported, but are constrained by:
- Injection style
- Lifecycle phase
- Proxy boundaries

> **Dependency resolution follows rules — annotations only express intent.**

---

## BeanPostProcessors and Extension Points

Spring’s extensibility is primarily enabled through **post-processing hooks**.

`BeanPostProcessor` implementations can:
- Inspect beans before initialization
- Modify or replace beans
- Wrap beans with proxies

Most core Spring features rely on this mechanism internally, including:
- Dependency injection
- Transaction management
- Configuration class processing

This design allows Spring to behave as a **platform**, not just a framework.

> **Spring is extensible because it was designed to be extended.**

---

## Proxying and AOP Architecture

Spring applies cross-cutting behavior using **proxy-based AOP**.

At runtime, beans may be:
- Wrapped with proxies
- Intercepted at method boundaries
- Enhanced with additional behavior

Spring supports:
- Interface-based proxies
- Class-based proxies

Because proxies sit **between the caller and the target**, they introduce structural constraints, such as:
- Self-invocation limitations
- Method visibility requirements

> **Behavior is added by wrapping, not inheritance.**

---

## Context Hierarchy and Boundaries

Spring supports **hierarchical ApplicationContexts**, enabling architectural separation.

Common use cases include:
- Parent contexts for shared infrastructure
- Child contexts for isolated components
- Modular system composition

Contexts define:
- Visibility boundaries
- Bean lookup rules
- Lifecycle isolation

> **Contexts define architectural boundaries.**

---

## Threading and Execution Model

The Spring container does **not** manage business execution threads.

Its responsibilities are limited to:
- Object lifecycle management
- Dependency wiring
- Infrastructure interception

Thread management is typically handled by:
- The JVM
- Application servers
- Explicit executors configured by the application

> **Spring manages objects, not execution.**

---

## Final Thoughts

Understanding Spring’s architecture removes the illusion of magic.

The framework is powerful not because it hides complexity,  
but because it **structures complexity through explicit phases and extension points**.

> **Spring is predictable once its architecture is understood.**

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
