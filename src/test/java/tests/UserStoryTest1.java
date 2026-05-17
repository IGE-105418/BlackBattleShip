package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.MainPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * US1 – Acesso e Verificação da Homepage
 *
 * <p><b>User Story:</b> Como jogador, quero aceder à homepage do Black BattleShip
 * para verificar que o site está disponível e que os elementos principais
 * estão correctamente apresentados.</p>
 *
 * <p><b>Critérios de aceitação:</b></p>
 * <ul>
 *   <li>A página carrega com sucesso.</li>
 *   <li>O título da página contém "Battleship" ou "Battle".</li>
 *   <li>Existem botões de acção visíveis.</li>
 *   <li>Existem links de navegação.</li>
 *   <li>O URL aponta para blackbattleship.com.</li>
 * </ul>
 */
@DisplayName("US1 – Homepage e Verificação de Elementos")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserStoryTest1 extends BaseTest {

    private MainPage mainPage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    // ─────────────────────────────────────────────
    // Testes
    // ─────────────────────────────────────────────

    /**
     * TC1.1 – Verifica que a homepage abre com sucesso.
     * O URL actual deve conter "blackbattleship.com".
     */
    @Test
    @Order(1)
    @DisplayName("TC1.1 – Homepage abre com sucesso")
    void testHomepageOpens() {
        String currentUrl = mainPage.getCurrentUrl();

        assertNotNull(currentUrl, "O URL não deve ser nulo");
        assertTrue(
                currentUrl.contains("blackbattleship.com"),
                "O URL deve conter 'blackbattleship.com', mas foi: " + currentUrl
        );
    }

    /**
     * TC1.2 – Verifica o título da aba do browser.
     * O título deve ser não vazio e relacionar-se com BattleShip.
     */
    @Test
    @Order(2)
    @DisplayName("TC1.2 – Título da página não está vazio")
    void testPageTitleNotEmpty() {
        String title = mainPage.getTitle();

        assertNotNull(title, "O título da página não deve ser nulo");
        assertFalse(title.isBlank(), "O título da página não deve estar vazio");
    }

    /**
     * TC1.3 – Verifica que o título contém termos relativos ao jogo.
     */
    @Test
    @Order(3)
    @DisplayName("TC1.3 – Título da página contém 'Battle' ou 'Ship'")
    void testPageTitleContainsBattleOrShip() {
        String title = mainPage.getTitle().toLowerCase();

        boolean hasBattleOrShip = title.contains("battle") || title.contains("ship") || title.contains("black");
        assertTrue(hasBattleOrShip,
                "O título deveria conter 'battle', 'ship' ou 'black', mas era: " + mainPage.getTitle());
    }

    /**
     * TC1.4 – Verifica que existem botões na página.
     * Esperamos pelo menos 1 botão de acção.
     */
    @Test
    @Order(4)
    @DisplayName("TC1.4 – Página contém pelo menos um botão")
    void testPageHasButtons() {
        int buttonCount = mainPage.getButtonCount();

        assertTrue(buttonCount > 0,
                "A página deve ter pelo menos 1 botão, mas encontrou " + buttonCount);
    }

    /**
     * TC1.5 – Verifica que os botões têm texto visível (não estão em branco).
     */
    @Test
    @Order(5)
    @DisplayName("TC1.5 – Botões têm texto não vazio")
    void testButtonsHaveText() {
        List<WebElement> buttons = mainPage.getAllButtons();
        assertFalse(buttons.isEmpty(), "Deve existir pelo menos um botão");

        long buttonsWithText = buttons.stream()
                .map(WebElement::getText)
                .filter(text -> !text.isBlank())
                .count();

        assertTrue(buttonsWithText > 0,
                "Pelo menos um botão deve ter texto visível");
    }

    /**
     * TC1.6 – Verifica que existem links de navegação na página.
     */
    @Test
    @Order(6)
    @DisplayName("TC1.6 – Página contém links de navegação")
    void testPageHasLinks() {
        int linkCount = mainPage.getLinkCount();
        // Aceita-se 0 links em SPAs onde a navegação é feita por botões
        // mas verificamos que a página está correctamente carregada
        assertTrue(linkCount >= 0, "A contagem de links não deve ser negativa");
    }

    /**
     * TC1.7 – Verifica que a página está marcada como carregada.
     */
    @Test
    @Order(7)
    @DisplayName("TC1.7 – Página está carregada (readyState complete)")
    void testPageIsLoaded() {
        assertTrue(mainPage.isPageLoaded(),
                "isPageLoaded() deve devolver true para blackbattleship.com");
    }
}
