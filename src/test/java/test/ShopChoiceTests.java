package test;

import page.ColumbiaMurrPeakJacketPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.StringUtils;

import java.util.List;

public class ShopChoiceTests extends CommonConditions{
    @Test
    public void sortAvailableShopsByAddressTest() {
        List<String> shopAddressesList = new ColumbiaMurrPeakJacketPage(driver)
                .openPage()
                .addJacketToBasket()
                .goToBasket()
                .openAvailableShopsList()
                .clickOnAddressSortParameter()
                .getAvailableShopAddressesList();

        Assert.assertTrue(StringUtils.stringsListIsSorted(shopAddressesList));
    }
}
