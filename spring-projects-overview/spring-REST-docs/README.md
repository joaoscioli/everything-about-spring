# Spring REST Docs

Spring REST Docs is a **documentation tool for RESTful APIs** that combines **unit tests with AsciiDoc or Markdown snippets** to generate accurate, up-to-date API documentation.

It integrates seamlessly with Spring MVC and WebTestClient, ensuring that your documentation always reflects the **real behavior of your API**.

---

## Why Spring REST Docs Exists

Before Spring REST Docs:

* API documentation often became **out of sync** with the code.
* Maintaining manuals and examples was **manual and error-prone**.
* Teams struggled to **automate API documentation** in CI/CD pipelines.

Spring REST Docs was created to **generate accurate, test-driven API documentation** automatically.

---

## Core Idea

Spring REST Docs does **not define a new API or framework**. Instead, it:

* Uses your existing tests to generate documentation
* Creates snippets in AsciiDoc or Markdown
* Lets you combine snippets into human-readable API docs

> **Your documentation reflects reality because it comes from real tests.**

---

## Position in the Spring Ecosystem

Spring REST Docs complements:

* Spring Boot (for test setup and REST endpoints)
* Spring MVC or WebFlux
* Testing frameworks (JUnit, Spock)
* Asciidoctor or Markdown tools

It is **agnostic of front-end clients** but ensures consistent API documentation for teams.

---

## Key Capabilities

### Test-Driven Documentation

* Generate documentation from actual unit or integration tests
* Ensure that docs are always up-to-date with API changes

### Flexible Output

* Produces snippets in **AsciiDoc** or **Markdown**
* Easily integrates with build tools (Maven, Gradle)

### Snippet Customization

* Document request/response fields
* Document path parameters, query parameters, headers
* Include cURL examples automatically

### Integration with Build Tools

* Generate a complete API guide during CI/CD
* Combine snippets into one cohesive document

---

## Typical Use Cases

Spring REST Docs is ideal for:

* REST APIs
* Microservices exposing HTTP endpoints
* Teams that want **accurate API documentation** integrated into CI/CD
* Public APIs where up-to-date documentation is critical

---

## Trade-offs

Spring REST Docs may not be ideal when:

* You want **fully automated live API documentation** (Swagger/OpenAPI may be easier)
* The project does not already have automated tests
* Documentation style does not fit **snippet-based generation**

---

## Documentation

### Reference Documentation

* [https://docs.spring.io/spring-restdocs/docs/current/reference/html5/](https://docs.spring.io/spring-restdocs/docs/current/reference/html5/)

Covers:

* Test-driven documentation approach
* Snippet configuration
* Integration with Asciidoctor

### API Documentation

* [https://docs.spring.io/spring-restdocs/api/](https://docs.spring.io/spring-restdocs/api/)

Useful for:

* Advanced customization
* Extending snippets and templates

---

## Relationship with Other Spring Projects

* **Spring MVC / WebFlux** → Underlying REST endpoints
* **Spring Boot Test** → Test context and setup
* **Spring Security** → Securing documented endpoints
* **Spring Auto REST Docs** → Optional automation layer

---

## Summary

Spring REST Docs enables **accurate, maintainable API documentation** by tying it directly to your tests.

It works best when:

* Tests are comprehensive
* CI/CD builds generate documentation automatically
* Documentation style is snippet-driven

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
