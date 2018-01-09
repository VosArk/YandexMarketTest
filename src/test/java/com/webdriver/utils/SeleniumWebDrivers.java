package com.webdriver.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Artem Nekrasov
 */

public class SeleniumWebDrivers {
    public static WebDriver driver;
    private static final String CHROME_DRIVER_PATH = ".\\chromedriver_win32\\chromedriver.exe";

    public static void setUpChromeDriver() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
    }
}
