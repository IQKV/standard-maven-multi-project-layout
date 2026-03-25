# Project Architecture

This section documents the architecture of the Spring Boot Multi-Module Project Template.

## Overview

This template follows a **Tactical DDD** layout with hexagonal architecture, organized as a Maven multi-module project. The architecture is designed to:

- Promote separation of concerns via bounded contexts
- Enable independent development and testing of modules
- Facilitate code reuse through a shared kernel module
- Support scalable application development

## Key Components

- **Root Project**: Parent POM providing common configuration and dependency management
- **Shared Module**: Optional shared kernel with common domain primitives, exceptions, and utilities
- **Infrastructure Module**: Optional cross-cutting technical concerns (config, security, persistence)
- **Service Modules**: Individual bounded-context modules following the DDD layer structure

## Module Structure

Each service module follows the Tactical DDD package layout:

```
[module-name]/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/iqkv/project/[module]/
    │   │   ├── [bounded-context]/
    │   │   │   ├── domain/        # Aggregates, entities, value objects, domain services
    │   │   │   ├── application/   # Use cases, DTOs, port interfaces
    │   │   │   └── adapter/
    │   │   │       ├── in/rest/   # REST controllers (inbound)
    │   │   │       └── out/persistence/  # Repository implementations (outbound)
    │   │   └── ModuleNameApplication.java
    │   └── resources/             # application.yml, logback-spring.xml, db/changelog/
    └── test/
        └── java/com/iqkv/project/[module]/
            ├── [bounded-context]/  # domain / application / adapter tests
            └── architecture/       # ArchUnit tests
```

For more details, see:

- [Module Structure](./module-structure.md)
- [Dependency Management](./dependency-management.md)
- [Spring Modulith](./spring-modulith.md)
