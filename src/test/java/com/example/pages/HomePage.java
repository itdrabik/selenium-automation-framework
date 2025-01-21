package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;

    // Constructor of the Class
    public HomePage(WebDriver driver) {
        this.driver = driver;
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
        WebElement loginModal = driver.findElement(By.id("signInModal"));
        return loginModal.isDisplayed();
    }

    // Method to check the visibility of an element
    public boolean isElementVisible(String elementId) {
        WebElement element = driver.findElement(By.id(elementId));
        return element.isDisplayed();
    }

    // Method to retrieve page title
    public String getPageTitle() {
        return driver.getTitle();
    }

    // Other methods that represent interactions with page elements
}
