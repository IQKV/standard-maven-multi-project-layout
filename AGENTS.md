# Repository Guidelines & Agent Instructions

## Overview

This document provides comprehensive guidelines for repository management, development workflows, and collaboration standards for Maven-based Java projects. It serves as a reference for both human developers and AI agents working with this codebase.

## 🏛️ Repository Structure & Organization

### Tactical DDD Multi-Module Project Layout

This project uses Maven multi-module structure combined with Tactical Domain-Driven Design (DDD) principles. Bounded contexts can be organized as separate Maven modules or as packages within a module, depending on the size and complexity of your domain.

```
project-root/
├── .github/                          # GitHub workflows and automation
│   └── workflows/                    # CI/CD pipeline definitions
├── pom.xml                           # Parent POM (aggregator)
├── README.md
├── AGENTS.md                         # This file
│
├── shared/                           # Shared kernel module (optional)
│   ├── pom.xml                       # Shared module POM
│   └── src/
│       ├── main/java/
│       │   └── com/example/project/shared/
│       │       ├── domain/           # Shared domain primitives
│       │       ├── exception/        # Common exceptions
│       │       └── util/             # Utility classes
│       └── test/java/
│
├── infrastructure/                   # Infrastructure module (optional)
│   ├── pom.xml
│   └── src/
│       └── main/java/
│           └── com/example/project/infrastructure/
│               ├── config/           # Spring configuration
│               ├── security/         # Security implementation
│               └── persistence/      # Common persistence utilities
│
├── [module-name]/                    # Service/Application module
│   ├── pom.xml                       # Module POM
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   │   └── com/example/project/[module]/
│       │   │       ├── [bounded-context-1]/  # Example: user
│       │   │       │   ├── domain/       # Domain layer (core business logic)
│       │   │       │   │   ├── model/    # Aggregates, entities, value objects
│       │   │       │   │   ├── service/  # Domain services
│       │   │       │   │   └── event/    # Domain events
│       │   │       │   ├── application/  # Application layer
│       │   │       │   │   ├── service/  # Application services (use cases)
│       │   │       │   │   ├── dto/      # Data transfer objects
│       │   │       │   │   └── port/     # Ports (interfaces for adapters)
│       │   │       │   └── adapter/      # Adapters (interface layer)
│       │   │       │       ├── in/       # Inbound adapters
│       │   │       │       │   └── rest/ # REST controllers
│       │   │       │       └── out/      # Outbound adapters
│       │   │       │           └── persistence/ # Repository implementations
│       │   │       ├── [bounded-context-2]/  # Example: order
│       │   │       │   ├── domain/
│       │   │       │   ├── application/
│       │   │       │   └── adapter/
│       │   │       └── ModuleNameApplication.java  # Spring Boot main class
│       │   └── resources/
│       │       ├── application.yml       # Base configuration
│       │       ├── application-local.yml # Local development profile
│       │       └── db/changelog/         # Database migrations (if using Liquibase)
│       └── test/
│           └── java/
│               └── com/example/project/[module]/
│                   ├── [bounded-context]/
│                   │   ├── domain/       # Domain model tests
│                   │   ├── application/  # Application service tests
│                   │   └── adapter/      # Adapter tests
│                   └── architecture/     # ArchUnit tests
│
└── [another-module]/                 # Another service module (if needed)
    ├── pom.xml
    └── src/...
```

**Multi-Module Organization Strategies:**

**Option 1: Module per Bounded Context (Large contexts)**

```
project-root/
├── pom.xml (parent)
├── shared/
├── user-service/        # User bounded context as module
├── order-service/       # Order bounded context as module
└── payment-service/     # Payment bounded context as module
```

**Option 2: Package per Bounded Context (Small contexts)**

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

**Option 3: Hybrid Approach (Recommended)**

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

**Key DDD Concepts:**

- **Bounded Context**: Logical boundary for a specific domain model (e.g., user, order, payment). Can be a Maven module or a package depending on size.
- **Domain Layer**: Core business logic, entities, value objects, aggregates, domain services
- **Application Layer**: Use cases, orchestration, DTOs, ports (interfaces)
- **Adapter Layer**: Implementation of ports (REST controllers, repository implementations)
- **Infrastructure**: Technical concerns (config, security, persistence framework)
- **Shared Kernel**: Common code shared across bounded contexts (separate Maven module)

**Module Dependencies:**

```xml
<!-- In a service module's pom.xml -->
<dependencies>
    <!-- Depend on shared kernel -->
    <dependency>
        <groupId>com.example</groupId>
        <artifactId>shared</artifactId>
        <version>${project.version}</version>
    </dependency>

    <!-- Depend on infrastructure if separated -->
    <dependency>
        <groupId>com.example</groupId>
        <artifactId>infrastructure</artifactId>
        <version>${project.version}</version>
    </dependency>
</dependencies>
```

## 🤖 AI Agent Guidelines

### AI Communication Standards

**CRITICAL: Agents must communicate concisely and avoid unnecessary verbosity.**

#### Response Length Guidelines

```yaml
summaries:
    max_length: "2-3 sentences"
    focus: "What was done, not how it was done"
    avoid: "Bullet point lists, detailed recaps, obvious statements"

explanations:
    when_detailed: "Complex technical concepts, architecture decisions, security implications"
    when_brief: "Simple changes, routine operations, status updates"

verification:
    format: "Minimal wording - state outcome only"
    example: "Tests pass. Coverage at 65%."
    avoid: "Lengthy descriptions of what was verified"
```

#### Prohibited Outputs

**NEVER create these files:**

- ❌ `SUMMARY.md` - Auto-generated work summaries
- ❌ `CHANGES.md` - Change documentation (use commit messages)
- ❌ `REVIEW.md` - Code review summaries
- ❌ `ANALYSIS.md` - Analysis reports (communicate directly)
- ❌ Any markdown file documenting agent work unless explicitly requested

**Why:** These files create noise, clutter the repository, and provide no value. Use commit messages and direct communication instead.

#### Concise vs Verbose Examples

**❌ VERBOSE (Don't do this):**

```
I have successfully completed the implementation of the new user authentication
endpoint. Here's a comprehensive summary of everything I did:

• Created a new UserAuthenticationController class
• Implemented the login method with proper validation
• Added JWT token generation logic
• Created comprehensive unit tests
• Updated the OpenAPI documentation

All tests are passing successfully and the code is ready for review.
```

**✅ CONCISE (Do this):**

```
Added user authentication endpoint with JWT token generation. Tests pass, coverage at 68%.
```

#### When to Be Detailed

Provide detailed explanations ONLY for:

1. **Complex Architecture Decisions**
2. **Security Implications**
3. **Breaking Changes**
4. **Non-Obvious Technical Choices**

#### Response Templates

**For Simple Changes:**

```
Changed X to Y. Tests pass.
```

**For Bug Fixes:**

```
Fixed [issue]. Root cause: [brief explanation]. Added regression test.
```

**For New Features:**

```
Implemented [feature]. Includes [key components]. Tests pass, coverage [X]%.
```

**For Refactoring:**

```
Refactored [component] to [improvement]. No behavior changes. Tests pass.
```

#### Communication Principles

1. **Action-Oriented**: Focus on what was done, not the process
2. **Results-First**: State the outcome immediately
3. **No Redundancy**: Don't repeat what's obvious from the code
4. **No Meta-Commentary**: Don't describe your own actions
5. **Trust the User**: They can read code; don't explain obvious changes
6. **Verification is Brief**: "Tests pass" is sufficient

### Technology Stack Context

Before making recommendations, agents should understand the project's technology stack:

**Runtime & Framework**

- Java 25 with modern features (records, pattern matching, text blocks, var)
- Spring Boot 4.x
- Maven for build management

**Data & Caching**

- Database: PostgreSQL/MySQL/H2 (check project configuration)
- Spring Data JPA with Hibernate
- Redis for caching (if configured)

**Security**

- Spring Security
- JWT authentication (if configured)
- Method-level security with `@PreAuthorize`

**Testing**

- JUnit 5 for unit tests
- Mockito for mocking
- Testcontainers for integration tests (if configured)
- ArchUnit for architecture validation

**API Documentation**

- SpringDoc OpenAPI 3
- Swagger UI

**Build & Deployment**

- Maven 3.9.0+
- Docker (if Dockerfile present)
- Environment profiles: local, staging, production

## 📋 Development Standards

### Branch Strategy

```
main (production-ready code)
├── develop (main development branch)
├── feature/* (new features)
├── bugfix/* (bug fixes)
├── improvement/* (enhancements)
├── hotfix/* (production fixes)
└── rfc/* (request for comments)
```

### Branch Naming Conventions

```bash
# Feature branches
feature/add-user-authentication
feature/implement-caching

# Bug fixes
bugfix/fix-null-pointer-exception
bugfix/resolve-connection-leak

# Improvements
improvement/optimize-database-queries
improvement/enhance-error-messages

# Hotfixes
hotfix/critical-security-patch

# RFC (Request for Comments)
rfc/new-authentication-flow
```

### Commit Message Format (Conventional Commits)

**Format:**

```
type(scope): subject

[optional body]

[optional footer]
```

**Allowed Types:**

- `feat`: New feature
- `fix`: Bug fix
- `rfc`: Request for comments / architectural proposal
- `docs`: Documentation changes
- `style`: Code style changes
- `improvement`: Enhancements to existing features
- `refactor`: Code refactoring
- `perf`: Performance improvements
- `test`: Adding or updating tests
- `chore`: Maintenance tasks
- `build`: Build system changes
- `ci`: CI/CD pipeline changes
- `revert`: Reverting previous commits

**Rules:**

- Subject line: 6-220 characters
- Use lowercase for type
- Use imperative mood ("add" not "added")
- No period at end of subject line

**Examples:**

```bash
feat(auth): add JWT authentication endpoint

fix(user): resolve null pointer in user service

The service was not checking for null values before
accessing user properties.

Closes #123

perf(database): optimize user search query with indexes

docs(readme): update installation instructions

refactor(service): extract validation logic to separate class
```

### AI Commit Message Generation

**When AI Should Generate Commit Messages:**

AI agents should automatically generate and present commit messages after:

- Completing multi-file changes (3+ files modified)
- Implementing new features or bug fixes
- Performing refactoring across multiple components
- Making configuration or infrastructure changes

**AI Workflow for Commit Message Generation:**

```yaml
after_completing_changes:
    1. analyze_changes: "Review all modified files and understand the scope"
    2. identify_type: "Determine the appropriate commit type"
    3. determine_scope: "Identify the affected component"
    4. craft_subject: "Write concise subject line (6-220 chars)"
    5. add_body_if_needed: "Include body for complex changes"
    6. present_to_user: "Show the generated commit message for review"
    7. wait_for_approval: "User can accept, modify, or reject"
```

**Example Presentation:**

```
I've completed the changes. Here's the suggested commit message:

---
feat(auth): add JWT authentication endpoint

Implements JWT-based authentication with token generation
and validation. Includes rate limiting and comprehensive tests.
---

Would you like me to use this commit message, or would you prefer to modify it?
```

### Code Quality Standards

#### Java Code Standards (Java 25)

**Use Modern Java Features:**

```java
// Records for immutable DTOs
public record UserDto(@NotBlank String username, @Email String email, Instant createdAt) {}

// Pattern matching with switch expressions
public String getUserRole(User user) {
  return switch (user.getRole()) {
    case ADMIN -> "Administrator";
    case USER -> "Regular User";
    default -> "Unknown";
  };
}

// Text blocks for multi-line strings
var query = """
  SELECT u FROM User u
  WHERE u.email = :email
  AND u.enabled = true
  """;

// var for local variables (when type is obvious)
var users = userRepository.findAll();
```

**Import Order (Checkstyle Enforced):**

```java
// 1. Static imports (alphabetically sorted)
import static org.assertj.core.api.Assertions.assertThat;

// 2. Standard Java/Jakarta packages (alphabetically sorted)
import jakarta.validation.Valid;
import java.time.Instant;
import java.util.List;
// 3. Third-party packages (alphabetically sorted)
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
```

**Import Rules:**

```yaml
import_formatting:
    - "Separate each group with a blank line"
    - "Sort imports alphabetically within each group"
    - "No wildcard imports (import java.util.*) - use explicit imports"
    - "No unused imports"
    - "Package and import statements must not be line-wrapped"

checkstyle_modules:
    AvoidStarImport:
        description: "Prohibits wildcard imports (import java.util.*)"
        enforcement: "Build fails on star imports"
        rationale: "Explicit imports improve code clarity and prevent naming conflicts"

    UnusedImports:
        description: "Detects and removes unused import statements"
        enforcement: "Build fails on unused imports"
```

**Service Layer Pattern:**

```java
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;

  @Transactional
  public UserDto createUser(UserCreateCommand command) {
    log.info("Creating user: {}", command.username());

    var user = User.builder().username(command.username()).email(command.email()).build();

    var savedUser = userRepository.save(user);
    return UserMapper.toDto(savedUser);
  }
}
```

**REST Controller Pattern:**

```java
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Management")
public class UserController {

  private final UserService userService;

  @GetMapping("/{id}")
  @Operation(summary = "Get user by ID")
  public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
    return userService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
}
```

#### Testing Standards

**Unit Tests - AAA Pattern:**

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @Test
  @DisplayName("Should create user successfully")
  void shouldCreateUser() {
    // Arrange
    var command = new UserCreateCommand("john.doe", "john@example.com");
    when(userRepository.save(any(User.class))).thenAnswer((i) -> i.getArgument(0));

    // Act
    var result = userService.createUser(command);

    // Assert
    assertThat(result.username()).isEqualTo("john.doe");
    verify(userRepository).save(any(User.class));
  }
}
```

**Integration Tests with Testcontainers:**

```java
@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class UserServiceIntegrationTest {

  @Container
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @Autowired
  private UserService userService;

  @Test
  void shouldCreateAndRetrieveUser() {
    // Test implementation
  }
}
```

**Architecture Tests with ArchUnit:**

```java
@AnalyzeClasses(packages = "com.example.project")
class ArchitectureTest {

  @ArchTest
  static final ArchRule servicesOnlyAccessedByControllersOrServices = classes()
    .that()
    .resideInAPackage("..service..")
    .should()
    .onlyBeAccessed()
    .byAnyPackage("..controller..", "..service..", "..config..");

  @ArchTest
  static final ArchRule repositoriesShouldBeInterfaces = classes().that().resideInAPackage("..repository..").should().beInterfaces();
}
```

### Maven Command Best Practices for AI Agents

**STRICT RECOMMENDATION: Always use `-Dcheckstyle.skip=true` when running Maven commands during development.**

```yaml
maven_commands:
    development_phase:
        recommended: "mvn clean verify -Dcheckstyle.skip=true"
        reason: "Focus on functionality and tests without style blocking"

    testing_phase:
        recommended: "mvn test -Dcheckstyle.skip=true"
        reason: "Rapid test iteration without style checks"

    style_check_phase:
        explicit: "mvn checkstyle:check"
        when: "Before committing or when explicitly requested"

workflow:
    1. develop: "Implement features with -Dcheckstyle.skip=true"
    2. test: "Run tests with -Dcheckstyle.skip=true"
    3. verify: "Ensure functionality works correctly"
    4. style: "Run mvn checkstyle:check separately"
    5. fix_style: "Address Checkstyle violations in focused pass"
    6. commit: "CI/CD enforces Checkstyle automatically"

rationale:
    - "Checkstyle violations should not block functional development"
    - "Style issues are better addressed in dedicated cleanup phase"
    - "CI/CD pipeline enforces style checks before merge"
    - "Faster iteration cycle for AI-assisted development"
```

**Multi-Module Maven Commands:**

```bash
# ✅ Build entire project from root
mvn clean install -Dcheckstyle.skip=true

# ✅ Build specific module only
mvn clean install -Dcheckstyle.skip=true -pl module-name

# ✅ Build module and its dependencies
mvn clean install -Dcheckstyle.skip=true -pl module-name -am

# ✅ Build all modules that depend on a module
mvn clean install -Dcheckstyle.skip=true -pl module-name -amd

# ✅ Test specific module
mvn test -Dcheckstyle.skip=true -pl module-name

# ✅ Run from specific module directory
cd module-name
mvn clean verify -Dcheckstyle.skip=true

# ✅ Skip tests for faster builds
mvn clean install -DskipTests -Dcheckstyle.skip=true

# ✅ Build reactor (all modules) in parallel
mvn clean install -T 1C -Dcheckstyle.skip=true

# ✅ Explicit style check when ready
mvn checkstyle:check

# ❌ NOT RECOMMENDED: Running verify without skip during active development
mvn clean verify  # May fail due to style issues, blocking progress
```

**Module-Specific Commands:**

```bash
# Build only changed modules (requires Git)
mvn clean install -Dcheckstyle.skip=true -pl $(git diff --name-only HEAD~1 | grep pom.xml | xargs dirname | paste -sd "," -)

# Run tests for all modules
mvn test -Dcheckstyle.skip=true

# Package specific module as JAR/WAR
mvn package -Dcheckstyle.skip=true -pl service-module
```

## 🔄 Workflow Management

### Pull Request Guidelines

#### PR Title Format

PR titles must follow Conventional Commits format:

```
type(scope): description

Examples:
feat(auth): add JWT authentication
fix(user): resolve null pointer exception
docs(readme): update installation guide
refactor(service): extract validation logic
```

#### PR Description Template

```markdown
## Description

Brief description of changes and motivation.

## Type of Change

- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update
- [ ] Performance improvement
- [ ] Refactoring

## Changes Made

- List specific changes
- Include affected components

## How Has This Been Tested?

- [ ] Unit tests added/updated
- [ ] Integration tests added/updated
- [ ] Manual testing performed

## Test Coverage

- Current coverage: X%
- Coverage change: +/-X%

## Checklist

- [ ] Code follows style guidelines
- [ ] Commit messages follow Conventional Commits
- [ ] Self-review completed
- [ ] Tests cover new/modified code
- [ ] All tests pass locally
- [ ] Documentation updated

## Security Considerations

- [ ] No sensitive data in logs
- [ ] Input validation implemented
- [ ] Authorization checks in place

## Additional Notes
```

#### Review Criteria

**Automated Checks (Must Pass):**

- ✅ All tests pass
- ✅ Checkstyle validation passes
- ✅ Code coverage meets minimum threshold
- ✅ Commit messages follow Conventional Commits
- ✅ No merge conflicts

**Manual Review Focus:**

```yaml
review_checklist:
    code_quality:
        - Modern Java features used appropriately
        - Proper exception handling
        - No code duplication

    security:
        - Input validation with @Valid
        - No SQL injection vulnerabilities
        - No sensitive data exposure

    testing:
        - AAA pattern in unit tests
        - Edge cases covered
        - Integration tests for critical paths

    documentation:
        - OpenAPI annotations on endpoints
        - Complex logic documented
        - README updated if needed
```

## 🔒 Security Guidelines

### Security Checklist

```yaml
authentication:
  - [ ] Secure authentication mechanism implemented
  - [ ] Password hashing (BCrypt or similar)
  - [ ] Token expiration configured
  - [ ] Account lockout protection

authorization:
  - [ ] Role-based access control
  - [ ] Method-level security with @PreAuthorize
  - [ ] User can only access own data

input_validation:
  - [ ] Bean Validation annotations (@Valid, @NotBlank)
  - [ ] SQL injection prevention (parameterized queries)
  - [ ] XSS prevention
  - [ ] Request size limits configured

data_protection:
  - [ ] Passwords never logged
  - [ ] Sensitive data encrypted
  - [ ] HTTPS enforced in production
  - [ ] Secure headers configured

secrets_management:
  - [ ] No hardcoded secrets
  - [ ] Environment variables for configuration
  - [ ] .env files gitignored
```

### Security Testing

```java
@Test
void shouldRejectInvalidToken() {
  var invalidToken = "invalid.token";
  assertThrows(AuthenticationException.class, () -> authService.validateToken(invalidToken));
}

@Test
@WithMockUser(roles = "USER")
void shouldDenyAccessToAdminEndpoint() throws Exception {
  mockMvc.perform(get("/api/v1/admin/users")).andExpect(status().isForbidden());
}
```

## 📊 Quality Assurance

### Code Quality Metrics

```yaml
test_coverage:
    minimum_threshold: ">= 60%"
    target: ">= 80%"

code_style:
    tool: "Checkstyle"
    enforcement: "Maven build fails on violations"

architecture:
    tool: "ArchUnit"
    rules:
        - "Services only accessed by controllers"
        - "No cyclic dependencies"
        - "Proper package structure"
```

### Quality Gates

```bash
# Run all quality checks locally (from root)
mvn clean verify

# Run all quality checks for specific module
mvn clean verify -pl module-name

# Run only tests (all modules)
mvn test

# Run tests for specific module
mvn test -pl module-name

# Check code coverage (generates reports for all modules)
mvn jacoco:report
# View: module-name/target/site/jacoco/index.html

# Aggregate coverage report (if configured)
mvn jacoco:report-aggregate

# Check code style (all modules)
mvn checkstyle:check

# Check code style for specific module
mvn checkstyle:check -pl module-name

# Run architecture tests (all modules)
mvn test -Dtest=*ArchitectureTest

# Run architecture tests for specific module
mvn test -Dtest=*ArchitectureTest -pl module-name
```

## 🚀 CI/CD Pipeline

### Recommended GitHub Actions Workflow

```yaml
name: CI/CD Pipeline

on:
    push:
        branches: [main, develop]
    pull_request:
        branches: [main, develop]

jobs:
    build-and-test:
        runs-on: ubuntu-latest

        steps:
            - uses: actions/checkout@v4

            - name: Set up JDK 25
              uses: actions/setup-java@v4
              with:
                  java-version: "25"
                  distribution: "temurin"
                  cache: "maven"

            - name: Build and Test
              run: mvn clean verify

            - name: Upload Coverage
              uses: codecov/codecov-action@v3
              with:
                  files: target/site/jacoco/jacoco.xml
```

## 📚 Documentation Standards

### API Documentation with OpenAPI

```java
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Management", description = "User operations")
public class UserController {

  @GetMapping("/{id}")
  @Operation(summary = "Get user by ID", description = "Retrieves a user by their unique identifier")
  @ApiResponses({ @ApiResponse(responseCode = "200", description = "User found"), @ApiResponse(responseCode = "404", description = "User not found") })
  public ResponseEntity<UserDto> getUser(@Parameter(description = "User ID", example = "1") @PathVariable Long id) {
    return userService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
}
```

### Code Documentation

**When to Document:**

- Complex business logic
- Non-obvious algorithms
- Security-critical code
- Public APIs

**When NOT to Document:**

- Self-explanatory code
- Simple getters/setters
- Obvious implementations

## 🎯 Agent Decision Framework

### User Confirmation Policy

**CRITICAL RULE: Always ask for user confirmation before applying changes.**

```yaml
before_making_changes:
    1. analyze: "Understand the request and identify required changes"
    2. explain: "Describe what changes will be made and why"
    3. assess_impact: "Evaluate impact, effort, and risk"
    4. present_options: "Offer alternatives if applicable"
    5. wait_for_approval: "STOP and wait for explicit user confirmation"
    6. apply_changes: "Only after user approves"
    7. verify: "Confirm changes work as expected"

exceptions:
    - read_only_operations: "Reading files, searching, analyzing"
    - information_requests: "Answering questions, explaining concepts"
    - recommendations: "Suggesting approaches without implementing"

never_auto_apply:
    - code_changes: "Any modification to source files"
    - configuration_changes: "application.yml, pom.xml, etc."
    - dependency_updates: "Adding or updating dependencies"
    - refactoring: "Code restructuring"
    - deletions: "Removing files or code"
    - security_changes: "Authentication, authorization, secrets"
```

### When to Intervene

**High Priority (Immediate Action - Still Requires Approval):**

- 🚨 Security vulnerabilities
- 🔴 Build failures blocking development
- 💥 Critical bugs affecting core functionality
- ⚠️ Authentication/authorization failures

**Medium Priority (Plan and Execute - Requires Approval):**

- 📉 Code quality degradation
- 🐌 Performance regressions
- ❌ Test failures
- 📦 Dependency updates

**Low Priority (Continuous Improvement - Requires Approval):**

- 📝 Documentation gaps
- ♻️ Refactoring opportunities
- 🎨 Code style improvements
- 🧪 Test coverage improvements

### Decision Matrix

```yaml
impact_assessment:
    critical: "Service unavailable, data loss risk"
    high: "Major feature broken, workaround exists"
    medium: "Minor feature affected"
    low: "Internal improvement, no user impact"

effort_estimation:
    small: "< 1 day"
    medium: "1-3 days"
    large: "1-2 weeks"
    xlarge: "> 2 weeks"

risk_evaluation:
    low: "Well-understood change, easy rollback"
    medium: "New pattern, tested rollback"
    high: "Complex change, difficult rollback"
    critical: "Breaking change, irreversible"
```

### Common Patterns and Solutions

**Pattern: Creating a new Maven module**

```bash
# 1. Create module directory
mkdir new-module

# 2. Create module pom.xml
cat > new-module/pom.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <parent>
        <groupId>com.example</groupId>
        <artifactId>parent-project</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>new-module</artifactId>
    <packaging>jar</packaging>
</project>
EOF

# 3. Add module to parent pom.xml <modules> section
# <module>new-module</module>

# 4. Create standard directory structure
mkdir -p new-module/src/main/java
mkdir -p new-module/src/main/resources
mkdir -p new-module/src/test/java
mkdir -p new-module/src/test/resources
```

**Pattern: Adding inter-module dependency**

```xml
<!-- In dependent module's pom.xml -->
<dependencies>
    <!-- Depend on another module -->
    <dependency>
        <groupId>com.example</groupId>
        <artifactId>shared</artifactId>
        <version>${project.version}</version>
    </dependency>
</dependencies>
```

**Pattern: Adding a new REST endpoint**

```java
// 1. Define DTO in application layer
public record CreateRequest(@NotBlank String name) {}

// 2. Add service method in application layer
@Service
public class MyService {

  public MyDto create(CreateRequest request) {
    /* ... */
  }
}

// 3. Add controller endpoint in adapter layer
@RestController
public class MyController {

  @PostMapping
  @Operation(summary = "Create resource")
  public ResponseEntity<MyDto> create(@Valid @RequestBody CreateRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(myService.create(request));
  }
}

// 4. Add tests
@Test
void shouldCreateResource() {
  /* ... */
}
```

**Pattern: Adding database migration (Liquibase)**

```xml
<changeSet id="001" author="developer">
    <createTable tableName="users">
        <column name="id" type="bigint" autoIncrement="true">
            <constraints primaryKey="true"/>
        </column>
        <column name="username" type="varchar(100)">
            <constraints nullable="false" unique="true"/>
        </column>
        <column name="created_at" type="timestamp"
                defaultValueComputed="CURRENT_TIMESTAMP"/>
    </createTable>
</changeSet>
```

**Pattern: Adding caching**

```java
@Service
public class MyService {

  @Cacheable(value = "items", key = "#id")
  public Optional<ItemDto> findById(Long id) {
    return repository.findById(id).map(ItemMapper::toDto);
  }

  @CacheEvict(value = "items", key = "#id")
  public void delete(Long id) {
    repository.deleteById(id);
  }
}
```

---

This document serves as a comprehensive reference for maintaining high-quality, secure, and well-organized Java projects while facilitating effective collaboration between human developers and AI agents.
