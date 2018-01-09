package com.webdriver.page;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.webdriver.utils.SeleniumWebDrivers.driver;

/**
 * @author Artem Nekrasov
 */

public class YandexMarketPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(YandexMarketPage.class);

    private static final By catalog = By.linkText("Электроника");

    public ElectronicsCatalogPage selectElectronicsCatalog() {
        logger.info("Открытие вкладки \"Электроника\"");
        driver.findElement(catalog).click();
        return new ElectronicsCatalogPage();
    }
}
