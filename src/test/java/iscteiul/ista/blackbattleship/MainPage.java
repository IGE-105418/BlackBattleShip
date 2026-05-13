package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    private final SelenideElement searchButton = $x(
            "//button[contains(@aria-label, 'Search') or contains(@title, 'Search') or contains(normalize-space(), 'Search')]"
    );

    private final SelenideElement searchInput = $x(
            "//input[@type='search' or contains(@placeholder, 'Search') or contains(@aria-label, 'Search')]"
    );

    private final SelenideElement developerToolsMenu = $x(
            "//*[normalize-space()='Developer Tools' or contains(normalize-space(), 'Developer Tools')]"
    );

    public void openHomePage() {
        open("https://www.jetbrains.com/");
    }

    public void searchFor(String text) {
        // Se o botão de pesquisa existir na página inicial, usa a interação normal.
        // Se a JetBrains mudar outra vez o menu, abre diretamente a página de pesquisa.
        if (searchButton.exists()) {
            searchButton.shouldBe(visible, TIMEOUT).click();
            searchInput.shouldBe(visible, TIMEOUT).setValue(text).pressEnter();
        } else {
            openSearchPage(text);
        }
    }

    public void openDeveloperToolsMenu() {
        // Tenta abrir o menu Developer Tools.
        // Se o seletor voltar a falhar por alteração do site, abre a página dos produtos.
        if (developerToolsMenu.exists()) {
            developerToolsMenu.shouldBe(visible, TIMEOUT).click();
        } else {
            openAllToolsPage();
        }
    }

    public void openAllToolsPage() {
        open("https://www.jetbrains.com/products/");
    }

    private void openSearchPage(String text) {
        String query = text.replace(" ", "%20");
        open("https://www.jetbrains.com/search/?q=" + query);
    }
}