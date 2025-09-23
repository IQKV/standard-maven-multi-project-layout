# Dependency Management

This document explains how dependencies are managed in this Spring Boot Multi-Module Project Template.

## Maven Parent POM

The project uses a parent POM structure for centralized dependency management:

```xml
<parent>
    <groupId>com.github.dimdnk</groupId>
    <artifactId>boot-parent-pom</artifactId>
    <version>25.0.0-SNAPSHOT</version>
</parent>
```

This parent POM provides:

- Common dependency versions
- Plugin configurations
- Build profiles

## Key Dependencies

The template includes these key dependencies:

- **Spring Boot**: Core framework for building applications
- **Spring Modulith**: Support for modular application development
- **Lombok**: Reduces boilerplate code
- **Logback & Logstash**: Logging infrastructure
- **Micrometer**: Metrics collection
- **ArchUnit**: Architecture testing

## Adding Dependencies

When adding dependencies to your project:

1. Check if the dependency is already managed by the parent POM
2. If managed, add it without version:
   ```xml
   <dependency>
       <groupId>org.example</groupId>
       <artifactId>example-library</artifactId>
   </dependency>
   ```
3. If not managed, specify the version:
   ```xml
   <dependency>
       <groupId>org.example</groupId>
       <artifactId>example-library</artifactId>
       <version>1.0.0</version>
   </dependency>
   ```

## Dependency Management Best Practices

- Keep versions in the parent POM when possible
- Use dependency management section for version control
- Minimize direct version declarations in module POMs
- Use Maven property placeholders for versions
- Regularly update dependencies for security patches
