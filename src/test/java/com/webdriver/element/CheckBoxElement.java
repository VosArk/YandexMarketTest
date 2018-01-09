package com.webdriver.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.webdriver.utils.SeleniumWebDrivers.driver;

/**
 * @author Artem Nekrasov
 */
public class CheckBoxElement {

    public static void selectCheckBox(By bySelector) {
        WebElement element = driver.findElement(bySelector);
        if (!element.isSelected()) element.click();
    }
}
