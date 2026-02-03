# Selenium BDD Framework with Cucumber

A comprehensive Behavior-Driven Development (BDD) test automation framework using Selenium WebDriver, Cucumber, and TestNG for functional testing.

## 🚀 Features

- **BDD Approach**: Write tests in Gherkin language for better collaboration
- **Page Object Model (POM)**: Maintainable and reusable page objects
- **Parallel Execution**: Support for parallel test execution
- **Multiple Browsers**: Chrome, Firefox, and Edge support
- **Automated Driver Management**: WebDriverManager handles driver binaries automatically
- **Comprehensive Reporting**: 
  - Cucumber HTML Reports
  - ExtentReports with screenshots
  - TestNG Reports
- **Logging**: Log4j2 integration for detailed logging
- **Configurable**: External configuration file for easy customization
- **Screenshot on Failure**: Automatic screenshot capture for failed scenarios
- **CI/CD Ready**: Maven-based project ready for Jenkins/GitLab CI integration

## 📋 Prerequisites

Before running this framework, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 11 or higher
  - Download from: https://www.oracle.com/java/technologies/downloads/
  - Verify installation: `java -version`

- **Apache Maven**: Version 3.6 or higher
  - Download from: https://maven.apache.org/download.cgi
  - Verify installation: `mvn -version`

- **IDE** (Optional but recommended):
  - IntelliJ IDEA
  - Eclipse
  - Visual Studio Code

- **Git** (Optional):
  - For version control
  - Download from: https://git-scm.com/

## 🛠️ Setup Instructions

### 1. Clone or Download the Project

```bash
# If using Git
git clone <repository-url>
cd selenium-bdd-framework

# OR download and extract the ZIP file
```

### 2. Install Dependencies

Navigate to the project directory and run:

```bash
mvn clean install -DskipTests
```

This will download all required dependencies defined in `pom.xml`.

### 3. Configure Test Properties

Edit the configuration file at `src/test/resources/config/config.properties`:

```properties
# Browser Configuration
browser=chrome              # Options: chrome, firefox, edge
headless=false             # Set to true for headless execution
implicit.wait=10           # Implicit wait in seconds
explicit.wait=15           # Explicit wait in seconds
page.load.timeout=30       # Page load timeout in seconds

# Application URL
app.url=https://www.saucedemo.com

# Screenshot Configuration
screenshot.on.failure=true
screenshot.on.pass=false
```

## 📂 Project Structure

```
selenium-bdd-framework/
│
├── src/
│   ├── main/java/
│   │   └── com/automation/
│   │       ├── base/              # Base classes
│   │       ├── constants/         # Constants
│   │       └── managers/          # Manager classes
│   │
│   └── test/
│       ├── java/com/automation/
│       │   ├── hooks/             # Cucumber hooks
│       │   │   └── Hooks.java
│       │   ├── pages/             # Page Object Models
│       │   │   ├── BasePage.java
│       │   │   ├── LoginPage.java
│       │   │   ├── ProductsPage.java
│       │   │   └── CartPage.java
│       │   ├── runners/           # Test runners
│       │   │   ├── TestRunner.java
│       │   │   └── SmokeTestRunner.java
│       │   ├── stepdefinitions/   # Step definitions
│       │   │   ├── LoginStepDefinitions.java
│       │   │   └── ProductsStepDefinitions.java
│       │   └── utils/             # Utility classes
│       │       ├── ConfigReader.java
│       │       └── DriverManager.java
│       │
│       └── resources/
│           ├── config/            # Configuration files
│           │   ├── config.properties
│           │   └── extent-config.xml
│           ├── features/          # Cucumber feature files
│           │   ├── Login.feature
│           │   └── Products.feature
│           ├── testdata/          # Test data files
│           ├── extent.properties  # ExtentReports config
│           └── log4j2.xml        # Logging configuration
│
├── target/                        # Build output directory
│   ├── cucumber-reports/          # Cucumber reports
│   ├── extent-reports/            # Extent reports
│   └── logs/                      # Log files
│
├── pom.xml                        # Maven project file
├── testng.xml                     # TestNG configuration
├── testng-smoke.xml              # Smoke test configuration
├── .gitignore                     # Git ignore file
└── README.md                      # This file
```

## 🏃 Running Tests

### Run All Regression Tests

```bash
mvn clean test
```

### Run Smoke Tests Only

```bash
mvn clean test -DsuiteXmlFile=testng-smoke.xml
```

### Run Tests with Specific Tags

Edit the `TestRunner.java` file and modify the `tags` parameter:

```java
@CucumberOptions(
    tags = "@smoke"  // Or "@regression", "@cart", etc.
)
```

### Run Tests in Headless Mode

Update `config.properties`:

```properties
headless=true
```

Then run:

```bash
mvn clean test
```

### Run Tests with Different Browser

Update `config.properties`:

```properties
browser=firefox  # Or chrome, edge
```

### Run from IDE

1. Right-click on `TestRunner.java` or `SmokeTestRunner.java`
2. Select "Run As" → "TestNG Test"

## 📊 Reports

After test execution, reports are generated in the following locations:

### 1. Cucumber HTML Report
- **Location**: `target/cucumber-reports/cucumber.html`
- **Description**: Standard Cucumber HTML report

### 2. ExtentReports
- **Location**: `target/extent-reports/ExtentReport.html`
- **Description**: Rich HTML report with charts and screenshots
- **Features**:
  - Dashboard with test statistics
  - Step-by-step execution details
  - Screenshots for failed tests
  - Execution timeline

### 3. TestNG Report
- **Location**: `target/surefire-reports/index.html`
- **Description**: TestNG execution report

### 4. Logs
- **Location**: `target/logs/automation.log`
- **Description**: Detailed execution logs

## 🏷️ Cucumber Tags

The framework supports the following tags for selective test execution:

- `@regression` - All regression tests
- `@smoke` - Critical smoke tests
- `@positive` - Positive test scenarios
- `@negative` - Negative test scenarios
- `@datadriven` - Data-driven tests
- `@cart` - Shopping cart related tests
- `@sort` - Sorting functionality tests

### Run Tests by Tag

```bash
# Run only smoke tests
mvn clean test -Dcucumber.filter.tags="@smoke"

# Run smoke and regression tests
mvn clean test -Dcucumber.filter.tags="@smoke or @regression"

# Run all except negative tests
mvn clean test -Dcucumber.filter.tags="not @negative"
```

## 📝 Writing New Tests

### 1. Create a Feature File

Create a new `.feature` file in `src/test/resources/features/`:

```gherkin
Feature: Checkout Process
  
  Scenario: Complete purchase successfully
    Given user has items in cart
    When user proceeds to checkout
    And user enters shipping information
    And user completes payment
    Then order should be confirmed
```

### 2. Create Page Object

Create a new page class in `src/test/java/com/automation/pages/`:

```java
public class CheckoutPage extends BasePage {
    @FindBy(id = "first-name")
    private WebElement firstNameField;
    
    public void enterFirstName(String firstName) {
        enterText(firstNameField, firstName);
    }
}
```

### 3. Create Step Definitions

Create step definition class in `src/test/java/com/automation/stepdefinitions/`:

```java
public class CheckoutStepDefinitions {
    private CheckoutPage checkoutPage = new CheckoutPage();
    
    @When("user enters shipping information")
    public void userEntersShippingInformation() {
        checkoutPage.enterFirstName("John");
        // Add more steps
    }
}
```

## 🔧 Troubleshooting

### Common Issues and Solutions

#### 1. Driver Executable Not Found
**Solution**: WebDriverManager should handle this automatically. If issues persist:
```bash
mvn clean install -U
```

#### 2. Tests Not Running
**Solution**: 
- Check if Java and Maven are properly installed
- Verify `testng.xml` path in `pom.xml`
- Ensure feature files have correct tags

#### 3. Browser Not Opening
**Solution**:
- Check browser is installed
- Update browser to latest version
- Try different browser in `config.properties`

#### 4. Import Errors in IDE
**Solution**:
- Run `mvn clean install`
- Refresh/reimport Maven project in IDE
- Check IDE Maven settings

#### 5. Cucumber Steps Not Found
**Solution**:
- Verify `glue` parameter in `@CucumberOptions` includes correct packages
- Check step definition methods match feature file steps

## 🚀 CI/CD Integration

### Jenkins

```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
    post {
        always {
            publishHTML([
                reportDir: 'target/extent-reports',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Report'
            ])
        }
    }
}
```

### GitLab CI

```yaml
test:
  script:
    - mvn clean test
  artifacts:
    paths:
      - target/extent-reports/
      - target/cucumber-reports/
    reports:
      junit: target/surefire-reports/TEST-*.xml
```

## 📚 Key Dependencies

| Dependency | Version | Purpose |
|------------|---------|---------|
| Selenium | 4.16.1 | Browser automation |
| Cucumber | 7.15.0 | BDD framework |
| TestNG | 7.8.0 | Test execution framework |
| WebDriverManager | 5.6.3 | Automatic driver management |
| ExtentReports | 5.1.1 | Test reporting |
| Log4j2 | 2.22.1 | Logging |

## 🤝 Best Practices

1. **Keep feature files simple and readable**
2. **Use Page Object Model for maintainability**
3. **Write reusable step definitions**
4. **Use meaningful tag names**
5. **Add proper logging for debugging**
6. **Take screenshots on failures**
7. **Keep configuration externalized**
8. **Write atomic and independent tests**
9. **Use assertions from TestNG/AssertJ**
10. **Follow naming conventions**

## 📞 Support

For issues or questions:
1. Check the troubleshooting section
2. Review logs in `target/logs/automation.log`
3. Check ExtentReports for detailed execution info

## 📄 License

This framework is open source and available for modification and distribution.

## 🔄 Version History

- **v1.0.0** - Initial release with core framework features

---

**Happy Testing! 🎉**
