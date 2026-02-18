# Spring REST Docs — When to Use

Spring REST Docs is a **test-driven API documentation tool**.  
It is designed to **generate documentation directly from your tests**, ensuring that your API docs **always reflect the real behavior** of your REST endpoints.

It is **not a framework to build APIs** or to generate interactive documentation.

---

## Ideal Use Cases

Spring REST Docs is especially suitable when:

### 1. Well-Defined REST APIs
- Clear and stable endpoints
- Teams need **accurate and detailed documentation** for developers or external clients

### 2. Automated Test Coverage
- Integration or unit tests are already implemented
- Documentation is generated **directly from tests**, making a solid test suite essential

### 3. Documentation Must Stay in Sync With Code
- Changes in the API are automatically reflected in the docs
- Ideal for **microservices**, **public APIs**, or **SaaS products**

### 4. Continuous Integration / Continuous Delivery (CI/CD)
- Snippets and documentation can be **automatically generated during builds**
- Reduces manual effort and ensures docs are **always up-to-date**

### 5. Accuracy Over Interactivity
- Produces **textual, example-driven documentation**
- Best when **precision is more important than interactive UIs** like Swagger

---

## When Not to Use

Avoid Spring REST Docs in these scenarios:

### 1. Projects Without Automated Tests
- The tool relies on tests to generate snippets
- Without tests, **documentation cannot be created**

### 2. Need for Interactive API Documentation
- If your team requires **Swagger/OpenAPI UI** or interactive testing
- REST Docs focuses on **static, test-driven documentation**

### 3. Rapid Prototyping or MVPs
- Initial setup may be **time-consuming compared to Swagger/OpenAPI**
- For prototypes, simplicity and speed are more important than accuracy

### 4. Frequently Changing APIs Without Tests
- Inconsistent or missing tests will lead to **incomplete or outdated documentation**

---

## Summary / Recommendations

**Use Spring REST Docs when:**
- APIs are **well-tested and stable**
- Documentation **accuracy and maintainability** is a priority
- CI/CD pipelines can **generate docs automatically**

**Avoid Spring REST Docs when:**
- The project **lacks automated tests**
- **Interactive API documentation** is required
- Rapid prototyping or exploration is the goal

> Spring REST Docs ensures **reliable, maintainable, and test-driven API documentation** when applied in the right context.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
