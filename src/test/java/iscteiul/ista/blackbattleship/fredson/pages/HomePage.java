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

    @Step("Fazer scroll até à secção das regras do jogo")
    public void scrollAteAsRegras() {
        $("body").shouldBe(Condition.visible);

        executeJavaScript("""
            const possibleElements = Array.from(
                document.querySelectorAll('h1, h2, h3, h4, h5, p, section')
            );

            const rulesElement = possibleElements.find(element => {
                const text = element.innerText ? element.innerText.toLowerCase() : '';
                return text.includes('rules of battleship')
                    || text.includes('rules')
                    || text.includes('how to play');
            });

            if (rulesElement) {
                rulesElement.scrollIntoView({
                    behavior: 'instant',
                    block: 'center'
                });
            } else {
                window.scrollTo({
                    top: document.body.scrollHeight,
                    behavior: 'instant'
                });
            }
        """);

        sleep(2000);
    }

    @Step("Validar que as regras do jogo estão visíveis na página")
    public void validarRegrasDoJogo() {
        $("body").shouldHave(Condition.text("Rules"));
        $("body").shouldHave(Condition.text("Battleship"));
    }

    @Step("Voltar ao topo da página")
    public void voltarAoTopo() {
        executeJavaScript("window.scrollTo({ top: 0, behavior: 'instant' });");
        sleep(1000);
    }

    @Step("Clicar na opção para jogar contra robot")
    public void clicarPlayVsRobot() {
        voltarAoTopo();

        $x("//*[self::button or self::a][contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'robot')]")
                .shouldBe(Condition.visible)
                .scrollTo()
                .click();
    }

    @Step("Clicar na opção para jogar com um amigo")
    public void clicarPlayWithFriend() {
        voltarAoTopo();

        $x("//*[self::button or self::a][contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'friend')]")
                .shouldBe(Condition.visible)
                .scrollTo()
                .click();
    }
}