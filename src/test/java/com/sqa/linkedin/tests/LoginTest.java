
package com.sqa.linkedin.tests;

import com.sqa.linkedin.base.BaseTest;
import com.sqa.linkedin.pages.LoginPage;
import com.sqa.linkedin.pages.SearchPage;
import com.sqa.linkedin.utils.Config;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "TC01 - valid login reaches the feed")
    public void validLogin() {

        loginWithValidCredentials();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/feed"),
                "Expected /feed but was "
                        + driver.getCurrentUrl()
        );

        Assert.assertTrue(
                new SearchPage(driver).isSearchBoxVisible(),
                "Search box should be visible after login"
        );
    }

    @Test(description = "TC02 - invalid password shows an error")
    public void invalidPassword() {

        LoginPage login = new LoginPage(driver);

        login.open();

        login.login(
                Config.username(),
                "WrongPassword#123"
        );

        boolean errorDisplayed =
                login.isErrorDisplayed();

        System.out.println(
                "Error Displayed: " + errorDisplayed
        );

        System.out.println(
                "Error Text: " + login.errorText()
        );

        Assert.assertTrue(
                errorDisplayed,
                "An error message should be displayed"
        );

        Assert.assertFalse(
                driver.getCurrentUrl().contains("/feed"),
                "User must not reach the feed"
        );
    }
}
