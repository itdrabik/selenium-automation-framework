package com.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FirstProductPage extends AbstractProductPage {

    //Constructor
    public FirstProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageUrl(){
        return "https://www.demoblaze.com/prod.html?idp_=1";
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
        System.out.println("Actual text: " + actualText); // Wyświetl tekst w konsoli
        String expectedText = "The Samsung Galaxy S6 is powered by 1.5GHz octa-core Samsung Exynos 7420 processor and it comes with 3GB of RAM. The phone packs 32GB of internal storage cannot be expanded.";
        return actualText.contains(expectedText);
    }
}
