package com.webdriver.test;

import com.webdriver.utils.SeleniumWebDrivers;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static com.webdriver.utils.SeleniumWebDrivers.driver;

/**
 * @author Artem Nekrasov
 */

public abstract class BaseTest {

    @BeforeTest
    public void SetUp() {
        SeleniumWebDrivers.setUpChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void closeDriver() {
        driver.close();
    }
}
