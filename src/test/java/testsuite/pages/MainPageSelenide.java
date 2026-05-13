package testsuite.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Page Object para a homepage do Black BattleShip – versão Selenide.
 *
 * <p>Parte 2 da ficha laboratorial: usa Selenide em vez de Selenium WebDriver puro.</p>
 *
 * <p><b>Nota:</b> No Selenide não é necessário inicializar o WebDriver na Page Object.
 * Os elementos são lazy – só interagem com o browser quando um método é chamado.</p>
 */
public class MainPageSelenide {

    // ─── Localizadores (SelenideElement é lazy) ───────────────────────────────

    /**
     * Campo de texto para o nickname do jogador.
     * Selector alternativo: $x("//input[@type='text']")
     */
    public final SelenideElement nicknameInput =
            $("input[type='text']");

    /**
     * Botão para jogar contra o robot/computador.
     */
    public final SelenideElement playVsRobotButton =
            $x("//*[self::button or self::a]" +
               "[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'robot')" +
               " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'computer')" +
               " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'single')]");

    /**
     * Botão para modo multiplayer.
     */
    public final SelenideElement multiplayerButton =
            $x("//*[self::button or self::a]" +
               "[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'multi')" +
               " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'online')]");

    /**
     * Link ou botão para a página de instruções.
     */
    public final SelenideElement instructionsLink =
            $x("//*[self::button or self::a]" +
               "[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'instruction')" +
               " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'how to')" +
               " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'rules')]");

    /**
     * Heading principal da página (h1 ou h2).
     */
    public final SelenideElement pageHeading =
            $("h1, h2, .title, .game-title");

    /**
     * Todos os botões da página (colecção Selenide).
     */
    public final com.codeborne.selenide.ElementsCollection allButtons =
            $$("button");

    /**
     * Todos os links da página.
     */
    public final com.codeborne.selenide.ElementsCollection allLinks =
            $$("a");
}
