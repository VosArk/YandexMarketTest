package com.webdriver;

import org.openqa.selenium.WebDriver;

class PageCatalogModels {
    private XPath xPath;
    private final static String XPATH_ADVANCED_SEARCH = "//a[contains(@href, '/gate/guru/redir/guru')]";
    private WebDriver localDriver;

    PageCatalogModels(WebDriver driver){
        xPath = new XPath();
        localDriver = driver;
    }

    void clickOnAdvancedSearch() {
        xPath.clickButtonByXPath(localDriver, XPATH_ADVANCED_SEARCH);
    }
}
