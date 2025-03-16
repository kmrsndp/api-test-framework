# API Test Framework

A robust API testing framework built with REST Assured and TestNG for testing RESTful web services.

## Features

- Built with REST Assured for API testing
- TestNG for test execution and reporting
- Lombok for reducing boilerplate code
- Log4j2 for logging
- Extent Reports for beautiful HTML reports
- Modular and maintainable test structure
- Support for different environments through configuration

## Project Structure

```
src
├── main/java
│   └── com/apitest
│       ├── clients
│       │   └── RestClient.java
│       ├── config
│       │   └── ConfigManager.java
│       └── models
│           └── Todo.java
└── test/java
    └── com/apitest
        ├── base
        │   └── BaseTest.java
        └── tests
            ├── TodoTest.java
            ├── TodoCreateTest.java
            └── TodoUpdateTest.java
```

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Git

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/api-test-framework.git
   ```

2. Navigate to the project directory:
   ```bash
   cd api-test-framework
   ```

3. Run the tests:
   ```bash
   mvn clean test
   ```

## Test Cases

The framework includes tests for the following operations:

### Todo API Tests
1. Get Operations
   - Get all todos
   - Get todo by ID
   - Get todos by user ID
   - Get non-existent todo

2. Create Operations
   - Create todo with valid data
   - Create todo with missing required fields
   - Create todo with empty title

3. Update Operations
   - Update todo with all fields
   - Update non-existent todo
   - Update todo with invalid data

## Configuration

The framework uses a configuration file (`config.properties`) to manage environment-specific settings:

```properties
base.url=https://jsonplaceholder.typicode.com
```

## Reports

Test execution reports can be found in:
- TestNG HTML Report: `target/surefire-reports/index.html`
- JUnit XML Reports: `target/surefire-reports/junitreports/`

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 