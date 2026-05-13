package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/**
 * Classe base para todos os testes JUnit 5.
 *
 * <p>Configura e destrói o WebDriver antes/depois de cada teste.
 * Todas as classes de teste herdam desta classe.</p>
 */
public abstract class BaseTest {

    /** URL do website a testar. */
    protected static final String BASE_URL = "https://www.blackbattleship.com/";

    /** Instância do WebDriver partilhada pelos testes. */
    protected WebDriver driver;

    /**
     * Executado antes de cada teste:
     * <ol>
     *   <li>Configura automaticamente o ChromeDriver via WebDriverManager.</li>
     *   <li>Cria uma instância do ChromeDriver.</li>
     *   <li>Maximiza a janela do browser.</li>
     *   <li>Define timeouts padrão.</li>
     * </ol>
     */
    @BeforeEach
    public void setUp() {
        // WebDriverManager descarrega e configura automaticamente o ChromeDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // Descomenta a linha seguinte para correr em modo headless (sem janela):
        // options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1280,900");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        // Não usar implicitWait em conjunto com waits explícitos — usar apenas waits explícitos
    }

    /**
     * Executado após cada teste:
     * fecha o browser e liberta os recursos do WebDriver.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
