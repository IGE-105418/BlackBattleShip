package iscteiul.ista.blackbattleship.testsSuitesTests;
import iscteiul.ista.blackbattleship.testsSuitesPages.ChatSelenidePage;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;

public class ChatSelenideTest {

    @BeforeEach
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
    }

    @Test
    public void testSendMessageWithSelenide() {
        ChatSelenidePage chatPage = new ChatSelenidePage();

        chatPage.openHomePage();
        sleep(2000);
        chatPage.dealWithCookies();
        chatPage.clickMessagingMenu();
        switchTo().frame(0);
        chatPage.selectParticipant();
        switchTo().defaultContent();

        chatPage.typeMessage("Olá com Selenide!");
        chatPage.sendMessage();

        sleep(2000);
    }
}