package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import util.StringUtils;

import java.util.Arrays;
import java.util.List;

public class ProductPage extends AbstractPage {
    @FindBy(xpath = "//li[@class='cb-item-actions-data-sizes']//input")
    private List<WebElement> productSizeInputs;

    @FindBy(xpath = "//a[text()='В корзину']")
    private WebElement addToBasketLink;

    @FindBy(xpath = "//div[@class='cb-item-popup']")
    private WebElement itemPopupWindow;

    @FindBy(xpath = "//p[@class='cb-item-popup-head-heading']")
    private WebElement popupWindowHeader;

    @FindBy(xpath = "//div[@class='cb-item-popup-body-text']")
    private WebElement productNameLabel;

    @FindBy(xpath = "//div[@class='cb-item-price-old']")
    private WebElement productPriceLabel;

    @FindBy(xpath = "//a[contains(@class, 'go_to_order_basket')]")
    private WebElement goToBasketLink;

    public ProductPage(WebDriver driver, String url) {
        super(driver, url);
    }

    public ProductPage openPage() {
        driver.get(url);
        logger.info("opened page with address: " + url);

        return this;
    }

    public ProductPage chooseFirstAvailableProductSize() {
        WebElement firstAvailableSizeInput = productSizeInputs.stream()
                .filter(WebElement::isEnabled)
                .findFirst()
                .get();
        logger.info("found first available size input");

        executeScript("arguments[0].setAttribute('class', 'checked')", firstAvailableSizeInput);
        logger.info("made first available size input checked");

        return this;
    }

    public ProductPage pressOnInBasketButton() {
        clickOn(goToBasketLink);
        logger.info("pressed on InBasket button");

        return this;
    }

    public List<String> readPopupWindowTitleAndProductOrderingStatus() {
        return Arrays.asList(
                itemPopupWindow.getAttribute("innerText").trim(),
                StringUtils.extractSneakersInfo(productNameLabel.getAttribute("innerText").trim()),
                productPriceLabel.getText(),
                goToBasketLink.getAttribute("innerText").trim()
        );
    }
}
