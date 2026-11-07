
package com.sqa.linkedin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage extends BasePage {

    private static final String LOGIN_URL =
            "https://www.linkedin.com/login";

    private final By usernameField = By.cssSelector(
            "input[autocomplete*='username'], " +
            "input[name='session_key'], input#username"
    );

    private final By passwordField =
            By.cssSelector("input[type='password']");

    private final By signInButton =
            By.xpath("//button[normalize-space()='Sign in']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {

        driver.get(LOGIN_URL);

        firstDisplayed(usernameField);
        slowDown();

        firstDisplayed(passwordField);
        slowDown();

        return this;
    }

    public void login(String username, String password) {

        WebElement user = firstDisplayed(usernameField);

        user.click();
        slowDown();

        user.clear();
        user.sendKeys(username);
        slowDown();

        WebElement pass = firstDisplayed(passwordField);

        pass.click();
        slowDown();

        pass.clear();
        pass.sendKeys(password);
        slowDown();

        WebElement button = wait.until(d -> {

            List<WebElement> buttons =
                    d.findElements(signInButton);

            for (WebElement element : buttons) {

                if (element.isDisplayed()
                        && element.isEnabled()) {

                    return element;
                }
            }

            return null;
        });

        slowDown();

        button.click();

        slowDown();
    }

    public void waitForFeed(int seconds) {

        new WebDriverWait(
                driver,
                Duration.ofSeconds(seconds)
        ).until(
                ExpectedConditions.urlContains("/feed")
        );
    }

    public boolean isErrorDisplayed() {

        try {

            return new WebDriverWait(
                    driver,
                    Duration.ofSeconds(15)
            ).until(d -> {

                String text = d.findElement(
                        By.tagName("body")
                ).getText().toLowerCase();

                return text.contains(
                        "wrong email or password"
                );
            });

        } catch (Exception e) {

            return false;
        }
    }

    public String errorText() {

        String text = driver.findElement(
                By.tagName("body")
        ).getText();

        if (text.toLowerCase().contains(
                "wrong email or password")) {

            return "Wrong email or password.";
        }

        return "Error message not found";
    }
}
