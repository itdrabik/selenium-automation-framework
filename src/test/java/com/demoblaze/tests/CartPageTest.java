package com.demoblaze.tests;

import com.demoblaze.config.ConfigManager;
import com.demoblaze.pages.CartPage;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartPageTest {


    private WebDriver driver;
    private CartPage cartPage;

    @BeforeEach
    public void setUp() {
        // Load data from config.properties
        String browser = ConfigManager.getProperty("browser");
        String url = "https://www.demoblaze.com/cart.html";


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

        // Open the page from the URL taken from the configuration file
        driver.get(url);

        cartPage = new CartPage(driver);
    }

    //The functional test testCartPageTitle, which uses a method from the CartPage class, verifies whether the title of the cart page is correct.
    @Test
    public void testCartPageTitle() {
        String title = cartPage.getPageTitle();
        assertEquals("STORE", title);
    }

    @Test
    public void testLoginButtonClick() {
        cartPage.clickLoginButton();
        assertTrue(cartPage.isLoginModalVisible(), "The login window should be visible");
    }

    @Test
    public void testLoginButtonVisible() {
        assertTrue(cartPage.isElementVisible("login2"), "The login button should be visible");
    }

    @Test
    public void testSignUpButtonClick() {
        cartPage.clickSignUpButton();
        assertTrue(cartPage.isSignUpModalVisible(), "The registration window should be visible");
    }

    @Test
    public void testSignUpButtonVisible() {
        assertTrue(cartPage.isElementVisible("signin2"), "The registration button should be visible");
    }

    //A functional test that verifies that the navigateToPage() method correctly redirects the user to the correct URL from cartPage.getPageUrl()
    @Test
    public void testNavigateToPage() {
        cartPage.navigateToPage();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = cartPage.getPageUrl(); // URL obtained from the CartPage class
        assertEquals(expectedUrl, currentUrl, "The page URL should match.");
    }

    //The functional test testPageContent verifies whether the page contains the expected content, "PRODUCT STORE," by checking the page's source code.
    @Test
    public void testPageContent() {
        String expectedContent = "PRODUCT STORE";
        String bodyText = driver.getPageSource();
        assertTrue(bodyText.contains(expectedContent), "Page content should contain 'PRODUCT STORE'");
    }

    //Functional test to see if the page loads
    @Test
    public void testPageLoaded() {
        assertTrue(cartPage.isPageLoaded(), "The cartpage should be loaded successfully.");
    }

    //Functional test that verifies that the text “Products” should be visible
    @Test
    public void testIsPageElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"page-wrapper\"]/div/div[1]/h2"), "'Products' text should be visible");
    }

    //Functional test that verifies that the text “Pic” should be visible
    @Test
    public void testIsPicElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"page-wrapper\"]/div/div[1]/div/table/thead/tr/th[1]"), "'Pic' text should be visible");
    }

    //Functional test that verifies that the text “Title” should be visible
    @Test
    public void testIsTitleElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"page-wrapper\"]/div/div[1]/div/table/thead/tr/th[2]"), "'Title' text should be visible");
    }

    //Functional test that verifies that the text “Price” should be visible
    @Test
    public void testIsPriceElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"page-wrapper\"]/div/div[1]/div/table/thead/tr/th[3]"), "'Price' text should be visible");
    }

    //Functional test that verifies that the text “x” should be visible
    @Test
    public void testIsXElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"page-wrapper\"]/div/div[1]/div/table/thead/tr/th[4]"), "'x' text should be visible");
    }

    //Functional test that verifies that the text “Total” should be visible
    @Test
    public void testIsTotalElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"page-wrapper\"]/div/div[2]/h2"), "'Total' text should be visible");
    }

    //Functional test that verifies that the "Place Order" button should be visible
    @Test
    public void testIsPlaceOrderButtonElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"page-wrapper\"]/div/div[2]/button"), "'Place Order' button should be visible");
    }

    //Functional test that verifies that the "About Us" text is visible
    @Test
    public void testIsAboutUsTextVisibleOnPage() {
        assertTrue(cartPage.isTextPresent("We believe performance needs to be validated at every stage of the software development cycle and our\n" +
                "              open source compatible, massively scalable platform makes that a reality."), "Text should be visible on the page.");
    }

    //Functional test that verifies that the address text is visible
    @Test
    public void testIsAddressTextVisibleOnPage() {
        assertTrue(cartPage.isTextPresent("Address: 2390 El Camino Real"), "Text should be visible on the page.");
    }

    //Functional test that verifies that the phone text is visible
    @Test
    public void testIsPhoneTextVisibleOnPage() {
        assertTrue(cartPage.isTextPresent("Phone: +440 123456"), "Text should be visible on the page.");
    }

    //Functional test that verifies that the address text is visible
    @Test
    public void testIsEmailTextVisibleOnPage() {
        assertTrue(cartPage.isTextPresent("Email: demo@blazemeter.com "), "Text should be visible on the page.");
    }

    //Functional test that verifies that LOGO should be visible
    @Test
    public void testIsLOGOElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"nava\"]/img"), "'LOGO should be visible");
    }

    //Functional test that verifies that second LOGO should be visible
    @Test
    public void testIsSecondLOGOElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"fotcont\"]/div[3]/div/div/h4/img"), "'LOGO should be visible");
    }

    //Functional test to check the visibility of the “Home” elements in the menu
    @Test
    public void testIsHomeMenuItemVisibleCssSelector() {
        boolean isVisible = cartPage.isElementVisibleCssSelector("#navbarExample > ul > li.nav-item.active > a");
        assertTrue(isVisible, "'Home' element should be visible");
    }

    //Functional test to check the visibility of the “Contact” elements in the menu
    @Test
    public void testIsContactMenuItemVisibleCssSelector() {
        boolean isVisible = cartPage.isElementVisibleCssSelector("#navbarExample > ul > li:nth-child(2) > a");
        assertTrue(isVisible, "'Contact' element should be visible");
    }

    //Functional test to check the visibility of the “About Us” elements in the menu
    @Test
    public void testIsAboutUsMenuItemVisibleCssSelector() {
        boolean isVisible = cartPage.isElementVisibleCssSelector("#navbarExample > ul > li:nth-child(3) > a");
        assertTrue(isVisible, "'About us' element should be visible");
    }

    //Functional test to check the visibility of the “Cart” elements in the menu
    @Test
    public void testIsCartMenuItemVisibleCssSelector() {
        boolean isVisible = cartPage.isElementVisibleCssSelector("#navbarExample > ul > li:nth-child(4) > a");
        assertTrue(isVisible, "'Cart' element should be visible");
    }

    @AfterEach
    public void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}