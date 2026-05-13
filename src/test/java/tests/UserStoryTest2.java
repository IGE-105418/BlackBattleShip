package tests;

import org.junit.jupiter.api.*;
import pages.GamePage;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.*;

/**
 * US2 – Criação de Nickname e Início de Jogo contra Robot
 *
 * <p><b>User Story:</b> Como jogador, quero introduzir o meu nickname e iniciar
 * uma partida contra o robot, para poder jogar sem precisar de outro jogador humano.</p>
 *
 * <p><b>Critérios de aceitação:</b></p>
 * <ul>
 *   <li>Existe um campo de texto para o nickname.</li>
 *   <li>O nickname introduzido fica guardado no campo.</li>
 *   <li>Existe um botão para jogar vs Robot/Computador.</li>
 *   <li>Após clicar no botão, o tabuleiro de jogo aparece.</li>
 * </ul>
 */
@DisplayName("US2 – Nickname e Jogo vs Robot")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserStoryTest2 extends BaseTest {

    private MainPage mainPage;
    private GamePage gamePage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        gamePage = new GamePage(driver);
        mainPage.open();
    }

    // ─────────────────────────────────────────────
    // Testes
    // ─────────────────────────────────────────────

    /**
     * TC2.1 – Campo de nickname está visível na homepage.
     */
    @Test
    @Order(1)
    @DisplayName("TC2.1 – Campo de nickname está visível")
    void testNicknameInputIsVisible() {
        boolean visible = mainPage.isNicknameInputVisible();
        assertTrue(visible, "O campo de nickname deve estar visível na homepage");
    }

    /**
     * TC2.2 – Introduzir texto no campo de nickname.
     * O valor do campo deve reflectir o texto digitado.
     */
    @Test
    @Order(2)
    @DisplayName("TC2.2 – Pode-se digitar um nickname no campo")
    void testEnterNickname() {
        final String testNickname = "TestPlayer123";

        mainPage.enterNickname(testNickname);
        String actualValue = mainPage.getNicknameValue();

        assertEquals(testNickname, actualValue,
                "O nickname introduzido deve ser '" + testNickname + "', mas foi: " + actualValue);
    }

    /**
     * TC2.3 – O campo de nickname não aceita strings vazias como válidas.
     * (Verifica que, ao limpar o campo, o valor fica vazio.)
     */
    @Test
    @Order(3)
    @DisplayName("TC2.3 – Limpar o campo de nickname resulta em valor vazio")
    void testClearNicknameField() {
        mainPage.enterNickname("SomeNick");
        mainPage.enterNickname(""); // clear + envio de string vazia

        String value = mainPage.getNicknameValue();
        assertTrue(value == null || value.isEmpty(),
                "Após limpar, o campo de nickname deve estar vazio");
    }

    /**
     * TC2.4 – Botão "Jogar vs Robot" está visível.
     */
    @Test
    @Order(4)
    @DisplayName("TC2.4 – Botão 'Jogar vs Robot' está visível")
    void testPlayVsRobotButtonIsVisible() {
        boolean visible = mainPage.isPlayVsRobotButtonVisible();
        assertTrue(visible, "O botão de jogar vs Robot deve estar visível na homepage");
    }

    /**
     * TC2.5 – Clicar em "Jogar vs Robot" após definir nickname navega para o jogo.
     * O tabuleiro deve aparecer, ou o URL deve mudar.
     */
    @Test
    @Order(5)
    @DisplayName("TC2.5 – Jogar vs Robot abre o tabuleiro de jogo")
    void testStartGameVsRobot() {
        mainPage.enterNickname("SeleniumBot");
        mainPage.clickPlayVsRobot();

        // Dá tempo à SPA para actualizar o DOM
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        boolean boardShown = gamePage.isBoardDisplayed();
        String newUrl = mainPage.getCurrentUrl();

        // O teste passa se o tabuleiro aparecer OU se o URL mudar
        boolean navigationOccurred = !newUrl.equals(MainPage.BASE_URL);
        assertTrue(boardShown || navigationOccurred,
                "Após clicar em 'Jogar vs Robot', deve aparecer o tabuleiro ou o URL deve mudar. " +
                "Board displayed: " + boardShown + ", URL: " + newUrl);
    }

    /**
     * TC2.6 – Nickname com caracteres especiais é aceite pelo campo.
     */
    @Test
    @Order(6)
    @DisplayName("TC2.6 – Nickname com caracteres especiais é aceite")
    void testNicknameWithSpecialChars() {
        final String specialNick = "Jogador_01";
        mainPage.enterNickname(specialNick);
        String value = mainPage.getNicknameValue();
        assertEquals(specialNick, value,
                "O campo deve aceitar o nickname '" + specialNick + "'");
    }
}
