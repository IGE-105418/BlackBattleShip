package iscteiul.ista.blackbattleship;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import java.time.Duration;

/**
 * Testes de demonstração para o site da JetBrains.
 *
 * <p>Esta classe faz parte do projeto-piloto (Parte 1A da ficha laboratorial).
 * Destina-se a aprender o padrão POM e o Selenium antes de testar o BlackBattleShip.</p>
 *
 * <p>Cada membro do grupo deve:
 * <ol>
 *   <li>Executar estes testes no seu ramo de trabalho.</li>
 *   <li>Corrigir localizadores que possam ter ficado desactualizados.</li>
 *   <li>Fazer Push do ramo (SEM Pull Request para main).</li>
 * </ol>
 * </p>
 */
@DisplayName("Parte 1A – Demo: Testes JetBrains (projecto-piloto)")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;

    /**
     * Configura o WebDriver antes de cada teste.
     * Usa WebDriverManager para gerir automaticamente o ChromeDriver.
     */
    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get("https://www.jetbrains.com/");
        mainPage = new MainPage(driver);
    }

    /** Fecha o browser após cada teste. */
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Testes
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Testa a funcionalidade de pesquisa no site da JetBrains.
     * Pesquisa "Selenium" e verifica que o campo de pesquisa retém o valor.
     */
    @Test
    @Order(1)
    @DisplayName("search – Pesquisar 'Selenium' e verificar campo de pesquisa")
    void search() {
        mainPage.clickSearchButton();
        // Pequena pausa para o campo de pesquisa ficar visível
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        mainPage.search("Selenium");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        assertEquals("Selenium", mainPage.getSearchInputValue(),
                "O campo de pesquisa deve conter 'Selenium'");
    }

    /**
     * Testa que o submenu de Developer Tools aparece ao clicar no menu.
     */
    @Test
    @Order(2)
    @DisplayName("toolsMenu – Menu Developer Tools abre submenu")
    void toolsMenu() {
        mainPage.clickToolsMenu();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        WebElement submenu = mainPage.waitForToolsSubmenu();
        assertTrue(submenu.isDisplayed(),
                "O submenu de Developer Tools deve estar visível");
    }

    /**
     * Testa a navegação para a página de todos os produtos da JetBrains.
     */
    @Test
    @Order(3)
    @DisplayName("navigationToAllTools – Navegar para página de todos os produtos")
    void navigationToAllTools() {
        mainPage.clickSeeDeveloperTools();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        mainPage.clickFindYourTools();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        assertTrue(mainPage.isProductsPageVisible(),
                "A página de produtos deve estar visível");
        assertTrue(driver.getTitle().contains("JetBrains"),
                "O título deve mencionar JetBrains, mas foi: " + driver.getTitle());
    }
}