
package com.sqa.linkedin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends BasePage {

    private final By searchBox = By.cssSelector(
            "input[data-testid='typeahead-input'], " +
            "input[placeholder='Search'], " +
            "input.search-global-typeahead__input"
    );

    private final By resultLinks = By.cssSelector(
            "a[href*='/in/'], a[href*='/company/'], a[href*='/school/']"
    );

    private final By peopleFilter = By.xpath(
            "//button[normalize-space()='People'] | " +
            "//a[normalize-space()='People']"
    );

    private final By profileLinks = By.cssSelector("a[href*='/in/']");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    // Always find a fresh Search Box
    private WebElement getSearchBox() {
        return wait.until(d -> {
            for (WebElement e : d.findElements(searchBox)) {
                try {
                    if (e.isDisplayed() && e.isEnabled()) {
                        return e;
                    }
                } catch (StaleElementReferenceException ignored) {
                    // Try locating the element again
                }
            }
            return null;
        });
    }

    public boolean isSearchBoxVisible() {
        return getSearchBox().isDisplayed();
    }

    public SearchPage searchFor(String term) {

        // Click using a fresh element
        wait.until(d -> {
            try {
                WebElement box = getSearchBox();
                box.click();
                return true;
            } catch (StaleElementReferenceException ignored) {
                return false;
            }
        });

        // Locate again before entering the search term
        wait.until(d -> {
            try {
                WebElement box = getSearchBox();
                box.clear();
                box.sendKeys(term);
                box.sendKeys(Keys.ENTER);
                return true;
            } catch (StaleElementReferenceException ignored) {
                return false;
            }
        });

        wait.until(ExpectedConditions.urlContains("/search/results"));

        return this;
    }

    public boolean hasVisibleResult() {
        return wait.until(d -> {
            for (WebElement e : d.findElements(resultLinks)) {
                try {
                    if (e.isDisplayed()) {
                        return true;
                    }
                } catch (StaleElementReferenceException ignored) {
                    // DOM changed; retry
                }
            }
            return false;
        });
    }

    public SearchPage openPeopleTab() {
        wait.until(d -> {
            try {
                WebElement button = clickable(peopleFilter);
                button.click();
                return true;
            } catch (StaleElementReferenceException ignored) {
                return false;
            }
        });

        wait.until(ExpectedConditions.urlContains("/search/results/people"));
        return this;
    }

    private WebElement firstProfileLink() {
        return wait.until(d -> {
            for (WebElement e : d.findElements(profileLinks)) {
                try {
                    String href = e.getAttribute("href");

                    if (e.isDisplayed()
                            && href != null
                            && href.contains("/in/")) {
                        return e;
                    }
                } catch (StaleElementReferenceException ignored) {
                    // Locate again
                }
            }
            return null;
        });
    }

    public String firstResultHref() {
        return firstProfileLink().getAttribute("href");
    }

     public void clickFirstResult() {

    wait.until(driver -> {
        try {
            for (WebElement element : driver.findElements(profileLinks)) {

                if (element.isDisplayed()
                        && element.isEnabled()
                        && element.getAttribute("href") != null
                        && element.getAttribute("href").contains("/in/")) {

                    element.click();
                    return true;
                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            return false;
        }

        return false;
    });
}
         
          public void waitForProfile() {
        wait.until(d ->
                d.getCurrentUrl().contains("/in/")
                && !d.getCurrentUrl().contains("/search/")
        );
    }

}