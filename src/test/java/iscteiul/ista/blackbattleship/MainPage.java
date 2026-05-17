package iscteiul.ista.blackbattleship;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;

// page_url = https://www.jetbrains.com/
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