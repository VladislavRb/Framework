package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.FeedbackPage;

public class SendFeedbackTests extends CommonConditions{
    @Test
    public void sendFeedbackWithNotAllObligatoryFieldsFilledTest() {
        String url = "http://www.sportmaster.by/contacts/";

        String fullName = "Иванов Иван Иванович";
        String phoneNumber = "7777777";
        String email = "";
        String feedbackTopic = "Требуется консультация";
        String shopAddress = "г. Минск, ул. Кульман, д.3, пом. 201А, ТЦ \"Coolman\", 2 этаж";
        String productSetNumber = "";
        String productSize = "";
        String feedbackMessage = "";

        boolean feedbackIsSent = new FeedbackPage(driver, url)
                .openPage()
                .fillObligatoryFields(fullName, phoneNumber, email, feedbackTopic, shopAddress, productSetNumber, productSize, feedbackMessage)
                .clickOnSendFeedbackButton()
                .feedbackIsSent();

        Assert.assertFalse(feedbackIsSent);
    }
}
