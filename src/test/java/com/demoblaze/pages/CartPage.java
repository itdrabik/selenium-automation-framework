package com.demoblaze.pages;

import com.demoblaze.utils.ActionWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends AbstractPage {

    private ActionWrapper actionWrapper;

    // Locators
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
    private By cartButton = By.id("cartur");

    // Constructor
    public CartPage(WebDriver driver) {
        super(driver);
        this.actionWrapper = new ActionWrapper(driver);
    }

    @Override
    public String getPageUrl() {
        return "https://www.demoblaze.com/cart.html";
    }

    @Override
    public boolean isPageLoaded() {
        return actionWrapper.isElementVisible(By.id("page-wrapper"));
    }

    // Clicks the "Sign Up" button
    public void clickSignUpButton() {
        actionWrapper.click(signUpButton);
    }

    // Checks if the "Sign Up" modal is visible
    public boolean isSignUpModalVisible() {
        return actionWrapper.isElementVisible(signUpModal);
    }

    // Checks if there are products in the cart
    public boolean isProductInCart() {
        return actionWrapper.isElementVisible(productList);
    }

    // Clicks the "Place Order" button
    public void clickPlaceOrder() {
        actionWrapper.click(placeOrderButton);
    }

    // Clicks the "Purchase" button
    public void clickPurchase() {
        actionWrapper.click(purchaseButton);
    }

    // Checks if the confirmation modal is visible
    public boolean isConfirmationVisible() {
        return actionWrapper.isElementVisible(confirmationModal);
    }

    // Confirms the purchase by clicking "OK"
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



    // Navigates to the cart page
    public void goToCart() {
        actionWrapper.click(cartButton);
    }

    // Checks if the cart is empty
    public boolean isCartEmpty() {
        return !actionWrapper.isElementVisible(By.xpath("//*[@id='tbodyid']/tr"));
    }

    // Fills out the order form
    public void fillOrderForm(String name, String country, String city, String creditCard, String month, String year) {
        actionWrapper.type(nameInput, name);
        actionWrapper.type(countryInput, country);
        actionWrapper.type(cityInput, city);
        actionWrapper.type(creditCardInput, creditCard);
        actionWrapper.type(monthInput, month);
        actionWrapper.type(yearInput, year);
    }
}
