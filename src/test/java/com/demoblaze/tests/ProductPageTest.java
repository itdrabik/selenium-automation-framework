package com.demoblaze.tests;

import com.demoblaze.config.ConfigManager;
import com.demoblaze.pages.FirstProductPage;
import com.demoblaze.pages.SecondProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.testng.Assert.assertTrue;

public class ProductPageTest {
    private WebDriver driver;


    @BeforeEach
    public void setUp() {
        // Load data from config.properties
        String browser = ConfigManager.getProperty("browser");


        // Checking what browser we have in config
        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup(); // Install the appropriate version of the driver
            ChromeOptions options = new ChromeOptions(); // Options for Chrome
            driver = new ChromeDriver(options); // Create a ChromeDriver instance
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup(); // Install the appropriate version of the driver
            FirefoxOptions options = new FirefoxOptions(); // Options for Firefox
            driver = new FirefoxDriver(options); // Create a FirefoxDriver instance
        } else if ("edge".equalsIgnoreCase(browser)) {
            WebDriverManager.edgedriver().setup(); // Install the appropriate version of the driver
            EdgeOptions options = new EdgeOptions(); // Options for Edge
            driver = new EdgeDriver(options); // Create an EdgeDriver instance
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    @Test
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
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}