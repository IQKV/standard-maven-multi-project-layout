# Testing Strategy

This document outlines the testing approach for this Spring Boot Multi-Module Project Template.

## Testing Levels

### Unit Tests

Unit tests verify individual components in isolation.

- **Framework**: JUnit 5
- **Location**: Place in the same module as the tested code, with matching package structure
- **Naming Convention**: `*Test.java`
- **Coverage Target**: 80% line coverage for service and business logic
