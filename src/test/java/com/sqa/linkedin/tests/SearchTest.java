package com.sqa.linkedin.tests;

import com.sqa.linkedin.base.BaseTest;
import com.sqa.linkedin.pages.SearchPage;
import com.sqa.linkedin.utils.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test(description = "TC03 - searching shows a results page")
    public void searchShowsResults() {
        loginWithValidCredentials();
        String term = Config.searchTerm();
        SearchPage search = new SearchPage(driver).searchFor(term);

        String url = driver.getCurrentUrl().toLowerCase();
        String lower = term.toLowerCase();
        Assert.assertTrue(url.contains("/search/results"), "Expected a results URL but was " + url);
        Assert.assertTrue(url.contains(lower) || url.contains(lower.replace(" ", "%20"))
                        || url.contains(lower.replace(" ", "+")),
                "URL should contain the keyword: " + url);
        Assert.assertTrue(search.hasVisibleResult(), "At least one result link should be visible");
    }
}
