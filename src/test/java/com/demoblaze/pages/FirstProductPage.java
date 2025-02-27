package com.demoblaze.pages;

import com.demoblaze.utils.ActionWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FirstProductPage extends AbstractProductPage {
    private ActionWrapper actionWrapper;

    // Locators
    private By pageWrapper = By.id("page-wrapper");
    private By specificFeature = By.xpath("/html/body/div[5]/div/div[2]/div[1]/div/div/p");

    // Constructor
    public FirstProductPage(WebDriver driver) {
        super(driver);
        this.actionWrapper = new ActionWrapper(driver);
    }

    @Override
    public String getPageUrl() {
        return "https://www.demoblaze.com/prod.html?idp_=1";
    }

    @Override
    public boolean isPageLoaded() {
        return actionWrapper.isElementVisible(pageWrapper);
    }

    @Override
    public boolean isSpecificFeatureVisible() {
        String actualText = actionWrapper.getElementText(specificFeature);
        System.out.println("Actual text: " + actualText);

        String expectedText = "The Samsung Galaxy S6 is powered by 1.5GHz octa-core Samsung Exynos 7420 processor and it comes with 3GB of RAM. The phone packs 32GB of internal storage cannot be expanded.";
        return actualText.contains(expectedText);
    }
}
