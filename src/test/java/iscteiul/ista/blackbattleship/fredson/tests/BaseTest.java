package iscteiul.ista.blackbattleship.fredson.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

/**
 * Classe base da TestSuite_111825.
 * Contém a configuração comum para todos os testes de aceitação.
 */
public class BaseTest {

    protected static final String BASE_URL = "https://papergames.io/en/battleship";

    @BeforeAll
    static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 30000;

        SelenideLogger.addListener(
                "allure",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}