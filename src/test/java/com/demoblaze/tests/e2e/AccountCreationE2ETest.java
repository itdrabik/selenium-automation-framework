package com.demoblaze.tests.e2e;

import com.demoblaze.config.DriverFactory;
import com.demoblaze.pages.AccountPage;
import com.demoblaze.utils.DataGenerator;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("User Management")  // Main functionality
@Feature("Account Creation")  // Test category
public class AccountCreationE2ETest {

    @Test
    @Story("User signs up using the registration modal")
    @Description("This test verifies that a user can create a new account via the Sign-Up modal and return to the home page.")
    @Severity(SeverityLevel.CRITICAL)
    public void testAccountCreationWithModal() {
        WebDriver driver = DriverFactory.getDriver();

        try {
            // Open the home page
            openHomePage(driver);

            // Initializing the registration form page
            AccountPage accountPage = new AccountPage(driver);

            // Generation of random data (login and password)
            String username = DataGenerator.generateRandomMail(); // Generating a random login
            String password = DataGenerator.generateRandomPassword(); // Generating a random password

            // Open registration modal and fill the form
            performAccountRegistration(accountPage, username, password);

            // Check if the user returned to the home page after registration
            verifyUserReturnedToHomePage(driver);

        } catch (Exception e) {
            // Capture screenshot on failure
            attachScreenshot(driver);
            throw e;
        } finally {
            // Terminate the browser
            DriverFactory.quitDriver();
        }
    }

    @Step("Open the home page")
    private void openHomePage(WebDriver driver) {
        driver.get("https://www.demoblaze.com");
    }

    @Step("Register new account with username: {0} and password: {1}")
    private void performAccountRegistration(AccountPage accountPage, String username, String password) {
        accountPage.openSignUpModal();  // Open Sign-Up modal
        accountPage.fillSignUpModal(username, password);  // Fill username and password
        accountPage.submitSignUp();  // Submit the form
        accountPage.confirmSignUpPopup();  // Confirm the pop-up
    }

    @Step("Verify user returned to the home page")
    private void verifyUserReturnedToHomePage(WebDriver driver) {
        assertEquals("https://www.demoblaze.com/", driver.getCurrentUrl(), "Not returned to home page after registration.");
    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    private byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
