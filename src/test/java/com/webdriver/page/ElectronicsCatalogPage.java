package com.webdriver.page;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.webdriver.utils.SeleniumWebDrivers.driver;

/**
 * @author Artem Nekrasov
 */

public class ElectronicsCatalogPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(ElectronicsCatalogPage.class);

    private static final By mobilePhonesLink = By.linkText("Мобильные телефоны");

    public CatalogPage clickOnMobilePhones() {
        logger.info("Переход в \"Мобильные телефоны\"");
        driver.findElement(mobilePhonesLink).click();
        return new CatalogPage();
    }
}
