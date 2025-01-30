package com.demoblaze.tests.e2e;

import com.demoblaze.config.DriverFactory;
import com.demoblaze.pages.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("E2E Testing")  // Main functionality
@Feature("Shopping Flow")  // Shopping test
public class AddToCartE2ETest {

    @Test
    @Story("User adds a product to the cart and completes the purchase")
    @Description("This test covers the full shopping flow: login, selecting a category, adding to cart, purchasing, and logout.")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddProductToCartAndPurchase() {
        WebDriver driver = DriverFactory.getDriver();
        try {
            openHomePage(driver);

            // Account login
            AccountPage accountPage = new AccountPage(driver);
            login(accountPage, "testUser123blablabla", "password123blablabla");

            // Move to the “Phones” category
            HomePage homePage = new HomePage(driver);
            homePage.selectCategory("Phones");

            // Clicking on the first product in the list
            homePage.clickFirstProduct();

            // Product page opens, add to cart
            ProductPage productPage = new ProductPage(driver);
            assertTrue(productPage.isPageLoaded(), "Product page did not load");
            productPage.addToCart();
            productPage.acceptPopup();

            // Continue to shopping cart
            CartPage cartPage = new CartPage(driver);
            cartPage.goToCart();
            assertTrue(cartPage.isProductInCart(), "Product not found in cart");

            // Order processing
            processOrder(cartPage);

            // Checking if the shopping cart is empty after purchase
            cartPage.goToCart();
            assertTrue(cartPage.isCartEmpty(), "Cart is not empty after purchase");

            // Logout
            logout(accountPage);

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

    @Step("Login with username: {0} and password: {1}")
    private void login(AccountPage accountPage, String username, String password) {
        accountPage.openLoginModal();
        accountPage.fillLoginModal(username, password);
        accountPage.submitLogin();
        assertTrue(accountPage.isLogoutButtonVisible(), "Login failed");
    }

    @Step("Process order")
    private void processOrder(CartPage cartPage) {
        cartPage.clickPlaceOrder();
        cartPage.fillOrderForm("John Doe", "USA", "New York", "123456789", "12", "2025");
        cartPage.clickPurchase();
        assertTrue(cartPage.isConfirmationVisible(), "Order confirmation modal not visible");
        cartPage.confirmPurchase();
    }

    @Step("Logout user")
    private void logout(AccountPage accountPage) {
        if (accountPage.isUserLoggedIn()) {
            accountPage.logout();
        } else {
            System.out.println("The user is NOT logged in! Unable to perform logout.");
        }
    }

    @Attachment(value = " Screenshot on Failure", type = "image/png")
    private byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
