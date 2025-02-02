# Selenium Automation Framework

This is a **Selenium-based test automation framework** built with **Java**, **JUnit 5**, and **WebDriverManager**.  
The framework is designed for **UI automation testing** and integrates **Allure Reporting** for test result
visualization.

## Project Structure

- `src/main/java/com/demoblaze/`
    - `config/` - Configuration management.
        - `ConfigManager.java` - Manages configuration settings.
        - `DriverFactory.java` - Handles WebDriver instance creation and management.
    - `pages/` - Page Object Model (POM) implementation.
        - `AbstractPage.java` - Base class for all page objects, defines common methods.
        - `AbstractProductPage.java` - Abstract class showcasing inheritance for product-related pages.
        - `AccountPage.java` - Represents the user account page.
        - `CartPage.java` - Represents the shopping cart page.
        - `FirstProductPage.java` - Specific page object for the first product.
        - `HomePage.java` - Represents the main landing page of the application.
        - `Page.java` - Interface demonstrating an alternative approach for handling page objects.
        - `ProductPage.java` - General page object for product details.
        - `SecondProductPage.java` - Alternative product page.
- `src/test/java/com/demoblaze/tests/`
    - `e2e/` - End-to-end UI tests.
        - `AccountCreationE2ETest.java` - Validates new user registration and account creation.
        - `AddToCartE2ETest.java` - Tests adding products to the cart and updating total price.
        - `LoginE2ETest.java` - Ensures correct login/logout functionality and session persistence.
    - `CartPageTest.java` - Integration test for validating cart functionalities.
    - `HomePageTest.java` - Integration test for homepage components and navigation.
    - `ProductPageTest.java` - Integration test for product details, filtering, and sorting.
    - `utils/`
        - `DataGenerator.java` - Utility class for generating test data dynamically.
- `src/main/resources/` - Configuration files, if needed.
- `target/` - Compiled output files and generated reports.
- `.gitignore` - Defines ignored files for Git.
- `pom.xml` - Maven configuration for dependencies and plugins.
- `README.md` - Project documentation.

## Features

- **Selenium WebDriver** for UI automation.
- **JUnit 5** for test execution.
- **WebDriverManager** for automated driver setup.
- **Page Object Model (POM)** with **abstraction**, **inheritance**, and **interfaces**.
- **E2E (End-to-End) tests** covering real user interactions.
- **Unit tests** for validating specific functionalities.
- **Allure Reporting** for test result visualization.
- **Polymorphism** applied to demonstrate different OOP techniques.
- **Encapsulation** for managing WebDriver and configurations.
- **Maven** for dependency and build management.

## Installation & Setup

1. Clone the repository:
   ```sh
   git clone https://github.com/itdrabik/selenium-automation-framework.git
   cd selenium-automation-framework
   ```

2. Install dependencies using Maven:
   ```sh
   mvn clean install
   ```

3. Run the tests:
   ```sh
   mvn test
   ```

4. Generate and serve Allure reports:
   ```sh
   allure serve target/allure-results
   ```

## Test Reporting

- Reports can be accessed via:
  ```sh
  allure serve target/allure-results
  ```
- Results are stored in the `target/allure-results/` directory.

## Design Patterns & OOP Principles

- **Page Object Model (POM)** Organizes UI elements and interactions in separate classes.
- **Polymorphism** - Implemented through method overriding and interfaces.
- **Abstract Classes** - Used in AbstractPage and AbstractProductPage to enforce structure.
- **Interfaces** - Demonstrates how different implementations can be handled.
- **Encapsulation** - Ensures WebDriver and configuration settings are handled in a structured way.