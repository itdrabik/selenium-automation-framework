package com.demoblaze.tests;

import com.demoblaze.config.ConfigManager;
import com.demoblaze.pages.CartPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Shopping Cart")
@Feature("Cart Page Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartPageTest {

    private WebDriver driver;
    private CartPage cartPage;

    @BeforeEach
    @Step("Setup WebDriver and navigate to Cart Page")
    public void setUp() {
        String browser = ConfigManager.getProperty("browser");
        String url = "https://www.demoblaze.com/cart.html";

        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        } else if ("edge".equalsIgnoreCase(browser)) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.get(url);
        cartPage = new CartPage(driver);
    }

    @Test
    @Order(1)
    @Story("Verify the title of the cart page")
    @Description("Check if the cart page title is 'STORE'")
    @Severity(SeverityLevel.NORMAL)
    public void testCartPageTitle() {
        assertEquals("STORE", cartPage.getPageTitle());
    }

    @Test
    @Order(2)
    @Story("Verify login button functionality")
    @Description("Check if the login modal appears after clicking the login button")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginButtonClick() {
        cartPage.clickLoginButton();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"logInModal\"]/div/div")));
        assertTrue(cartPage.isLoginModalVisible(), "The login window should be visible");
    }

    @Test
    @Order(3)
    @Story("Verify login button visibility")
    @Description("Check if the login button is visible on the page")
    @Severity(SeverityLevel.MINOR)
    public void testLoginButtonVisible() {
        assertTrue(cartPage.isElementVisible("login2"), "The login button should be visible");
    }

    @Test
    @Order(4)
    @Story("Verify signup button functionality")
    @Description("Check if the signup modal appears after clicking the signup button")
    @Severity(SeverityLevel.CRITICAL)
    public void testSignUpButtonClick() {
        cartPage.clickSignUpButton();
        assertTrue(cartPage.isSignUpModalVisible(), "The registration window should be visible");
    }

    @Test
    @Order(5)
    @Story("Verify signup button visibility")
    @Description("Check if the signup button is visible on the page")
    @Severity(SeverityLevel.MINOR)
    public void testSignUpButtonVisible() {
        assertTrue(cartPage.isElementVisible("signin2"), "The registration button should be visible");
    }

    @Test
    @Order(6)
    @Story("Verify navigation")
    @Description("Ensure navigateToPage() correctly redirects to expected URL")
    @Severity(SeverityLevel.NORMAL)
    public void testNavigateToPage() {
        cartPage.navigateToPage();
        assertEquals(cartPage.getPageUrl(), driver.getCurrentUrl(), "The page URL should match.");
    }

    @Test
    @Order(7)
    @Story("Verify page content")
    @Description("Check if the page contains 'PRODUCT STORE'")
    @Severity(SeverityLevel.MINOR)
    public void testPageContent() {
        assertTrue(driver.getPageSource().contains("PRODUCT STORE"), "Page content should contain 'PRODUCT STORE'");
    }

    @Test
    @Order(8)
    @Story("Verify cart page loading")
    @Description("Ensure that the cart page loads successfully")
    @Severity(SeverityLevel.NORMAL)
    public void testPageLoaded() {
        assertTrue(cartPage.isPageLoaded(), "The cart page should be loaded successfully.");
    }

    @Test
    @Order(9)
    @Story("Verify order button visibility")
    @Description("Check if the 'Place Order' button is visible")
    @Severity(SeverityLevel.CRITICAL)
    public void testIsPlaceOrderButtonElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"page-wrapper\"]/div/div[2]/button"), "'Place Order' button should be visible");
    }

    @Test
    @Order(10)
    @Story("Verify total section visibility")
    @Description("Check if the 'Total' section is visible")
    @Severity(SeverityLevel.MINOR)
    public void testIsTotalElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"page-wrapper\"]/div/div[2]/h2"), "'Total' text should be visible");
    }

    @Test
    @Order(11)
    @Story("Verify footer elements visibility")
    @Description("Check if the footer contains the expected contact information")
    @Severity(SeverityLevel.MINOR)
    public void testIsAboutUsTextVisibleOnPage() {
        assertTrue(cartPage.isTextPresent("We believe performance needs to be validated"), "Text should be visible on the page.");
    }

    @Test
    @Order(12)
    @Story("Verify footer address visibility")
    @Description("Check if the address text is visible in the footer")
    @Severity(SeverityLevel.MINOR)
    public void testIsAddressTextVisibleOnPage() {
        assertTrue(cartPage.isTextPresent("Address: 2390 El Camino Real"), "Address text should be visible on the page.");
    }

    @Test
    @Order(13)
    @Story("Verify footer phone visibility")
    @Description("Check if the phone number is visible in the footer")
    @Severity(SeverityLevel.MINOR)
    public void testIsPhoneTextVisibleOnPage() {
        assertTrue(cartPage.isTextPresent("Phone: +440 123456"), "Phone number should be visible on the page.");
    }

    @Test
    @Order(14)
    @Story("Verify footer email visibility")
    @Description("Check if the email is visible in the footer")
    @Severity(SeverityLevel.MINOR)
    public void testIsEmailTextVisibleOnPage() {
        assertTrue(cartPage.isTextPresent("Email: demo@blazemeter.com "), "Email should be visible on the page.");
    }

    @Test
    @Order(15)
    @Story("Verify 'Home' menu item visibility")
    @Description("Check if the 'Home' menu item is visible in the navigation bar")
    @Severity(SeverityLevel.NORMAL)
    public void testIsHomeMenuItemVisibleCssSelector() {
        assertTrue(cartPage.isElementVisibleCssSelector("#navbarExample > ul > li.nav-item.active > a"), "'Home' element should be visible");
    }

    @Test
    @Order(16)
    @Story("Verify 'Cart' menu item visibility")
    @Description("Check if the 'Cart' menu item is visible in the navigation bar")
    @Severity(SeverityLevel.NORMAL)
    public void testIsCartMenuItemVisibleCssSelector() {
        assertTrue(cartPage.isElementVisibleCssSelector("#navbarExample > ul > li:nth-child(4) > a"), "'Cart' element should be visible");
    }

    @Test
    @Order(17)
    @Story("Verify 'Products' section visibility")
    @Description("Ensure the 'Products' section is visible on the cart page")
    @Severity(SeverityLevel.NORMAL)
    public void testIsPageElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"page-wrapper\"]/div/div[1]/h2"), "'Products' text should be visible");
    }

    @Test
    @Order(18)
    @Story("Verify 'Pic' column visibility")
    @Description("Ensure the 'Pic' column is visible in the cart table")
    @Severity(SeverityLevel.NORMAL)
    public void testIsPicElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"page-wrapper\"]/div/div[1]/div/table/thead/tr/th[1]"), "'Pic' text should be visible");
    }

    @Test
    @Order(19)
    @Story("Verify 'Title' column visibility")
    @Description("Ensure the 'Title' column is visible in the cart table")
    @Severity(SeverityLevel.NORMAL)
    public void testIsTitleElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"page-wrapper\"]/div/div[1]/div/table/thead/tr/th[2]"), "'Title' text should be visible");
    }

    @Test
    @Order(20)
    @Story("Verify 'Price' column visibility")
    @Description("Ensure the 'Price' column is visible in the cart table")
    @Severity(SeverityLevel.NORMAL)
    public void testIsPriceElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"page-wrapper\"]/div/div[1]/div/table/thead/tr/th[3]"), "'Price' text should be visible");
    }

    @Test
    @Order(21)
    @Story("Verify 'X' column visibility")
    @Description("Ensure the 'X' column is visible in the cart table")
    @Severity(SeverityLevel.NORMAL)
    public void testIsXElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"page-wrapper\"]/div/div[1]/div/table/thead/tr/th[4]"), "'X' text should be visible");
    }

    @Test
    @Order(22)
    @Story("Verify main logo visibility")
    @Description("Check if the main logo is visible on the cart page")
    @Severity(SeverityLevel.NORMAL)
    public void testIsLOGOElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"nava\"]/img"), "Main 'LOGO' should be visible");
    }

    @Test
    @Order(23)
    @Story("Verify secondary logo visibility")
    @Description("Check if the secondary logo in the footer is visible on the cart page")
    @Severity(SeverityLevel.NORMAL)
    public void testIsSecondLOGOElementVisibleXpathSelector() {
        assertTrue(cartPage.isElementVisibleXpathSelector("//*[@id=\"fotcont\"]/div[3]/div/div/h4/img"), "Secondary 'LOGO' should be visible");
    }

    @Test
    @Order(24)
    @Story("Verify 'Contact' menu item visibility")
    @Description("Check if the 'Contact' menu item is visible in the navigation bar")
    @Severity(SeverityLevel.NORMAL)
    public void testIsContactMenuItemVisibleCssSelector() {
        assertTrue(cartPage.isElementVisibleCssSelector("#navbarExample > ul > li:nth-child(2) > a"), "'Contact' element should be visible");
    }

    @Test
    @Order(25)
    @Story("Verify 'About Us' menu item visibility")
    @Description("Check if the 'About Us' menu item is visible in the navigation bar")
    @Severity(SeverityLevel.NORMAL)
    public void testIsAboutUsMenuItemVisibleCssSelector() {
        assertTrue(cartPage.isElementVisibleCssSelector("#navbarExample > ul > li:nth-child(3) > a"), "'About us' element should be visible");
    }


    @AfterEach
    @Step("Close browser after test execution")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
