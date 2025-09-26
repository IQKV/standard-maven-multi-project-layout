# Installation Guide

This guide will walk you through the process of setting up the Spring Boot Multi-Module Project Template.

## Clone the Repository

```bash
# Clone the repository
git clone https://github.com/IQKV/standard-maven-multi-project-layout.git my-project

# Navigate to project directory
cd my-project
```

## Install Git Hooks

The project uses Git hooks to ensure code quality. Install them with:

```bash
pnpm install
```

## Start Local Development Services

The project includes Docker Compose configuration for local development dependencies:

```bash
docker compose -f compose.yaml up -d
```

## Build the Project

Build the entire project using Maven:

```bash
./mvnw clean install
```

## Run the Service

To run the main service:

```bash
cd servicename
../mvnw spring-boot:run
```

## Next Steps

After installation, check out the [Quick Start Guide](./quick-start.md) to begin development.
