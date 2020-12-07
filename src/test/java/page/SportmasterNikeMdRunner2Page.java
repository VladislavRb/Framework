package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import util.StringUtils;

import java.util.Arrays;
import java.util.List;

public class SportmasterNikeMdRunner2Page extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private JavascriptExecutor jsExecutor;

    private final By locatorForPopupWindowHeader = By.xpath("//p[@class='cb-item-popup-head-heading']");
    private final By locatorForSneakersName = By.xpath("//div[@class='cb-item-popup-body-text']");
    private final By locatorForSneakersPrice = By.xpath("//div[@class='cb-item-price-old']");
    private final By locatorForBasketLink = By.xpath("//a[contains(@class, 'go_to_order_basket')]");

    private static final String HOMEPAGE_URL = "http://www.sportmaster.by/catalogitem/krossovki_mugskie_nike_md_runner_2749794n06010/";

    @FindBy(xpath = "//li[@class='cb-item-actions-data-sizes']//li")
    private List<WebElement> sneakersSizes;

    @FindBy(xpath = "//a[text()='В корзину']")
    private WebElement goToBasketLink;

    @FindBy(xpath = "//div[@class='cb-item-popup']")
    private WebElement itemPopupWindow;

    public SportmasterNikeMdRunner2Page(WebDriver driver) {
        super(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }

    public SportmasterNikeMdRunner2Page openPage() {
        driver.get(HOMEPAGE_URL);
        logger.info("opened page with address: " + HOMEPAGE_URL);
        return this;
    }

    public SportmasterNikeMdRunner2Page chooseFirstAvailableSneakersSize() {
        WebElement firstAvailableSizeInput = sneakersSizes.stream()
                .filter(webElement -> webElement.findElement(By.tagName("input")).isEnabled())
                .findFirst()
                .get()
                .findElement(By.tagName("input"));
        logger.info("found first available size input");

        jsExecutor.executeScript(String.format("document.getElementById('%s').setAttribute('class', 'checked')",
                firstAvailableSizeInput.getAttribute("id")));
        logger.info("made first available size input checked");

        return this;
    }

    public SportmasterNikeMdRunner2Page pressOnInBasketButton() {
        jsExecutor.executeScript("arguments[0].click()", goToBasketLink);
        logger.info("pressed on InBasket button");

        return this;
    }

    public List<String> readPopupWindowTitleAndSneakersOrderingStatus() {
        return Arrays.asList(
                itemPopupWindow.findElement(locatorForPopupWindowHeader).getAttribute("innerText").trim(),
                StringUtils.extractSneakersInfo(itemPopupWindow.findElement(locatorForSneakersName).getAttribute("innerText").trim()),
                itemPopupWindow.findElement(locatorForSneakersPrice).getText(),
                itemPopupWindow.findElement(locatorForBasketLink).getAttribute("innerText").trim()
        );
    }
}
