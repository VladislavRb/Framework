package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.PageUtils;
import util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class LandingPage extends AbstractPage {
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

    public LandingPage(WebDriver driver, String url) {
        super(driver, url);
        language = StringUtils.extractLangFromLandingPageURL(url);
    }

    public LandingPage openPage() {
        driver.get(url);
        logger.info("opened page with address: " + url);

        return this;
    }

    public LandingPage openAvailableLanguagesList() {
        PageUtils.clickOn(jsExecutor, changeCountryButton);
        logger.info("opened available languages list");

        return this;
    }

    public LandingPage chooseKazakhFlagIcon() {
        PageUtils.clickOn(jsExecutor, kazakhFlagIcon);
        logger.info("chose icon of Kazakh Flag");

        return this;
    }

    public LandingPage chooseBelarusianFlagIcon() {
        PageUtils.clickOn(jsExecutor, belarusianFlagIcon);
        logger.info("chose icon of Belarusian flag");

        return this;
    }

    public String getCurrentCity() {
        return currentCitySpan.getText();
    }

    public LandingPage sendSearchStringToSearchInput(String searchString) {
        searchInput.sendKeys(searchString);
        PageUtils.clickOn(jsExecutor, searchButton);
        logger.info("sent search string to search input");

        return this;
    }

    public List<String> readAllItemTitlesOnPage() {
        return searchResultsList.stream()
                .map(searchResult -> searchResult.getAttribute("innerText"))
                .collect(Collectors.toList());
    }
}
