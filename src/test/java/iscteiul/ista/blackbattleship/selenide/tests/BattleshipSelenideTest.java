package iscteiul.ista.blackbattleship.selenide.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import iscteiul.ista.blackbattleship.selenide.pages.BattleshipSelenidePage;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite Selenide para o jogo Battleship.
 *
 * Esta suite valida funcionalidades principais da página Battleship usando Selenide,
 * Page Object Model e integração com Allure Report.
 */
@Epic("BlackBattleShip")
@Feature("Test Suite Selenide - Eduardo")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class BattleshipSelenideTest {

    private BattleshipSelenidePage battleshipPage;

    @BeforeAll
    static void setUpAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1280x800";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 30000;
        Configuration.reportsFolder = "target/selenide-reports";

        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @BeforeEach
    void setUp() {
        battleshipPage = page(BattleshipSelenidePage.class);
        battleshipPage.openPage();
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    @Story("US1 - Aceder à página principal")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida se a página principal do Battleship abre corretamente.")
    @DisplayName("01 - Página principal abre com URL correto")
    void testHomepageUrl() {
        assertTrue(
                battleshipPage.getCurrentUrl().contains("papergames.io/en/battleship"),
                "O URL deve corresponder à página Battleship da Papergames."
        );
    }

    @Test
    @Story("US1 - Aceder à página principal")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida se o título principal Battleship Online está visível.")
    @DisplayName("02 - Página apresenta título principal")
    void testHomepageTitleVisible() {
        assertTrue(
                battleshipPage.isMainTitleVisible(),
                "A página deve apresentar o título principal do jogo."
        );
    }

    @Test
    @Story("US1 - Aceder à página principal")
    @Severity(SeverityLevel.NORMAL)
    @Description("Valida se o conteúdo principal da homepage está presente.")
    @DisplayName("03 - Página apresenta texto principal do jogo")
    void testHomepageMainText() {
        assertTrue(
                battleshipPage.hasExpectedHomepageText(),
                "A página deve apresentar texto principal relacionado com Battleship."
        );
    }

    @Test
    @Story("US3 - Jogar contra robot")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida se existe a opção Play vs robot.")
    @DisplayName("04 - Opção Play vs robot está visível")
    void testPlayVsRobotOptionVisible() {
        assertTrue(
                battleshipPage.isPlayVsRobotVisible(),
                "A opção Play vs robot deve estar visível."
        );
    }

    @Test
    @Story("US3 - Jogar contra robot")
    @Severity(SeverityLevel.NORMAL)
    @Description("Valida se clicar em Play vs robot mantém contexto funcional de jogo.")
    @DisplayName("05 - Play vs robot inicia fluxo de jogo")
    void testPlayVsRobotFlow() {
        battleshipPage.clickPlayVsRobot();

        assertTrue(
                battleshipPage.pageStillContainsGameContext(),
                "Depois de clicar em Play vs robot, a página deve continuar num contexto de jogo."
        );
    }

    @Test
    @Story("US4 - Criar jogo multiplayer")
    @Severity(SeverityLevel.NORMAL)
    @Description("Valida se existe a opção Play with a friend.")
    @DisplayName("06 - Opção Play with a friend está visível")
    void testPlayWithFriendOptionVisible() {
        assertTrue(
                battleshipPage.isPlayWithFriendVisible(),
                "A opção Play with a friend deve estar visível."
        );
    }

    @Test
    @Story("US14 - Criar campeonato")
    @Severity(SeverityLevel.MINOR)
    @Description("Valida se existe opção para criar campeonato.")
    @DisplayName("07 - Opção Create tournament está visível")
    void testCreateTournamentOptionVisible() {
        assertTrue(
                battleshipPage.isCreateTournamentVisible(),
                "A opção Create tournament deve estar visível."
        );
    }

    @Test
    @Story("US6 - Consultar instruções/regras")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Valida se a secção de regras está disponível.")
    @DisplayName("08 - Secção de regras está visível")
    void testRulesSectionVisible() {
        assertTrue(
                battleshipPage.isRulesSectionVisible(),
                "A secção de regras deve estar visível."
        );
    }

    @Test
    @Story("US6 - Consultar instruções/regras")
    @Severity(SeverityLevel.NORMAL)
    @Description("Valida se as regras apresentam conteúdo explicativo.")
    @DisplayName("09 - Regras apresentam conteúdo explicativo")
    void testRulesContentVisible() {
        assertTrue(
                battleshipPage.hasRulesContent(),
                "As regras devem conter informação sobre turnos, navios e objetivo do jogo."
        );
    }

    @Test
    @Story("US6 - Consultar instruções/regras")
    @Severity(SeverityLevel.MINOR)
    @Description("Valida se existe conteúdo de estratégias/táticas.")
    @DisplayName("10 - Página apresenta estratégias ou táticas")
    void testStrategiesContentVisible() {
        assertTrue(
                battleshipPage.hasStrategiesContent(),
                "A página deve apresentar conteúdo relacionado com estratégias ou táticas."
        );
    }
}