# Spring Cloud Data Flow Projects

This folder contains example projects and mini-pipelines to demonstrate the core capabilities of **Spring Cloud Data Flow (SCDF)**.

Each project illustrates different aspects of SCDF, including:

* Stream orchestration
* Task orchestration
* Composed tasks
* Event-driven processing
* Observability
* Multi-platform deployment

These projects are designed to be **lightweight, educational, and production-aligned**, serving as references for both learning and experimentation.

---

## 01 - Stream of Event Ingestion and Processing

**Description:**  
A simple streaming pipeline that ingests events, processes them, and sends them to a sink.

**Applications:**

* **Source:** Random event generator or HTTP ingestion
* **Processor:** Filters or transforms events (e.g., converts text to uppercase, enriches data)
* **Sink:** Persists events to a database, file, or log

**Objective:**  
Demonstrate **Source → Processor → Sink** composition and continuous stream execution.

---

## 02 - Batch Task with Composed Workflow

**Description:**  
A single-run workflow composed of multiple tasks executed sequentially.

**Applications:**

* **Task 1:** Reads CSV files or fetches data from an external API
* **Task 2:** Transforms or enriches the data
* **Task 3:** Persists results or sends notifications

**Objective:**  
Show **task orchestration, sequential execution, and execution tracking**.

---

## 03 - Stream with Kafka Partitioning and Horizontal Scaling

**Description:**  
A streaming pipeline demonstrating Kafka partitioning with multiple processor instances.

**Applications:**

* **Source:** Continuous event producer (e.g., logs, sensor data)
* **Processor:** Consumes and aggregates events by key
* **Sink:** Stores processed results in a database or Elasticsearch

**Objective:**  
Illustrate **parallelism, partitioning, backpressure handling, and scaling**.

---

## 04 - Stream + Task Hybrid Pipeline

**Description:**  
A hybrid project combining long-running streams with on-demand tasks triggered by specific events.

**Applications:**

* **Stream:** Continuous event ingestion
* **Task:** Runs only when a specific event or threshold is detected

**Objective:**  
Demonstrate **integration between streams and tasks** and automated workflow triggering.

---

## 05 - Observability and Monitoring Pipeline

**Description:**  
A simple streaming pipeline instrumented for observability.

**Applications:**

* Stream apps integrated with **Spring Boot Actuator + Micrometer**
* Metrics collected via Prometheus
* Optional Grafana dashboard for visualization

**Objective:**  
Show **real-time monitoring and operational visibility** for SCDF pipelines.

---

## 06 - Demo IoT Pipeline

**Description:**  
Simulates data collection from IoT devices, demonstrating practical stream processing.

**Applications:**

* **Source:** Temperature/humidity sensor simulator
* **Processor:** Computes averages or detects anomalies
* **Sink:** Sends alerts to logs or email

**Objective:**  
Provide a **hands-on, real-world scenario** to practice SCDF streams, processors, and sinks.

---

## Notes

* Each project is intended to be **self-contained** and executable with minimal configuration.
* Projects can be deployed on **local, Kubernetes, or Cloud Foundry environments**.
* These examples illustrate **best practices for stream and task design**, pipeline composition, and observability.

---

Part of **Spring Projects Overview**  
within **Everything About Spring Framework**  
by *The Coding Cabin*
