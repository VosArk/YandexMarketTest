package com.webdriver.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.webdriver.element.CheckBoxElement.selectCheckBox;
import static com.webdriver.utils.SeleniumWebDrivers.driver;

/**
 * @author Artem Nekrasov
 */

public class CatalogPage extends BasePage {
    private static final By priceFromField = By.id("glf-pricefrom-var");
    private static final By priceToField = By.id("glf-priceto-var");
    private static final By onStockCheckbox = By.id("glf-onstock-select");
    private static final By smartPhoneCheckbox = By.xpath("//label[@class='checkbox__label' and contains(text(), 'смартфон')]");
    private static final By smartPhoneCheckboxChecked = By.xpath("//label[@class='checkbox__label' and contains(text(), 'смартфон')]/parent::*[contains(@class, 'checkbox_checked_yes')]");
    private static final By androidCheckbox = By.xpath("//label[@class='checkbox__label' and contains(text(), 'Android')]");
    private static final By androidCheckboxChecked = By.xpath("//label[@class='checkbox__label' and contains(text(), 'Android')]/parent::*[contains(@class, 'checkbox_checked_yes')]");
    private static final String ANDROID_LIST_XPATH = "//*[starts-with(@data-id,'model-')]";

    private static final Logger logger = LoggerFactory.getLogger(CatalogPage.class);

    public CatalogPage setPriceFrom(String price) {
        logger.info("Ввод значения нижнего ценового порога");
        driver.findElement(priceFromField).sendKeys(price);
        return this;
    }

    public CatalogPage setPriceTo(String price) {
        logger.info("Ввод значения верхнего ценового порога");
        driver.findElement(priceToField).sendKeys(price);
        return this;
    }

    public void selectOnStock() {
        logger.info("Выделить чекбокс \"В продаже\"");
        selectCheckBox(onStockCheckbox);
    }

    public void selectSmartphoneType() {
        logger.info("Выделить чекбокс \"смартфон\"");
        selectCheckBox(smartPhoneCheckbox);
    }

    public void selectAndroidPhones() {
        logger.info("Выделить чекбокс \"Android\"");
        selectCheckBox(androidCheckbox);
    }

    public boolean isCheckBoxOnStockSelected() {
        logger.info("Проверка значения чекбокса \"В продаже\"");
        return driver.findElement(onStockCheckbox).isSelected();
    }

    public boolean isCheckBoxSmartPhoneSelected() {
        logger.info("Проверка значения чекбокса \"смартфон\"");
        return driver.findElement(smartPhoneCheckboxChecked).isEnabled();
    }

    public boolean isCheckBoxAndroidSelected() {
        logger.info("Проверка значения чекбокса \"Android\"");
        return driver.findElement(androidCheckboxChecked).isEnabled();
    }

    public void findPhonesAtRateRange(double lowRate, double highRate, int resultCount) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        List<WebElement> smartPhonesList = driver.findElements(By.xpath(ANDROID_LIST_XPATH));
        logger.info("Всего устройств отображено на странице: " + smartPhonesList.size() + "\n");

        int tagCount = getTagCount(smartPhonesList.get(0).getText());
        String[][] smartPhonesArray = new String[smartPhonesList.size()][tagCount];
        for (int i = 0; i < smartPhonesList.size(); i++) {
            smartPhonesArray[i] = smartPhonesList.get(i).getText().split("\\n+?");
        }

        List<Double> rateList = new ArrayList<>();
        List<Integer> randArray = getRandomIndexList(smartPhonesList.size());

        int rowCounter = 0;

        int rateIndex = 2;
        int priceIndex = 1;
        int nameIndex = 0;

        for (int i = 0; i < smartPhonesList.size(); i++) {
            rateList.add(Double.parseDouble(smartPhonesArray[randArray.get(i)][rateIndex]));
            if (rateList.get(i) >= lowRate && rateList.get(i) <= highRate) {
                logger.info("Номер девайса на странице: " + randArray.get(i) + 1);
                logger.info("Наименование девайса: " + smartPhonesArray[randArray.get(i)][nameIndex]);
                logger.info("Стоимость девайса: " + smartPhonesArray[randArray.get(i)][priceIndex] + "\n");
                ++rowCounter;
            }
            if (rowCounter == resultCount) break;
        }
    }

    private List<Integer> getRandomIndexList(int count) {
        List<Integer> range = IntStream.range(0, count).boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(range);
        return range.subList(0, count);
    }

    private int getTagCount(String str) {
        int tagCount = 1;
        for (char element : str.toCharArray()) {
            if (element == '\n') ++tagCount;
        }
        return tagCount;
    }
}
