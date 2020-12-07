package test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.SportmasterNikeMdRunner2Page;

import java.util.Arrays;
import java.util.List;

public class BasketTests {
    private WebDriver driver;

    @BeforeTest (alwaysRun = true)
    public void browserSetup() {
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path + "\\src\\main\\resources\\chromedriver.exe");

        driver = new ChromeDriver();
    }

    @Test
    public void addSneakersToBasketListTest() {
        List<String> expectedResults = Arrays.asList(
                "Товар добавлен в корзину",
                "Кроссовки мужские Nike Md Runner 2",
                "176,00 руб.",
                "Перейти в корзину"
        );

        List<String> actualResults = new SportmasterNikeMdRunner2Page(driver)
                .openPage()
                .chooseFirstAvailableSneakersSize()
                .pressOnInBasketButton()
                .readPopupWindowTitleAndSneakersOrderingStatus();

        Assert.assertEquals(actualResults, expectedResults);
    }

    @AfterTest (alwaysRun = true)
    public void browserQuit() {
        driver.quit();
        driver = null;
    }
}
