package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page Object para a página de jogo (tabuleiro de batalha naval).
 *
 * <p>Representa o estado da partida em curso, quer contra o robot
 * quer em modo multiplayer. Contém localizadores para o tabuleiro,
 * células de ataque e elementos de estado.</p>
 */
public class GamePage extends BasePage {

    // ─────────────────────────────────────────────
    // Localizadores do tabuleiro
    // ─────────────────────────────────────────────

    /**
     * Container principal do tabuleiro de jogo.
     * Tentativa com vários seletores alternativos (separados por vírgula no CSS).
     */
    static final By GAME_BOARD = By.cssSelector(
            ".board, .game-board, #board, .battleship-board, " +
            "table.grid, .grid, #game-grid, .game-area"
    );

    /** Células individuais do tabuleiro inimigo (para atacar). */
    static final By ENEMY_CELLS = By.cssSelector(
            ".enemy .cell, .enemy-board .cell, .enemy td, " +
            ".opponent .cell, [data-board='enemy'] .cell, " +
            ".attack-board .cell, td.cell"
    );

    /** Células do tabuleiro do próprio jogador. */
    static final By MY_CELLS = By.cssSelector(
            ".my-board .cell, .player-board .cell, .own td, " +
            "[data-board='player'] .cell, .defense-board .cell"
    );

    /** Elemento que mostra o estado actual do jogo (turno, vitória, derrota). */
    static final By GAME_STATUS = By.cssSelector(
            ".status, .game-status, #status, .turn-info, " +
            ".message, .info-bar, .game-message, #game-info"
    );

    /** Mensagem de vitória. */
    static final By WIN_MESSAGE = By.xpath(
            "//*[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'win')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'venc')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'victory')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'ganhou')]"
    );

    /** Mensagem de derrota. */
    static final By LOSE_MESSAGE = By.xpath(
            "//*[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'lose')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'defeat')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'perdeu')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'game over')]"
    );

    /** Botão para iniciar ou confirmar o posicionamento dos navios. */
    static final By START_BTN = By.xpath(
            "//*[self::button or self::a]" +
            "[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'start')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'ready')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'play')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'begin')]"
    );

    /** Botão para voltar ao menu principal. */
    static final By BACK_TO_MENU_BTN = By.xpath(
            "//*[self::button or self::a]" +
            "[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'menu')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'home')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'back')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'voltar')]"
    );

    /** Indicador de turno do jogador. */
    static final By PLAYER_TURN_INDICATOR = By.cssSelector(
            ".your-turn, .player-turn, .turn.active, [class*='turn']"
    );

    // ─────────────────────────────────────────────
    // Construtor
    // ─────────────────────────────────────────────

    /**
     * Cria o Page Object da página de jogo.
     *
     * @param driver instância activa do WebDriver
     */
    public GamePage(WebDriver driver) {
        super(driver);
    }

    // ─────────────────────────────────────────────
    // Métodos de estado
    // ─────────────────────────────────────────────

    /**
     * Verifica se o tabuleiro de jogo está presente na página.
     * Inclui detecção de fases de preparação (escolha de dificuldade, colocação de navios).
     *
     * @return true se o tabuleiro ou ecrã de preparação do jogo estiver visível
     */
    public boolean isBoardDisplayed() {
        if (isElementVisible(GAME_BOARD)) return true;
        // SPA: detectar ecrã de dificuldade ou colocação de navios após clicar em Play
        By GAME_SETUP = By.xpath(
            "//*[contains(normalize-space(.),'difficulty') or contains(normalize-space(.),'Difficulty')" +
            " or contains(normalize-space(.),'Place your ships') or contains(normalize-space(.),'place your ships')" +
            " or contains(normalize-space(.),'Set up your fleet') or contains(normalize-space(.),'set up your fleet')" +
            " or contains(normalize-space(.),'Choose level') or contains(normalize-space(.),'Solo game')" +
            " or (contains(normalize-space(.),'Easy') and contains(normalize-space(.),'Hard'))]"
        );
        return isElementVisible(GAME_SETUP);
    }

    /**
     * Aguarda que o tabuleiro fique visível (útil após navegação).
     *
     * @return true se o tabuleiro aparecer dentro do timeout
     */
    public boolean waitForBoard() {
        try {
            waitForVisibility(GAME_BOARD);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Devolve o texto do elemento de estado do jogo.
     *
     * @return texto do estado ou string vazia
     */
    public String getGameStatusText() {
        try {
            return waitForVisibility(GAME_STATUS).getText().trim();
        } catch (TimeoutException e) {
            return "";
        }
    }

    /**
     * Verifica se a mensagem de vitória está visível.
     *
     * @return true se apareceu mensagem de vitória
     */
    public boolean isWinMessageDisplayed() {
        return isElementVisible(WIN_MESSAGE);
    }

    /**
     * Verifica se a mensagem de derrota está visível.
     *
     * @return true se apareceu mensagem de derrota
     */
    public boolean isLoseMessageDisplayed() {
        return isElementVisible(LOSE_MESSAGE);
    }

    /**
     * Verifica se o jogo terminou (vitória ou derrota).
     *
     * @return true se o jogo acabou
     */
    public boolean isGameOver() {
        return isWinMessageDisplayed() || isLoseMessageDisplayed();
    }

    /**
     * Devolve a lista de todas as células do tabuleiro inimigo.
     *
     * @return lista de WebElements (células)
     */
    public List<WebElement> getEnemyCells() {
        return findAll(ENEMY_CELLS);
    }

    /**
     * Devolve o número de células do tabuleiro inimigo.
     *
     * @return contagem de células
     */
    public int getEnemyCellCount() {
        return getEnemyCells().size();
    }

    /**
     * Clica numa célula específica do tabuleiro inimigo pelo índice.
     *
     * @param index índice da célula (0-based)
     */
    public void clickEnemyCell(int index) {
        List<WebElement> cells = getEnemyCells();
        if (index < cells.size()) {
            WebElement cell = cells.get(index);
            scrollToElement(cell);
            cell.click();
        }
    }

    /**
     * Tenta clicar numa célula do tabuleiro inimigo pela linha e coluna.
     * Usa data attributes {@code data-row} e {@code data-col} se existirem.
     *
     * @param row linha (1-10)
     * @param col coluna (1-10)
     */
    public void clickEnemyCellAt(int row, int col) {
        By cellLocator = By.cssSelector(
                String.format("[data-row='%d'][data-col='%d'], " +
                              "[data-x='%d'][data-y='%d'], " +
                              "td:nth-child(%d)", row, col, col, row, col)
        );
        try {
            WebElement cell = waitForClickable(cellLocator);
            cell.click();
        } catch (TimeoutException e) {
            // fallback: clicar por índice calculado numa grelha 10x10
            int index = (row - 1) * 10 + (col - 1);
            clickEnemyCell(index);
        }
    }

    /**
     * Clica no botão "Start" / "Ready" para iniciar o jogo.
     */
    public void clickStart() {
        WebElement btn = waitForClickable(START_BTN);
        btn.click();
    }

    /**
     * Verifica se o botão Start está visível.
     *
     * @return true se visível
     */
    public boolean isStartButtonVisible() {
        return isElementVisible(START_BTN);
    }

    /**
     * Navega de volta ao menu principal.
     */
    public void clickBackToMenu() {
        WebElement btn = waitForClickable(BACK_TO_MENU_BTN);
        btn.click();
    }

    /**
     * Aguarda e verifica se o jogo termina num período máximo.
     *
     * @param maxSeconds tempo máximo de espera em segundos
     * @return true se o jogo terminar dentro do prazo
     */
    public boolean waitForGameOver(int maxSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(maxSeconds))
                    .until(d -> isGameOver());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Faz hover (pairar o rato) sobre uma célula do tabuleiro inimigo.
     * Útil para verificar tooltips ou efeitos de hover.
     *
     * @param index índice da célula
     */
    public void hoverOverEnemyCell(int index) {
        List<WebElement> cells = getEnemyCells();
        if (index < cells.size()) {
            new Actions(driver).moveToElement(cells.get(index)).perform();
        }
    }
}
