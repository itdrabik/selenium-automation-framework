package com.demoblaze.tests;

import com.demoblaze.config.ConfigManager;
import com.demoblaze.pages.HomePage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import com.demoblaze.config.DriverFactory;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Home Page Tests")
@Feature("Verification of Home Page Elements and Functionality")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomePageTest {

    private WebDriver driver;
    private HomePage homePage;
    private final String url = "https://www.demoblaze.com";

    @BeforeEach
    @Step("Setup browser and navigate to the home page")
    public void setUp() {
        driver = DriverFactory.getDriver();

        driver.get(url);
        homePage = new HomePage(driver);
    }

    @Test
    @Order(1)
    @Story("Verify Home Page Title")
    @Description("Test to check if the title of the home page is correct.")
    @Severity(SeverityLevel.NORMAL)
    public void testHomePageTitle() {
        assertEquals("STORE", homePage.getPageTitle());
    }

    @Test
    @Order(2)
    @Story("Login Button")
    @Description("Verify clicking the login button opens the modal")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginButtonClick() {
        homePage.clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"logInModal\"]/div/div")));
        assertTrue(homePage.isLoginModalVisible(), "The login window should be visible");
    }

    @Test
    @Order(3)
    @Story("Verify Login Button Visibility")
    @Description("Test to check if the login button is visible on the homepage.")
    @Severity(SeverityLevel.MINOR)
    public void testLoginButtonVisible() {
        assertTrue(homePage.isElementVisible("login2"), "The login button should be visible");
    }

    @Test
    @Order(4)
    @Story("Sign Up Button")
    @Description("Verify clicking the sign-up button opens the modal")
    @Severity(SeverityLevel.CRITICAL)
    public void testSignUpButtonClick() {
        homePage.clickSignUpButton();
        assertTrue(homePage.isSignUpModalVisible(), "The registration window should be visible");
    }

    @Test
    @Order(5)
    @Story("Verify Sign Up Button Visibility")
    @Description("Test to check if the sign-up button is visible on the homepage.")
    @Severity(SeverityLevel.MINOR)
    public void testSignUpButtonVisible() {
        assertTrue(homePage.isElementVisible("signin2"), "The registration button should be visible");
    }

    @Test
    @Order(6)
    @Story("URL Validation")
    @Description("Verify that the homepage URL matches the expected value from the config file.")
    @Severity(SeverityLevel.NORMAL)
    public void testUrlFromConfig() {
        assertEquals(ConfigManager.getProperty("url"), driver.getCurrentUrl(), "URL should match");
    }

    @Test
    @Order(7)
    @Story("Navigation")
    @Description("Verify navigation to the home page using navigateToPage() method.")
    @Severity(SeverityLevel.NORMAL)
    public void testNavigateToPage() {
        homePage.navigateToPage();
        assertEquals(homePage.getPageUrl(), driver.getCurrentUrl(), "The page URL should match.");
    }

    @Test
    @Order(8)
    @Story("Verify Home Page Content")
    @Description("Check if the home page contains 'PRODUCT STORE' text in the source.")
    @Severity(SeverityLevel.NORMAL)
    public void testPageContent() {
        assertTrue(driver.getPageSource().contains("PRODUCT STORE"), "Page content should contain 'PRODUCT STORE'");
    }

    @Test
    @Order(9)
    @Story("Page Load Verification")
    @Description("Verify that the home page loads correctly.")
    @Severity(SeverityLevel.CRITICAL)
    public void testPageLoaded() {
        assertTrue(homePage.isPageLoaded(), "The homepage should be loaded successfully.");
    }

    @Test
    @Order(10)
    @Story("UI Elements")
    @Description("Verify visibility of the Gallery element.")
    @Severity(SeverityLevel.MINOR)
    public void testGalleryElementVisible() {
        assertTrue(homePage.isElementVisibleXpathSelector("//*[@id=\"carouselExampleIndicators\"]/div/div[1]/img"), "The Gallery element should be visible");
    }

    @Test
    @Order(11)
    @Story("Categories Section")
    @Description("Verify visibility of the 'Category' section.")
    @Severity(SeverityLevel.MINOR)
    public void testCategoryElementVisible() {
        assertTrue(homePage.isElementVisible("cat"), "The 'Category' element should be visible");
    }

    @Test
    @Order(12)
    @Story("Categories Section")
    @Description("Verify visibility of the 'Phones' category.")
    @Severity(SeverityLevel.MINOR)
    public void testPhoneElementVisible() {
        assertTrue(homePage.isElementVisibleXpathSelector("(//*[@id='itemc'])[1]"), "'Phone' should be visible");
    }

    @Test
    @Order(13)
    @Story("Categories Section")
    @Description("Verify visibility of the 'Laptops' category.")
    @Severity(SeverityLevel.MINOR)
    public void testLaptopsElementVisible() {
        assertTrue(homePage.isElementVisibleXpathSelector("(//*[@id='itemc'])[2]"), "'Laptops' should be visible");
    }

    @Test
    @Order(14)
    @Story("Categories Section")
    @Description("Verify visibility of the 'Monitors' category.")
    @Severity(SeverityLevel.MINOR)
    public void testMonitorsElementVisible() {
        assertTrue(homePage.isElementVisibleXpathSelector("(//*[@id='itemc'])[3]"), "'Monitors' should be visible");
    }

    @Test
    @Order(15)
    @Story("Product Listing")
    @Description("Verify visibility of at least one product on the home page.")
    @Severity(SeverityLevel.NORMAL)
    public void testRandomProductElementVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement randomProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@id=\"tbodyid\"]/div)[1]")));
        assertTrue(randomProduct.isDisplayed(), "Random product should be visible");
    }

    @Test
    @Order(16)
    @Story("UI Navigation")
    @Description("Verify visibility of the 'Previous' button.")
    @Severity(SeverityLevel.NORMAL)
    public void testPreviousButtonVisible() {
        assertTrue(homePage.isElementVisible("prev2"), "The 'Previous' button should be visible");
    }

    @Test
    @Order(17)
    @Story("UI Navigation")
    @Description("Verify visibility of the 'Next' button.")
    @Severity(SeverityLevel.NORMAL)
    public void testNextButtonVisible() {
        assertTrue(homePage.isElementVisible("next2"), "The 'Next' button should be visible");
    }

    @Test
    @Order(18)
    @Story("Menu Items")
    @Description("Verify that the 'Contact' menu item is visible.")
    @Severity(SeverityLevel.MINOR)
    public void testIsContactMenuItemVisibleCssSelector() {
        boolean isVisible = homePage.isElementVisibleCssSelector("#navbarExample > ul > li:nth-child(2) > a");
        assertTrue(isVisible, "'Contact' element should be visible");
    }

    @Test
    @Order(19)
    @Story("Menu Items")
    @Description("Verify that the 'About Us' menu item is visible.")
    @Severity(SeverityLevel.MINOR)
    public void testIsAboutUsMenuItemVisibleCssSelector() {
        boolean isVisible = homePage.isElementVisibleCssSelector("#navbarExample > ul > li:nth-child(3) > a");
        assertTrue(isVisible, "'About us' element should be visible");
    }

    @Test
    @Order(20)
    @Story("Menu Items")
    @Description("Verify that the 'Cart' menu item is visible.")
    @Severity(SeverityLevel.MINOR)
    public void testIsCartMenuItemVisibleCssSelector() {
        boolean isVisible = homePage.isElementVisibleCssSelector("#navbarExample > ul > li:nth-child(4) > a");
        assertTrue(isVisible, "'Cart' element should be visible");
    }


    @AfterEach
    @Step("Close the browser")
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
