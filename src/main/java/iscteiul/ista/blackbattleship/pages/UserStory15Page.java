package iscteiul.ista.blackbattleship.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object Class correspondente à página de Chat e interações iniciais (US15).
 * Encapsula os localizadores (locators) e os métodos de interação.
 */
public class UserStory15Page {
    private WebDriver driver;
    private WebDriverWait wait;

    // Localizadores (extraídos do teu ficheiro.side)
    private By messagingMenuLocator = By.xpath("//span[contains(.,'Messaging')]");
    private By participantNameLocator = By.xpath("//span[contains(.,'Teste02')]");
    private By continuePromptLocator = By.cssSelector(".continue-prompt-text");
    private By chatInputLocator = By.xpath("//textarea");
    private By sendButtonLocator = By.xpath("//form/button/span[3]");

    /**
     * Construtor da classe Page Object.
     * @param driver O WebDriver instanciado no teste.
     */
    public UserStory15Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Navega para a página inicial base.
     */
    public void openHomePage(String baseUrl) {
        driver.get(baseUrl + "/en/");
    }

    /**
     * Clica no menu de mensagens.
     */
    public void clickMessagingMenu() {
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(messagingMenuLocator));
        menu.click();
    }

    /**
     * Seleciona o participante para iniciar a conversa.
     */
    public void selectParticipant() {
        WebElement participant = wait.until(ExpectedConditions.elementToBeClickable(participantNameLocator));
        participant.click();
    }

    /**
     * Fecha prompts ou popups que possam aparecer antes de focar na conversa.
     */
    public void clickContinuePrompt() {
        WebElement prompt = wait.until(ExpectedConditions.elementToBeClickable(continuePromptLocator));
        prompt.click();
    }

    /**
     * Escreve uma mensagem na caixa de texto.
     * @param message A mensagem a enviar.
     */
    public void typeMessage(String message) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(chatInputLocator));
        input.click();
        input.sendKeys(message);
    }

    /**
     * Clica no botão para enviar a mensagem.
     */
    public void sendMessage() {
        WebElement sendBtn = wait.until(ExpectedConditions.elementToBeClickable(sendButtonLocator));
        sendBtn.click();
    }
}