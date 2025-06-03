> ## 🤔 What is this template all about?
>
> - This template can be used as a base layer for a multi-module Maven projects.
> - Make project easy to maintain with **7 issue templates**.
> - Quick start documentation with an extraordinary README structure.
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
> Java 21, Maven, Spring Boot

<a name="instructions"></a>

## 📒 Instructions

> [!TIP]
>
> #### Install Prerequisites:
>
> - [Node LTS version](https://nodejs.org/en/blog/release/v22.15.0/), [Git](https://git-scm.com/), [Docker](https://www.docker.com/get-started/), [Docker Compose](https://docs.docker.com/compose/)
> - [OpenJDK LTS](https://aws.amazon.com/corretto/), [Maven](https://maven.apache.org/install.html)

### 🔺 Local development

```bash
# Clone the repository
git clone https://github.com/IQKV/standard-maven-multi-project-layout.git multi-module-project

# Navigate to project directory
cd multi-module-project

# Install git hooks
npm install

# Start local dev services dependencies
docker compose -f compose.yaml up -d

#  Run the application in dev mode
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

```bash
mvn clean test
```

The next layer, integration tests, test all places where your application serializes or deserializes data.
Application Service's REST API, Repositories, or calling third-party services are good examples.

Run this to enable integration tests, powered by testcontainers:

```bash
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

Please, follow [Contributing](.github/CONTRIBUTING.md) page.

<a name="codeofconduct"></a>

## 📙 Code of Conduct

Please, follow [Code of Conduct](.github/CODE_OF_CONDUCT.md) page.

<a name="troubleshooting"></a>

## 💥 Troubleshooting

...

<a name="license"></a>

## 📑 License

This project is licensed under the Apache License. See the [LICENSE](LICENSE) file for more details.
