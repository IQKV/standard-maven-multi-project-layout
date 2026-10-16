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

## Minimal Change Discipline

Solve the whole problem with the least new code. The best code is the code never written.

### Scope before you write

Read the task and the code it touches. List every place the change must reach: callers, tests, fixtures, config, exports. Check what it could break for users: data it would destroy or expose, callers that stop working. That is scope. Extra features are not.

### Pick the smallest complete change

Take the first option that fully works:

1. Does it need to exist? Drop features, options and flexibility nobody asked for — name them in one line so the user can decide.
2. Already in this codebase (helper, component, service, pattern)? Use it the way the surrounding code does.
3. Standard library or platform feature? Use it, unless the project has its own.
4. An installed dependency? Use it. Never add a dependency for a few lines.
5. Can it be one line a reader gets at a glance? One line.
6. Otherwise: the minimum code that works.

Be lazy about the solution, never about the change itself:

- Finish every part the task needs, including the callers, tests and fixtures the change breaks.
- No abstraction, wrapper, option, config, boilerplate or "for later" code nobody asked for. Keep values in the form the platform already gives you. Deletion beats addition. Keep the layers, interfaces and conventions the codebase already has.
- The shortest working diff wins, once you know everything it must touch. A one-liner that needs decoding is not short.
- Comment only the why the code cannot show, in one line.
- Bug fix: grep every caller of the function you touch, then fix the root cause once in the shared code.
- Code you move or merge keeps its error handling and validation.
- Between options of equal size, take the one that is correct on edge cases.
- New non-trivial logic (a branch, a loop, a parser, money or security, or a whole new script) leaves one small test or an assert-based self-check. Trivial changes need none.
- A shortcut with a known limit gets a one-line comment: `shortcut: <the limit>, <when to upgrade>`.
- Push back when the need does not justify a part: state the smaller option in one line instead of building the bigger one silently.

Never cut: validation at trust boundaries, error handling that prevents data loss, security, accessibility, the calibration real hardware needs, anything the user explicitly asked for.

### Token discipline

- Locate with Grep/Glob, then read only the ranges you need — not whole trees or whole files.
- Prefer editing existing files to creating new ones; reuse code that is already there before writing.
- Never paste file contents back in a reply. Cite the path and lines.
- Keep replies to the outcome: what changed, what passed, any risk (see AI Communication Standards).

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

## Evaluation

How we judge whether an agent task in this repository is actually done. Evaluation is evidence-based — a claim without the command and its observed result is not a pass.

> This section starts minimal and grows with the repository. Add a gate only when a change type repeatedly slips through without it.

### Definition of done

A task is evaluated as done only when all of the following hold:

- Every gate required by the change type below has run and passed.
- The final report names the exact command(s) run and the observed result (pass/fail) — per AI Agent Guidelines.
- No required gate was skipped silently. A gate that could not run is reported as a gap, never as a pass.

### Required gates by change type

| Change type | Required gates |
| --- | --- |
| Any change | `./mvnw verify` from the repository root — multi-module builds do not run from a module directory |
| Domain / application / service logic | Unit tests (JUnit 5 + Mockito, AAA); a new non-trivial branch, loop or parser needs a test |
| Module boundary or package move | ArchUnit `TechnicalStructureTest` in the affected module, then `./mvnw verify` from root |
| Activating a stub module | Root `<modules>` updated, then `./mvnw verify` from root |
| Dependency or build config | `./mvnw verify` from root |
| Docs, README or `AGENTS.md` only | `pnpm formatter:check`; no code gate — state that none applies |

### Ordering

Run the narrowest relevant gate first, then the full set for the change type. On the first failure, stop and report it — do not continue on an unproven base (see Execution Discipline).

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
