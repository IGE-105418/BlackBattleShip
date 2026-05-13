package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Classe base para todos os Page Objects.
 * Fornece WebDriver, WebDriverWait e métodos utilitários partilhados.
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    /** Timeout padrão para waits explícitos (segundos). */
    protected static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(15);

    /**
     * Inicializa o page object com o driver fornecido e cria um wait explícito.
     *
     * @param driver instância do WebDriver activa
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        PageFactory.initElements(driver, this);
    }

    // ─────────────────────────────────────────────
    // Métodos de espera
    // ─────────────────────────────────────────────

    /**
     * Aguarda que um elemento fique visível.
     *
     * @param locator localizador do elemento
     * @return elemento quando visível
     */
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Aguarda que um elemento fique clicável.
     *
     * @param locator localizador do elemento
     * @return elemento quando clicável
     */
    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Aguarda que um elemento esteja presente no DOM (não necessariamente visível).
     *
     * @param locator localizador do elemento
     * @return elemento quando presente
     */
    protected WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Aguarda que o título da página contenha o texto indicado.
     *
     * @param titleFragment fragmento esperado no título
     */
    protected void waitForTitleContains(String titleFragment) {
        wait.until(ExpectedConditions.titleContains(titleFragment));
    }

    /**
     * Aguarda que a URL contenha o fragmento indicado.
     *
     * @param urlFragment fragmento esperado na URL
     */
    protected void waitForUrlContains(String urlFragment) {
        wait.until(ExpectedConditions.urlContains(urlFragment));
    }

    /** Aguarda carregamento completo da página via JavaScript readyState. */
    protected void waitForPageLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }

    // ─────────────────────────────────────────────
    // Métodos utilitários
    // ─────────────────────────────────────────────

    /**
     * Verifica se um elemento está presente no DOM.
     *
     * @param locator localizador do elemento
     * @return true se presente, false caso contrário
     */
    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Verifica se um elemento está visível na página.
     *
     * @param locator localizador do elemento
     * @return true se visível
     */
    protected boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    /**
     * Faz scroll até um elemento ficar visível no viewport.
     *
     * @param element elemento alvo
     */
    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Clica num elemento via JavaScript (fallback quando o click normal falha).
     *
     * @param element elemento alvo
     */
    protected void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /**
     * Devolve o URL actual da página.
     *
     * @return URL como string
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Devolve o título actual da página.
     *
     * @return título como string
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Devolve todos os elementos que correspondem ao localizador.
     *
     * @param locator localizador
     * @return lista de WebElements (pode ser vazia)
     */
    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }
}
