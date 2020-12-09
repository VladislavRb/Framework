package util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

public class PageUtils {
    public static void clickOn(JavascriptExecutor jsExecutor, WebElement clickableWebElement) {
        jsExecutor.executeScript("arguments[0].click()", clickableWebElement);
    }

    public static void makeInputChecked(JavascriptExecutor jsExecutor, WebElement input) {
        jsExecutor.executeScript(String.format("document.getElementById('%s').setAttribute('class', 'checked')",
                input.getAttribute("id")));
    }

    public static WebElement getFirstAvailableInput(List<WebElement> availableInputsList) {
        return availableInputsList.stream()
                .filter(WebElement::isEnabled)
                .findFirst()
                .get();
    }

    public static WebElement getWebElementByXpath(WebDriver driver, String xpath, int timeout, int pollingPeriod) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingPeriod))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return wait
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath(xpath)));
    }

    public static WebElement getWebElementByXpath(WebDriver driver, String xpath) {
        return getWebElementByXpath(driver, xpath, 10, 1);
    }

    public static void setAttribute(JavascriptExecutor jsExecutor, String cssSelector, String attributeName, String attributeValue) {
        jsExecutor.executeScript("document.querySelector(arguments[0]).setAttribute(arguments[1], arguments[2])",
                cssSelector, attributeName, attributeValue);
    }
}
