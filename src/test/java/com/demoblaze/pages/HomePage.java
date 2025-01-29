package com.demoblaze.pages;

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
    private By categoryList = By.id("cat");
    private By firstProduct = By.cssSelector(".card-title a");

    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageUrl() {
        return "https://www.demoblaze.com/";
    }

    @Override
    public boolean isPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(signUpModal));
            return modal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }



    public void selectCategory(String categoryName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement category = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(categoryName)));
        category.click();
    }

    public void clickFirstProduct() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By firstProductLocator = By.xpath("(//*[@id='tbodyid']//a)[1]"); // XPath of the first product
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(firstProductLocator));
        firstProduct.click();
    }


}
