> ## 🤔 What is this template all about?
>
> - This template can be used as a base layer for a multi-module Maven projects.
> - Make the project easy to maintain with **7 issue templates**.
> - Quick-start documentation with an extraordinary README structure.
> - Manage issues with **20 issue labels**.
> - Make _community healthier_ with all the guides like code of conduct, contributing, support, security...
> - Learn more with the [official GitHub guide on creating repositories from a template](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-repository-from-a-template).
> - To start using it, click **[Use this template](https://github.com/IQKV/standard-maven-multi-project-layout/generate)** to create your new repository.

---

<a name="description"></a>

# 🚀 Description

Provided structure shows you how to create a multi-module project with Spring Boot.

> ##### 🛠️ Technology stack
>
> Java 25, Maven, Spring Boot

<a name="customization"></a>

## 🔧 GitHub Template Customization

Follow these steps to customize this template for your project:

1. **Repository Setup**:
   - Click the **[Use this template](https://github.com/IQKV/standard-maven-multi-project-layout/generate)** button
   - Name your repository and provide a description
   - Choose public or private visibility

2. **Project Renaming**:
   - Update `groupId`, `artifactId`, and `name` in the root `pom.xml`
   - Rename the `servicename` directory to match your service name
   - Update module references in the root `pom.xml`

3. **GitHub Configuration**:
   - Edit `.github/CODEOWNERS` to set code ownership
   - Update `.github/FUNDING.yml` with your sponsorship information
   - Customize issue templates in `.github/ISSUE_TEMPLATE/` as needed
   - Review and adjust GitHub Actions workflows in `.github/workflows/`

4. **Documentation**:
   - Update this README.md with your project details
   - Review and customize documentation in the `docs/` directory
   - Update LICENSE file if needed

5. **Development Setup**:
   - Customize Docker configuration in `compose.yaml`
   - Adjust `.devcontainer` settings if using dev containers

For more detailed customization instructions, see the [documentation](./template-docs/template/README.md).

<a name="instructions"></a>

## 📒 Instructions

> [!TIP]
>
> #### Install Prerequisites:
>
> - [Node LTS version](https://nodejs.org/en/blog/release/v22.15.0/), [pnpm](https://pnpm.io/installation), [Git](https://git-scm.com/), [Docker](https://www.docker.com/get-started/), [Docker Compose](https://docs.docker.com/compose/)
> - [OpenJDK LTS](https://aws.amazon.com/corretto/), [Maven](https://maven.apache.org/install.html)

### 🔺 Local development

```shell script
# Clone the repository
git clone https://github.com/IQKV/standard-maven-multi-project-layout.git multi-module-project

# Navigate to project directory
cd multi-module-project

# Install git hooks
pnpm install

# Start local dev services dependencies
docker compose -f compose.yaml up -d

#  Run the servicename instance in dev mode
cd servicename
mvn spring-boot:run -Dspring-boot.run.profiles=local -P dev

```

### ⚙️ Environment Variables

| Variable             | Description               | Default |
| -------------------- | ------------------------- | ------- |
| `LOGGING_LEVEL_ROOT` | Set default logging level | `INFO`  |

### 🔺 Tests

The application contains different test layers according to the [Test Pyramid](https://martinfowler.com/bliki/TestPyramid.html).

Unit tests are the base of the pyramid. They should make up the biggest part of an automated test suite.

To run JUnit tests, use:

```shell script
mvn clean test
```

The next layer, integration tests, test all places where your application serializes or deserializes data.
Application Service's REST API, Repositories, or calling third-party services are good examples.

Run this to enable integration tests, powered by testcontainers:

```shell script
mvn clean verify -P use-testcontainers
```

The minimum percentage of code coverage required for the workflow to pass is **80%**.

---

<a name="changelog"></a>

## 📆 Changelog

Conventional changelog located [here](CHANGELOG.md).

<a name="acknowledgments"></a>

## 👍 Acknowledgments

...

<a name="contributing"></a>

## 🙏 Community & Contributions

Please follow [Contributing](.github/CONTRIBUTING.md) page.

<a name="codeofconduct"></a>

## 📙 Code of Conduct

Please follow [Code of Conduct](.github/CODE_OF_CONDUCT.md) page.

<a name="troubleshooting"></a>

## 💥 Troubleshooting

...

<a name="license"></a>

## 📑 License

This project is licensed under the Apache License. See the [LICENSE](LICENSE) file for more details.

---

## _GitHub Project Tooling Overview_

### 🛠️ **Build & Package Management**

- **Java**: OpenJDK 21 LTS
- **Maven**: Multi-module project with Maven wrapper (`mvnw`)
- **Node.js**: LTS version (≥22.15.0) with **pnpm** package manager
- **Docker**: Container support with Docker Compose

### 📦 **Development Dependencies**

#### Code Quality & Formatting

- **Prettier**: Code formatting
  - Java plugin
  - Package.json plugin
- **Stylelint**: CSS/SCSS linting
- **ESLint**: JavaScript linting
- **EditorConfig**: Consistent coding styles
- **Cursor Rules**: Predefined code quality rules for Java development covering:
  - Maven best practices
  - Object-oriented design
  - Spring framework
  - Security guidelines
  - Performance optimization
  - Functional programming
  - Testing standards

#### Git Workflow & Versioning

- **Husky**: Git hooks management (v9.1.7)
- **lint-staged**: Run linters on staged files (v16.1.5)
- **Commitlint**: Conventional commit message validation
- **Commitizen**: Interactive commit message creation
- **release-it**: Automated versioning and releases (v19.0.4)
- **Conventional Changelog**: Automated changelog generation

### 🔄 **Available Scripts**

```shell script
# Code Quality
pnpm lint                # Run stylelint
pnpm lint:stylelint      # Run CSS/SCSS linting
pnpm prettier:check      # Check code formatting
pnpm prettier:write      # Format code

# Project Management
pnpm node_modules:clear  # Clean node modules
pnpm release            # Create automated release
pnpm prepare            # Set up husky hooks

# Maven Commands
mvn clean test             # Run unit tests
mvn clean verify -P use-testcontainers  # Run integration tests
mvn spring-boot:run        # Start application
```

### ⚙️ **GitHub Workflows**

- **Build Node.js Project**: Automated CI/CD pipeline
- **Check PR Title**: Validates pull request titles
- **Check Commit Message**: Validates conventional commits
- **Use Template**: Repository template setup

### 🔍 **Quality Gates**

- **Code Coverage**: Minimum 80% required
- **Pre-commit Hooks**: Automatic linting and formatting
- **Conventional Commits**: Enforced commit message format
- **Dependabot**: Automated dependency updates
- **Test Pyramid**: Unit tests + Integration tests (Testcontainers)

### 📋 **Project Structure Features**

- **7 Issue Templates**: Standardized issue reporting
- **20 Issue Labels**: Organized issue management
- **Community Files**: Code of conduct, contributing guide, security policy
- **Dev Container**: Consistent development environment
