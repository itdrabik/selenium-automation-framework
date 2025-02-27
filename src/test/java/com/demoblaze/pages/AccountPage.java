package com.demoblaze.pages;

import com.demoblaze.utils.ActionWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends AbstractPage {

    private ActionWrapper actionWrapper;

    // Locators
    private By signUpButton = By.id("signin2");
    private By signUpModal = By.id("signInModal");
    private By usernameInput = By.id("sign-username");
    private By passwordInput = By.id("sign-password");
    private By modalSignUpButton = By.xpath("//button[text()='Sign up']");
    private By loginButton = By.id("login2");
    private By loginUsername = By.id("loginusername");
    private By loginPassword = By.id("loginpassword");
    private By submitLoginButton = By.xpath("//button[text()='Log in']");
    private By logoutButton = By.id("logout2");

    // Constructor
    public AccountPage(WebDriver driver) {
        super(driver);
        this.actionWrapper = new ActionWrapper(driver);
    }

    @Override
    public String getPageUrl() {
        return "https://www.demoblaze.com/";
    }

    // Opens the Sign Up modal
    public void openSignUpModal() {
        actionWrapper.click(signUpButton);
        actionWrapper.waitForElementPresence(signUpModal);
    }

    // Fills the Sign Up form
    public void fillSignUpModal(String username, String password) {
        actionWrapper.type(usernameInput, username);
        actionWrapper.type(passwordInput, password);
    }

    // Submits the Sign Up form
    public void submitSignUp() {
        actionWrapper.click(modalSignUpButton);
    }

    // Confirms the sign-up alert pop-up
    public void confirmSignUpPopup() {
        actionWrapper.acceptAlert();
    }

    // Opens the Login modal
    public void openLoginModal() {
        actionWrapper.click(loginButton);
    }

    // Fills the login form
    public void fillLoginModal(String username, String password) {
        actionWrapper.type(loginUsername, username);
        actionWrapper.type(loginPassword, password);
    }

    // Submits login form
    public void submitLogin() {
        actionWrapper.click(submitLoginButton);
    }

    // Logs the user out
    public void logout() {
        if (isUserLoggedIn()) {
            actionWrapper.click(logoutButton);
        } else {
            System.out.println("User is already logged out.");
        }
    }

    // Checks if the user is logged in
    public boolean isUserLoggedIn() {
        return actionWrapper.isElementVisible(logoutButton);
    }

    // Checks if the logout button is visible
    public boolean isLogoutButtonVisible() {
        return actionWrapper.isElementVisible(logoutButton);
    }
}
