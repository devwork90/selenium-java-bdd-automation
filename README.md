# Sipho Selenium and Java BDD Test Automation

## Project Overview
This project is a BDD (Behavior Driven Development) test automation framework built using **Java**, **Selenium WebDriver**, and **Cucumber**. It is designed to automate web application tests with a modular, maintainable, and reusable architecture.

The project follows the **Page Object Model (POM)** design pattern combined with **Abstract Components** to ensure reusable components and clean separation of concerns between test logic and page interactions.

---

## Design Pattern

### 1. Page Object Model (POM)
- Each page of the application has a corresponding **Page Object class** in `src/main/java/BddBasedPackages/sipho_qa_demo/pageobjects/`.
- Page objects contain:
    - WebElements using `@FindBy`.
    - Methods for interacting with page elements (click, type, select, etc.).
- Example Pages:
    - `EmailDisclaimerPage.java`
    - `LandingPage.java`

### 2. Abstract Components
- Common components and reusable methods are defined in `AbstractComponent.java` inside `AbstractComponents` package.
- This allows shared behaviors (e.g., waiting, scrolling, clicking) to be inherited by multiple page classes.

### 3. Test Layer
- Tests are defined in **step definition classes** inside `stepDefinitions`.
    - Example: `EmailDisclaimerSteps.java`
- The **BaseTest.java** class handles test setup and teardown.
- `TestRunner.java` runs Cucumber feature files.

---

## Project Structure

│
├─ Drivers/ # Browser drivers (chromedriver, geckodriver, etc.)
├─ sipho_qa_demo/
│ ├─ src/main/java/
│ │ └─ BddBasedPackages/sipho_qa_demo/
│ │ ├─ AbstractComponents/ # Reusable abstract components
│ │ ├─ pageobjects/ # Page Object classes
│ │ └─ SecuritEase/ # Custom utilities or security-related scripts
│ │
│ ├─ test/java/
│ │ └─ BddBasedPackages/sipho_qa_demo/
│ │ ├─ TestComponent/ # BaseTest.java
│ │ ├─ stepDefinitions/ # Step definition classes
│ │ └─ testRunner/ # TestRunner.java
│ │
│ └─ resources/features/ # Feature files for BDD scenarios
│
├─ target/ # Compiled files and test results
├─ pom.xml # Maven dependencies and build configuration
└─ testng.xml # TestNG suite configuration


---

## Prerequisites
- Java 21 or above
- Maven
- Chrome or Firefox browser
- Browser drivers placed in the `Drivers/` folder
- IDE: IntelliJ IDEA, Eclipse, or similar

---

## Installation

1. Clone the repository:
```bash
git clone <repository-url>

## Challenges
Problem Summary

Your test script is designed to:

Click on a link in an HTML page labeled “Data Privacy Statement”.

Expect the browser to open a PDF whose URL contains shp-data-privacy.pdf.

Assert that the current URL matches the expected PDF URL.

However, every time the test runs, you get:

Actual URL: https://www.shopriteholdings.co.za/email-disclaimer.html


Instead of a PDF URL.
The test fails because the browser never navigates directly to the PDF.

Why This Happens
1. The link is not a direct PDF link

The HTML page you click (email-disclaimer.html) likely contains another HTML page with a link to the PDF, rather than linking directly to the PDF itself.
Many modern websites do this to control user experience, show disclaimers, or track analytics.
So, when Selenium clicks the link, the browser loads that intermediate HTML page — not the PDF file.

2. Modern browser PDF handling

Modern browsers such as Chrome, Edge, and Firefox do not treat PDFs as standard navigations:

PDFs may open in an internal PDF viewer or new tab.

Automation drivers often cannot detect new tabs or embedded PDF viewers.

driver.getCurrentUrl() may still return the parent page’s URL even when a PDF is displayed.

3. Selenium limitations with PDFs

Selenium is built for HTML DOM interaction, not for managing or asserting PDF content.
It cannot reliably detect when a PDF is opened or assert its URL if the browser handles it internally.
Conditions like ExpectedConditions.urlContains(".pdf") often fail for this reason.

Conclusion / Core Issue

This failure is not caused by a bug in your test logic, but by:

The link being an intermediate HTML page, not a direct PDF resource.

Browser PDF handling quirks in modern automation modes.

Selenium’s inherent limitation in detecting PDF navigations.

In short

Clicking a link that opens a PDF does not always trigger a detectable URL change in Selenium, so your test continues to see the original email-disclaimer.html URL.
