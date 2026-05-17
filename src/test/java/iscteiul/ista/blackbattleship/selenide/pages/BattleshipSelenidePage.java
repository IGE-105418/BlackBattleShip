package iscteiul.ista.blackbattleship.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object Selenide para a página Battleship da Papergames.
 *
 * Esta classe concentra os localizadores e ações usadas pela test suite Selenide.
 */
public class BattleshipSelenidePage {

    private static final String URL = "https://papergames.io/en/battleship";

    private final SelenideElement body = $("body");

    private final SelenideElement mainTitle = $x(
            "//h1[contains(normalize-space(), 'Battleship Online') or contains(normalize-space(), 'Battleship')]"
    );

    private final SelenideElement playWithFriendButton = $x(
            "//*[self::a or self::button or self::span][contains(normalize-space(), 'Play with a friend')]"
    );

    private final SelenideElement playVsRobotButton = $x(
            "//*[self::a or self::button or self::span][contains(normalize-space(), 'Play vs robot')]"
    );

    private final SelenideElement createTournamentButton = $x(
            "//*[self::a or self::button or self::span][contains(normalize-space(), 'Create tournament')]"
    );

    private final SelenideElement randomPlayerOption = $x(
            "//*[contains(normalize-space(), 'Play online with a random player')]"
    );

    private final SelenideElement rulesTitle = $x(
            "//*[contains(normalize-space(), 'Rules of Battleship game online')]"
    );

    @Step("Abrir a página principal do Battleship")
    public BattleshipSelenidePage openPage() {
        open(URL);
        body.shouldBe(visible).shouldHave(text("Battleship"));
        return this;
    }

    @Step("Obter URL atual")
    public String getCurrentUrl() {
        return webdriver().driver().url();
    }

    @Step("Obter título da página")
    public String getPageTitle() {
        return title();
    }

    @Step("Validar se o título principal está visível")
    public boolean isMainTitleVisible() {
        return mainTitle.shouldBe(visible).exists();
    }

    @Step("Validar se a opção Play with a friend está visível")
    public boolean isPlayWithFriendVisible() {
        return playWithFriendButton.shouldBe(visible).exists();
    }

    @Step("Validar se a opção Play vs robot está visível")
    public boolean isPlayVsRobotVisible() {
        return playVsRobotButton.shouldBe(visible).exists();
    }

    @Step("Validar se a opção Create tournament está visível")
    public boolean isCreateTournamentVisible() {
        return createTournamentButton.shouldBe(visible).exists();
    }

    @Step("Validar se a opção de jogar com jogador aleatório está visível")
    public boolean isRandomPlayerOptionVisible() {
        return randomPlayerOption.shouldBe(visible).exists();
    }

    @Step("Validar se a secção de regras está visível")
    public boolean isRulesSectionVisible() {
        return rulesTitle.shouldBe(visible).exists();
    }

    @Step("Validar texto principal da página")
    public boolean hasExpectedHomepageText() {
        body.shouldBe(visible);

        String text = body.getText().toLowerCase();

        return text.contains("battleship online")
                && text.contains("first to sink all opponent ships wins");
    }

    @Step("Validar conteúdo das regras")
    public boolean hasRulesContent() {
        body.shouldBe(visible);

        String text = body.getText().toLowerCase();

        return text.contains("turn-based")
                && text.contains("ships")
                && text.contains("sink")
                && text.contains("opponent");
    }

    @Step("Validar conteúdo sobre estratégias")
    public boolean hasStrategiesContent() {
        body.shouldBe(visible);

        String text = body.getText().toLowerCase();

        return text.contains("strategies")
                || text.contains("tactics")
                || text.contains("targeting");
    }

    @Step("Clicar em Play vs robot")
    public BattleshipSelenidePage clickPlayVsRobot() {
        playVsRobotButton.shouldBe(visible).click();
        return this;
    }

    @Step("Validar se a página continua funcional depois de clicar em Play vs robot")
    public boolean pageStillContainsGameContext() {
        body.shouldBe(visible);

        String text = body.getText().toLowerCase();

        return text.contains("battleship")
                || text.contains("robot")
                || text.contains("game")
                || text.contains("player");
    }
}