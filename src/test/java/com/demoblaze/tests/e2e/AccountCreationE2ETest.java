package com.demoblaze.tests.e2e;

import com.demoblaze.config.DriverFactory;
import com.demoblaze.pages.AccountPage;
import com.demoblaze.utils.DataGenerator;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountCreationE2ETest {

    @Test
    public void testAccountCreationWithModal() {
        WebDriver driver = DriverFactory.getDriver();

        try {
            // Open home page
            driver.get("https://www.demoblaze.com"); // Website address

            // Initializing the registration form page
            AccountPage accountPage = new AccountPage(driver);

            // Generation of random data (login and password)
            String username = DataGenerator.generateRandomMail(); // Generating a random login
            String password = DataGenerator.generateRandomPassword(); // Generating a random password

            // Open registration modal
            accountPage.openSignUpModal();

            // Fill out the form in the modal
            accountPage.fillSignUpModal(username, password);

            // Submit form
            accountPage.submitSignUp();

            // Confirm pop-up
            accountPage.confirmSignUpPopup();

            // Check if the user returned to the home page after registration
            assertEquals("https://www.demoblaze.com/", driver.getCurrentUrl(), "Not returned to home page after registration.");
        } finally {
            // Terminate the browser
            DriverFactory.quitDriver();
        }
    }
}