package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected JavascriptExecutor jsExecutor;
    protected final Logger logger = LogManager.getRootLogger();

    protected String url;

    protected abstract AbstractPage openPage();

    protected AbstractPage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;

        this.jsExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
}
