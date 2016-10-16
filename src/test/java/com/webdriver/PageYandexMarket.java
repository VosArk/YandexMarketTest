package com.webdriver;

import org.openqa.selenium.WebDriver;

class PageYandexMarket {
    private XPath xPath;
    private final static String XPATH_CATALOG = "//li[@href='#catalog']";
    private final static String LINK_NAME_MOBILE_PHONES = "Мобильные телефоны";
    private WebDriver localDriver;

    PageYandexMarket(WebDriver driver){
        xPath = new XPath();
        localDriver = driver;
    }

    void clickOnCatalog() {
        xPath.clickButtonByXPath(localDriver, XPATH_CATALOG);
    }

    void clickOnMobilePhones() {
        xPath.clickButtonByLinkName(localDriver, LINK_NAME_MOBILE_PHONES);
    }
}
