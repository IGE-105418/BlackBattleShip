package testsuite;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import testsuite.pages.MainPageSelenide;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.junit.jupiter.api.Assertions.*;

/**
 * US1 – Acesso e Verificação da Homepage (versão Selenide).
 *
 * <p>Parte 2 da ficha laboratorial: usa Selenide + Allure Report.</p>
 *
 * <p>Cada membro do grupo deve criar o seu próprio pacote em
 * {@code src/test/java/testsuite_NUMERO/} e implementar as suas user stories.</p>
 */
@Feature("US1 – Homepage")
@DisplayName("US1 – Homepage (Selenide + Allure)")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserStorySelenideTest1 {

    private static final String BASE_URL = "https://www.blackbattleship.com/";
    private MainPageSelenide mainPage;

    /**
     * Configuração global (executada uma vez para toda a classe).
     * Regista o listener Allure para integração com relatórios.
     */
    @BeforeAll
    static void setUpAll() {
        Configuration.browserSize = "1280x900";
        Configuration.timeout = 15_000;
        Configuration.pageLoadTimeout = 30_000;
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    /** Abre a homepage antes de cada teste. */
    @BeforeEach
    void setUp() {
        open(BASE_URL);
        mainPage = new MainPageSelenide();
    }

    /** Fecha o browser após cada teste. */
    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    // ─────────────────────────────────────────────────────────────────────────

    /**
     * TC1.1 – Homepage abre com URL correcto.
     */
    @Test
    @Order(1)
    @Story("TC1.1 – URL correcto")
    @Description("Verifica que o URL após abertura contém 'blackbattleship.com'")
    @DisplayName("TC1.1 – Homepage abre com URL correcto")
    void testHomepageUrl() {
        assertTrue(Selenide.webdriver().driver().url().contains("blackbattleship.com"),
                "URL deve conter 'blackbattleship.com'");
    }

    /**
     * TC1.2 – Título da página não está vazio.
     */
    @Test
    @Order(2)
    @Story("TC1.2 – Título não vazio")
    @Description("O título da aba do browser não deve estar em branco")
    @DisplayName("TC1.2 – Título não está vazio")
    void testPageTitleNotEmpty() {
        assertFalse(title().isBlank(), "O título da página não deve estar vazio");
    }

    /**
     * TC1.3 – Página tem pelo menos um botão visível.
     */
    @Test
    @Order(3)
    @Story("TC1.3 – Botões visíveis")
    @Description("Deve existir pelo menos um botão visível na homepage")
    @DisplayName("TC1.3 – Pelo menos um botão visível")
    void testPageHasVisibleButton() {
        assertFalse(mainPage.allButtons.isEmpty(),
                "A homepage deve ter pelo menos um botão");
    }

    /**
     * TC1.4 – Campo de nickname está visível.
     */
    @Test
    @Order(4)
    @Story("TC1.4 – Nickname input")
    @Description("O campo de texto para o nickname deve estar visível na homepage")
    @DisplayName("TC1.4 – Campo de nickname visível")
    void testNicknameInputVisible() {
        mainPage.nicknameInput.shouldBe(visible);
    }

    /**
     * TC1.5 – Pode-se digitar um nickname.
     */
    @Test
    @Order(5)
    @Story("TC1.5 – Digitar nickname")
    @Description("O campo de nickname deve aceitar texto e reflectir o valor digitado")
    @DisplayName("TC1.5 – Digitar nickname no campo")
    void testEnterNickname() {
        mainPage.nicknameInput.shouldBe(visible).setValue("SelenidePlayer");
        mainPage.nicknameInput.shouldHave(value("SelenidePlayer"));
    }
}
