package test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.SportmasterMensHikingHoodiesPage;

import java.util.List;

public class FilterTests {
    private WebDriver driver;

    @BeforeTest (alwaysRun = true)
    public void browserSetup() {
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path + "\\src\\main\\resources\\chromedriver.exe");

        driver = new ChromeDriver();
    }

    @Test
    public void filterByBrandTest() {
        List<String> allItemTitlesList = new SportmasterMensHikingHoodiesPage(driver)
                .openPage()
                .clickOnIcePeakCategoryLink()
                .readAllItemTitlesOnPage();

        Assert.assertTrue(allItemTitlesList.stream().allMatch(itemTitle -> itemTitle.contains("IcePeak")));
    }

    @AfterTest (alwaysRun = true)
    public void browserQuit() {
        driver.quit();
        driver = null;
    }
}
