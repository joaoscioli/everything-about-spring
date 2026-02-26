# Spring Framework Pitfalls

This document lists common mistakes, hidden traps, and bad practices
when working with the Spring Framework (especially Spring Boot).

The goal is to:

-   Help developers avoid common errors
-   Improve code quality and maintainability
-   Serve as a checklist during code reviews
-   Clarify misunderstood framework behaviors

---

# 1. Dependency Injection Pitfalls

## Field Injection

### Problem

Using `@Autowired` directly on fields:

``` java
@Autowired
private UserService userService;
```

### Why It's a Problem

-   Makes unit testing harder
-   Prevents immutability
-   Hides dependencies
-   Violates clean architecture principles

### Recommended Approach

Use constructor injection:

``` java
private final UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}
```

---

## Circular Dependencies

### Problem

Common error:

BeanCurrentlyInCreationException

### Why It Happens

Two classes depend on each other, creating a dependency cycle the
container cannot resolve.

### How to Avoid

-   Refactor responsibilities
-   Introduce a mediator class
-   Use events
-   Rethink the design

---

## Instantiating Beans with `new`

### Problem

Manually creating objects that should be managed by Spring.

### Why It's a Problem

-   Dependencies won't be injected
-   AOP won't work
-   `@Transactional` won't work
-   Lifecycle won't be managed

---

# 2. Transaction Management Pitfalls

## `@Transactional` Not Working

Common causes:

-   Method is private
-   Internal method calls
-   Class not managed by Spring

Spring uses proxy-based AOP. Internal calls do not pass through the
proxy.

---

## No Rollback on Exception

Checked exceptions do not trigger rollback by default.

Use:

``` java
@Transactional(rollbackFor = Exception.class)
```

---

# 3. REST & Web Pitfalls

## Forgetting `@RestController`

Using `@Controller` expecting JSON responses will cause Spring to
resolve a view instead.

---

## Not Validating Input

Use:

``` java
@Valid
```

With annotations like `@NotNull`, `@Email`, and `@Size`.

---

# 4. Performance Pitfalls

## N+1 Query Problem

Lazy loading can trigger unexpected multiple queries.

Use fetch joins or entity graphs to optimize queries.

---

## Mutable State in Singleton Beans

Singleton beans storing mutable state may cause concurrency issues.

Prefer stateless design.

---

# 5. Configuration Pitfalls

## Overusing `@Value`

Prefer:

``` java
@ConfigurationProperties
```

For structured and type-safe configuration.

---

# 6. Security Pitfalls

## Exposing Actuator Endpoints

Restrict or secure sensitive endpoints in production.

---
## Overly Permissive Security Rules

Avoid:

``` java
.anyRequest().permitAll()
```

---

# 7. Testing Pitfalls

## Using `@SpringBootTest` Everywhere

Prefer test slices:

-   `@WebMvcTest`
-   `@DataJpaTest`

---

# Final Checklist

-   Use constructor injection
-   Validate inputs
-   Secure endpoints
-   Avoid N+1 queries
-   Keep services cohesive
-   Write isolated tests

---

# Conclusion

Most Spring issues are not framework bugs ---\
they are misunderstandings of dependency injection, AOP proxies, bean
lifecycle, and configuration management.

Understanding these pitfalls is essential for building scalable, secure,
and maintainable applications.
