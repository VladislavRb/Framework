package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public abstract class AbstractPage {
    protected final Logger logger = LogManager.getRootLogger();
    protected WebDriver driver;
    protected JavascriptExecutor jsExecutor;
    protected String url;

    private Wait<WebDriver> wait;

    protected abstract AbstractPage openPage();

    protected void executeScript(String script, Object... scriptArguments) {
        jsExecutor.executeScript(script, scriptArguments);
    }

    protected void clickOn(WebElement clickableElement) {
        executeScript("arguments[0].click()", clickableElement);
    }

    protected WebElement getWebElementByXpath(String xpath) {
        return wait
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath(xpath)));
    }

    protected AbstractPage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;

        this.jsExecutor = (JavascriptExecutor) driver;

        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        PageFactory.initElements(driver, this);
    }
}
