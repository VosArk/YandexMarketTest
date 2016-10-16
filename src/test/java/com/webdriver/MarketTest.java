package com.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MarketTest {
    private final static double LOW_RATE = 3.5;
    private final static double HIGH_RATE = 4.5;
    private final static int RESULT_COUNT = 3;
    private WebDriver driver;
    private String baseUrl;
    private PageYandexMarket pageYandexMarket;
    private PageCatalogModels pageCatalogModels;
    private PageCatalog pageCatalog;


    @BeforeTest
    public void SetUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", ".\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        pageYandexMarket = new PageYandexMarket(driver);
        pageCatalogModels = new PageCatalogModels(driver);
        pageCatalog = new PageCatalog(driver);
        baseUrl = "https://market.yandex.ru/";
        driver.manage().window().maximize();
    }

    @Test
    public void navigateToYandexMarket() {
        System.out.println("1. Выполняется переход по адресу " + baseUrl);
        driver.get(baseUrl);
        String currURL = driver.getCurrentUrl();
        Assert.assertEquals(currURL, baseUrl, "Переход по адресу " + baseUrl + " не осуществлен. Адрес текущей страницы " + currURL);
        System.out.println("Осуществлен переход на " + currURL);
        System.out.println(driver.getTitle());

        System.out.println("2. Открытие вкладки \"Каталог\"");
        pageYandexMarket.clickOnCatalog();
        //assert and sout"success"
        //Assert.assertTrue(!driver.findElements(By.xpath("//...")).isEmpty(),"Отсутствует блок \"...\""); //Популярные товары - вынести название отдельно, по нему и искать

        System.out.println("3. Переход в \"Мобильные телефоны\"");
        pageYandexMarket.clickOnMobilePhones();
        //assert and sout"success"

        System.out.println("4. Переход в \"Расширенный поиск\"");
        pageCatalogModels.clickOnAdvancedSearch();
        //assert

        System.out.println("5. Ввод значения нижнего ценового порога");
        pageCatalog.setPriceFrom();

        System.out.println("6. Ввод значения верхнего ценового порога");
        pageCatalog.setPriceTo();

        System.out.println("7. Кликнуть чекбокс \"В продаже\"");
        pageCatalog.checkOnStock();
        //Assert.assertTrue(checkOnStock.isSelected(), "Значение чекбокса выбрано неверно");

        System.out.println("8. Раскрыть блок \"Тип\"");
        pageCatalog.clickOnType();
        //assert что есть нижний элемент Смартфон

        System.out.println("9. Кликнуть чекбокс \"смартфон\"");
        pageCatalog.checkOnSmartPhone();

        System.out.println("10. Кликнуть чекбокс \"Android\"");
        pageCatalog.checkOnAndroid();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List <WebElement> list = driver.findElements(By.xpath("//*[starts-with(@data-id,'model-')]"));
        System.out.println("Всего устройств отображено на странице: " + list.size() + "\n");

        int tagCount = 0;
        char[] symb = list.get(0).getText().toCharArray();
        for(int i = 0; i < list.get(0).getText().length(); i++) {
            if(symb[i] == '\n') ++tagCount;
        }
        ++tagCount;

        String[][] arraySmartPhones = new String[list.size()][tagCount];
        for(int i = 0; i < list.size(); i++) {
            arraySmartPhones[i] = list.get(i).getText().split("\\n+?");
        }

        List<Double> doubleList = new ArrayList<Double>();
        Random random = new Random();
        List<Integer> randArray = new ArrayList<Integer>();
        int buf;
        while(randArray.size() < list.size()) {
            do {
                buf = random.nextInt(list.size());
            }
            while(randArray.contains(buf));
            randArray.add(buf);
        }

        int rowCounter = 0;
        for(int i = 0; i < list.size(); i++){
            doubleList.add(Double.parseDouble(arraySmartPhones[randArray.get(i)][0]));
            if(doubleList.get(i) >= LOW_RATE && doubleList.get(i) <= HIGH_RATE) {
                System.out.printf("%-30.30s %-30.30s%n", "Номер девайса на странице: ", randArray.get(i) + 1);
                System.out.printf("%-30.30s %-30.30s%n", "Наименование девайса: ", arraySmartPhones[randArray.get(i)][3]);
                System.out.println("Стоимость девайса: " + arraySmartPhones[randArray.get(i)][1] + " " + arraySmartPhones[randArray.get(i)][2] + "\n");
                ++rowCounter;
            }
            if(rowCounter == RESULT_COUNT) break;
        }

        //driver.close();
    }
}
