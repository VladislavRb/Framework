package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class CommonConditions {
    protected WebDriver driver;
    private static final String RESOURCES_PATH = "\\src\\main\\resources";

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        System.setProperty("webdriver.chrome.driver", RESOURCES_PATH + "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterTest(alwaysRun = true)
    public void browserQuit() {
        driver.quit();
    }
}
