package util;

import org.openqa.selenium.*;

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
}
