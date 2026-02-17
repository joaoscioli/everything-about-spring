# Pitfalls

This document describes common pitfalls, anti-patterns, and
architectural mistakes when working with the Spring ecosystem.

Even though Spring greatly simplifies enterprise development,
misunderstandings about proxies, transactions, persistence, and
configuration can lead to subtle and hard-to-debug issues.

---

# 1. Dependency Injection Pitfalls

## 1.1 Field Injection

## NO - Avoid:

``` java
@Autowired
private UserService userService;
```

### Why this is a problem

-   Harder to test
-   Breaks immutability
-   Makes dependencies less explicit
-   Cannot enforce required dependencies via constructor

## OK - Prefer Constructor Injection

``` java
@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
}
```

Benefits:

-   Clear dependencies
-   Immutable design
-   Better testability
-   Encourages clean architecture

---

# 2. Misusing Stereotype Annotations

All stereotype annotations are meta-annotated with `@Component`, but
they have semantic meaning.

Annotation      Intended Use
  --------------- -------------------
`@Component`    Generic component
`@Service`      Business logic
`@Repository`   Data access layer
`@Controller`   Web layer

Using them incorrectly reduces readability and architectural clarity.

---

# 3. Transaction Management Pitfalls

Spring uses proxies to implement `@Transactional`, which leads to
several common mistakes.

## 3.1 Internal Method Calls Do Not Trigger Transactions

``` java
public void methodA() {
    methodB(); // transactional proxy is bypassed
}

@Transactional
public void methodB() {
}
```

Because Spring uses proxies, internal calls bypass transactional
interception.

### Solution

-   Move the transactional method to another bean
-   Call through the proxied bean

---

## 3.2 Missing @Transactional

Write operations without `@Transactional` may:

-   Not flush changes
-   Not rollback properly
-   Cause inconsistent data state

---

# 4. Spring Data JPA Pitfalls

## 4.1 N+1 Query Problem

Loading parent entities and lazily accessing children inside a loop can
trigger multiple queries.

### Solutions

-   `JOIN FETCH`
-   `@EntityGraph`
-   DTO projections
-   Query optimization

---

# 5. Controller Layer Anti-Patterns

Controllers should:

-   Validate input
-   Map DTOs
-   Delegate to services
-   Return responses

Correct layering:

Controller → Service → Repository

---

# 6. Proxy-Related Pitfalls

Spring proxies only intercept public methods invoked through the Spring
context.

Common mistakes:

-   Marking methods as `private`
-   Marking methods as `final`
-   Calling methods internally

---

# 7. Overusing @SpringBootTest

Using `@SpringBootTest` for every test:

-   Starts the entire application context
-   Makes tests slow
-   Reduces feedback loop speed

Prefer:

-   `@WebMvcTest`
-   `@DataJpaTest`
-   `@MockBean`
-   Pure unit tests

---

# 8. Configuration Anti-Patterns

Avoid:

-   Hard-coded credentials
-   Environment-specific values in source code

Use environment-specific configuration files or environment variables.

---

# 9. Circular Dependencies

Circular dependencies cause `BeanCurrentlyInCreationException`.

Solutions:

-   Refactor responsibilities
-   Introduce abstraction
-   Rethink architecture

---

# 10. Not Understanding Bean Lifecycle

Understand:

-   `@PostConstruct`
-   `@PreDestroy`
-   Bean scopes

---

# Conclusion

Most Spring issues are not caused by the framework itself, but by
misunderstanding its core mechanisms:

-   IoC container behavior
-   Proxy-based AOP
-   Transaction boundaries
-   Persistence context lifecycle
-   Auto-configuration design

Understanding these fundamentals is essential to avoid subtle bugs and
scalability issues.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*