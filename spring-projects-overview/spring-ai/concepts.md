# Core Concepts

Spring AI introduces abstractions that allow AI capabilities to be integrated into Spring applications in a structured way.

Understanding these core concepts is essential to designing reliable, maintainable, and production-ready AI systems.

AI is not a traditional deterministic system.
It behaves probabilistically, consumes tokens, incurs cost, and introduces architectural trade-offs.

This document explains the foundational concepts behind Spring AI.

---

## Model Abstraction

Spring AI interacts with AI systems through model abstractions.

A model represents a capability, not a vendor.

Examples of model types:

* ChatModel
* EmbeddingModel
* ImageModel
* Completion-style models (where supported)

Important distinction:

Model ≠ Provider

A provider (OpenAI, Azure OpenAI, etc.) implements the model capability.
Your application depends on the abstraction, not the provider.

This enables:

* Vendor replacement
* Cleaner architecture
* Testability
* Reduced coupling

---

## Prompt

A prompt is the structured input sent to a model.

It may include:

* System instructions
* User messages
* Context data
* Retrieved documents (in RAG systems)

Prompts are not simple strings.
They are structured instructions that strongly influence output quality.

Architectural considerations:

* Prompts should be versioned
* Prompts should not be hardcoded in controllers
* Prompt logic belongs in the application service layer
* Prompt size affects token usage and cost

Prompt engineering is part of system design, not UI logic.

---

## Message Types

Spring AI models conversations as structured messages.

Common message roles:

* SystemMessage → Defines behavior and constraints
* UserMessage → Represents user input
* AssistantMessage → Represents model responses

A conversation is a sequence of messages.

Instead of concatenating strings, Spring AI models conversation as structured data.

This improves:

* Clarity
* Control
* Context management
* Multi-turn conversations

---

## Embeddings

An embedding is a numerical vector representation of text.

It captures semantic meaning.

Similar texts produce similar vectors.

Embeddings are used for:

* Semantic search
* Document similarity
* Clustering
* Retrieval-Augmented Generation (RAG)

Important:

Embeddings do not generate text.
They enable similarity comparison.

---

## Vector Store

A vector store is a system that stores embeddings and allows similarity search.

It enables:

* Nearest-neighbor search
* Context retrieval
* Semantic lookup

Vector stores differ from traditional relational databases.

They are optimized for:

* High-dimensional vector comparison
* Similarity scoring
* Fast retrieval of semantically related data

Vector stores are often used in RAG architectures.

---

## Retrieval-Augmented Generation (RAG)

RAG is an architectural pattern.

Instead of relying solely on model training data:

1. The user input is embedded
2. Similar documents are retrieved from a vector store
3. Retrieved context is injected into the prompt
4. The model generates a response using that context

RAG helps:

* Reduce hallucination
* Provide domain-specific knowledge
* Keep data private
* Update knowledge without retraining models

Trade-offs:

* Increased latency
* Increased cost
* More infrastructure complexity

RAG should be applied intentionally, not automatically.

---

## Streaming

Streaming allows model responses to be delivered token-by-token instead of waiting for the full completion.

Benefits:

* Improved user experience
* Faster perceived response time
* Real-time chat interfaces

Architectural implications:

* Requires reactive or streaming-capable endpoints
* Requires backpressure management
* Partial responses must be handled carefully

Streaming changes how APIs are designed.

---

## Temperature and Sampling

Models generate text probabilistically.

Temperature controls randomness.

Lower temperature:

* More deterministic
* Less creative
* More stable output

Higher temperature:

* More creative
* More variable
* Less predictable

Sampling parameters affect:

* Testability
* Consistency
* Reliability of structured outputs

Production systems often use lower temperature settings for stability.

---

## Tokens and Context Window

AI models process text as tokens.

A token is not exactly a word.
It may be:

* A word
* A subword
* A punctuation fragment

Every request consumes:

* Input tokens
* Output tokens

Models also have a context window limit:

* Maximum tokens per request
* Includes prompt + context + response

Architectural implications:

* Large prompts increase cost
* Large RAG contexts may exceed limits
* Token budgeting is required in production systems

Cost scales with token usage.

---

## Hallucination

Hallucination occurs when a model generates incorrect or fabricated information confidently.

Important:

Models do not "know" facts.
They generate statistically probable text.

Hallucination risks increase when:

* The model lacks relevant context
* Prompts are vague
* Temperature is high
* No validation is applied

Mitigation strategies:

* Use RAG
* Add explicit instructions
* Validate structured outputs
* Apply guardrails

AI responses should never be blindly trusted.

---

## Determinism vs Probabilistic Systems

Traditional systems:

Input → Deterministic Output

AI systems:

Input → Probabilistic Output

The same input may produce different responses.

This affects:

* Automated testing
* JSON parsing
* Validation logic
* Business rule enforcement

Design implications:

* Do not rely on exact string matching
* Validate structured outputs carefully
* Use schema-constrained generation where possible

AI systems must be treated as probabilistic components inside deterministic architectures.

---

## AI as an External Distributed Dependency

AI providers are:

* Remote services
* Latency-bound
* Rate-limited
* Cost-based
* Probabilistic

This makes them similar to:

* External APIs
* Payment gateways
* Messaging providers

They must be handled with:

* Timeouts
* Retry policies
* Observability
* Circuit breakers
* Cost monitoring

AI is not an in-memory function call.
It is an external distributed operation.

---

## Final Conceptual Principle

Spring AI does not replace architecture discipline.

It provides:

* Abstractions
* Integration
* Structure

But developers must understand:

* Token economics
* Latency trade-offs
* Non-determinism
* Security implications

AI is powerful — but architecturally different from traditional software components.

Design accordingly.

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*