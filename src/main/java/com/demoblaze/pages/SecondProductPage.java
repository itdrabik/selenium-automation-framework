package com.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SecondProductPage extends AbstractProductPage {

    //Constructor
    public SecondProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageUrl() {
        return "https://www.demoblaze.com/prod.html?idp_=2";
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
    public boolean isSpecificFeatureVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[5]/div/div[2]/div[1]/div/div/p")));
        String actualText = element.getText();
        System.out.println("Actual text: " + actualText); // Display text in the console
        String expectedText = "The Nokia Lumia 1520 is powered by 2.2GHz quad-core Qualcomm Snapdragon 800 processor and it comes with 2GB of RAM.";
        return actualText.contains(expectedText);
    }
}