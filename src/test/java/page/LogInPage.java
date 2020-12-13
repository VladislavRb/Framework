package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends AbstractPage{
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

    public LogInPage(WebDriver driver, String url) {
        super(driver, url);
    }

    public LogInPage openPage() {
        driver.get(url);
        logger.info("opened page with address: " + url);

        return this;
    }

    public LogInPage sendEmailAndPasswordToInputFields(String userEmail, String userPassword) {
        emailInput.sendKeys(userEmail);
        passwordInput.sendKeys(userPassword);

        clickOn(logInButton);
        logger.info("sent email and password to input fields");

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
