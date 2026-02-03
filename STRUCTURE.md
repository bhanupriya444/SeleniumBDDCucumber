# Project Structure Documentation

## Overview
This document explains the purpose and structure of each directory and file in the framework.

## Root Level Files

### pom.xml
- **Purpose**: Maven Project Object Model file
- **Contains**: 
  - Project dependencies (Selenium, Cucumber, TestNG, etc.)
  - Build plugins configuration
  - Project metadata
- **Edit When**: Adding new dependencies or changing project configuration

### testng.xml
- **Purpose**: TestNG suite configuration for regression tests
- **Contains**: Test suite definition with TestRunner class
- **Edit When**: Adding new test runners or changing suite structure

### testng-smoke.xml
- **Purpose**: TestNG suite configuration for smoke tests
- **Contains**: Test suite definition with SmokeTestRunner class
- **Edit When**: Creating specific test suites

### README.md
- **Purpose**: Main documentation file
- **Contains**: Complete setup and usage instructions
- **Edit When**: Adding new features or updating documentation

### QUICKSTART.md
- **Purpose**: Quick reference guide for new users
- **Contains**: Fast setup and common operations
- **Edit When**: Simplifying onboarding process

### .gitignore
- **Purpose**: Git ignore rules
- **Contains**: Patterns for files/folders to exclude from version control
- **Edit When**: Adding new generated files or IDE-specific files

### run-tests.bat / run-tests.sh
- **Purpose**: Helper scripts for running tests
- **Contains**: Interactive menu for test execution
- **Edit When**: Adding new test execution options

## Source Code Structure

### src/main/java/com/automation/

#### base/
- **Purpose**: Base classes for the framework
- **Usage**: Extend these for creating new components
- **Currently**: Reserved for future base implementations

#### constants/
- **Purpose**: Framework-wide constants
- **Usage**: Store unchanging values used across framework
- **Currently**: Reserved for constant definitions

#### managers/
- **Purpose**: Manager classes for framework services
- **Usage**: Centralized management of resources
- **Currently**: Reserved for service managers

### src/test/java/com/automation/

#### hooks/
**Purpose**: Cucumber hooks for setup and teardown

##### Hooks.java
- **@Before**: Initializes WebDriver before each scenario
- **@After**: Quits WebDriver and captures screenshots on failure
- **Edit When**: Adding global setup/teardown logic

#### pages/
**Purpose**: Page Object Model implementations

##### BasePage.java
- **Base class** for all page objects
- **Contains**: Common methods (click, enter text, wait, etc.)
- **Extend**: All page objects should extend this class

##### LoginPage.java
- **Represents**: Login page elements and actions
- **Contains**: Locators and methods specific to login page
- **Edit When**: Login page UI changes

##### ProductsPage.java
- **Represents**: Products listing page
- **Contains**: Methods for product interactions, cart, sorting
- **Edit When**: Products page UI changes

##### CartPage.java
- **Represents**: Shopping cart page
- **Contains**: Cart item management methods
- **Edit When**: Cart page UI changes

#### runners/
**Purpose**: Test runner classes for Cucumber

##### TestRunner.java
- **Runs**: All regression tests (@regression tag)
- **Configuration**: Plugin setup, report paths, options
- **Edit When**: Changing test execution settings

##### SmokeTestRunner.java
- **Runs**: Smoke tests only (@smoke tag)
- **Configuration**: Optimized for quick validation
- **Edit When**: Modifying smoke test configuration

#### stepdefinitions/
**Purpose**: Cucumber step definitions (glue code)

##### LoginStepDefinitions.java
- **Maps**: Gherkin steps to Java code for login feature
- **Contains**: Given/When/Then implementations for login
- **Edit When**: Adding new login scenarios

##### ProductsStepDefinitions.java
- **Maps**: Gherkin steps for products feature
- **Contains**: Product browsing and cart operations
- **Edit When**: Adding new product scenarios

#### utils/
**Purpose**: Utility and helper classes

##### ConfigReader.java
- **Reads**: Configuration from properties file
- **Provides**: Static methods to access config values
- **Edit When**: Adding new configuration parameters

##### DriverManager.java
- **Manages**: WebDriver lifecycle
- **Provides**: Driver initialization, configuration, cleanup
- **ThreadLocal**: Supports parallel execution
- **Edit When**: Adding new browser support or driver settings

## Test Resources

### src/test/resources/

#### config/
**Purpose**: Configuration files

##### config.properties
- **Contains**: Framework configuration (browser, URLs, timeouts)
- **Edit**: Change test environment settings here
- **Format**: Key-value pairs

##### extent-config.xml
- **Contains**: ExtentReports styling and configuration
- **Edit**: Customize report appearance
- **Format**: XML configuration

#### features/
**Purpose**: Cucumber feature files (Gherkin)

##### Login.feature
- **Contains**: Login functionality scenarios
- **Scenarios**: Valid login, invalid credentials, error messages
- **Tags**: @regression, @smoke, @positive, @negative

##### Products.feature
- **Contains**: Product management scenarios
- **Scenarios**: View products, add to cart, sorting
- **Tags**: @regression, @smoke, @cart, @sort

**Adding New Features**:
1. Create new .feature file
2. Write scenarios in Gherkin
3. Add appropriate tags
4. Run to generate step definition snippets

#### testdata/
**Purpose**: Test data files

##### testdata.json
- **Contains**: Test data in JSON format
- **Usage**: User credentials, product data
- **Edit**: Add new test data sets here

#### extent.properties
- **Purpose**: ExtentReports configuration
- **Contains**: Report paths, settings, system info
- **Edit**: Change report generation settings

#### log4j2.xml
- **Purpose**: Logging configuration
- **Contains**: Log appenders, patterns, log levels
- **Edit**: Modify logging behavior

## Generated Directories (Runtime)

### target/
**Generated during**: Maven build/test execution

#### cucumber-reports/
- **cucumber.html**: Cucumber HTML report
- **cucumber.json**: JSON report for CI/CD integration
- **cucumber.xml**: JUnit XML report

#### extent-reports/
- **ExtentReport.html**: Rich HTML report with charts
- **Screenshots**: Embedded in report for failed tests

#### logs/
- **automation.log**: Detailed execution logs
- **Rotates**: Based on size and date

#### surefire-reports/
- **TestNG reports**: XML and HTML format
- **Used by**: CI/CD tools for test results

#### screenshots/
- **Captured**: On test failures
- **Named**: By scenario and timestamp

## Key Design Patterns

### 1. Page Object Model (POM)
```
pages/
  ├── BasePage.java (Common methods)
  ├── LoginPage.java (Specific page)
  └── ProductsPage.java (Specific page)
```

### 2. Singleton Pattern
- **DriverManager**: Single WebDriver instance per thread

### 3. Factory Pattern
- **DriverManager**: Creates appropriate driver based on configuration

### 4. Builder Pattern
- **Step Definitions**: Build test scenarios step by step

## File Naming Conventions

- **Java Classes**: PascalCase (e.g., LoginPage.java)
- **Feature Files**: PascalCase (e.g., Login.feature)
- **Configuration Files**: lowercase with dots (e.g., config.properties)
- **Test Data**: lowercase (e.g., testdata.json)
- **Scripts**: lowercase with hyphens (e.g., run-tests.sh)

## Best Practices

### When Adding New Test:
1. Write feature file in `features/`
2. Create/update page object in `pages/`
3. Implement step definitions in `stepdefinitions/`
4. Add test data in `testdata/` if needed
5. Tag appropriately for selective execution

### When Modifying Framework:
1. Update relevant documentation files
2. Follow existing code structure and patterns
3. Add appropriate logging
4. Update configuration if needed
5. Test changes before committing

### When Troubleshooting:
1. Check `target/logs/automation.log`
2. Review ExtentReport for detailed steps
3. Check configuration in `config.properties`
4. Verify feature file syntax
5. Ensure step definitions match feature steps

## Maintenance

### Regular Updates:
- **Dependencies**: Update versions in pom.xml monthly
- **Documentation**: Keep README and docs current
- **Test Data**: Review and update test data quarterly
- **Configuration**: Adjust timeouts based on test environment

### Code Reviews:
- Check for code duplication
- Ensure proper error handling
- Verify logging is adequate
- Confirm assertions are meaningful

## Extension Points

### Easy to Add:
- New page objects (extend BasePage)
- New step definitions (use existing as template)
- New feature files (follow existing structure)
- New configuration parameters (add to ConfigReader)

### Requires More Work:
- New browser support (modify DriverManager)
- New reporting formats (update TestRunner)
- Database integration (add new manager class)
- API testing (add REST utilities)

---
**Last Updated**: Version 1.0.0
