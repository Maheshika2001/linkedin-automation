# LinkedIn UI Automation – IT3153 Assignment 04

## 1. Project Description

This project is an automated UI testing suite developed using Java, Selenium WebDriver, TestNG and Maven.

The project automates four LinkedIn test cases:

- Successful login
- Login with an invalid password
- Keyword searching
- Opening the first search result

The Page Object Model (POM) design pattern is used to improve code readability, reusability and maintainability.

## 2. Technologies Used

- Java 21
- Selenium WebDriver 4.52.0
- TestNG 7.10.2
- Apache Maven
- Google Chrome
- Selenium Manager
- Visual Studio Code

## 3. Project Structure

```text
linkedin-automation/
│
├── screenshots/
├── src/
│   └── test/
│       ├── java/
│       │   └── com/sqa/linkedin/
│       │       ├── base/
│       │       ├── listeners/
│       │       ├── pages/
│       │       ├── tests/
│       │       └── utils/
│       │
│       └── resources/
│           └── testng.xml
│
├── .gitignore
├── config.properties
├── pom.xml
└── README.md
```

## 4. Prerequisites

Install the following before running the project:

1. Java JDK 17 or later.
2. Apache Maven.
3. Google Chrome browser.
4. Visual Studio Code or another Java IDE.

Selenium Manager automatically manages the required browser driver.

## 5. Configure LinkedIn Credentials

The project uses a `Config.java` class to read LinkedIn credentials.

Credentials can be configured using either:

- A local `config.properties` file.
- Environment variables.

### Option 1: Using config.properties

Create a file named `config.properties` in the project root directory (same location as pom.xml).

Add the following:

```properties
linkedin.username=YOUR_LINKEDIN_EMAIL
linkedin.password=YOUR_LINKEDIN_PASSWORD

browser=chrome
search.term=software testing
timeout.seconds=45
checkpoint.wait.seconds=180
```

Replace the example username and password with your own LinkedIn account credentials.

### Option 2: Using Environment Variables

In Windows PowerShell, run:

```powershell
$env:LINKEDIN_USERNAME="your_email@example.com"
$env:LINKEDIN_PASSWORD="your_password"
```

Environment variables take priority over credentials in `config.properties`.

### Security Note

Never upload real credentials to GitHub.

The `config.properties` file must be included in `.gitignore`.

## 6. How to Run the Project

### Step 1: Clone the Repository

```bash
git clone https://github.com/Maheshika2001/linkedin-automation.git
```

### Step 2: Open the Project Directory

```bash
cd linkedin-automation
```

### Step 3: Configure Credentials

Create and configure the local `config.properties` file as explained in Section 5.

### Step 4: Run All Tests

```bash
mvn clean test
```

### Step 5: Run Individual Tests

Login tests:

```bash
mvn clean -Dtest=LoginTest test
```

Search test:

```bash
mvn clean -Dtest=SearchTest test
```

Open search result test:

```bash
mvn clean -Dtest=OpenResultTest test
```

## 7. Test Cases

| Test ID | Description |
|---|---|
| TC01 | Successful LinkedIn login |
| TC02 | Login with an invalid password |
| TC03 | Search for a keyword |
| TC04 | Open the first search result |

## 8. Screenshots and Test Reports

The project uses `ScreenshotListener` to automatically capture screenshots when a test fails.

Screenshots are saved in the `screenshots/` directory.

Test reports are generated in:

```text
target/surefire-reports/
```

## 9. Security and Ethical Considerations

- Only the author's own LinkedIn account is used.
- Credentials are kept private.
- CAPTCHA and security verification steps are not bypassed.
- Security checkpoints must be completed manually.
- Automated testing is performed responsibly.

## 10. Final Test Execution

The final full test execution on 7 November 2026 completed successfully.

```text
Tests run: 4
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

## 11. Author Information

Name: BGMM Banagala

Index Number: D/BIT/24/0081

University: General Sir John Kotelawala Defence University

Module: IT3153 – Software Quality Assurance

Assignment: Assignment 04