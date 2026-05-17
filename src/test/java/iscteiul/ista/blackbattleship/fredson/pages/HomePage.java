package iscteiul.ista.blackbattleship.fredson.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object da página principal do jogo Battleship Online.
 * Contém os localizadores e ações principais da página inicial.
 */
public class HomePage {

    @Step("Abrir página principal da Batalha Naval")
    public void abrirPaginaInicial() {
        open("https://papergames.io/en/battleship");
        aceitarCookiesSeAparecer();
    }

    @Step("Aceitar cookies caso apareça uma janela de consentimento")
    public void aceitarCookiesSeAparecer() {
        if ($$("button").findBy(Condition.exactText("Accept")).exists()) {
            $$("button").findBy(Condition.exactText("Accept")).click();
        } else if ($$("button").findBy(Condition.exactText("OK")).exists()) {
            $$("button").findBy(Condition.exactText("OK")).click();
        } else if ($$("button").findBy(Condition.text("Accept")).exists()) {
            $$("button").findBy(Condition.text("Accept")).click();
        }
    }

    @Step("Validar que a página principal foi carregada")
    public void validarPaginaInicial() {
        $("body").shouldBe(Condition.visible);
        $("body").shouldHave(Condition.text("Battleship"));
    }

    @Step("Validar que existem opções de jogo na página")
    public void validarOpcoesDeJogo() {
        $("body").shouldHave(Condition.text("Play"));
        $("body").shouldHave(Condition.text("robot"));
    }

    @Step("Validar que as regras do jogo estão visíveis na página")
    public void validarRegrasDoJogo() {
        $("body").shouldHave(Condition.text("Rules"));
        $("body").shouldHave(Condition.text("Battleship"));
    }

    @Step("Clicar na opção para jogar contra robot")
    public void clicarPlayVsRobot() {
        $$("button, a").findBy(Condition.text("robot"))
                .shouldBe(Condition.visible)
                .click();
    }

    @Step("Clicar na opção para jogar com um amigo")
    public void clicarPlayWithFriend() {
        $$("button, a").findBy(Condition.text("friend"))
                .shouldBe(Condition.visible)
                .click();
    }
}