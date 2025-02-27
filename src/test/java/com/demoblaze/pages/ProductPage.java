package com.demoblaze.pages;

import com.demoblaze.utils.ActionWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends AbstractPage {
    private ActionWrapper actionWrapper;

    // Locators
    private By addToCartButton = By.xpath("//a[text()='Add to cart']");
    private By productTitle = By.className("name"); // Product header

    // Constructor
    public ProductPage(WebDriver driver) {
        super(driver);
        this.actionWrapper = new ActionWrapper(driver);
    }

    @Override
    public String getPageUrl() {
        return "https://www.demoblaze.com/prod.html";
    }

    // Clicks the "Add to Cart" button
    public void addToCart() {
        actionWrapper.click(addToCartButton);
    }

    // Accepts the alert popup after adding a product to the cart
    public void acceptPopup() {
        actionWrapper.acceptAlert();
    }

    @Override
    public boolean isPageLoaded() {
        return actionWrapper.isElementVisible(productTitle);
    }
}
