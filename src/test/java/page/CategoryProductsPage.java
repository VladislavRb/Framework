package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryProductsPage extends AbstractPage {
    @FindBy(xpath = "//a[@class='products-list__box-name']/div[1]")
    private List<WebElement> filteredProductTitlesList;

    public CategoryProductsPage(WebDriver driver, String url) {
        super(driver, url);
    }

    public CategoryProductsPage openPage() {
        driver.get(url);
        logger.info("opened page with address: " + url);

        return this;
    }

    public CategoryProductsPage clickOnCategoryLink(String category) {
        clickOn(getWebElementByXpath(String.format("//a[contains(text(), '%s')]", category)));
        logger.info(String.format("clicked on %s category link", category));

        return this;
    }

    public List<String> readAllItemTitlesOnPage() {
        return filteredProductTitlesList.stream()
                .map(webElement -> webElement.getAttribute("innerText"))
                .collect(Collectors.toList());
    }
}
