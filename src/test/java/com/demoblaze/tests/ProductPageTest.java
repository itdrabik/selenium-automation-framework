package com.demoblaze.tests;

import com.demoblaze.pages.FirstProductPage;
import com.demoblaze.pages.SecondProductPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import com.demoblaze.config.DriverFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Product Page Tests")
@Feature("Verification of Product Page Elements")
public class ProductPageTest {

    private WebDriver driver;


    @BeforeEach
    @Step("Setup browser for product page tests")
    public void setUp() {
       driver = DriverFactory.getDriver();
       driver.get(driver.getCurrentUrl());



    }

    @Test
    @Story("Verify elements on the first product page")
    @Description("Check if all necessary elements (title, price, description, 'Add to Cart' button, specific feature, and image) are visible on the first product page.")
    @Severity(SeverityLevel.CRITICAL)
    public void testFirstProductPageElements() {
        driver.get("https://www.demoblaze.com/prod.html?idp_=1"); // URL for the first product

        FirstProductPage firstProductPage = new FirstProductPage(driver);

        assertTrue(firstProductPage.isProductTitleVisible(), "Product title should be visible");
        assertTrue(firstProductPage.isProductPriceVisible(), "Product price should be visible");
        assertTrue(firstProductPage.isProductDescriptionVisible(), "Product description should be visible");
        assertTrue(firstProductPage.isAddToCartButtonVisible(), "'Add to Cart' button should be visible");
        assertTrue(firstProductPage.isSpecificFeatureVisible(), "Specific feature for FirstProductPage should be visible");
        assertTrue(firstProductPage.isPictureVisible(), "Product picture should be visible");
    }

    @Test
    @Story("Verify elements on the second product page")
    @Description("Check if all necessary elements (title, price, description, 'Add to Cart' button, specific feature, and image) are visible on the second product page.")
    @Severity(SeverityLevel.CRITICAL)
    public void testSecondProductPageElements() {
        driver.get("https://www.demoblaze.com/prod.html?idp_=2"); // URL for the second product

        SecondProductPage secondProductPage = new SecondProductPage(driver);

        assertTrue(secondProductPage.isProductTitleVisible(), "Product title should be visible");
        assertTrue(secondProductPage.isProductPriceVisible(), "Product price should be visible");
        assertTrue(secondProductPage.isProductDescriptionVisible(), "Product description should be visible");
        assertTrue(secondProductPage.isAddToCartButtonVisible(), "'Add to Cart' button should be visible");
        assertTrue(secondProductPage.isSpecificFeatureVisible(), "Specific feature for SecondProductPage should be visible");
        assertTrue(secondProductPage.isPictureVisible(), "Product picture should be visible");
    }

    @AfterEach
    @Step("Close the browser after product page test")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
