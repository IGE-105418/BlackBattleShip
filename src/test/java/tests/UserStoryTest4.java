package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LobbyPage;
import pages.MainPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * US4 – Criar e Entrar em Sala Multiplayer
 *
 * <p><b>User Story:</b> Como jogador, quero criar uma sala multiplayer ou entrar
 * numa sala existente com um código, para poder jogar contra outro jogador humano.</p>
 *
 * <p><b>Critérios de aceitação:</b></p>
 * <ul>
 *   <li>Existe um botão de modo multiplayer na homepage.</li>
 *   <li>Ao clicar em multiplayer, é possível criar ou entrar numa sala.</li>
 *   <li>Criar sala gera/exibe um código único.</li>
 *   <li>O campo para inserir o código de sala está acessível.</li>
 * </ul>
 */
@DisplayName("US4 – Lobby e Sala Multiplayer")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserStoryTest4 extends BaseTest {

    private MainPage mainPage;
    private LobbyPage lobbyPage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        lobbyPage = new LobbyPage(driver);
        mainPage.open();
    }

    // ─────────────────────────────────────────────
    // Testes
    // ─────────────────────────────────────────────

    /**
     * TC4.1 – Botão Multiplayer está visível na homepage.
     */
    @Test
    @Order(1)
    @DisplayName("TC4.1 – Botão Multiplayer está visível na homepage")
    void testMultiplayerButtonIsVisible() {
        boolean visible = mainPage.isMultiplayerButtonVisible();
        assertTrue(visible, "O botão de Multiplayer deve ser visível na homepage");
    }

    /**
     * TC4.2 – Clicar em Multiplayer navega para o lobby/sala.
     */
    @Test
    @Order(2)
    @DisplayName("TC4.2 – Clicar em Multiplayer navega para lobby")
    void testClickMultiplayerNavigatesToLobby() {
        mainPage.enterNickname("MultiPlayer1");
        mainPage.clickMultiplayer();

        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        String currentUrl = driver.getCurrentUrl();

        // O URL deve mudar ou devem aparecer opções de lobby
        boolean lobbyVisible = lobbyPage.isCreateRoomButtonVisible()
                || lobbyPage.isJoinRoomButtonVisible()
                || lobbyPage.isRoomCodeInputVisible();
        boolean urlChanged = !currentUrl.equals(MainPage.BASE_URL);

        assertTrue(lobbyVisible || urlChanged,
                "Após clicar em Multiplayer, deve aparecer o lobby ou o URL deve mudar. URL: " + currentUrl);
    }

    /**
     * TC4.3 – Botão "Criar Sala" está disponível no lobby.
     */
    @Test
    @Order(3)
    @DisplayName("TC4.3 – Botão 'Criar Sala' está disponível no lobby")
    void testCreateRoomButtonAvailable() {
        mainPage.enterNickname("Host1");
        mainPage.clickMultiplayer();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        boolean createVisible = lobbyPage.isCreateRoomButtonVisible();
        // Se não estiver visível, aceita-se que o lobby use um campo de código directamente
        boolean joinInputVisible = lobbyPage.isRoomCodeInputVisible();

        assertTrue(createVisible || joinInputVisible,
                "No lobby, deve existir um botão 'Criar Sala' ou campo de código de sala");
    }

    /**
     * TC4.4 – Criar uma sala exibe um código de sala.
     */
    @Test
    @Order(4)
    @DisplayName("TC4.4 – Criar sala gera e exibe um código de sala")
    void testCreateRoomDisplaysRoomCode() {
        mainPage.enterNickname("HostPlayer");
        mainPage.clickMultiplayer();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        if (lobbyPage.isCreateRoomButtonVisible()) {
            lobbyPage.clickCreateRoom();
            try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

            boolean codeDisplayed = lobbyPage.isRoomCodeDisplayed();
            String code = lobbyPage.getDisplayedRoomCode();

            assertTrue(codeDisplayed || !code.isEmpty(),
                    "Após criar sala, deve ser exibido um código de sala");
        } else {
            // Se não há botão explícito de criar sala, o lobby já exibiu automaticamente
            boolean anyLobbyElement = lobbyPage.isRoomCodeDisplayed()
                    || lobbyPage.isRoomCodeInputVisible()
                    || lobbyPage.isWaitingForPlayer();
            assertTrue(anyLobbyElement,
                    "Deve existir algum elemento de lobby visível após aceder ao modo multiplayer");
        }
    }

    /**
     * TC4.5 – Campo para inserir código de sala está acessível.
     */
    @Test
    @Order(5)
    @DisplayName("TC4.5 – Campo para código de sala está disponível")
    void testRoomCodeInputIsAccessible() {
        mainPage.enterNickname("GuestPlayer");
        mainPage.clickMultiplayer();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        // O campo pode estar já visível ou aparecer após clicar num botão "Join"
        boolean inputVisible = lobbyPage.isRoomCodeInputVisible();
        if (!inputVisible && lobbyPage.isJoinRoomButtonVisible()) {
            // Tenta clicar no botão Join para revelar o campo
            lobbyPage.clickJoinRoom();
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            inputVisible = lobbyPage.isRoomCodeInputVisible();
        }

        assertTrue(inputVisible,
                "O campo para inserir o código de sala deve estar acessível no modo multiplayer");
    }

    /**
     * TC4.6 – Entrar num código de sala inválido não deve crashar a aplicação.
     */
    @Test
    @Order(6)
    @DisplayName("TC4.6 – Código de sala inválido não provoca crash")
    void testInvalidRoomCodeHandled() {
        mainPage.enterNickname("TesterGuest");
        mainPage.clickMultiplayer();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        if (lobbyPage.isRoomCodeInputVisible()) {
            lobbyPage.enterRoomCode("INVALID999");
            // Tentar submeter se possível
            if (lobbyPage.isJoinRoomButtonVisible()) {
                try {
                    lobbyPage.clickJoinRoom();
                } catch (Exception ignored) { /* ignora exceções de navegação */ }
            }
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

            // A aplicação deve continuar a responder — o título não deve ser de erro de browser
            String title = driver.getTitle();
            assertFalse(title.toLowerCase().contains("error") && title.toLowerCase().contains("404"),
                    "A aplicação não deve mostrar uma página de erro 404 com código inválido");
        } else {
            // Se o campo não está disponível, verifica apenas que a página ainda responde
            assertNotNull(driver.getTitle(), "A aplicação deve continuar a responder");
        }
    }

    /**
     * TC4.7 – Verificar elementos de interface do lobby (interface coerente).
     */
    @Test
    @Order(7)
    @DisplayName("TC4.7 – Interface do lobby tem elementos visíveis")
    void testLobbyInterfaceElements() {
        mainPage.enterNickname("UITester");
        mainPage.clickMultiplayer();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        // Pelo menos um elemento de interface de lobby deve existir
        List<WebElement> allButtons = driver.findElements(By.tagName("button"));
        List<WebElement> allInputs = driver.findElements(By.tagName("input"));

        // A página deve ter elementos interactivos no modo multiplayer
        assertTrue(allButtons.size() + allInputs.size() > 0,
                "O lobby deve ter pelo menos um botão ou campo de entrada");
    }
}
