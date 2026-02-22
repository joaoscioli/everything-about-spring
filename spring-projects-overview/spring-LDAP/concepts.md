# Core Concepts

Understanding Spring LDAP requires understanding both LDAP fundamentals
and how Spring abstracts directory access.

Spring LDAP does not change how LDAP works. It changes how you interact
with it.

---

## 01 - Directory Information Tree (DIT)

LDAP data is organized as a hierarchical tree called the Directory
Information Tree (DIT).

Each entry:

-   Has a Distinguished Name (DN)
-   Contains attributes
-   Belongs to one or more object classes
-   Is positioned within the hierarchy

Example:
````text
cn=John Doe,ou=users,dc=example,dc=com
````
Unlike relational databases, relationships are expressed through
hierarchy --- not joins.

---

## 02 - Distinguished Name (DN)

A Distinguished Name uniquely identifies an entry in the directory.

It represents the full path of the entry inside the tree.

Example structure:
````text
cn=John Doe → common name\
ou=users → organizational unit\
dc=example → domain component
````
DN construction is a critical architectural concern and should not be
scattered across business logic.

---

## 03 - Relative Distinguished Name (RDN)

An RDN is a single component of a DN.

Example:
````text
cn=John Doe
````
Multiple RDNs compose a full DN.

Spring LDAP provides utilities to safely construct DNs without manual
string concatenation.

---

## 04 - Attributes

Each LDAP entry contains attributes.

Examples:

-   uid
-   mail
-   cn
-   memberOf

Attributes:

-   Can be single or multi-valued
-   Are schema-defined
-   May have constraints

Spring LDAP maps attributes to Java objects through mappers or ODM.

---

## 05 - Object Classes

Object classes define the schema rules for entries.

They determine:

-   Required attributes
-   Optional attributes
-   Entry type classification

Understanding object classes is essential when creating or modifying
entries.

---

## 06 - Search Filters

LDAP queries are filter-based, not SQL-based.

Example filter:
````text
(uid=jdoe)
````
Filters can combine logical operators:
````text
(&(objectClass=person)(uid=jdoe))
````
There are no joins or relational constructs.

Spring LDAP provides methods to execute search filters safely.

---

## 07 - Search Scope

LDAP searches operate within defined scopes:

-   OBJECT_SCOPE → Only the base object
-   ONELEVEL_SCOPE → Direct children
-   SUBTREE_SCOPE → Entire subtree

Improper scope selection can cause performance degradation.

Spring LDAP allows explicit control of search scope.

---

## 08 - ContextSource

ContextSource is responsible for:

-   Creating LDAP connections
-   Managing authentication credentials
-   Defining base DN
-   Handling connection configuration

It acts as the connection factory for LdapTemplate.

---

## 09 - LdapTemplate

LdapTemplate is the central abstraction in Spring LDAP.

It provides:

-   search()
-   bind()
-   unbind()
-   modifyAttributes()
-   lookup()

It handles:

-   Context lifecycle
-   Resource cleanup
-   Exception translation

It follows the Template Pattern, similar to Spring JDBC.

---

## 10 - Object-Directory Mapping (ODM)

ODM allows mapping LDAP entries to Java objects using annotations.

It defines:

-   DN attributes
-   Entry base
-   Object class
-   Attribute mappings

ODM simplifies read/write operations but should be used carefully in
complex directory schemas.

It is not equivalent to JPA and does not provide relational
capabilities.

---

## 11 - Bind vs Compare

Two important authentication mechanisms:

Bind: - Attempts to authenticate by binding to LDAP with provided
credentials.

Compare: - Verifies an attribute value without fully binding as that
user.

Spring LDAP supports both approaches depending on security design.

---

## 12 - Exception Translation

Spring LDAP translates low-level LDAP exceptions into:

Spring's DataAccessException hierarchy.

This ensures:

-   Consistency across data technologies
-   Simplified error handling
-   Cleaner service-layer design

---

## 13 - Transactions

LDAP does not provide traditional ACID transactions like relational
databases.

Spring LDAP offers limited transaction support, but:

-   It should not be treated as a transactional persistence framework.
-   Most operations should be considered atomic per entry.

---

## Conceptual Summary

Spring LDAP concepts combine:

-   Hierarchical directory modeling
-   Template-based execution
-   Infrastructure abstraction
-   Controlled mapping strategies

Understanding these fundamentals prevents architectural misuse and
improves integration design quality.

---

Part of **Spring Projects Overview**
within **Everything About Spring Framework**
by *The Coding Cabin*