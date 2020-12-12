package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class LandingPage extends AbstractPage {
    @FindBy(xpath = "//meta[@property='og:locale']")
    private WebElement langMetaTag;

    @FindBy(xpath = "//a[contains(@class, 'changeCountryFlag mainFlag')]")
    private WebElement changeCountryButton;

    @FindBy(xpath = "//a[contains(@class, 'changeCountryFlag')]")
    private List<WebElement> flagIcons;

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
    }

    public LandingPage openPage() {
        driver.get(url);
        logger.info("opened page with address: " + url);

        return this;
    }

    public LandingPage openAvailableLanguagesList() {
        clickOn(changeCountryButton);
        logger.info("opened available languages list");

        return this;
    }

    public LandingPage chooseFlagIcon(String countryString) {
        WebElement chosenCountryFlagIcon = flagIcons.stream()
                .filter(flagIcon -> flagIcon.getAttribute("class").contains(countryString))
                .collect(Collectors.toList()).get(0);

        clickOn(chosenCountryFlagIcon);
        logger.info(String.format("chose icon of %s flag", countryString));

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
