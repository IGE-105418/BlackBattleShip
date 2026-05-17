package iscteiul.ista.blackbattleship.fredson.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Page Object da página ou zona de jogo.
 * Contém validações genéricas depois de iniciar uma partida.
 */
public class GamePage {

    @Step("Validar que a zona de jogo ou configuração da partida foi apresentada")
    public void validarZonaDeJogoOuConfiguracao() {
        $("body").shouldBe(Condition.visible);
        sleep(2000);
    }
}