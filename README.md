# Spring Boot Todo List - Testing Playground

<!-- TODO: Replace YOUR_USERNAME and YOUR_REPOSITORY_NAME with your actual GitHub details -->

## Overview

This project serves as a practical learning platform focused on mastering software testing techniques within the Spring Boot ecosystem. It implements a basic **CRUD (Create, Read, Update, Delete) Todo List** application, providing a tangible codebase to explore and apply concepts beyond manual testing (like Postman).

The primary goal is to gain hands-on experience with:

- **Unit Testing:** Isolating and verifying individual components (especially service layer logic) using JUnit 5 and Mockito.
- **Integration Testing:** Validating the collaboration between different layers (Controller -> Service -> Repository -> Database) using modern tools like Testcontainers.
- **Containerization:** Packaging the Spring Boot application using Docker for consistent environments.
- **Continuous Integration (CI):** Automating the build and testing process using GitHub Actions.

## Features

The application provides a RESTful API for managing Todo items:

- ✅ **Create** a new Todo item with a description.
- ✅ **Retrieve** all Todo items.
- ✅ **Retrieve** a single Todo item by its ID.
- ✅ **Update** an existing Todo item's description or completion status (via PATCH).
- ✅ **Delete** a Todo item by its ID.

## Technology Stack

- **Backend Framework:** Spring Boot 3.x
- **Language:** Java 21
- **Build Tool:** Apache Maven
- **Web:** Spring Web (Tomcat embedded)
- **Data Persistence:** Spring Data JPA (Hibernate)
- **Database:** PostgreSQL (Designed for use with [Neon](https://neon.tech/) serverless Postgres)
- **Testing:**
  - JUnit 5
  - Mockito
  - AssertJ
  - Testcontainers (for PostgreSQL integration tests)
  - Spring Boot Test
- **Containerization:** Docker
- **CI/CD:** GitHub Actions
- **Utilities:** Lombok

## Prerequisites

Before you begin, ensure you have the following installed:

- **JDK 21:** Java Development Kit version 21 or later.
- **Apache Maven:** Build automation tool.
- **Git:** Version control system.
- **Docker:** Containerization platform (required for building/running the Docker image).
- **Neon Account:** A free account on [Neon](https://neon.tech/) to provision the serverless PostgreSQL database.

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY_NAME.git
cd YOUR_REPOSITORY_NAME
```
<!-- TODO: Replace YOUR_USERNAME and YOUR_REPOSITORY_NAME -->

### 2. Set Up Neon Database

- Go to Neon and create a new project (e.g., todo-list).
- Create a database within the project (e.g., todo).
- From the Neon dashboard, obtain your database connection string. It will look something like:

```
postgresql://<user>:<password>@<host>-pooler.region.aws.neon.tech/<dbname>?sslmode=require
```

### 3. Configure Application Properties

- Navigate to `src/main/resources/`.
- You will find `application.yml`. Edit this file to include your Neon database credentials.
- Update the `spring.datasource` section:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://<host>-pooler.region.aws.neon.tech:5432/<dbname>?sslmode=require # Replace with your Neon host and dbname
    username: <user>       # Replace with your Neon user
    password: <password>   # Replace with your Neon password
  # ... other properties
```

> 🚨 **SECURITY WARNING:** This project currently stores database credentials directly in `application.yml`. This is **NOT** secure and should **NEVER** be done in production or public repositories. For this learning exercise, it simplifies setup. In real applications, use environment variables, Spring Cloud Config, HashiCorp Vault, or other secrets management solutions.

### 4. Build and Run Locally

You can run the application using the Spring Boot Maven plugin:

```bash
./mvnw spring-boot:run
```

The application will start on [http://localhost:8080](http://localhost:8080). You can interact with the API using tools like Postman or curl (see API contract details in previous discussions or code).

### Running with Docker

A multi-stage Dockerfile is provided to build a lean container image.

**Build the Docker Image:**

```bash
docker build -t todo-list-backend:latest .
```

**Run the Docker Container:**

You need to provide the database credentials to the container, preferably via environment variables.

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://<host>-pooler.region.aws.neon.tech:5432/<dbname>?sslmode=require \
  -e SPRING_DATASOURCE_USERNAME=<user> \
  -e SPRING_DATASOURCE_PASSWORD=<password> \
  --name todo-app \
  todo-list-backend:latest
```
<!-- TODO: Replace placeholders with your actual Neon credentials -->

The application inside the container will be accessible at [http://localhost:8080](http://localhost:8080).

### Running Tests

The project includes both unit tests (using Mockito) and integration tests (using Testcontainers).

To run all tests:

```bash
./mvnw test
```

Or run the full verification lifecycle (includes tests):

```bash
./mvnw verify
```

## CI/CD Pipeline

This repository uses GitHub Actions for Continuous Integration. The workflow is defined in `.github/workflows/ci.yml`.

- **Trigger:** Runs automatically on every push and pull_request targeting the main branch.
- **Steps:**
  - Checks out the source code.
  - Sets up JDK 21.
  - Runs `mvn clean verify` to compile the code and execute all unit and integration tests.
- **Status:** The badge at the top of this README shows the current build and test status of the main branch.

## Learning Objectives Revisited

This project specifically aims to reinforce understanding of:

- 🧪 **Unit Testing:** Writing focused tests for service logic using Mockito for dependency isolation.
- 🔗 **Integration Testing:** Using Testcontainers to run tests against a real PostgreSQL database instance managed via Docker.
- 🐳 **Dockerization:** Creating efficient, multi-stage Docker images for Spring Boot applications.
- 🚀 **CI/CD:** Automating build and test validation using GitHub Actions.