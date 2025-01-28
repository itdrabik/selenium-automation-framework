package com.demoblaze.pages;

import com.demoblaze.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends AbstractPage {

    // Locators
    private By signUpButton = By.id("signin2");
    private By signUpModal = By.xpath("/html/body/div[2]/div/div/div[3]/button[2]");


    //Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageUrl(){
        return "https://www.demoblaze.com/";
    }

    @Override
    public boolean isPageLoaded() {
        // We create a WebDriverWait object that will wait up to 3 seconds for the item to appear.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            // Wait for the element to appear, which indicates that the page is loaded.
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cat")));
            return true; // If the element has become visible, the page is loaded.
        } catch (Exception e) {
            // If the item could not be found while waiting, we consider that the page is not loaded.
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