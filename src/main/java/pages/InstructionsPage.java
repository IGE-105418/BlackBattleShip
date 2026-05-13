package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Page Object para a página de Instruções / Regras do jogo.
 *
 * <p>Inclui localizadores para o conteúdo das instruções e navegação
 * de regresso ao menu principal.</p>
 */
public class InstructionsPage extends BasePage {

    // ─────────────────────────────────────────────
    // Localizadores
    // ─────────────────────────────────────────────

    /** Título da secção de instruções. */
    static final By INSTRUCTIONS_HEADING = By.cssSelector(
            "h1, h2, h3, .instructions-title, .rules-title, " +
            "#instructions h1, #instructions h2, #rules h1"
    );

    /** Container principal do conteúdo das instruções. */
    static final By INSTRUCTIONS_CONTENT = By.cssSelector(
            ".instructions, #instructions, .rules, #rules, " +
            ".how-to-play, #how-to-play, .tutorial, [class*='instruct']"
    );

    /** Parágrafos de texto dentro das instruções. */
    static final By INSTRUCTIONS_PARAGRAPHS = By.cssSelector(
            ".instructions p, #instructions p, .rules p, " +
            ".how-to-play p, .tutorial p"
    );

    /** Botão/link para regressar ao menu principal. */
    static final By BACK_BTN = By.xpath(
            "//*[self::button or self::a]" +
            "[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'back')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'return')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'menu')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'voltar')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'home')" +
            " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'close')]"
    );

    /** Seta ou ícone de voltar atrás (aria-label). */
    static final By BACK_ARROW = By.cssSelector(
            "[aria-label*='back' i], [aria-label*='close' i], " +
            ".back-arrow, .close-btn, .btn-back, button.back"
    );

    /** Lista de regras (elementos de lista). */
    static final By RULES_LIST = By.cssSelector(
            ".instructions ul, .rules ul, .how-to-play ul, " +
            ".instructions ol, .rules ol"
    );

    /** Imagens ou diagramas nas instruções. */
    static final By INSTRUCTION_IMAGES = By.cssSelector(
            ".instructions img, .rules img, .how-to-play img"
    );

    // ─────────────────────────────────────────────
    // Construtor
    // ─────────────────────────────────────────────

    /**
     * Cria o Page Object da página de Instruções.
     *
     * @param driver instância activa do WebDriver
     */
    public InstructionsPage(WebDriver driver) {
        super(driver);
    }

    // ─────────────────────────────────────────────
    // Métodos de leitura de conteúdo
    // ─────────────────────────────────────────────

    /**
     * Verifica se a secção de instruções está visível na página.
     *
     * @return true se o container das instruções está visível
     */
    public boolean isInstructionsVisible() {
        return isElementVisible(INSTRUCTIONS_CONTENT);
    }

    /**
     * Aguarda que as instruções fiquem visíveis.
     *
     * @return true se ficarem visíveis dentro do timeout
     */
    public boolean waitForInstructions() {
        try {
            waitForVisibility(INSTRUCTIONS_CONTENT);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Devolve o texto do título da secção de instruções.
     *
     * @return texto do título ou string vazia
     */
    public String getInstructionsTitleText() {
        try {
            return waitForVisibility(INSTRUCTIONS_HEADING).getText().trim();
        } catch (TimeoutException e) {
            return "";
        }
    }

    /**
     * Devolve o texto completo da secção de instruções.
     *
     * @return texto completo ou string vazia
     */
    public String getInstructionsText() {
        try {
            return waitForVisibility(INSTRUCTIONS_CONTENT).getText().trim();
        } catch (TimeoutException e) {
            return "";
        }
    }

    /**
     * Verifica se as instruções contêm um determinado texto.
     *
     * @param expectedText texto a pesquisar (case-insensitive)
     * @return true se o texto for encontrado
     */
    public boolean instructionsContainText(String expectedText) {
        String content = getInstructionsText().toLowerCase();
        return content.contains(expectedText.toLowerCase());
    }

    /**
     * Devolve o número de parágrafos nas instruções.
     *
     * @return número de parágrafos
     */
    public int getParagraphCount() {
        return findAll(INSTRUCTIONS_PARAGRAPHS).size();
    }

    /**
     * Verifica se existe uma lista de regras.
     *
     * @return true se existe uma lista (ul ou ol)
     */
    public boolean hasRulesList() {
        return isElementPresent(RULES_LIST);
    }

    /**
     * Devolve todos os parágrafos do conteúdo das instruções.
     *
     * @return lista de WebElements parágrafo
     */
    public List<WebElement> getInstructionParagraphs() {
        return findAll(INSTRUCTIONS_PARAGRAPHS);
    }

    // ─────────────────────────────────────────────
    // Navegação
    // ─────────────────────────────────────────────

    /**
     * Clica no botão de regresso ao menu principal.
     */
    public void clickBack() {
        try {
            WebElement btn = waitForClickable(BACK_BTN);
            btn.click();
        } catch (TimeoutException e) {
            // Tenta com o seletor da seta de voltar
            WebElement arrow = waitForClickable(BACK_ARROW);
            arrow.click();
        }
    }

    /**
     * Verifica se o botão de voltar está disponível.
     *
     * @return true se algum botão de voltar estiver visível
     */
    public boolean isBackButtonVisible() {
        return isElementVisible(BACK_BTN) || isElementVisible(BACK_ARROW);
    }

    /**
     * Navega de volta ao menu usando o botão do browser.
     */
    public void navigateBack() {
        driver.navigate().back();
        waitForPageLoad();
    }
}
