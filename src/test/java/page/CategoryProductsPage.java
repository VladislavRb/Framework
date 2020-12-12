package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class MensHikingHoodiesPage extends AbstractPage {
    @FindBy(xpath = "//a[contains(text(), 'IcePeak')]")
    private WebElement IcePeakCategoryLink;

    @FindBy(xpath = "//a[@class='products-list__box-name']/div[1]")
    private List<WebElement> filteredHoodieTitlesList;

    public MensHikingHoodiesPage(WebDriver driver) {
        super(driver);
    }

    public MensHikingHoodiesPage openPage() {
        driver.get(HOMEPAGE_URL);
        logger.info("opened page with address: " + HOMEPAGE_URL);

        return this;
    }

    public MensHikingHoodiesPage clickOnIcePeakCategoryLink() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", IcePeakCategoryLink);
        logger.info("clicked on IcePeak Category Link");

        return this;
    }

    public List<String> readAllItemTitlesOnPage() {
        return filteredHoodieTitlesList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
