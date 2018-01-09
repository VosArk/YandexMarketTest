package com.webdriver.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import static com.webdriver.utils.SeleniumWebDrivers.driver;

/**
 * @author Artem Nekrasov
 */

public abstract class BasePage {
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    public void openPage(String pageUrl) {
        logger.info("Выполняется переход по адресу " + pageUrl);
        driver.get(pageUrl);
        String currURL = driver.getCurrentUrl();
        Assert.assertEquals(currURL, pageUrl, "Переход по адресу " + pageUrl + " не осуществлен. Адрес текущей страницы " + currURL);
        logger.info("Осуществлен переход на " + currURL);
        logger.info(driver.getTitle());
    }
}
