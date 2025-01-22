package com.demoblaze.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public abstract class AbstractPage implements Page {

    protected WebDriver driver;

    // Constructor for AbstractPage
    public AbstractPage(WebDriver driver) {
        this.driver = driver;
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
        WebElement loginButton = driver.findElement(By.id("login2"));
        loginButton.click();
    }

    // Method to check the visibility of the pop-up window for Login
    public boolean isLoginModalVisible() {
        WebElement loginModal = driver.findElement(By.id("logInModal"));
        return loginModal.isDisplayed();
    }

    // Method that clicks the “SignUpButton” button
    public void clickSignUpButton() {
        WebElement signUpButton = driver.findElement(By.id("signin2"));
        signUpButton.click();
    }

    // Method to check the visibility of the pop-up window for SignUp
    public boolean isSignUpModalVisible() {
        WebElement signUpModal = driver.findElement(By.id("signInModal"));
        return signUpModal.isDisplayed();
    }

    // Method to check the visibility of an element
    public boolean isElementVisible(String elementId) {
        WebElement element = driver.findElement(By.id(elementId));
        return element.isDisplayed();
    }
}
