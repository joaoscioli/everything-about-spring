# Spring REST Docs — Example Projects

This folder contains **example projects** demonstrating how to use **Spring REST Docs** to generate **accurate, test-driven API documentation**.  
Each project highlights specific features and best practices for documenting REST APIs in Spring.

---

## Projects Overview

### 1. Simple REST API Documentation
- **Description:** A basic CRUD API (e.g., `Books` or `Users`) with REST endpoints.
- **Objective:** Demonstrate **core Spring REST Docs features**.
- **Highlights:**
    - MockMvc or WebTestClient integration
    - Snippets for request/response fields and cURL examples
- **Folder:** `simple-rest-api-docs`

---

### 2. Secured REST API Documentation
- **Description:** REST API with **Spring Security** (JWT or Basic Auth).
- **Objective:** Document **protected endpoints** and authentication headers.
- **Highlights:**
    - Snippets for roles, headers, and status codes
    - Example of secured endpoint documentation
- **Folder:** `secured-rest-api-docs`

---

### 3. Pagination & Filtering Example
- **Description:** API with **paginated and filtered endpoints** (e.g., products or orders).
- **Objective:** Demonstrate documentation of **query parameters, pagination, and filtering**.
- **Highlights:**
    - Query parameter snippets
    - Response examples with lists and pagination metadata
- **Folder:** `pagination-filtering-docs`

---

### 4. Error Handling Documentation
- **Description:** API with **custom error handling**.
- **Objective:** Show how to **document error responses and HTTP status codes**.
- **Highlights:**
    - Snippets for error responses
    - Documentation of HTTP status codes and messages
- **Folder:** `error-handling-docs`

---

### 5. Advanced Microservice Integration
- **Description:** Two microservices (e.g., `Orders` and `Customers`) communicating via REST.
- **Objective:** Demonstrate documentation **across multiple services** for consistent API docs.
- **Highlights:**
    - Documentation for distributed endpoints
    - Integration with CI/CD pipelines for automated snippet generation
- **Folder:** `microservices-integration-docs`

---

## Notes
- Each project contains a **full test suite** used to generate REST Docs snippets.
- Snippets are combined using **templates** (AsciiDoc or Markdown) to produce **readable API documentation**.
- Projects can be used as **starting points** for your own REST Docs integration in real applications.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
