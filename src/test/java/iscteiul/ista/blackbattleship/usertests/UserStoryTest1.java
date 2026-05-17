package iscteiul.ista.blackbattleship.usertests;

import iscteiul.ista.blackbattleship.pages.BattleshipHomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

/**
 * US1 - Aceder à página principal.
 *
 * Como jogador,
 * quero aceder à página principal do BlackBattleShip,
 * para confirmar que o jogo está disponível e pronto a ser utilizado.
 */
public class UserStoryTest1 {

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
    @DisplayName("US1 - A página principal abre com o URL correto")
    void testHomepageUrl() {
        assertTrue(
                homePage.getCurrentUrl().contains("papergames.io/en/battleship"),
                "O URL deve corresponder à página do jogo Battleship."
        );
    }

    @Test
    @DisplayName("US1 - A página principal apresenta o título do jogo")
    void testHomepageTitleVisible() {
        assertTrue(
                homePage.isMainTitleVisible(),
                "A página deve apresentar conteúdo/título relacionado com Battleship."
        );
    }

    @Test
    @DisplayName("US1 - A página apresenta opções principais de jogo")
    void testHomepageMainOptionsVisible() {
        assertTrue(
                homePage.isPlayWithFriendVisible() || homePage.isPlayVsRobotVisible(),
                "A página deve apresentar pelo menos uma opção para iniciar uma partida."
        );
    }
}