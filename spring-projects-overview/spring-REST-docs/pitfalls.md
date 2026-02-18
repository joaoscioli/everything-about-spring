# Spring REST Docs — Pitfalls

Spring REST Docs is a **powerful tool for test-driven API documentation**, but there are **common pitfalls and limitations** that developers should be aware of.

Understanding these pitfalls helps teams **avoid errors and maintain accurate documentation**.

---

## Common Pitfalls

### 1. Dependence on Tests
- REST Docs **cannot generate documentation without automated tests**.
- If tests fail or are incomplete, **snippets will not be generated correctly**.

### 2. Untested Endpoints
- Any endpoint **not covered by tests** will remain **undocumented**.
- Ensure **minimum test coverage for all public endpoints**.

### 3. Outdated Snippets
- Changes in the API without updating tests can lead to **stale or incorrect documentation**.
- Documentation is only accurate if **tests are maintained and up-to-date**.

### 4. Configuration Overhead
- Initial setup can seem **complex**, especially in large projects or microservices.
- Plan **snippet and template organization** from the start to avoid maintenance headaches.

### 5. Static Documentation
- REST Docs generates **textual, example-driven documentation**, not interactive UIs.
- For **interactive API documentation**, consider combining with **Swagger/OpenAPI**.

### 6. Misconfigured CI/CD Integration
- Improper integration in CI/CD pipelines may cause:
    - Snippets **not being processed**
    - **Broken builds**
- Always verify that the documentation generation step is properly configured.

---

## Performance Considerations
- Integration tests generating documentation may **increase test execution time**, especially with many endpoints.
- Recommendation: **run documentation generation separately** or in a **parallel pipeline** to minimize impact on build time.

---

## Summary / Recommendations
- Ensure **comprehensive, up-to-date tests** before generating documentation.
- Plan **snippet and template structure** early.
- Combine with **Swagger/OpenAPI** if interactive documentation is required.
- Integrate documentation generation into **CI/CD pipelines** for consistency.

> By understanding these pitfalls, teams can maintain **reliable, accurate, and maintainable REST API documentation** with Spring REST Docs.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*