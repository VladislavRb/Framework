package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class LandingPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private static final String HOMEPAGE_URL_WITHOUT_LANGUAGE_PART = "http://www.sportmaster.";

    private JavascriptExecutor jsExecutor;
    private String language;

    @FindBy(xpath = "//a[contains(@class, 'changeCountryFlag mainFlag')]")
    private WebElement changeCountryButton;

    @FindBy(xpath = "//a[contains(@class, 'changeCountryFlag-kz')]")
    private WebElement kazakhFlagIcon;

    @FindBy(xpath = "//a[contains(@class, 'changeCountryFlag-by')]")
    private WebElement belarusianFlagIcon;

    @FindBy(xpath = "//a[@class='b-basket-toolbar__link-select citySelect']/span")
    private WebElement currentCitySpan;

    @FindBy(xpath = "//div[@class='wrap']//input[@id='autocomplete-ajax']")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@class='wrap']//input[@class='icn-search']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='products-list__box-title-full']")
    private List<WebElement> searchResultsList;

    private void clickOn(WebElement clickableWebElement) {
        jsExecutor.executeScript("arguments[0].click()", clickableWebElement);
    }

    public LandingPage(WebDriver driver, String lang) {
        super(driver);
        language = lang;
        jsExecutor = (JavascriptExecutor) driver;
    }

    public LandingPage(WebDriver driver) {
        this(driver, "by");
    }

    public LandingPage openPage() {
        String fullPageURL = StringUtils.getFullPageURL(HOMEPAGE_URL_WITHOUT_LANGUAGE_PART, language);

        driver.get(fullPageURL);
        logger.info("opened page with address: " + fullPageURL);

        return this;
    }

    public LandingPage openAvailableLanguagesList() {
        clickOn(changeCountryButton);
        logger.info("opened available languages list");

        return this;
    }

    public LandingPage chooseKazakhFlagIcon() {
        clickOn(kazakhFlagIcon);
        logger.info("chose icon of Kazakh Flag");

        return this;
    }

    public LandingPage chooseBelarusianFlagIcon() {
        clickOn(belarusianFlagIcon);
        logger.info("chose icon of Belarusian flag");

        return this;
    }

    public String getCurrentCity() {
        return currentCitySpan.getText();
    }

    public LandingPage sendSearchStringToSearchInput(String searchString) {
        searchInput.sendKeys(searchString);
        clickOn(searchButton);
        logger.info("sent search string to search input");

        return this;
    }

    public List<String> readAllItemTitlesOnPage() {
        return searchResultsList.stream()
                .map(searchResult -> searchResult.getAttribute("innerText"))
                .collect(Collectors.toList());
    }
}
