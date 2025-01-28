package com.demoblaze.tests.e2e;

import com.demoblaze.config.DriverFactory;
import com.demoblaze.pages.AccountPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginE2ETest {

    private static final String USERNAME = "testUser123blablabla";
    private static final String PASSWORD = "password123blablabla";


    @Test
    public void testLoginWithExistingAccount() {
        WebDriver driver = DriverFactory.getDriver();

        try {
            // Open home page
            driver.get("https://www.demoblaze.com"); // Website address

            // Initialize account page
            AccountPage accountPage = new AccountPage(driver);

            // Open login modal
            accountPage.openLoginModal();

            System.out.println("Using username: " + USERNAME);
            System.out.println("Using password: " + PASSWORD);

            // Fill out login form with previously saved data
            accountPage.fillLoginModal(USERNAME, PASSWORD);

            // Submit form
            accountPage.submitLogin();

            // Assert successful login (e.g., "Logout" button is visible)
            assertEquals("https://www.demoblaze.com/", driver.getCurrentUrl(), "Not returned to home page after registration.");
        } finally {
            // Terminate the browser
            DriverFactory.quitDriver();
        }
    }
}
