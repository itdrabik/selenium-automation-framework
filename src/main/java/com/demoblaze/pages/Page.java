package com.demoblaze.pages;

public interface Page {

    // URL of the site
    String getPageUrl();

    // Method to verify page title
    void testPageTitle();

    // Method that navigates to another subpage
    void navigateToPage();

    // Method to check if a page has logged in
    boolean isPageLoaded();

}
