# LinkedIn UI Automation – IT3153 Assignment 04

Selenium WebDriver + TestNG + Maven (Java 17+), Page Object Model.
Automates: login (valid + invalid), search, and opening a search result.

## Prerequisites
- JDK 17 or later, Apache Maven 3.8+
- Google Chrome or Firefox (the driver is downloaded automatically by Selenium Manager)
- **Your own LinkedIn account** (a dedicated test account is recommended)

## 1. Configure credentials (never commit them)
Option A – file:
```
copy config.properties.example config.properties     (Windows)
cp config.properties.example config.properties        (macOS/Linux)
```
Edit `config.properties` and set `linkedin.username` and `linkedin.password`.
`config.properties` is listed in `.gitignore`.

Option B – environment variables:
```
set LINKEDIN_USERNAME=you@example.com && set LINKEDIN_PASSWORD=secret          (Windows cmd)
export LINKEDIN_USERNAME=you@example.com LINKEDIN_PASSWORD=secret               (macOS/Linux)
```

## 2. Run
```
mvn clean test                       # whole suite (browser from config.properties, default chrome)
mvn test -Dbrowser=firefox           # choose a browser
mvn test -Dtest=LoginTest#validLogin # a single test
```
> Note: when `-Dtest=` is used, Surefire ignores `testng.xml`, so the failure-screenshot listener
> is skipped. Run the full suite to get automatic screenshots.

## 3. Output
- Failed tests save a PNG automatically in `screenshots/` (via `ScreenshotListener`).
- Surefire reports: `target/surefire-reports/`.

## Project layout
```
src/test/java/com/sqa/linkedin
  base/       BaseTest (start/stop browser), DriverManager
  pages/      BasePage (explicit waits), LoginPage, SearchPage
  tests/      LoginTest, SearchTest, OpenResultTest
  listeners/  ScreenshotListener
  utils/      Config
src/test/resources/testng.xml
```

## Security & ethics
- Only use your own account. LinkedIn's User Agreement restricts automated access.
- CAPTCHAs / verification checkpoints are **not bypassed**. The login step waits up to
  `checkpoint.wait.seconds` (default 180 s) so you can complete the step manually in the browser.
- LinkedIn may block browser automation with Cloudflare anti-bot checks. If the page shows
  `Attention Required` or `Verify you are human`, the test cannot continue until the check is
  completed manually or the browser is run with a genuine user profile.
- Do not run the suite repeatedly in quick succession – this can trigger bot detection.

## Waits
All synchronisation uses `WebDriverWait` / `ExpectedConditions` (explicit waits). There is no `Thread.sleep`.
