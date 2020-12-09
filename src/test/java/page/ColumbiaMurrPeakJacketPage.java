package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.PageUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ColumbiaMurrPeakJacketPage extends AbstractPage{
    private final Logger logger = LogManager.getRootLogger();
    private static final String HOMEPAGE_URL = "http://www.sportmaster.by/catalogitem/kurtka_uteplennaya_mugskaya_columbia_murr_peak_ii1798761clb466/";

    private JavascriptExecutor jsExecutor;

    @FindBy(xpath = "//li[@class='cb-item-actions-data-sizes']//input")
    private List<WebElement> jacketSizeInputs;

    @FindBy(xpath = "//a[text()='В корзину']")
    private WebElement addToBasketButton;

    @FindBy(xpath = "//a[contains(@class,'button go_to_order_basket')]")
    private WebElement goToBasketButton;

    @FindBy(xpath = "//a[contains(@class,'cart-shop-btn')]")
    private WebElement availableShopsLink;

    @FindBy(xpath = "//div[@data-sort-type='address']/a")
    private WebElement addressSortParameter;

    @FindBy(xpath = "//div[@data-m5-tab-content='list']//span[@class='js-address']")
    private List<WebElement> availableShopAddressesList;

    private void clickOn(WebElement clickableWebElement) {
        jsExecutor.executeScript("arguments[0].click()", clickableWebElement);
    }

    public ColumbiaMurrPeakJacketPage(WebDriver driver) {
        super(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }

    public ColumbiaMurrPeakJacketPage openPage() {
        driver.get(HOMEPAGE_URL);

        return this;
    }

    public ColumbiaMurrPeakJacketPage addJacketToBasket() {
        WebElement firstAvailableSizeInput = PageUtils.getFirstAvailableInput(jacketSizeInputs);
        logger.info("found first available size input");

        PageUtils.makeInputChecked(jsExecutor, firstAvailableSizeInput);
        logger.info("made first available size input checked");

        PageUtils.clickOn(jsExecutor, addToBasketButton);
        logger.info("pressed on addToBasket button");

        return this;
    }

    public ColumbiaMurrPeakJacketPage goToBasket() {
        PageUtils.clickOn(jsExecutor, goToBasketButton);
        logger.info("pressed on goToBasket button");

        return this;
    }

    public ColumbiaMurrPeakJacketPage openAvailableShopsList() {
        PageUtils.clickOn(jsExecutor, availableShopsLink);
        logger.info("opened available shops list");

        return this;
    }

    public ColumbiaMurrPeakJacketPage clickOnAddressSortParameter() {
        PageUtils.clickOn(jsExecutor, addressSortParameter);
        logger.info("chose address sort parameter");

        return this;
    }

    public List<String> getAvailableShopAddressesList() {
        return availableShopAddressesList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
