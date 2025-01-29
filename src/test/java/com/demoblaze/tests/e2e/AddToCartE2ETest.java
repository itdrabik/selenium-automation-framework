package com.demoblaze.tests.e2e;

import com.demoblaze.config.DriverFactory;
import com.demoblaze.pages.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddToCartE2ETest {

    @Test
    public void testAddProductToCartAndPurchase() {
        WebDriver driver = DriverFactory.getDriver();
        try {
            driver.get("https://www.demoblaze.com");

            // Account login
            AccountPage accountPage = new AccountPage(driver);
            accountPage.openLoginModal();
            accountPage.fillLoginModal("testUser123blablabla", "password123blablabla");
            accountPage.submitLogin();
            assertTrue(accountPage.isLogoutButtonVisible(), "Login failed");

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
            cartPage.clickPlaceOrder();
            cartPage.fillOrderForm("John Doe", "USA", "New York", "123456789", "12", "2025");
            cartPage.clickPurchase();
            assertTrue(cartPage.isConfirmationVisible(), "Order confirmation modal not visible");
            cartPage.confirmPurchase();

            // Checking if the shopping cart is empty after purchase
            cartPage.goToCart();
            assertTrue(cartPage.isCartEmpty(), "Cart is not empty after purchase");

            // Logout
            if (accountPage.isUserLoggedIn()) {
                accountPage.logout();
            } else {
                System.out.println("The user is NOT logged in! Unable to perform logout.");
            }

        } finally {
            DriverFactory.quitDriver();
        }
    }
}
