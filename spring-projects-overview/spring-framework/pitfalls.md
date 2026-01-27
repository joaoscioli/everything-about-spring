# Spring Framework – Common Pitfalls and Anti-Patterns

This document highlights the most common pitfalls, architectural mistakes, and anti-patterns encountered when working with the Spring Framework.  
Understanding these issues is essential for building scalable, maintainable, and production-ready Spring applications.

This is **not** a beginner list. These pitfalls usually appear in **mid to large-scale systems**, legacy migrations, or poorly structured Spring Boot projects.

---

## 1. Treating Spring as “Magic”

### The Problem
One of the most common mistakes is treating Spring as a black box that "just works".

Developers often:
- Add annotations without understanding what they do
- Rely on defaults without knowing the consequences
- Debug by trial and error

### Why This Is Dangerous
- Makes debugging extremely hard
- Leads to unpredictable behavior
- Prevents proper architectural decisions

### Example
```java
@Autowired
private MyService service;
```

#### Many developers don’t understand:
- When injection happens
- How proxies are involved
- Whether the bean is singleton, prototype, or scoped

#### Recommendation
- Learn how Spring works internally
- Understand:
  - Bean lifecycle
  - ApplicationContext vs BeanFactory
  - Proxies (JDK vs CGLIB)
- Never assume “Spring will handle it”

---

## 2. Overusing @Autowired
### The Problem
Blind dependency injection everywhere.


### Common Symptoms
- Field injection everywhere
- Hidden dependencies
- Difficult unit testing

### Example (Anti-Pattern)
``` java
@Component
public class OrderService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private InventoryService inventoryService;
}
```

### Why This Is Bad
- Dependencies are not explicit
- Class becomes harder to reason about
- Constructor invariants are lost

### Better Approach
```java
@Component
public class OrderService {

    private final PaymentService paymentService;
    private final InventoryService inventoryService;

    public OrderService(PaymentService paymentService,
                        InventoryService inventoryService) {
        this.paymentService = paymentService;
        this.inventoryService = inventoryService;
    }
}
```

### Recommendation
- Prefer constructor injection
- Avoid field injection
- Make dependencies explicit

---

## 3. God Services and Anemic Architecture
### The Problem
Huge service classes containing:
- Business logic
- Validation
- Orchestration
- Database logic
- External API calls

### Symptoms
- Services with 1,000+ lines
- Methods with 20+ parameters
- Everything depends on everything

### Why This Happens
- Lack of domain modeling
- Misuse of “Service” as a dumping ground
- MVC misunderstood as architecture

### Recommendation
- Apply DDD concepts where appropriate
- Split responsibilities:
  - Domain services
  - Application services
  - Infrastructure services
- Keep services cohesive

---

## 4. Misusing Spring Boot Auto-Configuration
### The Problem
Relying too much on auto-configuration without understanding it.

### Common Issues
- Unexpected beans created
- Conflicting configurations
- Hard-to-trace startup failures

### Example
- Multiple **DataSource** beans created unintentionally:

``` yaml
spring.datasource.url=...
```
Plus a manual configuration:
```java
@Bean
public DataSource dataSource() { ... }
```
### Recommendation
- Understand how auto-configuration works
- Use:
  - **@ConditionalOnClass**
  - **@ConditionalOnMissingBean**
- Disable auto-config when necessary:
```java
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
```

---

## 5. Ignoring Bean Scopes and Lifecycle
### The Problem
Assuming everything is a singleton.

### Common Mistakes
- Using mutable state inside singleton beans
- Injecting request-scoped beans into singletons incorrectly

### Example
```java
@Component
public class UserContext {
    private String currentUser;
}
```
### Why This Breaks
- Spring beans are singleton by default
- Shared mutable state causes concurrency bugs

### Recommendation
- Avoid mutable state in singleton beans
- Understand scopes:
  - singleton
  - prototype
  - request
  - session
- Use scoped proxies when required

---

## 6. Transaction Mismanagement
### The Problem
Incorrect usage of **@Transactional**.

### Common Mistakes
- Putting **@Transactional** on private methods
- Calling transactional methods internally
- Mixing transactions with async execution

### Example (Broken)
```java
@Transactional
private void processOrder() { ... }
```
### Why It Fails
- Spring uses proxies
- Private methods are not proxied
- Self-invocation bypasses proxies

### Recommendation
- Place **@Transactional** on public methods
- Separate transactional boundaries into dedicated services
- Understand propagation and isolation levels

---

## 7. Catching Exceptions Too Early
### The Problem
Swallowing exceptions inside services.

### Example
```java
try {
    repository.save(entity);
} catch (Exception e) {
    log.error("Error", e);
}
```
### Why This Is Dangerous
- Breaks transaction rollback
- Hides real failures
- Creates inconsistent system state

### Recommendation
- Let exceptions propagate
- Handle them at the correct layer:
  - Controller (API boundary)
  - Global exception handlers (**@ControllerAdvice**)
- Use checked vs unchecked exceptions intentionally

---

## 8. Mixing Layers (No Clear Architecture)
### The Problem
Controllers talking directly to repositories.
Repositories calling external APIs.
Services returning entities directly.

### Symptoms
- Tight coupling
- No clear boundaries
- Hard refactoring

### Recommendation
Adopt a clear layered approach:
- Controller → Application Service → Domain → Infrastructure
- Enforce direction of dependencies
- Avoid leaking infrastructure details

---

## 9. Abusing @ComponentScan
### The Problem
Scanning the entire classpath without control.

### Example
```java
@ComponentScan("com")
```
### Why This Is Bad
- Unintended beans registered
- Performance issues
- Conflicts between modules

### Recommendation
- Use narrow and explicit component scans
- Prefer explicit configuration in large systems
- Modularize packages properly

---

## 10. Configuration Sprawl
### The Problem
Too many configuration styles mixed together:
- **@Value**
- **Environment**
- **@ConfigurationProperties**
- YAML + properties + profiles

### Recommendation
- Standardize configuration strategy
- Prefer **@ConfigurationProperties**
- Group related configs logically
- Document configuration clearly

---

## 11. Ignoring Observability and Actuator
### The Problem
Applications without visibility.

### Missing Pieces
- Health checks
- Metrics
- Tracing
- Proper logging

### Recommendation
- Use Spring Boot Actuator
- Expose meaningful health indicators
- Integrate metrics early (Micrometer)
- Never debug production blind

---

## 12. Treating Spring as the Architecture
### The Problem
Confusing framework with architecture.
Spring is a tool, not a system design.

### Recommendation
- Architecture comes first
- Spring should serve the architecture
- Avoid framework-driven design

---

## Final Thoughts
Most Spring problems are not caused by Spring itself, but by:
- Lack of architectural clarity
- Overreliance on annotations
- Poor understanding of fundamentals
### Mastering Spring means:
- Knowing when to use it
- Knowing when not to use a feature
- Designing systems that remain understandable years later
> *Spring rewards discipline — and punishes shortcuts.*

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
