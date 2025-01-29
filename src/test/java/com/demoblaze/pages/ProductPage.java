package com.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage extends AbstractPage {
    private By addToCartButton = By.xpath("//a[text()='Add to cart']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageUrl() {
        return "https://www.demoblaze.com/prod.html";
    }



    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void acceptPopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    @Override
    public boolean isPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("name"))); // Product header
            return productTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
