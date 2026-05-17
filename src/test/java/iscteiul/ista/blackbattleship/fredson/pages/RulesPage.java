package iscteiul.ista.blackbattleship.fredson.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

/**
 * Page Object associado à validação das regras/instruções do jogo.
 */
public class RulesPage {

    @Step("Validar texto das regras da Batalha Naval")
    public void validarTextoDasRegras() {
        $("body").shouldHave(Condition.text("Rules"));
        $("body").shouldHave(Condition.text("Battleship"));
    }
}