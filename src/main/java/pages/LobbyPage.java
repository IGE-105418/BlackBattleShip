package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object para o lobby de jogo multiplayer.
 *
 * <p>Gere as acções de criação e adesão a salas de jogo online,
 * incluindo a leitura do código de sala e estado dos jogadores.</p>
 */
public class LobbyPage extends BasePage {

    // ─────────────────────────────────────────────
    // Localizadores
    // ─────────────────────────────────────────────

    /** Botão para criar uma nova sala multiplayer. */
    static final By CREATE_ROOM_BTN = By.xpath(
            "//*[self::button or self::a or self::div]" +
            "[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'create')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'new room')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'nova sala')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'host')]"
    );

    /** Campo para introduzir o código de uma sala existente. */
    static final By ROOM_CODE_INPUT = By.cssSelector(
            "input[type='text'][placeholder*='code' i], " +
            "input[type='text'][placeholder*='room' i], " +
            "input[type='text'][placeholder*='sala' i], " +
            "input[type='text'][placeholder*='id' i], " +
            "input[id*='room' i], input[name*='room' i], " +
            "input[id*='code' i], input[name*='code' i]"
    );

    /** Botão para entrar/juntar-se a uma sala. */
    static final By JOIN_ROOM_BTN = By.xpath(
            "//*[self::button or self::a]" +
            "[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'join')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'entrar')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'connect')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'enter')]"
    );

    /** Área que exibe o código da sala criada. */
    static final By ROOM_CODE_DISPLAY = By.cssSelector(
            ".room-code, #room-code, .code, .game-code, " +
            "[class*='room-id'], [class*='game-id'], " +
            "[id*='room-code'], [id*='game-code']"
    );

    /** Lista de jogadores na sala. */
    static final By PLAYER_LIST = By.cssSelector(
            ".players, .player-list, #players, .lobby-players, " +
            "[class*='player-list'], [class*='players']"
    );

    /** Indicador de espera por outro jogador. */
    static final By WAITING_INDICATOR = By.xpath(
            "//*[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'wait')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'aguard')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'connecting')]"
    );

    /** Botão de copiar o código da sala (pode ser um ícone). */
    static final By COPY_CODE_BTN = By.cssSelector(
            "button.copy, button[aria-label*='copy' i], " +
            "[class*='copy'], [title*='copy' i], [title*='copiar' i]"
    );

    // ─────────────────────────────────────────────
    // Construtor
    // ─────────────────────────────────────────────

    /**
     * Cria o Page Object do Lobby multiplayer.
     *
     * @param driver instância activa do WebDriver
     */
    public LobbyPage(WebDriver driver) {
        super(driver);
    }

    // ─────────────────────────────────────────────
    // Métodos de interacção
    // ─────────────────────────────────────────────

    /**
     * Clica no botão "Criar Sala".
     */
    public void clickCreateRoom() {
        WebElement btn = waitForClickable(CREATE_ROOM_BTN);
        btn.click();
    }

    /**
     * Verifica se o botão "Criar Sala" está visível.
     *
     * @return true se visível
     */
    public boolean isCreateRoomButtonVisible() {
        return isElementVisible(CREATE_ROOM_BTN);
    }

    /**
     * Clica no botão "Entrar na Sala".
     */
    public void clickJoinRoom() {
        WebElement btn = waitForClickable(JOIN_ROOM_BTN);
        btn.click();
    }

    /**
     * Verifica se o botão "Entrar" está visível.
     *
     * @return true se visível
     */
    public boolean isJoinRoomButtonVisible() {
        return isElementVisible(JOIN_ROOM_BTN);
    }

    /**
     * Preenche o campo de código de sala e tenta entrar.
     *
     * @param roomCode código da sala a que se pretende juntar
     */
    public void enterRoomCode(String roomCode) {
        WebElement input = waitForVisibility(ROOM_CODE_INPUT);
        input.clear();
        input.sendKeys(roomCode);
    }

    /**
     * Preenche o código e clica em "Join".
     *
     * @param roomCode código da sala
     */
    public void joinRoomWithCode(String roomCode) {
        enterRoomCode(roomCode);
        clickJoinRoom();
    }

    /**
     * Devolve o código da sala actualmente exibido no ecrã.
     * Tenta obter o texto do elemento dedicado ao código.
     *
     * @return código da sala ou string vazia se não encontrado
     */
    public String getDisplayedRoomCode() {
        try {
            WebElement codeEl = waitForVisibility(ROOM_CODE_DISPLAY);
            String text = codeEl.getText().trim();
            if (text.isEmpty()) {
                // Alguns sites colocam o código num atributo
                text = codeEl.getAttribute("data-code");
                if (text == null) text = "";
            }
            return text;
        } catch (TimeoutException e) {
            return "";
        }
    }

    /**
     * Verifica se o display do código de sala está visível.
     *
     * @return true se o código está a ser mostrado
     */
    public boolean isRoomCodeDisplayed() {
        return isElementVisible(ROOM_CODE_DISPLAY);
    }

    /**
     * Verifica se o indicador de "à espera de jogador" está visível.
     *
     * @return true se está à espera
     */
    public boolean isWaitingForPlayer() {
        return isElementVisible(WAITING_INDICATOR);
    }

    /**
     * Verifica se a lista de jogadores está visível.
     *
     * @return true se a lista está visível
     */
    public boolean isPlayerListVisible() {
        return isElementVisible(PLAYER_LIST);
    }

    /**
     * Devolve o texto da lista de jogadores.
     *
     * @return texto do elemento de lista de jogadores
     */
    public String getPlayerListText() {
        try {
            return waitForVisibility(PLAYER_LIST).getText().trim();
        } catch (TimeoutException e) {
            return "";
        }
    }

    /**
     * Verifica se o campo de código de entrada está visível.
     *
     * @return true se o campo está visível
     */
    public boolean isRoomCodeInputVisible() {
        return isElementVisible(ROOM_CODE_INPUT);
    }
}
