package test;

import page.LogInPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogInTests extends CommonConditions{
    @Test
    public void logInUserWithNotAllObligatoryFieldsFilled() {
        String userEmail = "exp@gmail.com";

        boolean gotLogInError = new LogInPage(driver)
                .openPage()
                .sendEmailAndPasswordToInputFields(userEmail, "")
                .checkIfErrorHasOccured();

        Assert.assertTrue(gotLogInError);
    }
}
