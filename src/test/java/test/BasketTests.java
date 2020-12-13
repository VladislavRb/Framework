package test;

import page.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class BasketTests extends CommonConditions{
    @Test
    public void addSneakersToBasketListTest() {
        String url = "http://www.sportmaster.by/catalogitem/krossovki_mugskie_nike_md_runner_2749794n06010/";

        List<String> expectedResults = Arrays.asList(
                "Товар добавлен в корзину",
                "Кроссовки мужские Nike Md Runner 2",
                "176,00 руб.",
                "Перейти в корзину"
        );

        List<String> actualResults = new ProductPage(driver, url)
                .openPage()
                .chooseFirstAvailableProductSize()
                .clickOnAddToBasketLink()
                .readPopupWindowTitleAndProductOrderingStatus();

        Assert.assertEquals(actualResults, expectedResults);
    }
}
