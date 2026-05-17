package iscteiul.ista.blackbattleship.usertests;

import iscteiul.ista.blackbattleship.pages.BattleshipHomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

/**
 * US6 - Consultar instruções/regras.
 *
 * Como jogador novo,
 * quero consultar as instruções do jogo,
 * para compreender as regras antes de começar.
 */
public class UserStoryTest6 {

    private WebDriver driver;
    private BattleshipHomePage homePage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1280,800");

        driver = new ChromeDriver(options);
        homePage = new BattleshipHomePage(driver);
        homePage.open();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("US6 - A secção de regras está disponível")
    void testRulesSectionVisible() {
        assertTrue(
                homePage.isRulesSectionVisible(),
                "A página deve apresentar a secção de regras do Battleship."
        );
    }

    @Test
    @DisplayName("US6 - As regras apresentam conteúdo explicativo")
    void testRulesContentVisible() {
        assertTrue(
                homePage.hasRulesContent(),
                "As regras devem conter informação sobre turnos, navios, ataques ou objetivo do jogo."
        );
    }
}