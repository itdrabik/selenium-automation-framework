package com.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By signUpButton = By.id("signin2"); // Sign up button to open modal
    private By signUpModal = By.id("signInModal"); // Modal registration
    private By usernameInput = By.id("sign-username"); // Login field
    private By passwordInput = By.id("sign-password"); // Password field
    private By modalSignUpButton = By.xpath("//button[text()='Sign up']"); // Sign up button in modal
    private By popup = By.xpath("//div[@class='modal-dialog']//button[text()='OK']"); // Pop-up confirmations

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Waiting up to 10 seconds
    }

    public void openSignUpModal() {
        driver.findElement(signUpButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(signUpModal)); // Wait for the modal
    }

    public void fillSignUpModal(String username, String password) {
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        usernameField.clear();
        usernameField.sendKeys(username);

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void submitSignUp() {
        WebElement signUpBtn = wait.until(ExpectedConditions.elementToBeClickable(modalSignUpButton));
        signUpBtn.click();
    }

    public void confirmSignUpPopup() {
        wait.until(ExpectedConditions.alertIsPresent()); // Expect a pop-up
        driver.switchTo().alert().accept(); // Click OK on the pop-up
    }

    public void openLoginModal() {
        driver.findElement(By.id("login2")).click();
    }


    public void fillLoginModal(String username, String password) {
        // We wait until the login field is visible and clicked
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
        usernameField.clear();
        usernameField.sendKeys(username);

        // We wait until the password field is visible and clicked
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginpassword")));
        passwordField.clear();
        passwordField.sendKeys(password);
    }


    public void submitLogin() {
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
    }

}