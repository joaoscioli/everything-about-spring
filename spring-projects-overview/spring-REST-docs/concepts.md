# Spring REST Docs — Concepts

Spring REST Docs is **not a framework for building APIs**. Its purpose is to **document existing RESTful APIs** using a **test-driven approach**.

The core principle is simple: **documentation is generated from tests**, ensuring that the docs always reflect the real behavior of the API.

---

## Key Concepts

### 1. Test-Driven Documentation
- Documentation is **generated directly from integration or unit tests**.
- Guarantees that examples in the documentation **match the actual API behavior**.
- Prevents the common problem of **out-of-date manual documentation**.

### 2. Snippets
- Small, reusable blocks of documentation generated automatically from tests.
- Common snippet types include:
    - **Request Fields** – fields sent in the HTTP request
    - **Response Fields** – fields returned in the HTTP response
    - **Path Parameters** – variables in the endpoint URL
    - **Query Parameters** – parameters in the query string
    - **Headers** – request or response headers
    - **cURL Examples** – automatically generated CLI examples
- Snippets are **modular** and can be **reused across multiple endpoints or documents**.

### 3. Templates / Documentation Assembly
- Snippets alone are not a complete document.
- Templates (AsciiDoc or Markdown) **combine snippets into readable API guides or reference documentation**.
- Templates control **layout, formatting, and structure** of the final documentation.

### 4. Output Formats
- Supported formats:
    - **AsciiDoc** – for professional, styled documentation
    - **Markdown** – for simpler or GitHub-friendly docs
- Can be processed with tools like **Asciidoctor** to generate HTML, PDF, or other formats.

### 5. Integration with Testing Frameworks
- Compatible with **JUnit, Spock**, or other supported frameworks.
- Works with **Spring MVC Test** or **WebTestClient** to capture request and response data during tests.

### 6. CI/CD Integration
- Snippets and documentation can be **generated automatically in build pipelines**.
- Ensures that documentation is always **up-to-date** with code changes.
- Supports integration with **Maven** and **Gradle** build tools.

### 7. Extensibility / Custom Snippets
- Developers can create **custom snippets** for headers, error responses, or domain-specific fields.
- Templates can be customized to **control layout, styling, and organization**.
- Allows **tailored documentation** for unique API requirements.

---

## Important Terms

| Term | Description |
|------|-------------|
| **Snippet** | A small file documenting a specific aspect of an endpoint. |
| **Template** | Combines multiple snippets into complete documentation. |
| **Request Fields** | Fields sent in the HTTP request. |
| **Response Fields** | Fields returned in the HTTP response. |
| **Path Parameter** | Variable embedded in the endpoint URL. |
| **Query Parameter** | Parameter passed in the query string of the URL. |
| **cURL Example** | Example of calling the endpoint via terminal, generated automatically. |

---

## Example Snippet

```text
Request Fields:
  name: string
  age: integer

Response Fields:
  id: long
  name: string
  age: integer
```
This snippet can be combined with a template to produce a full endpoint documentation page.

---

## Summary

- Spring REST Docs ensures that your documentation is accurate, maintainable, and test-driven.
- Key concepts to understand before implementation:
  - Snippets – modular documentation blocks
  - Templates – assemble snippets into readable guides
  - Test-Driven Documentation – source of truth comes from real tests
  - Output Formats – AsciiDoc or Markdown
- Mastering these concepts allows teams to produce reliable API documentation with minimal manual effort.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
