package test;

import page.SportmasterMensHikingHoodiesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.List;

public class FilterTests extends CommonConditions{
    @Test
    public void filterByBrandTest() {
        List<String> allItemTitlesList = new SportmasterMensHikingHoodiesPage(driver)
                .openPage()
                .clickOnIcePeakCategoryLink()
                .readAllItemTitlesOnPage();

        Assert.assertTrue(allItemTitlesList.stream().allMatch(itemTitle -> itemTitle.contains("IcePeak")));
    }
}
