
package com.sqa.linkedin.base;

import com.sqa.linkedin.pages.LoginPage;
import com.sqa.linkedin.utils.Config;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;

public abstract class BaseTest {

    protected WebDriver driver;

    protected WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        if ("firefox".equalsIgnoreCase(Config.browser())) {

            FirefoxOptions options = new FirefoxOptions();

            options.setPageLoadStrategy(PageLoadStrategy.EAGER);

            driver = new FirefoxDriver(options);

        } else {

            ChromeOptions options = new ChromeOptions();

            // Faster page loading
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);

            options.addArguments(
                    "--start-maximized",
                    "--disable-notifications",
                    "--disable-extensions",
                    "--disable-popup-blocking",
                    "--disable-dev-shm-usage",
                    "--no-sandbox",
                    "--remote-allow-origins=*",
                    "--disable-background-networking",
                    "--disable-background-timer-throttling",
                    "--disable-renderer-backgrounding",
                    "--disable-features=Translate"
            );

            options.setExperimentalOption(
                    "excludeSwitches",
                    List.of("enable-automation")
            );

            driver = new ChromeDriver(options);
        }

        // Avoid unnecessary implicit waiting
        driver.manage().timeouts()
                .implicitlyWait(Duration.ZERO);

        // Set page loading timeout
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(30));

        // Explicit wait
        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );

        DriverManager.set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {

            driver.quit();

            driver = null;
        }

        DriverManager.remove();
    }

    /**
     * Login using configured credentials.
     * Manual CAPTCHA or verification is supported.
     */
    protected void loginWithValidCredentials() {

        LoginPage login = new LoginPage(driver);

        login.open();

        login.login(
                Config.username(),
                Config.password()
        );

        login.waitForFeed(
                Config.checkpointSeconds()
        );
    }
}
