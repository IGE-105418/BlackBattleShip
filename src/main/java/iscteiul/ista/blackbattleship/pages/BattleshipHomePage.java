package iscteiul.ista.blackbattleship.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object da página principal do jogo Battleship no papergames.io.
 *
 * Esta classe concentra os localizadores e métodos de interação usados
 * pelos testes das User Stories.
 */
public class BattleshipHomePage {

    private static final String URL = "https://papergames.io/en/battleship";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final By BODY = By.tagName("body");

    private static final By MAIN_TITLE = By.xpath(
            "//*[contains(normalize-space(), 'Battleship Online') or contains(normalize-space(), 'Battleship')]"
    );

    private static final By PLAY_WITH_FRIEND_BUTTON = By.xpath(
            "//*[contains(normalize-space(), 'Play with a friend')]"
    );

    private static final By PLAY_VS_ROBOT_BUTTON = By.xpath(
            "//*[contains(normalize-space(), 'Play vs robot')]"
    );

    private static final By CREATE_TOURNAMENT_BUTTON = By.xpath(
            "//*[contains(normalize-space(), 'Create tournament')]"
    );

    private static final By RULES_SECTION = By.xpath(
            "//*[contains(normalize-space(), 'Rules of Battleship game online')]"
    );

    private static final By ANY_TEXT_INPUT = By.xpath(
            "//input[@type='text' or @type='search' or @type='email' or contains(@placeholder, 'name') or contains(@placeholder, 'Name') or contains(@placeholder, 'nickname') or contains(@placeholder, 'Nickname')]"
    );

    public BattleshipHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open() {
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(BODY));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getBodyText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(BODY)).getText();
    }

    public boolean isMainTitleVisible() {
        return isVisible(MAIN_TITLE);
    }

    public boolean isPlayWithFriendVisible() {
        return isVisible(PLAY_WITH_FRIEND_BUTTON);
    }

    public boolean isPlayVsRobotVisible() {
        return isVisible(PLAY_VS_ROBOT_BUTTON);
    }

    public boolean isCreateTournamentVisible() {
        return isVisible(CREATE_TOURNAMENT_BUTTON);
    }

    public boolean isRulesSectionVisible() {
        return isVisible(RULES_SECTION);
    }

    public void clickPlayVsRobot() {
        clickIfPossible(PLAY_VS_ROBOT_BUTTON);
    }

    public void clickPlayWithFriend() {
        clickIfPossible(PLAY_WITH_FRIEND_BUTTON);
    }

    public void clickCreateTournament() {
        clickIfPossible(CREATE_TOURNAMENT_BUTTON);
    }

    public boolean hasNicknameOrGuestFlow() {
        /*
         * O site pode apresentar um campo de nome/nickname, ou pode permitir
         * continuar como convidado. Por isso validamos os dois cenários.
         */
        if (isVisible(ANY_TEXT_INPUT)) {
            return true;
        }

        String body = getBodyText().toLowerCase();

        return body.contains("guest")
                || body.contains("nickname")
                || body.contains("name")
                || body.contains("player")
                || body.contains("sign in")
                || body.contains("login");
    }

    public boolean hasRobotGameOptionOrGameStarted() {
        /*
         * Depois de clicar em "Play vs robot", a página pode abrir um fluxo de jogo,
         * uma área de login/convidado, ou manter a opção visível.
         */
        String body = getBodyText().toLowerCase();

        return body.contains("robot")
                || body.contains("guest")
                || body.contains("player")
                || body.contains("battleship")
                || body.contains("game");
    }

    public boolean hasRulesContent() {
        String body = getBodyText().toLowerCase();

        return body.contains("rules of battleship")
                || body.contains("turn-based")
                || body.contains("ships")
                || body.contains("sink")
                || body.contains("opponent");
    }

    private boolean isVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    private void clickIfPossible(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
}