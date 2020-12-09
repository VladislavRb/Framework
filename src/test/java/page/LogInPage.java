package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends AbstractPage{
    private final Logger logger = LogManager.getRootLogger();
    private static final String HOMEPAGE_URL = "http://www.sportmaster.by/cabinet/";

    private JavascriptExecutor jsExecutor;

    @FindBy(id = "auth-email")
    private WebElement emailInput;

    @FindBy(id = "auth-email-pass")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@class='auth-submit']")
    private WebElement logInButton;

    @FindBy(xpath = "//div[@class='sm-form__errors-block  errorlogin2']/div")
    private WebElement errorHeading;

    @FindBy(xpath = "//div[@class='sm-form__errors-block  errorlogin2']//li")
    private WebElement incorrectLoginOrPasswordLabel;

    public LogInPage(WebDriver driver) {
        super(driver);

        jsExecutor = (JavascriptExecutor) driver;
    }

    public LogInPage openPage() {
        driver.get(HOMEPAGE_URL);
        logger.info("opened page with address: " + HOMEPAGE_URL);

        return this;
    }

    public LogInPage sendEmailAndPasswordToInputFields(String userEmail, String userPassword) {
        emailInput.sendKeys(userEmail);
        passwordInput.sendKeys(userPassword);

        jsExecutor.executeScript("arguments[0].click()", logInButton);

        return this;
    }

    public boolean checkIfErrorHasOccured() {
        try {
            return errorHeading.getAttribute("innerText").equals("Ошибка!") &&
                    incorrectLoginOrPasswordLabel.getAttribute("innerText").equals("Неверный логин или пароль");
        }
        catch (NoSuchElementException exception) {
            return false;
        }
    }
}
