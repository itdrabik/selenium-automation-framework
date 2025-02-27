package com.demoblaze.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import com.demoblaze.utils.ActionWrapper;


public abstract class AbstractPage implements Page {

    protected WebDriver driver;
    protected ActionWrapper actionWrapper;

    // Constructor for AbstractPage
    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        if (driver != null) {
            this.actionWrapper = new ActionWrapper(driver);
        } else {
            throw new IllegalArgumentException("WebDriver cannot be null!");
        }
    }

    @Override
    public void navigateToPage() {
        driver.get(getPageUrl());
    }

    @Override
    public boolean isPageLoaded() {
        return driver.getTitle().contains("Expected Keyword");
    }

    @Override
    public void testPageTitle() {
        String title = driver.getTitle();
        if (!title.contains("Expected Title")) {
            throw new AssertionError("Page title does not match. Found: " + title);
        }
    }

    // Abstract method to be implemented by child classes
    @Override
    public abstract String getPageUrl();

    // Method to get the page title (common for all pages)
    public String getPageTitle() {
        return driver.getTitle();
    }

    // Method to click login button
    public void clickLoginButton() {
        actionWrapper.click(By.id("login2"));
    }

    // Method to check the visibility of the pop-up window for Login
    public boolean isLoginModalVisible() {
        return actionWrapper.isElementVisible(By.id("logInModal"));
    }

    // Method that clicks the “SignUpButton” button
    public void clickSignUpButton() {
        actionWrapper.click(By.id("signin2"));
    }

    // Method to check the visibility of the pop-up window for SignUp
    public boolean isSignUpModalVisible() {
        return actionWrapper.isElementVisible(By.id("signInModal"));
    }

    // Method to check the visibility of an element using Id
    public boolean isElementVisible(String elementId) {
        return actionWrapper.isElementVisible(By.id(elementId));
    }

    // Method to check the visibility of an element using xpath selector
    public boolean isElementVisibleXpathSelector(String elementXpath) {
        return actionWrapper.isElementVisible(By.xpath(elementXpath));
    }

    // Method to check the visibility of an element using CSS selector
    public boolean isElementVisibleCssSelector(String elementCssSelector) {
        return actionWrapper.isElementVisible(By.cssSelector(elementCssSelector));
    }

    // Method that checks the text on the page
    public boolean isTextPresent(String text) {
        return driver.getPageSource().contains(text);
    }
}
