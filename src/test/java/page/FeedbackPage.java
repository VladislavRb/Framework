package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FeedbackPage extends AbstractPage{
    @FindBy(xpath = "//div[@data-m5-tab-content='contact-type-2']//input[@name='send[ФИО]']")
    private WebElement fullNameInput;

    @FindBy(xpath = "//div[@data-m5-tab-content='contact-type-2']//input[@name='send[Контактный телефон]']")
    private WebElement phoneNumberInput;

    @FindBy(xpath = "//div[@data-m5-tab-content='contact-type-2']//input[@name='send[E-mail]']")
    private WebElement emailInput;

    @FindBy(xpath = "//div[@data-m5-tab-content='contact-type-2']//select[@name='send[Тема обращения2]']")
    private WebElement feedbackTopicSelect;

    @FindBy(xpath = "//div[@data-m5-tab-content='contact-type-2']//select[@name='send[Адрес магазина]']")
    private WebElement shopAddressSelect;

    @FindBy(xpath = "//div[@data-m5-tab-content='contact-type-2']//input[@name='send[Артикул товара]']")
    private WebElement productSetNumberInput;

    @FindBy(xpath = "//div[@data-m5-tab-content='contact-type-2']//input[@name='send[Размер]']")
    private WebElement productSizeInput;

    @FindBy(xpath = "//div[@data-m5-tab-content='contact-type-2']//textarea[@name='send[Текст обращения]']")
    private WebElement feedbackMessageTextarea;

    @FindBy(xpath = "//div[@data-m5-tab-content='contact-type-2']//input[@id='user-agreement']")
    private WebElement userAgreementCheckbox;

    @FindBy(xpath = "//div[@data-m5-tab-content='contact-type-2']//button")
    private WebElement sendFeedbackButton;

    @FindBy(xpath = "//div[@class='popup-sp']//div[@class='h1']")
    private WebElement successfulFeedbackDeliveryStatusLabel;

    @FindBy(xpath = "//div[@class='rel has-error']")
    private List<WebElement> incorrectlyFilledFields;

    public FeedbackPage(WebDriver driver, String url) {
        super(driver, url);
    }

    public FeedbackPage openPage() {
        driver.get(url);
        logger.info("opened page with address: " + url);

        return this;
    }

    public FeedbackPage fillObligatoryFields(
            String fullName, String phoneNumber, String email, String feedbackTopic, String shopAddress, String productSetNumber, String productSize, String feedbackMessage) {
        fullNameInput.sendKeys(fullName);
        phoneNumberInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), "55");
        phoneNumberInput.sendKeys(phoneNumber);
        emailInput.sendKeys(email);
        new Select(feedbackTopicSelect).selectByVisibleText(feedbackTopic);
        new Select(shopAddressSelect).selectByVisibleText(shopAddress);
        productSetNumberInput.sendKeys(productSetNumber);
        productSizeInput.sendKeys(productSize);
        feedbackMessageTextarea.sendKeys(feedbackMessage);

        clickOn(userAgreementCheckbox);
        executeScript("document.getElementsByTagName('iframe')[1].remove();");
        logger.info("sent all provided data to corresponding fields");

        return this;
    }

    public FeedbackPage clickOnSendFeedbackButton() {
        executeScript("arguments[0].removeAttribute('disabled')", sendFeedbackButton);
        clickOn(sendFeedbackButton);
        logger.info("clicked on send feedback button");

        return this;
    }

    public boolean feedbackIsSent() {
        return incorrectlyFilledFields.size() == 0;
    }
}
