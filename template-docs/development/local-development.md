# Local Development

This guide covers the local development workflow for this Spring Boot Multi-Module Project Template.

## Setup Local Environment

1. Clone the repository:

   ```bash
   git clone https://github.com/IQKV/your-repo-name.git
   cd your-repo-name
   ```

2. Install Git hooks:

   ```bash
   pnpm install
   ```

3. Start local dependencies:
   ```bash
   docker compose -f compose.yaml up -d
   ```

## Development Workflow

1. **Build the project**:

   ```bash
   ./mvnw clean install
   ```

2. **Run the application**:

   ```bash
   cd servicename
   ../mvnw spring-boot:run
   ```

3. **Run with specific profile**:
   ```bash
   ../mvnw spring-boot:run -Dspring-boot.run.profiles=dev
   ```

## Hot Reload

The project is configured with Spring Boot DevTools for hot reloading:

1. Make changes to your code
2. DevTools will automatically restart the application

## Debugging

1. Run with debug mode:

   ```bash
   ../mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
   ```

2. Connect your IDE debugger to port 5005
