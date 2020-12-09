package test;

import page.MensHikingHoodiesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FilterTests extends CommonConditions{
    @Test
    public void filterByBrandTest() {
        List<String> allItemTitlesList = new MensHikingHoodiesPage(driver)
                .openPage()
                .clickOnIcePeakCategoryLink()
                .readAllItemTitlesOnPage();

        Assert.assertTrue(allItemTitlesList.stream().allMatch(itemTitle -> itemTitle.contains("IcePeak")));
    }
}
