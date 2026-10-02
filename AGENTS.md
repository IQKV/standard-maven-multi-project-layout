# AI Agent Development Guide

## Project Overview

**standard-maven-multi-project-layout** — Starter template for multi-module Spring Boot projects. The root POM is `packaging=pom` and orchestrates child modules. The `servicename` module is the runnable Spring Boot application; additional modules (`shared`, `module-a-name`) are commented-out stubs to activate when needed.

**Key characteristics:**

- Java 25, multi-module Maven, parent `com.iqkv:boot-parent-pom`
- Active module: `servicename` (Spring Boot app with ArchUnit structure tests)
- Checkstyle disabled at root (`checkstyle.skip=true`) — enable per-module when ready
- JaCoCo disabled by default (`jacoco.skip=true`) — enable per-module when ready
- Node.js tooling (pnpm + Husky) for commit hooks and formatting only

## Project Structure

```
standard-maven-multi-project-layout/
├── pom.xml                          # Root aggregator POM (packaging=pom)
├── servicename/                     # Spring Boot application module
│   ├── pom.xml                      # Module POM — inherits root
│   └── src/
│       ├── main/java/com/iqkv/multimoduleservice/servicename/
│       │   └── ServicenameApplication.java
│       └── test/java/com/iqkv/multimoduleservice/servicename/
│           ├── IntegrationTest.java
│           ├── ServicenameApplicationTests.java
│           └── TechnicalStructureTest.java   # ArchUnit package structure test
├── docker/                          # Docker support files (Grafana, DBGate, Postgres)
├── compose.yaml                     # Local dev stack
├── Dockerfile                       # Production container
└── package.json                     # Node tooling only
```

## How to Use This Template

When creating a new service from this template:

1. Rename `servicename/` to your actual module name
2. Update `<modules>` in the root `pom.xml`
3. Update `groupId`, `artifactId`, `name`, `description` in both POMs
4. Rename the Java package from `com.iqkv.multimoduleservice.servicename` to your package
5. Enable Checkstyle (`checkstyle.skip=false`) and JaCoCo (`jacoco.skip=false`) when ready
6. Activate commented-out modules (`shared`, etc.) as needed

## Execution Discipline

- Root cause first. Read both the root POM and the affected module POM before editing.
- Multi-module builds run from the root — always run `./mvnw verify` from root, not from a module directory.
- After two identical failures without new evidence, change approach — do not retry blindly.
- No speculative module additions — activate stubs only when directly requested.

## Security

- Keep credentials, tokens, and private config out of commits, logs, and shared text.
- Flag files likely to contain secrets (`.env`, `application-local.yml`) before staging.
- No hardcoded secrets — use environment variables or Spring config properties.
- Never bypass `--no-verify` unless explicitly requested.

## AI Agent Guidelines

- **Ask before applying**: describe changes, list affected POMs, wait for approval.
- **Approval phrases**: "Yes", "Proceed", "Apply", "Do it", "Looks good"
- **Never create** summary or review markdown files automatically.
- After structural changes: run `./mvnw verify` from root, report in 2–3 sentences.

## Commit Standards

Format: `type(scope): subject`

- Subject: imperative, lowercase, no trailing period, ≤ 72 chars
- Types: `feat`, `fix`, `improvement`, `refactor`, `docs`, `test`, `chore`, `ci`, `perf`, `revert`
- Scope: affected module or area (e.g., `servicename`, `root`, `docker`, `deps`, `checkstyle`)
- For `fix`: symptom + trigger, not the code change
  - ✅ `fix(servicename): context fails to load when Postgres is unreachable at startup`
  - ❌ `fix(servicename): add datasource health check`

Examples:
- `feat(servicename): add Spring Security with JWT filter chain`
- `chore(root): enable Checkstyle and JaCoCo for all modules`
- `refactor(servicename): rename package to match actual service name`
- `chore(deps): update boot-parent-pom to 0.25.0`

## Development Commands

```bash
# Build and test all modules from root
./mvnw verify

# Run the application module
./mvnw -pl servicename spring-boot:run

# Skip Checkstyle during development
./mvnw verify -Dcheckstyle.skip=true

# Node tooling
pnpm formatter:check   # check formatting
pnpm formatter:write   # auto-format
```
