package com.webdriver;

import org.openqa.selenium.WebDriver;

class PageCatalog {
    private XPath xPath;
    private final static String PRICE_FROM_ID = "glf-pricefrom-var";
    private final static String PRICE_TO_ID = "glf-priceto-var";
    private final static String PRICE_FROM = "5125";
    private final static String PRICE_TO = "10123";
    private final static String ON_STOCK_ID = "glf-onstock-select";
    private final static String TYPE_XPATH = "//div[contains(@class, 'n-filter-block_closed_yes')]";
    private final static String SMARTPHONE_ID = "glf-2142542726-1195192805";
    private final static String ANDROID_ID = "glf-2134007594-select";
    private WebDriver localDriver;

    PageCatalog(WebDriver driver){
        xPath = new XPath();
        localDriver = driver;
    }

    void setPriceFrom() {
        xPath.setFieldValueById(localDriver, PRICE_FROM_ID, PRICE_FROM);
    }

    void setPriceTo() {
        xPath.setFieldValueById(localDriver, PRICE_TO_ID, PRICE_TO);
    }

    void checkOnStock() {
        xPath.clickOnCheckBoxById(localDriver, ON_STOCK_ID);
    }

    void clickOnType() {
        xPath.clickButtonByXPath(localDriver, TYPE_XPATH);
    }

    void checkOnSmartPhone() {
        xPath.clickOnCheckBoxById(localDriver, SMARTPHONE_ID);
    }

    void checkOnAndroid() {
        xPath.clickOnCheckBoxById(localDriver, ANDROID_ID);
    }

}
