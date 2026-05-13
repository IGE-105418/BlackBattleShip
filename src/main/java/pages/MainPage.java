package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Page Object para a página principal (homepage) do Black BattleShip.
 *
 * <p>URL base: https://www.blackbattleship.com/</p>
 *
 * <p><b>Nota sobre localizadores:</b> O site é uma Single Page Application (SPA).
 * Se algum selector falhar, inspecione o DOM no Chrome com F12 e ajuste os
 * localizadores {@code By.*} nesta classe.</p>
 */
public class MainPage extends BasePage {

    /** URL base do site. */
    public static final String BASE_URL = "https://www.blackbattleship.com/";

    // ─────────────────────────────────────────────
    // Localizadores (By) – ajustar conforme DOM real
    // ─────────────────────────────────────────────

    /** Campo de texto para introduzir o nickname do jogador. */
    static final By NICKNAME_INPUT = By.cssSelector(
            "input[type='text'], input[id*='nick' i], input[name*='nick' i], " +
            "input[placeholder*='name' i], input[placeholder*='nick' i], input[placeholder*='jogador' i]"
    );

    /** Botão para jogar contra o robot/computador. */
    static final By PLAY_VS_ROBOT_BTN = By.xpath(
            "//*[self::button or self::a or self::div]" +
            "[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'robot')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'computer')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'single')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'vs cpu')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'ia')]"
    );

    /** Botão para modo multiplayer. */
    static final By MULTIPLAYER_BTN = By.xpath(
            "//*[self::button or self::a or self::div]" +
            "[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'multi')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'online')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'2 player')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'pvp')]"
    );

    /** Link ou botão para a página de instruções. */
    static final By INSTRUCTIONS_LINK = By.xpath(
            "//*[self::button or self::a or self::div or self::span]" +
            "[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'instruction')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'how to')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'rules')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'regras')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'instruc')]"
    );

    /** Título principal visível na página (h1 ou elemento de destaque). */
    static final By PAGE_HEADING = By.cssSelector("h1, h2, .title, .game-title, #title");

    /** Todos os botões da página. */
    static final By ALL_BUTTONS = By.tagName("button");

    /** Todos os links da página. */
    static final By ALL_LINKS = By.tagName("a");

    // ─────────────────────────────────────────────
    // @FindBy (PageFactory) – alternativa declarativa
    // ─────────────────────────────────────────────

    @FindBy(css = "input[type='text']")
    private WebElement nicknameField;

    // ─────────────────────────────────────────────
    // Construtor
    // ─────────────────────────────────────────────

    /**
     * Cria o Page Object da página principal.
     *
     * @param driver instância activa do WebDriver
     */
    public MainPage(WebDriver driver) {
        super(driver);
    }

    // ─────────────────────────────────────────────
    // Métodos de navegação e interacção
    // ─────────────────────────────────────────────

    /**
     * Abre a homepage e aguarda o carregamento completo.
     */
    public void open() {
        driver.get(BASE_URL);
        waitForPageLoad();
    }

    /**
     * Verifica se a página principal está carregada.
     *
     * @return true se a URL contém "blackbattleship.com"
     */
    public boolean isPageLoaded() {
        return driver.getCurrentUrl().contains("blackbattleship.com");
    }

    /**
     * Devolve o título da aba do browser.
     *
     * @return título da página
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Devolve o texto do heading principal (h1/h2) da página.
     *
     * @return texto do heading ou string vazia se não encontrado
     */
    public String getHeadingText() {
        try {
            return waitForVisibility(PAGE_HEADING).getText().trim();
        } catch (TimeoutException e) {
            return "";
        }
    }

    /**
     * Preenche o campo de nickname com o valor fornecido.
     *
     * @param nickname nome do jogador
     */
    public void enterNickname(String nickname) {
        WebElement input = waitForVisibility(NICKNAME_INPUT);
        input.clear();
        input.sendKeys(nickname);
    }

    /**
     * Devolve o valor actual do campo de nickname.
     *
     * @return texto do campo
     */
    public String getNicknameValue() {
        WebElement input = waitForVisibility(NICKNAME_INPUT);
        return input.getAttribute("value");
    }

    /**
     * Verifica se o campo de nickname está visível.
     *
     * @return true se visível
     */
    public boolean isNicknameInputVisible() {
        return isElementVisible(NICKNAME_INPUT);
    }

    /**
     * Clica no botão "Jogar vs Robot/Computador".
     */
    public void clickPlayVsRobot() {
        WebElement btn = waitForClickable(PLAY_VS_ROBOT_BTN);
        btn.click();
    }

    /**
     * Verifica se o botão de jogar vs robot está visível.
     *
     * @return true se visível
     */
    public boolean isPlayVsRobotButtonVisible() {
        return isElementVisible(PLAY_VS_ROBOT_BTN);
    }

    /**
     * Clica no botão Multiplayer.
     */
    public void clickMultiplayer() {
        WebElement btn = waitForClickable(MULTIPLAYER_BTN);
        btn.click();
    }

    /**
     * Verifica se o botão Multiplayer está visível.
     *
     * @return true se visível
     */
    public boolean isMultiplayerButtonVisible() {
        return isElementVisible(MULTIPLAYER_BTN);
    }

    /**
     * Navega para a página de Instruções.
     */
    public void clickInstructions() {
        WebElement link = waitForClickable(INSTRUCTIONS_LINK);
        link.click();
    }

    /**
     * Verifica se o link de instruções está visível.
     *
     * @return true se visível
     */
    public boolean isInstructionsLinkVisible() {
        return isElementVisible(INSTRUCTIONS_LINK);
    }

    /**
     * Devolve todos os botões presentes na página.
     *
     * @return lista de WebElements do tipo button
     */
    public List<WebElement> getAllButtons() {
        return findAll(ALL_BUTTONS);
    }

    /**
     * Devolve todos os links (âncoras) presentes na página.
     *
     * @return lista de WebElements do tipo a
     */
    public List<WebElement> getAllLinks() {
        return findAll(ALL_LINKS);
    }

    /**
     * Conta o número de botões na página.
     *
     * @return número de botões
     */
    public int getButtonCount() {
        return getAllButtons().size();
    }

    /**
     * Conta o número de links na página.
     *
     * @return número de links
     */
    public int getLinkCount() {
        return getAllLinks().size();
    }

    /**
     * Verifica se um link com o href indicado existe na página.
     *
     * @param hrefFragment fragmento do href a pesquisar
     * @return true se encontrado
     */
    public boolean hasLinkWithHref(String hrefFragment) {
        return findAll(ALL_LINKS).stream()
                .map(el -> el.getAttribute("href"))
                .filter(href -> href != null)
                .anyMatch(href -> href.contains(hrefFragment));
    }
}
