# Module Structure

This document describes the standard module structure used in this Spring Boot Multi-Module Project Template, following Tactical DDD principles.

## Multi-Module Project Layout

The project uses Maven multi-module structure combined with Tactical DDD. Bounded contexts can be organized as separate Maven modules or as packages within a module.

```
project-root/
├── .github/
│   └── workflows/                    # CI/CD pipeline definitions
├── pom.xml                           # Parent POM (aggregator)
├── AGENTS.md
│
├── shared/                           # Shared kernel module (optional)
│   ├── pom.xml
│   └── src/
│       └── main/java/com/iqkv/project/shared/
│           ├── domain/               # Shared domain primitives
│           ├── exception/            # Common exceptions
│           └── util/                 # Utility classes
│
├── infrastructure/                   # Infrastructure module (optional)
│   ├── pom.xml
│   └── src/
│       └── main/java/com/iqkv/project/infrastructure/
│           ├── config/               # Spring configuration
│           ├── security/             # Security implementation
│           └── persistence/          # Common persistence utilities
│
└── [module-name]/                    # Service/application module (e.g. servicename)
    ├── pom.xml
    └── src/
        ├── main/
        │   ├── java/com/iqkv/project/[module]/
        │   │   ├── [bounded-context]/        # e.g. user, order, payment
        │   │   │   ├── domain/               # Domain layer — core business logic
        │   │   │   │   ├── model/            # Aggregates, entities, value objects
        │   │   │   │   ├── service/          # Domain services
        │   │   │   │   └── event/            # Domain events
        │   │   │   ├── application/          # Application layer — use cases
        │   │   │   │   ├── service/          # Application services
        │   │   │   │   ├── dto/              # Data transfer objects
        │   │   │   │   └── port/             # Ports (interfaces for adapters)
        │   │   │   └── adapter/              # Adapters — interface layer
        │   │   │       ├── in/
        │   │   │       │   └── rest/         # REST controllers (inbound)
        │   │   │       └── out/
        │   │   │           └── persistence/  # Repository implementations (outbound)
        │   │   └── ModuleNameApplication.java  # Spring Boot main class
        │   └── resources/
        │       ├── application.yml           # Base configuration
        │       ├── application-dev.yml       # Dev profile overrides
        │       ├── logback-spring.xml        # Structured JSON logging
        │       └── db/changelog/             # Database migrations (Liquibase)
        └── test/
            └── java/com/iqkv/project/[module]/
                ├── [bounded-context]/
                │   ├── domain/               # Domain model tests
                │   ├── application/          # Application service tests
                │   └── adapter/              # Adapter tests
                └── architecture/             # ArchUnit tests
                    └── TechnicalStructureTest.java
```

## Multi-Module Organization Strategies

**Option 1: Module per Bounded Context** (large, independent contexts)

```
project-root/
├── pom.xml (parent)
├── shared/
├── user-service/        # User bounded context as module
├── order-service/       # Order bounded context as module
└── payment-service/     # Payment bounded context as module
```

**Option 2: Package per Bounded Context** (small, related contexts)

```
project-root/
├── pom.xml (parent)
├── shared/
└── main-service/
    └── src/main/java/.../mainservice/
        ├── user/        # User bounded context as package
        ├── order/       # Order bounded context as package
        └── payment/     # Payment bounded context as package
```

**Option 3: Hybrid Approach** (recommended)

```
project-root/
├── pom.xml (parent)
├── shared/              # Shared kernel module
├── core-service/        # Main service with multiple small contexts
│   └── src/.../coreservice/
│       ├── user/        # Package-level bounded context
│       └── profile/     # Package-level bounded context
└── payment-service/     # Large context as separate module
```

## Key DDD Concepts

- **Bounded Context**: Logical boundary for a specific domain model. Can be a Maven module or a package depending on size.
- **Domain Layer**: Core business logic — entities, value objects, aggregates, domain services
- **Application Layer**: Use cases, orchestration, DTOs, ports (interfaces)
- **Adapter Layer**: Implementation of ports (REST controllers, repository implementations)
- **Infrastructure**: Technical concerns (config, security, persistence framework)
- **Shared Kernel**: Common code shared across bounded contexts (separate Maven module)

## Inter-Module Dependencies

```xml
<!-- In a service module's pom.xml -->
<dependencies>
    <dependency>
        <groupId>com.iqkv</groupId>
        <artifactId>shared</artifactId>
        <version>${project.version}</version>
    </dependency>
    <dependency>
        <groupId>com.iqkv</groupId>
        <artifactId>infrastructure</artifactId>
        <version>${project.version}</version>
    </dependency>
</dependencies>
```

## Adding a New Module

1. Create a new directory for your module
2. Add a `pom.xml` with the appropriate parent reference
3. Add the module to the root `pom.xml` `<modules>` section
4. Follow the Tactical DDD package structure above
