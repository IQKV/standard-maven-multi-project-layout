# Project Architecture

This section documents the architecture of the Spring Boot Multi-Module Project Template.

## Overview

This template follows a modular architecture based on Maven multi-module project structure and Spring Modulith principles. The architecture is designed to:

- Promote separation of concerns
- Enable independent development and testing of modules
- Facilitate code reuse across modules
- Support scalable application development

## Key Components

- **Root Project**: Provides common configuration and dependency management
- **Service Modules**: Individual functional modules (e.g., `servicename`)
- **Shared Libraries**: Optional common code shared across modules

## Module Structure

Each module follows a standard Maven project structure:

```
module-name/
├── src/
│   ├── main/
│   │   ├── java/        # Java source code
│   │   ├── resources/   # Configuration files and resources
│   │   └── docker/      # Docker-related files
│   └── test/
│       ├── java/        # Test source code
│       └── resources/   # Test resources
└── pom.xml              # Module-specific Maven configuration
```

For more details, see:
- [Module Structure](./module-structure.md)
- [Dependency Management](./dependency-management.md)
- [Spring Modulith](./spring-modulith.md)