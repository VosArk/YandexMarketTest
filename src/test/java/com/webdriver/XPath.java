package com.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

class XPath {

    void clickButtonByXPath(WebDriver driver, String xPathStr){
        WebElement element = driver.findElement(By.xpath(xPathStr));
        element.click();
    }

    void clickButtonByLinkName(WebDriver driver, String linkName) {
        WebElement element = driver.findElement(By.linkText(linkName));
        element.click();
    }

    void setFieldValueById(WebDriver driver, String idValue, String fieldValue) {
        WebElement element = driver.findElement(By.id(idValue));
        element.clear();
        element.sendKeys(fieldValue);
    }

    void clickOnCheckBoxById(WebDriver driver, String idValue) {
        WebElement element = driver.findElement(By.id(idValue));
        if(!element.isSelected()) element.click();
    }

    boolean isElementDisplayedById(WebDriver driver, String idValue) {
        return driver.findElement(By.id(idValue)).isDisplayed();
    }

    boolean isCheckBoxSelectedById(WebDriver driver, String idValue) {
        return driver.findElement(By.id(idValue)).isSelected();
    }

    List<WebElement> getListOfWebElements(WebDriver driver, String xPathValue) {
        return driver.findElements(By.xpath(xPathValue));
    }

}
