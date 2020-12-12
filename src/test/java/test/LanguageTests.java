package test;

import page.LandingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LanguageTests extends CommonConditions{
    @Test
    public void switchFromBelarusianToKazakhLanguageTest() {
        String url = "https://www.sportmaster.by";

        String cityAfterLanguageSwitch = new LandingPage(driver, url)
                .openPage()
                .openAvailableLanguagesList()
                .chooseFlagIcon("kz")
                .getCurrentCity();

        Assert.assertEquals(cityAfterLanguageSwitch, "Нур-Султан");
    }

    @Test
    public void switchFromKazakhToBelarusianLanguageTest() {
        String url = "https://www.sportmaster.kz";

        String cityAfterLanguageSwitch = new LandingPage(driver, url)
                .openPage()
                .openAvailableLanguagesList()
                .chooseFlagIcon("by")
                .getCurrentCity();

        Assert.assertEquals(cityAfterLanguageSwitch, "Минск");
    }
}
