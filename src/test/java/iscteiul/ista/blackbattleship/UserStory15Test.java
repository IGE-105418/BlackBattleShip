package iscteiul.ista.blackbattleship;

import iscteiul.ista.blackbattleship.pages.UserStory15Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

/**
 * Classe de testes (Page Test Class) para a User Story 15 (Chat).
 * Utiliza o padrão Page Object Model.
 */
public class UserStory15Test {
    private WebDriver driver;
    private UserStory15Page chatPage;
    private final String BASE_URL = "https://papergames.io";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // Opcional, para não abrir a janela visualmente
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1024, 768));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        chatPage = new UserStory15Page(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Testa o cenário de envio de uma mensagem no chat (US15).
     * @throws InterruptedException Exceção para o Thread.sleep
     */
    @Test
    public void testSendMessageInChat() throws InterruptedException {
        // 1. Acede à página inicial
        chatPage.openHomePage(BASE_URL);

        // Pequena pausa para simular humano (conforme pedido no guião 5.e)
        Thread.sleep(1000);

        // 2. Navega até mensagens
        chatPage.clickMessagingMenu();
        Thread.sleep(1000);

        // Tratamento de Frames (do .side) - Podes ter de ajustar os índices se falhar
        driver.switchTo().frame(0); // Exemplo genérico, o IDE usou relative=parent

        // 3. Abre a conversa com o participante
        chatPage.selectParticipant();
        Thread.sleep(1000);

        // Volta ao frame principal e muda para outro frame (do .side index=8)
        driver.switchTo().defaultContent();
        // driver.switchTo().frame(8); // Descomenta se necessário, os frames podem ser dinâmicos!

        // 4. Aceita prompts se existirem
        // chatPage.clickContinuePrompt(); // Descomenta se a página bloquear aqui

        // 5. Escreve e envia a mensagem
        chatPage.typeMessage("ola ");
        Thread.sleep(500);
        chatPage.sendMessage();

        // Tempo final para observação
        Thread.sleep(2000);
    }
}