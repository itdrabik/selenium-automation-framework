package com.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractProductPage extends AbstractPage {

    public AbstractProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        // We create a WebDriverWait object that will wait up to 3 seconds for the item to appear.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            // Wait for the element to appear, which indicates that the page is loaded.
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("page-wrapper")));
            return true; // If the element has become visible, the page is loaded.
        } catch (Exception e) {
            // If the item could not be found while waiting, we consider that the page is not loaded.
            return false;
        }
    }

    @Override
    public String getPageUrl() {
        return null;
    }

    private WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isProductTitleVisible() {
        return waitForElement(By.cssSelector("#tbodyid > h2")).isDisplayed();
    }

    public boolean isProductPriceVisible() {
        return waitForElement(By.cssSelector("#tbodyid > h3")).isDisplayed();
    }

    public boolean isProductDescriptionVisible() {
        return waitForElement(By.cssSelector("#more-information > p")).isDisplayed();
    }

    public boolean isAddToCartButtonVisible() {
        return waitForElement(By.cssSelector(".btn-success")).isDisplayed();
    }

    public boolean isPictureVisible() {
        return waitForElement(By.cssSelector("#imgp > div > img")).isDisplayed();
    }

    public abstract boolean isSpecificFeatureVisible();
}
