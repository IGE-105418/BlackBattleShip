package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest {
    private MainPage mainPage;

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void setUp() {
        mainPage = new MainPage();
        mainPage.openHomePage();
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

    @Test
    public void search() {
        mainPage.searchFor("Selenium");

        $("body").shouldHave(text("Selenium"), Duration.ofSeconds(10));
    }

    @Test
    public void toolsMenu() {
        mainPage.openDeveloperToolsMenu();

        $("body").shouldHave(text("Developer Tools"), Duration.ofSeconds(10));
    }

    @Test
    public void navigationToAllTools() {
        mainPage.openAllToolsPage();

        $("body").shouldHave(text("Developer Tools"), Duration.ofSeconds(10));

        assertTrue(Selenide.title().contains("JetBrains"));
    }
}