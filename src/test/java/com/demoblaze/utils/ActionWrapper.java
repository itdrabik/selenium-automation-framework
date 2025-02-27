package com.demoblaze.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionWrapper {
    private WebDriver driver;
    private WebDriverWait wait;

    public ActionWrapper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }


    public void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }


    public boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


    public void waitForElementPresence(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public void acceptAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present.");
        }
    }

    public String getElementText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }
}
