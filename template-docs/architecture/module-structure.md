# Module Structure

This document describes the standard module structure used in this Spring Boot Multi-Module Project Template.

## Standard Module Layout

Each module in the project follows this standard structure:

```
module-name/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── github/
│   │   │           └── dimdnk/
│   │   │               └── modulename/
│   │   │                   ├── config/       # Configuration classes
│   │   │                   ├── controller/   # REST controllers
│   │   │                   ├── domain/       # Domain model
│   │   │                   ├── dto/          # Data Transfer Objects
│   │   │                   ├── exception/    # Custom exceptions
│   │   │                   ├── repository/   # Data access layer
│   │   │                   ├── service/      # Business logic
│   │   │                   └── Application.java  # Main application class
│   │   └── resources/
│   │       ├── application.yml               # Application configuration
│   │       ├── application-dev.yml           # Development profile
│   │       ├── application-production.yml          # Production profile
│   │       └── logback-spring.xml            # Logging configuration
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── github/
│       │           └── dimdnk/
│       │               └── modulename/
│       │                   ├── controller/   # Controller tests
│       │                   ├── repository/   # Repository tests
│       │                   ├── service/      # Service tests
│       │                   └── ArchitectureTest.java  # Architecture tests
│       └── resources/
│           └── application-test.yml          # Test configuration
└── pom.xml                                   # Module POM file
```

## Key Components

### Java Package Structure

The Java package structure follows the standard Java package naming convention:

- `com.iqkv.modulename`: Base package for the module
- `config`: Spring configuration classes
- `controller`: REST API controllers
- `domain`: Domain model classes
- `dto`: Data Transfer Objects for API requests/responses
- `exception`: Custom exception classes
- `repository`: Data access layer (JPA repositories)
- `service`: Business logic implementation

### Resources

- `application.yml`: Main application configuration
- `application-{profile}.yml`: Profile-specific configurations
- `logback-spring.xml`: Logging configuration

### Tests

- Controller tests: API endpoint tests
- Repository tests: Data access layer tests
- Service tests: Business logic tests
- Architecture tests: Tests to enforce architectural constraints using ArchUnit

## Adding a New Module

To add a new module to the project:

1. Create a new directory for your module
2. Add a `pom.xml` file with the appropriate parent reference
3. Add the module to the parent `pom.xml`
4. Follow the standard package structure for your module

For more details on adding modules, see the [Customization Guide](../customization/adding-modules.md).
