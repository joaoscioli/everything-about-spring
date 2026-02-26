# Architecture — Spring Web Services

Spring Web Services (Spring-WS) is architected around a **message-driven, contract-first model** for building SOAP-based systems.

Unlike REST-oriented frameworks, Spring-WS is designed around XML contracts and SOAP message processing rather than HTTP routing semantics.

This document explains the internal architecture, execution flow, and structural components of Spring-WS.

---

## 1. Architectural Philosophy

### Message-Oriented Architecture

Spring-WS routes requests based on **XML payload structure**, not HTTP paths or verbs.

Routing decisions are made using:

* XML namespace
* Root element name
* SOAP body structure

> Routing is based on XML structure, not URL mapping.

Endpoints are message handlers, not REST controllers.

---

### Contract-First Design

Spring-WS strongly promotes defining services using:

* XSD (XML Schema Definition)
* WSDL (Web Services Description Language)

The implementation conforms to the contract — not the other way around.

Architectural implications:

* Strict contract governance
* Decoupling between schema and Java classes
* Controlled evolution of service APIs
* Strong interoperability guarantees

---

### Transport Abstraction

Spring-WS is transport-agnostic.

Although commonly used over HTTP, it can operate over:

* HTTP
* JMS
* Email
* Other messaging transports

The SOAP message model remains independent from the underlying transport.

---

## 2. High-Level Request Flow (Server-Side)

A typical SOAP request follows this execution flow:
````text
Client  
↓  
Transport (HTTP/JMS)  
↓  
MessageDispatcherServlet  
↓  
EndpointMapping  
↓  
EndpointAdapter  
↓  
Endpoint (@Endpoint)  
↓  
Marshaller / Unmarshaller  
↓  
SOAP Response
````
---

## 3. Core Server Components

### MessageDispatcherServlet

The central entry point for SOAP requests.

Responsibilities:

* Receives incoming SOAP messages
* Delegates to endpoint mappings
* Coordinates adapters and interceptors
* Manages fault handling

It is conceptually similar to Spring MVC's DispatcherServlet, but operates on SOAP messages instead of HTTP routes.

---

### EndpointMapping

Determines which endpoint handles the request.

Mapping is based on:

* XML namespace
* Root element (localPart)
* Payload structure

URL patterns are not used for routing.

---

### EndpointAdapter

Responsible for:

* Invoking the appropriate endpoint method
* Resolving method arguments
* Applying marshalling/unmarshalling
* Handling return values

It bridges the SOAP infrastructure and annotated endpoint methods.

---

### Endpoint

Endpoints are annotated with:
````text
@Endpoint
````
Methods typically use:
````text
@PayloadRoot
@RequestPayload
@ResponsePayload
````


An endpoint is a **message handler**, not a controller.

It operates strictly on SOAP payloads.

---

### Marshalling Layer

Spring-WS separates XML handling from business logic.

Marshalling converts:

Java Object → XML

Unmarshalling converts:

XML → Java Object

Commonly implemented using JAXB or other OXM frameworks.

This layer ensures:

* Schema compliance
* Clear separation of XML processing
* Clean business logic methods

---

## 4. SOAP Message Structure

Spring-WS processes full SOAP envelopes:

SOAP Envelope
* Header
* Body

### Body

Contains the main XML payload.
Most endpoint logic operates on the body.

### Header

Contains metadata such as:

* Security tokens
* Digital signatures
* Transaction information

Headers are commonly processed through interceptors.

---

## 5. Interceptor Chain

Spring-WS provides interceptor support for message-level processing.

Interceptors can perform:

* Pre-processing
* Post-processing
* Fault handling

Common use cases:

* Logging
* Authentication
* Schema validation
* Security enforcement
* Metrics collection

Interceptors operate before and after endpoint invocation.

---

## 6. Validation Architecture

Spring-WS supports XSD-based validation.

Validation typically occurs:

* Before endpoint invocation
* Through configured interceptors

Invalid messages result in:

* SOAP Fault responses

This enforces strict adherence to service contracts.

---

## 7. WS-Security Layer

Spring-WS supports message-level security.

Capabilities include:

* Digital signatures
* Encryption
* Security header validation
* Token-based authentication

Security is applied through:

* Interceptors
* Security configuration layers

Unlike HTTPS (transport-level security), WS-Security operates at the message level.

---

## 8. Client-Side Architecture

Spring-WS also provides client capabilities through `WebServiceTemplate`.

Client flow:

Client Code  
↓  
WebServiceTemplate  
↓  
Marshaller  
↓  
Transport  
↓  
Remote Service  
↓  
Unmarshaller

---

### WebServiceTemplate Responsibilities

* Sending SOAP requests
* Marshalling request objects
* Receiving and unmarshalling responses
* Handling SOAP Faults
* Supporting client-side interceptors

This abstracts low-level SOAP message handling from application code.

---

## 9. Comparison with Spring MVC Architecture

| Spring MVC | Spring Web Services |
|------------|--------------------|
| URL-based routing | XML payload-based routing |
| HTTP verbs (GET, POST) | SOAP operations |
| JSON commonly used | XML mandatory |
| @Controller | @Endpoint |
| REST semantics | Contract-driven SOAP semantics |

Spring-WS is not a REST alternative — it is a SOAP infrastructure framework.

---

## 10. Threading & Runtime Model

When used over HTTP:

* Runs inside a servlet container
* Follows thread-per-request model
* Endpoints should remain stateless
* Compatible with embedded servers via Spring Boot

The runtime model aligns with traditional servlet-based applications.

---

## 11. Architectural Strengths

* Strong contract governance
* Strict schema validation
* Clear separation between transport, XML, and business logic
* Enterprise interoperability
* Mature SOAP support

---

## 12. Architectural Constraints

* Verbose XML processing
* Tight coupling to XSD contracts
* Higher debugging complexity
* Performance overhead compared to JSON-based systems
* Requires deep understanding of SOAP standards

---

## Summary

Spring Web Services is architected for:

* Stability
* Contract precision
* Enterprise interoperability
* Message-level processing

It is not optimized for lightweight APIs or rapid iteration.

Instead, it provides a **structured, standards-driven architecture** for systems where formal XML contracts and SOAP messaging are mandatory.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
