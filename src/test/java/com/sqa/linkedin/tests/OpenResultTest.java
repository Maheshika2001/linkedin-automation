package com.sqa.linkedin.tests;

import com.sqa.linkedin.base.BaseTest;
import com.sqa.linkedin.pages.SearchPage;
import com.sqa.linkedin.utils.Config;

import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenResultTest extends BaseTest {

    @Test(description = "TC04 - Open first search result")
    public void openFirstResult() {

        // Step 1: Login
        loginWithValidCredentials();

        // Step 2: Get search term
        String term = Config.searchTerm();

        // Step 3: Search
        SearchPage search = new SearchPage(driver);
        search.searchFor(term);

        // Step 4: Verify search results
        Assert.assertTrue(
                search.hasVisibleResult(),
                "Search results should be visible"
        );

        // Step 5: Click first profile result
        search.clickFirstResult();

        // Step 6: Wait for profile page
        search.waitForProfile();

        // Step 7: Verify profile URL
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/in/"),
                "Profile page should be opened"
        );
    }
}