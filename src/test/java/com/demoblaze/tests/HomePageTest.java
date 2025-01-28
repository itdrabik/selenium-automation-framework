package com.demoblaze.tests;

import com.demoblaze.config.ConfigManager;
import com.demoblaze.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class HomePageTest {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        // Load data from config.properties
        String browser = ConfigManager.getProperty("browser");
        String url = ConfigManager.getProperty("url");


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

        homePage = new HomePage(driver);
    }

    //The functional test testHomePageTitle, which uses a method from the HomePage class, verifies whether the title of the home page is correct.
    @Test
    public void testHomePageTitle() {
        String title = homePage.getPageTitle();
        assertEquals("STORE", title);
    }

    @Test
    public void testLoginButtonClick() {
       homePage.clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"logInModal\"]/div/div")));
        assertTrue(homePage.isLoginModalVisible(), "The login window should be visible");
    }

    @Test
    public void testLoginButtonVisible() {
        assertTrue(homePage.isElementVisible("login2"), "The login button should be visible");
    }

    @Test
    public void testSignUpButtonClick() {
        homePage.clickSignUpButton();
        assertTrue(homePage.isSignUpModalVisible(),"The registration window should be visible");
    }

    //Functional test to check the visibility of the “Contact” elements in the menu
    @Test
    public void testIsContactMenuItemVisibleCssSelector() {
        boolean isVisible = homePage.isElementVisibleCssSelector("#navbarExample > ul > li:nth-child(2) > a");
        assertTrue(isVisible, "'Contact' element should be visible");
    }

    //Functional test to check the visibility of the “About Us” elements in the menu
    @Test
    public void testIsAboutUsMenuItemVisibleCssSelector() {
        boolean isVisible = homePage.isElementVisibleCssSelector("#navbarExample > ul > li:nth-child(3) > a");
        assertTrue(isVisible, "'About us' element should be visible");
    }

    //Functional test to check the visibility of the “Cart” elements in the menu
    @Test
    public void testIsCartMenuItemVisibleCssSelector() {
        boolean isVisible = homePage.isElementVisibleCssSelector("#navbarExample > ul > li:nth-child(4) > a");
        assertTrue(isVisible, "'Cart' element should be visible");
    }

    @Test
    public void testSignUpButtonVisible() {
        assertTrue(homePage.isElementVisible("signin2"), "The registration button should be visible");
    }

    //The functional test testUrl, utilizing configuration from the config.properties file, verifies whether the current URL of the page matches the expected URL.
    @Test
    public void testUrlFromConfig() {
        String expectedUrl = ConfigManager.getProperty("url");
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl, "URL should be " + expectedUrl);
    }

    //A functional test that verifies that the navigateToPage() method correctly redirects the user to the correct URL from homePage.getPageUrl()
    @Test
    public void testNavigateToPage() {
        homePage.navigateToPage();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = homePage.getPageUrl(); // URL obtained from the HomePage class
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
        assertTrue(homePage.isPageLoaded(), "The homepage should be loaded successfully.");
    }

    //Functional test to verify that the gallery element is visible.
    @Test
    public void testGalleryElementVisible() {
        assertTrue(homePage.isElementVisibleXpathSelector("//*[@id=\"carouselExampleIndicators\"]/div/div[1]/img"), "The Gallery element should be visible");
    }

    //Functional test to verify that the “Categories” element is visible.
    @Test
    public void testCategoryElementVisible() {
        assertTrue(homePage.isElementVisible("cat"), "The 'Category' element should be visible");
    }

    //Functional test to verify that the “Phone” element is visible.
    @Test
    public void testPhoneElementVisible() {
        assertTrue(homePage.isElementVisibleXpathSelector("(//*[@id='itemc'])[1]"), "'Phone' should be visible");
    }

    //Functional test to verify that the “Laptops” element is visible.
    @Test
    public void testLaptopsElementVisible() {
        assertTrue(homePage.isElementVisibleXpathSelector("(//*[@id='itemc'])[2]"), "'Laptops' should be visible");
    }

    //Functional test to verify that the “Monitors” element is visible.
    @Test
    public void testMonitorsElementVisible() {
        assertTrue(homePage.isElementVisibleXpathSelector("(//*[@id='itemc'])[3]"), "'Monitors' should be visible");
    }

    //Functional test to verify that the random product element is visible.
    @Test
    public void testRandomProductElementVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String productXPath = "(//*[@id=\"tbodyid\"]/div)[1]";
        WebElement randomProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productXPath)));
        assertTrue(randomProduct.isDisplayed(), "Random product should be visible");
    }

    //Functional test to verify that the "Previous" button element is visible.
    @Test
    public void testPreviousButtonVisible() {
        assertTrue(homePage.isElementVisible("prev2"), "The 'Previous' button should be visible");
    }

    //Functional test to verify that the "Next" button element is visible.
    @Test
    public void testNextButtonVisible() {
        assertTrue(homePage.isElementVisible("next2"), "The 'Next' button should be visible");
    }

    @AfterEach
    public void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}