# Spring Web Services – Projects

## Overview

This directory contains practical projects demonstrating real-world usage of Spring Web Services (Spring-WS).

The goal of these projects is to:

- Demonstrate contract-first SOAP development
- Show enterprise integration patterns
- Explore WS-Security implementation
- Provide both server and client examples
- Simulate realistic enterprise scenarios

These projects are organized from basic to advanced complexity.

---

# Project List

## 1. basic-soap-service

A minimal SOAP service built using a contract-first approach.

Demonstrates:

- XSD schema definition
- WSDL generation
- @Endpoint mapping
- JAXB marshalling/unmarshalling
- Spring Boot integration

Recommended starting point.

---

## 2. banking-soap-service

Simulates a banking SOAP system.

Demonstrates:

- Account operations
- Schema validation
- Custom SOAP Fault handling
- Business logic separation
- Versioned XSD

Represents a typical enterprise SOAP API.

---

## 3. b2b-order-integration

Enterprise-style B2B order processing system.

Demonstrates:

- Spring Integration
- XML transformation
- Message interceptors
- Logging and auditing

Shows how SOAP fits into distributed enterprise workflows.

---

## 4. secure-soap-service

SOAP service secured with WS-Security.

Demonstrates:

- UsernameToken authentication
- Message signing
- Encryption
- Integration with Spring Security

Critical for financial and regulated environments.

---

## 5. soap-client-consumer

SOAP client consuming an external service.

Demonstrates:

- WebServiceTemplate usage
- Client-side marshalling
- Error handling
- Timeout configuration

Shows the consumer side of SOAP systems.

---

## 6. legacy-modernization

Simulates exposing a legacy system through Spring-WS.

Demonstrates:

- Adapter pattern
- XML-to-domain translation
- Integration with existing backend
- Enterprise modernization strategies

---

# Prerequisites

- Java 17+
- Maven or Gradle
- Basic understanding of XML and SOAP
- Familiarity with Spring Boot

---

# How to Run

Each project contains its own README with:

- Build instructions
- Startup commands
- Example SOAP requests
- Testing instructions (SoapUI)

---

# Learning Path

If you are new:

1. Start with basic-soap-service
2. Move to banking-soap-service
3. Explore soap-client-consumer
4. Study secure-soap-service
5. Finish with b2b-order-integration

---

# Purpose of These Projects

These examples are designed to:

- Teach SOAP fundamentals
- Demonstrate enterprise best practices
- Bridge theory and real implementation
- Prepare developers for enterprise environments

---

Part of **Spring Web Services Module**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
