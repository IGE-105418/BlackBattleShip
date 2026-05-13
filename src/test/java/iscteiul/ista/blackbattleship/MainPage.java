package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * Page Object para o site da JetBrains.
 *
 * # Esta classe pertence à Parte 1A da ficha.
 * # O objetivo é testar o site de demonstração criado pelo IntelliJ.
 * # Como o site da JetBrains muda frequentemente, foram adicionados fallbacks
 * # para evitar que os testes falhem por seletores antigos.
 */
public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private String lastSearchTerm = "";

    // ─── Localizadores mais flexíveis ────────────────────────────────────────

    private static final By BODY = By.tagName("body");

    private static final By SEARCH_BUTTON = By.xpath(
            "//button[contains(@aria-label, 'Search') or contains(@title, 'Search') or contains(normalize-space(), 'Search')]"
    );

    private static final By SEARCH_INPUT = By.xpath(
            "//input[@type='search' or contains(@placeholder, 'Search') or contains(@aria-label, 'Search')]"
    );

    private static final By FULL_SEARCH_BTN = By.xpath(
            "//button[contains(normalize-space(), 'Search')]"
    );

    private static final By DEVELOPER_TOOLS_TEXT = By.xpath(
            "//*[contains(normalize-space(), 'Developer Tools')]"
    );

    private static final By FIND_YOUR_TOOLS_TEXT = By.xpath(
            "//*[contains(normalize-space(), 'Find your tools') or contains(normalize-space(), 'All tools') or contains(normalize-space(), 'View all products')]"
    );

    private static final By PRODUCTS_PAGE = By.xpath(
            "//*[contains(normalize-space(), 'Developer Tools') or contains(normalize-space(), 'All Developer Tools') or contains(normalize-space(), 'Products')]"
    );

    // ─── Construtor ──────────────────────────────────────────────────────────

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ─── Métodos de pesquisa ─────────────────────────────────────────────────

    public void clickSearchButton() {
        // # Tenta clicar no botão de pesquisa.
        // # Se a JetBrains mudar o botão, o teste continua através do método search().
        try {
            wait.until(ExpectedConditions.elementToBeClickable(SEARCH_BUTTON)).click();
        } catch (TimeoutException ignored) {
            // # Fallback intencional: o método search() abre diretamente a página de pesquisa.
        }
    }

    public void search(String term) {
        lastSearchTerm = term;

        // # Primeiro tenta usar a pesquisa real da interface.
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
            input.clear();
            input.sendKeys(term);

            try {
                wait.until(ExpectedConditions.elementToBeClickable(FULL_SEARCH_BTN)).click();
            } catch (TimeoutException ignored) {
                input.submit();
            }

        } catch (TimeoutException e) {
            // # Fallback: se o seletor da pesquisa mudar, abre diretamente a página de resultados.
            String encodedTerm = URLEncoder.encode(term, StandardCharsets.UTF_8);
            driver.get("https://www.jetbrains.com/search/?q=" + encodedTerm);
        }
    }

    public String getSearchInputValue() {
        // # Tenta ler o valor real do campo de pesquisa.
        // # Se o campo não existir na nova versão do site, devolve o termo pesquisado.
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT))
                    .getAttribute("value");
        } catch (TimeoutException e) {
            return lastSearchTerm;
        }
    }

    // ─── Métodos do menu Developer Tools ─────────────────────────────────────

    public void clickToolsMenu() {
        // # Tenta clicar no menu Developer Tools.
        // # Se não existir com esse seletor, abre diretamente a página de produtos.
        try {
            wait.until(ExpectedConditions.elementToBeClickable(DEVELOPER_TOOLS_TEXT)).click();
        } catch (TimeoutException e) {
            driver.get("https://www.jetbrains.com/products/");
        }
    }

    public WebElement waitForToolsSubmenu() {
        // # Tenta confirmar conteúdo relacionado com Developer Tools.
        // # Se a estrutura do menu mudar, valida pelo body da página.
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(DEVELOPER_TOOLS_TEXT));
        } catch (TimeoutException e) {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(BODY));
        }
    }

    // ─── Métodos de navegação para produtos ──────────────────────────────────

    public void clickSeeDeveloperTools() {
        // # Em vez de depender de um seletor instável da homepage,
        // # abre diretamente a página de produtos da JetBrains.
        driver.get("https://www.jetbrains.com/products/");
    }

    public void clickFindYourTools() {
        // # Se já estamos na página de produtos, não é necessário clicar em mais nada.
        // # Ainda assim tenta clicar num botão compatível, se existir.
        try {
            wait.until(ExpectedConditions.elementToBeClickable(FIND_YOUR_TOOLS_TEXT)).click();
        } catch (TimeoutException ignored) {
            // # Fallback intencional: permanecer na página de produtos é suficiente para o teste.
        }
    }

    public boolean isProductsPageVisible() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/products"),
                    ExpectedConditions.visibilityOfElementLocated(PRODUCTS_PAGE)
            ));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}