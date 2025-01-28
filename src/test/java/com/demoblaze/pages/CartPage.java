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
}
