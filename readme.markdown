# SDET Automation Framework

A robust, scalable test automation framework combining UI and API testing using Selenium WebDriver, Java, TestNG, and RestAssured. Designed for seamless execution via CLI, Docker, and Jenkins CI/CD pipelines.

---

## Framework Architecture

- **Selenium WebDriver**: UI automation of web application flows.
- **RestAssured**: API testing and validation.
- **TestNG**: Test orchestration, parallel execution, and reporting.
- **Maven**: Build and dependency management.
- **Page Object Model (POM)**: Encapsulation of UI elements for maintainability.
- **Data-Driven Testing**: Externalized test data for flexible test scenarios.
- **Docker**: Containerized test execution environment.
- **Jenkins**: Continuous Integration and execution orchestration.

---

## Tools & Technologies Used

| Tool/Technology | Purpose                         |
|-----------------|--------------------------------|
| Java            | Programming language            |
| Selenium WebDriver | UI automation                 |
| RestAssured     | API automation and validation  |
| TestNG          | Test execution and management  |
| Maven           | Project build and dependency   |
| Docker          | Containerization               |
| Jenkins         | CI/CD pipeline                 |
| Git             | Version control                |

---

## Features Supported

- Cross-browser UI testing (configurable via properties)
- Parallel test execution via TestNG
- API testing integrated with UI flows
- Data-driven testing with external CSV/Excel/JSON
- Dockerized test environment for consistent builds
- Jenkins pipeline for automated build & test execution
- Detailed HTML reports and logs

---

## Getting Started

### Prerequisites

- Java JDK 11 or higher
- Maven 3.x
- Docker (for containerized runs)
- Jenkins (optional, for CI/CD)
- Git

---

### Running Tests Locally via CLI

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/your-repo.git
   cd your-repo


2. Run all tests with Maven:
   
   mvn clean test

3.  Run tests with specific TestNG suite:
    mvn clean test -DsuiteXmlFile=testng.xml
