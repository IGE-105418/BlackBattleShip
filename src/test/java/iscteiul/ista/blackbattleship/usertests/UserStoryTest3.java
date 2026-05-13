package iscteiul.ista.blackbattleship.usertests;

import iscteiul.ista.blackbattleship.pages.BattleshipHomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

/**
 * US3 - Jogar contra o robot.
 *
 * Como jogador individual,
 * quero iniciar uma partida contra o robot,
 * para poder jogar sem precisar de outro jogador humano.
 */
public class UserStoryTest3 {

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
    @DisplayName("US3 - Existe opção para jogar contra o robot")
    void testPlayVsRobotOptionVisible() {
        assertTrue(
                homePage.isPlayVsRobotVisible(),
                "A página deve apresentar a opção Play vs robot."
        );
    }

    @Test
    @DisplayName("US3 - Clicar em Play vs robot inicia ou apresenta fluxo de jogo")
    void testPlayVsRobotStartsFlow() {
        homePage.clickPlayVsRobot();

        assertTrue(
                homePage.hasRobotGameOptionOrGameStarted(),
                "Ao clicar em Play vs robot, deve iniciar ou apresentar um fluxo relacionado com o jogo."
        );
    }
}