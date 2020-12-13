package test;

import page.CategoryProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FilterTests extends CommonConditions{
    @Test
    public void filterByIcePeakBrandTest() {
        String url = "http://www.sportmaster.by/catalog/mugskaya_odegda88-dgemperi_i_sviteri26/?filter-170=1866";

        List<String> allItemTitlesList = new CategoryProductsPage(driver, url)
                .openPage()
                .clickOnCategoryLink("IcePeak")
                .readAllItemTitlesOnPage();

        Assert.assertTrue(allItemTitlesList.stream()
                .allMatch(itemTitle -> itemTitle.contains("IcePeak")));
    }
}
