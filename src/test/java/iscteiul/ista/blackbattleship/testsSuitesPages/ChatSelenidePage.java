package iscteiul.ista.blackbattleship.testsSuitesPages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class ChatSelenidePage {

    // Localizadores
    private final SelenideElement messagingMenu = $x("//a[contains(@href, '/chat') or contains(., 'Messaging') or contains(., 'Mensagens') or contains(., 'Chat')]");
    private final SelenideElement participant = $x("//span[contains(.,'Teste02')]");
    private final SelenideElement chatInput = $x("//textarea");
    private final SelenideElement sendButton = $x("//form/button/span[3]");
    private final SelenideElement acceptCookiesBtn = $x("//button[contains(., 'Accept') or contains(., 'Consent')]");

    public void openHomePage() {
        open("https://papergames.io/en/");
    }

    public void dealWithCookies() {
        try {
            // Espera até 5 segundos para ver se o botão aparece, sem quebrar o teste se não aparecer
            if (acceptCookiesBtn.exists()) {
                acceptCookiesBtn.click();
            }
        } catch (Exception e) {
            System.out.println("Nenhum pop-up de cookies detetado. A avançar...");
        }
    }

    public void clickMessagingMenu() {
        messagingMenu.click();
    }

    public void selectParticipant() {
        participant.click();
    }

    public void typeMessage(String message) {
        chatInput.setValue(message);
    }

    public void sendMessage() {
        sendButton.click();
    }
}