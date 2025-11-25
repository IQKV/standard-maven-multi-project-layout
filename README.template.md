# Project Name 🚀

<!-- TEMPLATE: This README.template.md is a starter template. Copy parts into your real README.md and replace placeholders. -->

<details>
  <summary><strong>How to use this template (click to expand)</strong></summary>

1. Rename the title above to your project name and optionally add a logo right below it.
2. Add badges (build, tests, coverage, license) under the title.
3. Fill each section below with your actual project content (keep the section order if you like it).
4. Replace placeholder code blocks and bullet points with real commands and steps.
5. Keep the "Template Usage" links if you want quick access to template docs, or remove them in your final README.md.
6. Remove this guidance block after you finish customizing.

</details>

- Add your project logo.
- Write a short introduction to the project.
- If you are using badges, add them here.

<details>
  <summary><strong>Badge examples (optional)</strong></summary>

- Build: <code>![CI](https://img.shields.io/github/actions/workflow/status/ORG/REPO/ci.yml?label=CI)</code>
- Tests: <code>![Tests](https://img.shields.io/badge/tests-passing-brightgreen)</code>
- Coverage: <code>![Coverage](https://img.shields.io/badge/coverage-100%25-brightgreen)</code>
- License: <code>![License](https://img.shields.io/github/license/ORG/REPO)</code>

</details>

## :beginner: About

Add a detailed introduction about the project here, everything you want the reader to know.

## 📚 Documentation

- [API Documentation](docs/api/README.md)
- [Architecture Overview](docs/architecture/README.md)
- [Deployment Guide](docs/deployment/README.md)
- [Contributing Guidelines](.github/CONTRIBUTING.md)

---

<details>
  <summary><strong>✅ Pre-publish checklist (remove in final README)</strong></summary>

- [ ] Title updated and logo added
- [ ] Badges added (CI, tests, coverage, license)
- [ ] About/Usage/Installation/Commands completed
- [ ] Development prerequisites and environment documented
- [ ] Architecture notes reflect your stack and modules
- [ ] Links verified (Getting Started, docs, external resources)
- [ ] Guidance blocks, template-docs folder, are removed before publishing

</details>

---

## 📚 Template Usage

- [Getting Started](template-docs/getting-started/README.md)
- [Project Architecture](template-docs/architecture/README.md)
- [Development Workflow](template-docs/development/README.md)
- [GitHub Template Features](template-docs/template/README.md)
- [API Documentation](template-docs/api/README.md)

## 🧩 Boilerplate Architecture

- **Project Structure**: Maven multi-module layout with Tactical DDD
- **GitHub Integration**: Issue templates, labels, and workflows
- **Quality Tools**: Code formatting, linting, and testing setup
- **Documentation**: Community guidelines and contribution process

### Tactical Domain-Driven Design (DDD)

This project follows Tactical DDD principles to organize code around business domains:

**Layered Architecture per Bounded Context:**

- **Domain Layer**: Core business logic, entities, value objects, aggregates, and domain services
- **Application Layer**: Use cases, application services, DTOs, and port interfaces
- **Adapter Layer**: Implementation of ports (REST controllers, repository implementations)
  - **Inbound Adapters**: REST controllers, message consumers
  - **Outbound Adapters**: Database repositories, external API clients

**Multi-Module Organization:**

Bounded contexts can be organized as:

- **Separate Maven modules** for large, independent contexts
- **Packages within a module** for smaller, related contexts
- **Hybrid approach** combining both strategies

**Benefits:**

- Clear separation of concerns
- Independent deployment of bounded contexts (when using separate modules)
- Easier testing and maintenance
- Better team collaboration with clear boundaries
- Flexibility to evolve architecture as domain understanding grows

See [AGENTS.md](AGENTS.md) for detailed structure and development guidelines.
