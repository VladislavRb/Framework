package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    private String getFullPageURL() {
        return new StringBuilder(HOMEPAGE_URL_WITHOUT_LANGUAGE_PART).append(language).append("/").toString();
    }

    private void clickOn(WebElement clickableWebElement) {
        jsExecutor.executeScript("arguments[0].click()", clickableWebElement);
    }

    public LandingPage(WebDriver driver, String lang) {
        super(driver);
        language = lang;
        jsExecutor = (JavascriptExecutor) driver;
    }

    public LandingPage openPage() {
        String fullPageURL = getFullPageURL();

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
}
