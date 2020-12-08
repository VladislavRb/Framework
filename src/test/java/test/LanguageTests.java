package test;

import page.LandingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LanguageTests extends CommonConditions{
    @Test
    public void switchFromBelarusianToKazakhLanguageTest() {
        String cityAfterLanguageSwitch = new LandingPage(driver, "by")
                .openPage()
                .openAvailableLanguagesList()
                .chooseKazakhFlagIcon()
                .getCurrentCity();

        Assert.assertEquals(cityAfterLanguageSwitch, "Нур-Султан");
    }

    @Test
    public void switchFromKazakhToBelarusianLanguageTest() {
        String cityAfterLanguageSwitch = new LandingPage(driver, "kz")
                .openPage()
                .openAvailableLanguagesList()
                .chooseBelarusianFlagIcon()
                .getCurrentCity();

        Assert.assertEquals(cityAfterLanguageSwitch, "Минск");
    }
}
