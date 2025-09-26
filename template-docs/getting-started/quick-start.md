# Quick Start Guide

This guide will help you quickly get started with development using this Spring Boot Multi-Module Project Template.

## 1. Create Your Project from the Template

Click the "Use this template" button on the GitHub repository to create your own repository based on this template.

## 2. Clone and Setup

```bash
# Clone your new repository
git clone https://github.com/IQKV/your-repo-name.git

# Navigate to the project directory
cd your-repo-name

# Install git hooks
pnpm install

# Start local development dependencies
docker compose -f compose.yaml up -d
```

## 3. Customize the Project

1. Update the project information in the root `pom.xml`:

   - Change `groupId`, `artifactId`, and `name` to match your project
   - Update the `description` field

2. Rename the `servicename` module to match your service name:

   ```bash
   mv servicename your-service-name
   ```

3. Update the module reference in the root `pom.xml`:
   ```xml
   <modules>
       <module>your-service-name</module>
   </modules>
   ```

## 4. Build and Run

```bash
# Build the project
./mvnw clean install

# Run your service
cd your-service-name
../mvnw spring-boot:run
```

## 5. Access Your Application

Once running, your application will be available at:

- http://localhost:8080

## 6. Next Steps

- Add additional modules as needed
- Customize GitHub workflows in the `.github/workflows` directory
- Explore the Spring Modulith features for modular application development
- Check the [Architecture Documentation](../architecture/README.md) for more details on the project structure
