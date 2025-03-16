# API Test Framework

A Java-based API testing framework using Rest Assured and TestNG for testing JSONPlaceholder APIs.

## Project Structure

```
src/
├── main/java/com/apitest/
│   ├── config/         # Configuration management
│   ├── constants/      # Constants and enums
│   ├── models/         # POJO classes for API responses
│   ├── utils/          # Utility classes
│   └── clients/        # API clients
└── test/java/com/apitest/
    ├── base/           # Base test classes
    └── tests/          # Test classes
```

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## Setup

1. Clone the repository
2. Install dependencies:
```bash
mvn clean install
```

## Running Tests

To run all tests:
```bash
mvn clean test
```

To run a specific test class:
```bash
mvn clean test -Dtest=TodoTest
```

## Test Reports

Test reports are generated in the `test-output/reports` directory after test execution.

## Configuration

Configuration properties are stored in `src/main/resources/config.properties`. Update this file to modify:
- Base URL
- API endpoints
- Timeouts
- Report configuration 