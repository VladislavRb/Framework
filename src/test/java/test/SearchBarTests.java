package test;

import page.LandingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchBarTests extends CommonConditions{
    @Test
    public void checkSearchOfOutventureJacketResultsTest() {
        String url = "https://www.sportmaster.by";
        String searchString = "Куртка пуховая для мальчиков Outventure";

        List<String> receivedItemTitlesList = new LandingPage(driver, url)
                .openPage()
                .sendSearchStringToSearchInput(searchString)
                .readAllItemTitlesOnPage();

        Assert.assertTrue(receivedItemTitlesList.stream()
                .allMatch(itemTitle -> itemTitle.contains(searchString)));
    }
}
