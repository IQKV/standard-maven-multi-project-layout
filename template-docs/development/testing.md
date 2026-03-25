# Testing Strategy

This document outlines the testing approach for this Spring Boot Multi-Module Project Template.

## Testing Levels

### Unit Tests

Unit tests verify individual components in isolation.

- **Framework**: JUnit 5
- **Location**: Place in the same module as the tested code, mirroring the DDD package structure:
    - `[bounded-context]/domain/` — domain model and service tests
    - `[bounded-context]/application/` — application service (use case) tests
    - `[bounded-context]/adapter/` — adapter tests (REST controllers, repository implementations)
- **Naming Convention**: `*Test.java`
- **Coverage Target**: 80% line coverage for domain and application layers
