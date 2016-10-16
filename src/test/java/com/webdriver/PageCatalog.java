package com.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private final static String ANDROID_LIST_XPATH = "//*[starts-with(@data-id,'model-')]";
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

    boolean isTypeAlreadyClicked() {
        //(new WebDriverWait(localDriver, 10)).until(ExpectedConditions.elementToBeClickable(By.id(SMARTPHONE_ID)));
        return xPath.isElementDisplayedById(localDriver, SMARTPHONE_ID);
    }

    boolean isCheckBoxOnStockSelected() {
        return xPath.isCheckBoxSelectedById(localDriver, ON_STOCK_ID);
    }

    boolean isCheckBoxSmartPhoneSelected() {
        return xPath.isCheckBoxSelectedById(localDriver, SMARTPHONE_ID);
    }

    boolean isCheckBoxAndroidSelected() {
        return xPath.isCheckBoxSelectedById(localDriver, ANDROID_ID);
    }

    List<WebElement> getListOfAndroid() {
        return xPath.getListOfWebElements(localDriver, ANDROID_LIST_XPATH);
    }

    List<Integer> getListOfRandomInt(int count) {
        Random random = new Random();
        List<Integer> randArray = new ArrayList<Integer>();
        int buf;
        while(randArray.size() < count) {
            do {
                buf = random.nextInt(count);
            }
            while(randArray.contains(buf));
            randArray.add(buf);
        }
        return randArray;
    }

    int getTagCount(String str) {
        int tagCount = 0;
        char[] symb = str.toCharArray();
        for(int i = 0; i < str.length(); i++) {
            if(symb[i] == '\n') ++tagCount;
        }
        return (++tagCount);
    }
}
