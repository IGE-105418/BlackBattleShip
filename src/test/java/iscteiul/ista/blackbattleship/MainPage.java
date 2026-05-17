package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object para o site da JetBrains (código de demonstração).
 *
 * <p>Esta classe faz parte do projeto-piloto (Parte 1A da ficha laboratorial).
 * Serve para aprender o padrão POM antes de testar o BlackBattleShip.</p>
 *
 * <p><b>Atenção:</b> Os localizadores podem necessitar de actualização se o site
 * da JetBrains tiver mudado. Use o Inspector do browser (F12) para os validar.</p>
 */
public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // ─── Localizadores ───────────────────────────────────────────────────────

    /** Botão "Developer Tools" no menu principal. */
    private static final By DEVELOPER_TOOLS_BTN =
            By.xpath("//*[@data-test-marker='Developer Tools']");

    /** Botão "Find your tools" na sugestão de produtos. */
    private static final By FIND_YOUR_TOOLS_BTN =
            By.xpath("//*[@data-test='suggestion-action']");

    /** Item de menu Developer Tools. */
    private static final By TOOLS_MENU =
            By.xpath("//div[@data-test='main-menu-item' and @data-test-marker='Developer Tools']");

    /** Botão de pesquisa no cabeçalho. */
    private static final By SEARCH_BUTTON =
            By.cssSelector("[data-test='site-header-search-action']");

    /** Campo de pesquisa (após clicar no botão). */
    private static final By SEARCH_INPUT =
            By.cssSelector("[data-test='search-input']");

    /** Botão "Full search". */
    private static final By FULL_SEARCH_BTN =
            By.cssSelector("button[data-test='full-search-button']");

    /** Campo de pesquisa na página de resultados. */
    private static final By SEARCH_INPUT_RESULTS =
            By.cssSelector("input[data-test='search-input']");

    /** Submenu de ferramentas. */
    private static final By MAIN_SUBMENU =
            By.cssSelector("div[data-test='main-submenu']");

    /** Página de produtos. */
    private static final By PRODUCTS_PAGE =
            By.id("products-page");

    // ─── Construtor ──────────────────────────────────────────────────────────

    /**
     * @param driver instância do WebDriver activa
     */
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ─── Métodos ─────────────────────────────────────────────────────────────

    /** Clica no botão de pesquisa no cabeçalho. */
    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON)).click();
    }

    /**
     * Pesquisa um termo no campo de pesquisa.
     *
     * @param term termo a pesquisar
     */
    public void search(String term) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT)).sendKeys(term);
        wait.until(ExpectedConditions.elementToBeClickable(FULL_SEARCH_BTN)).click();
    }

    /**
     * Devolve o valor do campo de pesquisa nos resultados.
     *
     * @return texto do campo de pesquisa
     */
    public String getSearchInputValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT_RESULTS))
                   .getAttribute("value");
    }

    /** Clica no item de menu "Developer Tools". */
    public void clickToolsMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(TOOLS_MENU)).click();
    }

    /**
     * Verifica se o submenu de ferramentas está visível.
     *
     * @return o WebElement do submenu (já visível)
     */
    public WebElement waitForToolsSubmenu() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(MAIN_SUBMENU));
    }

    /** Clica no botão "See Developer Tools". */
    public void clickSeeDeveloperTools() {
        wait.until(ExpectedConditions.elementToBeClickable(DEVELOPER_TOOLS_BTN)).click();
    }

    /** Clica no botão "Find Your Tools". */
    public void clickFindYourTools() {
        wait.until(ExpectedConditions.elementToBeClickable(FIND_YOUR_TOOLS_BTN)).click();
    }

    /**
     * Verifica se a página de todos os produtos está visível.
     *
     * @return true se a página estiver visível
     */
    public boolean isProductsPageVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTS_PAGE)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
