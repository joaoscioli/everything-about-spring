# Spring REST Docs — Architecture

Spring REST Docs is a **test-driven API documentation tool** that integrates with Spring MVC, WebFlux, and testing frameworks to produce accurate, maintainable documentation.

Its architecture is designed to **generate documentation directly from tests**, ensuring that the API docs always reflect the real behavior of the system.

---

## Overview

The core idea of Spring REST Docs is simple:

1. **Execute tests** against your REST endpoints
2. **Capture request and response details** during test execution
3. **Generate reusable snippets** in AsciiDoc or Markdown
4. **Assemble snippets** into human-readable documentation

> **Flow:** Test Execution → Snippet Generation → Snippet Storage → Documentation Assembly → Output

This approach eliminates the mismatch between **API behavior and documentation**, which is common when documentation is manually maintained.

---

## Core Components

### 1. Test Runner / Framework Integration
- Supports **JUnit, Spock, or other compatible test frameworks**
- Executes integration or unit tests for REST endpoints
- Intercepts requests and responses for snippet generation

### 2. Snippet Generator
- Converts captured test data into **documentation snippets**
- Common snippets include:
    - Request fields
    - Response fields
    - Path parameters
    - Query parameters
    - Headers
    - cURL examples
- Snippets are **small, reusable files**, usually in `.adoc` or `.md`

### 3. Template / Assembler
- Combines snippets into **complete API documentation**
- Uses **AsciiDoc or Markdown templates**
- Supports structured output such as:
    - API reference guides
    - Example requests and responses
    - Detailed field descriptions

### 4. Output / Build Integration
- Works with **Maven or Gradle plugins** to process snippets
- Integrates seamlessly with **CI/CD pipelines**
- Automatically generates documentation artifacts during build
- Can coexist with:
    - Spring Boot Test
    - Spring MVC / WebFlux
    - Spring Security (for secured endpoints)

---

## Conceptual Flow

```text
Test Execution
      │
      ▼
Snippet Generation
      │
      ▼
Snippet Storage
      │
      ▼
Documentation Assembly
      │
      ▼
Final Output (AsciiDoc/Markdown)
```
This flow ensures documentation accuracy and automated generation throughout the development lifecycle.
 
---

## Integration Points
- Spring Boot / Spring MVC / WebFlux → Defines the REST endpoints to be documented
- Testing frameworks → Drive test execution and snippet generation
- Asciidoctor / Markdown processors → Assemble snippets into complete documentation
- CI/CD pipelines → Automate documentation generation and publishing

---

## Architectural Principles

#### 1 Test-Driven Documentation
Documentation is derived from actual API behavior, not manual description.

#### 2 Separation of Concerns
Snippet generation is decoupled from template rendering, allowing flexible assembly.

#### 3 Reusability
Snippets are modular and can be reused across multiple documentation pages or projects.

#### 4 Automation
Designed to integrate with build pipelines for continuous, up-to-date documentation.

---
## Extensibility & Customization

**Custom Snippets:** You can create custom snippets for specific headers, error responses, or domain-specific fields.

**Template Customization:** Modify AsciiDoc or Markdown templates to control layout, styling, and organization.

**Integration Hooks:** Extend or override snippet generation with your own handlers if you need specialized documentation.

---

## Handling Test Failures

- If a test fails, the corresponding snippet will not be generated.
- CI/CD pipelines should report failed documentation generation to prevent publishing incomplete docs.
- Ensure comprehensive tests for critical endpoints to maintain reliable documentation.

---

Spring REST Docs ensures that your API documentation is accurate, maintainable, and fully integrated into the development workflow, making it a cornerstone for teams focused on reliable REST APIs.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
