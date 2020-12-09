package test;

import page.NikeMdRunner2Page;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class BasketTests extends CommonConditions{
    @Test
    public void addSneakersToBasketListTest() {
        List<String> expectedResults = Arrays.asList(
                "Товар добавлен в корзину",
                "Кроссовки мужские Nike Md Runner 2",
                "176,00 руб.",
                "Перейти в корзину"
        );

        List<String> actualResults = new NikeMdRunner2Page(driver)
                .openPage()
                .chooseFirstAvailableSneakersSize()
                .pressOnInBasketButton()
                .readPopupWindowTitleAndSneakersOrderingStatus();

        Assert.assertEquals(actualResults, expectedResults);
    }
}
