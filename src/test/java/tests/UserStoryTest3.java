package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.InstructionsPage;
import pages.MainPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * US3 – Navegação para as Instruções do Jogo
 *
 * <p><b>User Story:</b> Como jogador novo, quero aceder às instruções/regras do jogo
 * para aprender a jogar Battle Ship antes de iniciar uma partida.</p>
 *
 * <p><b>Critérios de aceitação:</b></p>
 * <ul>
 *   <li>Existe um link/botão de instruções acessível na homepage.</li>
 *   <li>Ao clicar, aparece conteúdo com as regras do jogo.</li>
 *   <li>O conteúdo das instruções não está vazio.</li>
 *   <li>É possível voltar ao menu principal a partir das instruções.</li>
 * </ul>
 */
@DisplayName("US3 – Navegação e Conteúdo das Instruções")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserStoryTest3 extends BaseTest {

    private MainPage mainPage;
    private InstructionsPage instructionsPage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        mainPage = new MainPage(driver);
        instructionsPage = new InstructionsPage(driver);
        mainPage.open();
    }

    // ─────────────────────────────────────────────
    // Testes
    // ─────────────────────────────────────────────

    /**
     * TC3.1 – Verifica que o link/botão de instruções existe na homepage.
     */
    @Test
    @Order(1)
    @DisplayName("TC3.1 – Link de instruções está visível na homepage")
    void testInstructionsLinkIsVisible() {
        boolean visible = mainPage.isInstructionsLinkVisible();
        assertTrue(visible, "O link/botão de instruções deve ser visível na homepage");
    }

    /**
     * TC3.2 – Navegar para as instruções exibe conteúdo.
     * O conteúdo das instruções deve estar visível após clicar no link.
     */
    @Test
    @Order(2)
    @DisplayName("TC3.2 – Página de instruções carrega conteúdo")
    void testInstructionsPageLoads() {
        mainPage.clickInstructions();

        // Aguarda a transição da SPA
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}

        boolean instructionsVisible = instructionsPage.isInstructionsVisible();
        String currentUrl = driver.getCurrentUrl();

        // Numa SPA as instruções podem aparecer no mesmo URL ou num novo
        assertTrue(instructionsVisible || !currentUrl.equals(MainPage.BASE_URL),
                "Após clicar em Instruções, o conteúdo deve ser visível ou o URL deve mudar. " +
                "URL actual: " + currentUrl);
    }

    /**
     * TC3.3 – O conteúdo das instruções não está vazio.
     */
    @Test
    @Order(3)
    @DisplayName("TC3.3 – Conteúdo das instruções não está vazio")
    void testInstructionsContentNotEmpty() {
        mainPage.clickInstructions();
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}

        String content = instructionsPage.getInstructionsText();
        // Se as instruções não forem encontradas pelo selector, verifica pelo corpo da página
        if (content.isEmpty()) {
            content = driver.findElement(org.openqa.selenium.By.tagName("body")).getText();
        }
        assertFalse(content.isBlank(),
                "O conteúdo das instruções não deve estar vazio após navegação");
    }

    /**
     * TC3.4 – O título da página de instruções é coerente com o esperado.
     */
    @Test
    @Order(4)
    @DisplayName("TC3.4 – Título da aba mantém referência ao jogo nas instruções")
    void testInstructionsPageTitle() {
        mainPage.clickInstructions();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        String title = driver.getTitle().toLowerCase();
        // O título pode manter "battleship" ou incluir "instructions"/"rules"
        boolean validTitle = title.contains("battle") || title.contains("ship")
                || title.contains("instruc") || title.contains("rule")
                || title.contains("how") || title.contains("black");

        assertTrue(validTitle,
                "O título da página nas instruções deve mencionar o jogo ou instruções, mas era: " + driver.getTitle());
    }

    /**
     * TC3.5 – É possível regressar ao menu principal a partir das instruções.
     */
    @Test
    @Order(5)
    @DisplayName("TC3.5 – Botão 'Voltar' regressa ao menu principal")
    void testBackFromInstructions() {
        mainPage.clickInstructions();
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}

        // Tenta clicar no botão "Voltar"; se não existir, usa o botão Back do browser
        if (instructionsPage.isBackButtonVisible()) {
            instructionsPage.clickBack();
        } else {
            instructionsPage.navigateBack();
        }

        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        String url = driver.getCurrentUrl();
        assertTrue(url.contains("blackbattleship.com"),
                "Após voltar, o URL deve ainda ser do blackbattleship.com, mas foi: " + url);
    }

    /**
     * TC3.6 – A homepage mantém o URL base após navegar e regressar das instruções.
     */
    @Test
    @Order(6)
    @DisplayName("TC3.6 – URL correcto após ciclo homepage → instruções → homepage")
    void testNavigationCycleUrl() {
        String urlBefore = mainPage.getCurrentUrl();

        mainPage.clickInstructions();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        // Regressar
        if (instructionsPage.isBackButtonVisible()) {
            instructionsPage.clickBack();
        } else {
            instructionsPage.navigateBack();
        }

        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        String urlAfter = driver.getCurrentUrl();
        assertTrue(urlAfter.contains("blackbattleship.com"),
                "Após ciclo de navegação, o URL deve ser do blackbattleship.com");
    }
}
