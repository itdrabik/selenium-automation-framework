import com.example.config.ConfigManager;
import com.example.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

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

    @Test
    public void testSignUpButtonVisible() {
        assertTrue(homePage.isElementVisible("signin2"), "The registration button should be visible");
    }

    //The functional test testUrl, utilizing configuration from the config.properties file, verifies whether the current URL of the page matches the expected URL.
    @Test
    public void testUrl() {
        String expectedUrl = ConfigManager.getProperty("url");
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl, "URL should be " + expectedUrl);
    }

    //The functional test testPageContent verifies whether the page contains the expected content, "PRODUCT STORE," by checking the page's source code.
    @Test
    public void testPageContent() {
        String expectedContent = "PRODUCT STORE";
        String bodyText = driver.getPageSource();
        assertTrue(bodyText.contains(expectedContent), "Page content should contain 'PRODUCT STORE'");
    }

    @AfterEach
    public void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}