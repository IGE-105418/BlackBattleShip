package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;

/**
 * Page Object para o site da JetBrains.
 *
 * # Esta classe pertence à Parte 1A da ficha.
 * # O objetivo é testar o site de demonstração criado pelo IntelliJ.
 * # Como o site da JetBrains muda frequentemente, foram adicionados fallbacks
 * # para evitar que os testes falhem por seletores antigos.
 */
public class MainPage {

    public SelenideElement seeDeveloperToolsButton =
            $$x("//*[@data-test-marker='Developer Tools']")
                    .findBy(Condition.visible);

    public SelenideElement findYourToolsButton =
            $$x("//*[@data-test='suggestion-action']")
                    .findBy(Condition.visible);

    public SelenideElement toolsMenu =
            $$x("//div[@data-test='main-menu-item' and @data-test-marker='Developer Tools']")
                    .findBy(Condition.visible);

    public SelenideElement searchButton =
            $$("[data-test='site-header-search-action']")
                    .findBy(Condition.visible);
}