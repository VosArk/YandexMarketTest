package com.webdriver.test;

import com.webdriver.page.CatalogPage;
import com.webdriver.page.YandexMarketPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Artem Nekrasov
 */

public class MarketTest extends BaseTest {

    private YandexMarketPage yandexMarketPage;
    private CatalogPage catalogPage;

    private static final double LOW_RATE = 3.5;
    private static final double HIGH_RATE = 4.5;
    private static final int RESULT_COUNT = 3;
    private static final String PRICE_FROM = "5125";
    private static final String PRICE_TO = "10123";
    private static final String YANDEX_MARKET_PAGE_URL = "https://market.yandex.ru/";

    @BeforeTest
    public void SetUp() {
        super.SetUp();
        yandexMarketPage = new YandexMarketPage();
        catalogPage = new CatalogPage();
    }

    @Test
    public void yandexMarketTest() {
        yandexMarketPage.openPage(YANDEX_MARKET_PAGE_URL);
        yandexMarketPage.selectElectronicsCatalog()
                .clickOnMobilePhones()
                .setPriceFrom(PRICE_FROM)
                .setPriceTo(PRICE_TO)
                .selectOnStock();
        Assert.assertTrue(catalogPage.isCheckBoxOnStockSelected(), "Значение чекбокса \"В продаже\" выбрано неверно");

        catalogPage.selectSmartphoneType();
        Assert.assertTrue(catalogPage.isCheckBoxSmartPhoneSelected(), "Значение чекбокса \"смартфон\" выбрано неверно");

        catalogPage.selectAndroidPhones();
        Assert.assertTrue(catalogPage.isCheckBoxAndroidSelected(), "Значение чекбокса \"Android\" выбрано неверно");

        catalogPage.findPhonesAtRateRange(LOW_RATE, HIGH_RATE, RESULT_COUNT);
    }
}
