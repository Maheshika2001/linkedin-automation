
package com.sqa.linkedin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(45)
        );
    }

    // Slow down browser actions
    protected void slowDown() {

        try {
            Thread.sleep(1200);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }

    protected WebElement firstDisplayed(By locator) {

        return wait.until(d -> {

            for (WebElement element : d.findElements(locator)) {

                if (element.isDisplayed()) {
                    return element;
                }
            }

            return null;
        });
    }

    protected WebElement clickable(By locator) {

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    protected void clickSlowly(By locator) {

        slowDown();

        clickable(locator).click();

        slowDown();
    }
}
