package com.demoblaze.pages;

import com.demoblaze.utils.ActionWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {

    private ActionWrapper actionWrapper;

    // Locators
    private By signUpButton = By.id("signin2");
    private By signUpModal = By.xpath("/html/body/div[2]/div/div/div[3]/button[2]");
    private By categoryList = By.id("cat");
    private By firstProduct = By.cssSelector(".card-title a");

    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
        this.actionWrapper = new ActionWrapper(driver);
    }

    @Override
    public String getPageUrl() {
        return "https://www.demoblaze.com/";
    }

    @Override
    public boolean isPageLoaded() {
        return actionWrapper.isElementVisible(categoryList);
    }

    // Clicks the "Sign Up" button
    public void clickSignUpButton() {
        actionWrapper.click(signUpButton);
    }

    // Checks if the "Sign Up" modal is visible
    public boolean isSignUpModalVisible() {
        return actionWrapper.isElementVisible(signUpModal);
    }

    // Selects a category by name
    public void selectCategory(String categoryName) {
        actionWrapper.click(By.linkText(categoryName));
    }

    // Clicks the first product in the list
    public void clickFirstProduct() {
        actionWrapper.click(By.xpath("(//*[@id='tbodyid']//a)[1]"));
    }
}
