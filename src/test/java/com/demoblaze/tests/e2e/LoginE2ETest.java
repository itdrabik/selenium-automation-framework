package com.demoblaze.tests.e2e;

import com.demoblaze.config.DriverFactory;
import com.demoblaze.pages.AccountPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("User Management")
@Feature("Login")
public class LoginE2ETest {

    private static final String USERNAME = "testUser123blablabla";
    private static final String PASSWORD = "password123blablabla";

    @Test
    @Story("User logs in using existing account")
    @Description("This test verifies that a user can log in with an existing account and return to the home page.")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithExistingAccount() {
        WebDriver driver = DriverFactory.getDriver();

        try {
            openHomePage(driver);

            // Initialize account page
            AccountPage accountPage = new AccountPage(driver);

            // Perform login
            performLogin(accountPage, USERNAME, PASSWORD);

            // Verify user is redirected to the home page
            verifyUserIsOnHomePage(driver);

        } catch (Exception e) {
            attachScreenshot(driver);
            throw e;
        } finally {
            DriverFactory.quitDriver();
        }
    }

    @Step("Open the home page")
    private void openHomePage(WebDriver driver) {
        driver.get("https://www.demoblaze.com");
    }

    @Step("Log in with username: {0} and password: {1}")
    private void performLogin(AccountPage accountPage, String username, String password) {
        accountPage.openLoginModal();
        accountPage.fillLoginModal(username, password);
        accountPage.submitLogin();
    }

    @Step("Verify user is redirected to the home page")
    private void verifyUserIsOnHomePage(WebDriver driver) {
        assertEquals("https://www.demoblaze.com/", driver.getCurrentUrl(), "Not returned to home page after login.");
    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    private byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
