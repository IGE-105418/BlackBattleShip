package iscteiul.ista.blackbattleship.usertests;

import iscteiul.ista.blackbattleship.pages.BattleshipHomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

/**
 * US2 - Criar nickname.
 *
 * Como jogador,
 * quero introduzir um nickname ou continuar como convidado,
 * para ser identificado durante a partida.
 */
public class UserStoryTest2 {

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
    @DisplayName("US2 - O fluxo de jogo permite identificação do jogador")
    void testNicknameOrGuestFlowAvailable() {
        homePage.clickPlayVsRobot();

        assertTrue(
                homePage.hasNicknameOrGuestFlow(),
                "Depois de iniciar o jogo, deve existir um fluxo de nickname, login, jogador ou convidado."
        );
    }
}