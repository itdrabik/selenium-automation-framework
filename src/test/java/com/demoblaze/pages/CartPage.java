package com.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends AbstractPage {

    // Locatory
    private By signUpButton = By.id("signin2");
    private By signUpModal = By.xpath("/html/body/div[2]/div/div/div[3]/button[2]");
    private By productList = By.id("tbodyid");
    private By placeOrderButton = By.xpath("//button[text()='Place Order']");
    private By nameInput = By.id("name");
    private By countryInput = By.id("country");
    private By cityInput = By.id("city");
    private By creditCardInput = By.id("card");
    private By monthInput = By.id("month");
    private By yearInput = By.id("year");
    private By purchaseButton = By.xpath("//button[text()='Purchase']");
    private By confirmationModal = By.className("sweet-alert");
    private By confirmationOkButton = By.xpath("//button[text()='OK']");

    // Konstruktor
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageUrl() {
        return "https://www.demoblaze.com/cart.html";
    }

    @Override
    public boolean isPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("page-wrapper")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Clicking on the “Sign Up” button
    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    // Checking whether the registration modal is visible
    public boolean isSignUpModalVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // We wait a maximum of 10 seconds
        try {
            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(signUpModal));
            return modal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Checking if the cart has products
    public boolean isProductInCart() {
        return !driver.findElements(productList).isEmpty();
    }

    // Clicking "Place Order"
    public void clickPlaceOrder() {
        driver.findElement(placeOrderButton).click();
    }


    // Clicking "Purchase"
    public void clickPurchase() {
        driver.findElement(purchaseButton).click();
    }

    // Checking if the confirmation modal appears
    public boolean isConfirmationVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationModal)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Clicking "OK" on the confirmation modal
    public void confirmPurchase() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Wait for the confirmation modal
            WebElement confirmationModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sweet-alert")));

            // Wait a while for the modal to stabilize (it may disappear too quickly)
            Thread.sleep(500); // Temporary wait - can be converted to explicit wait

            // Click “OK”
            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
            okButton.click();

            // Wait for the modal to disappear
            wait.until(ExpectedConditions.invisibilityOf(confirmationModal));
        } catch (Exception e) {
            System.out.println("The order confirmation modal did not appear.");
        }

        // If the “Place Order” modal still exists, close it manually.
        try {
            WebElement placeOrderModal = driver.findElement(By.id("orderModal"));
            if (placeOrderModal.isDisplayed()) {
                System.out.println("Modal 'Place Order' still open. Closing it.");
                WebElement closeButton = driver.findElement(By.xpath("//button[@class='close' and @data-dismiss='modal']"));
                closeButton.click();
                wait.until(ExpectedConditions.invisibilityOf(placeOrderModal));
            }
        } catch (Exception e) {
            System.out.println("Modal 'Place Order' already closed.");
        }

        // Wait for the user to return to the shopping cart page.
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartur")));
            System.out.println("The user returned to the Cart page.");
        } catch (Exception e) {
            System.out.println("It was not possible to return to the Cart site.");
        }
    }


    public void goToCart() {
        WebElement cartButton = driver.findElement(By.id("cartur"));
        cartButton.click();
    }

    public boolean isCartEmpty() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='tbodyid']/tr"))); // We are waiting for the product to disappear
            return driver.findElements(By.xpath("//*[@id='tbodyid']/tr")).isEmpty(); // We check for rows in the product table
        } catch (Exception e) {
            return false;
        }
    }

    public void fillOrderForm(String name, String country, String city, String creditCard, String month, String year) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // We are waiting for the form to appear on the site
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        // We fill out the form with data
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("country")).sendKeys(country);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("card")).sendKeys(creditCard);
        driver.findElement(By.id("month")).sendKeys(month);
        driver.findElement(By.id("year")).sendKeys(year);
    }


}
