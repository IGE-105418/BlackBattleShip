package iscteiul.ista.blackbattleship.fredson.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

/**
 * Page Object da página ou zona de jogo.
 * Contém validações genéricas depois de iniciar uma partida.
 */
public class GamePage {

    @Step("Validar que a zona de jogo ou configuração da partida foi apresentada")
    public void validarZonaDeJogoOuConfiguracao() {
        $("body").shouldBe(Condition.visible);
    }

    @Step("Validar que existe conteúdo relacionado com a partida")
    public void validarConteudoDaPartida() {
        $("body").shouldBe(Condition.visible);
    }
}