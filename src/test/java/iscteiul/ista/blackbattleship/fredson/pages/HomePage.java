package iscteiul.ista.blackbattleship.fredson.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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
        // # Espera curta para dar tempo à janela de consentimento/cookies aparecer.
        sleep(1000);

        try {
            SelenideElement botaoConsentimento = $x(
                    "//button[" +
                            "contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'accept') or " +
                            "contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'agree') or " +
                            "contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'consent') or " +
                            "contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'ok')" +
                            "]"
            );

            if (botaoConsentimento.exists() && botaoConsentimento.isDisplayed()) {
                clicarComJavaScript(botaoConsentimento);
                sleep(1000);
            }
        } catch (Exception ignored) {
            // # Se não houver botão de cookies, continua o teste normalmente.
        }

        // # Fallback para overlays do tipo Funding Choices/Google Consent que podem bloquear cliques.
        try {
            executeJavaScript("""
            const selectors = [
                '.fc-dialog-container',
                '.fc-consent-root',
                '.fc-dialog',
                '[class*="fc-dialog"]',
                '[class*="fc-consent"]'
            ];

            selectors.forEach(selector => {
                document.querySelectorAll(selector).forEach(element => {
                    element.style.display = 'none';
                    element.style.visibility = 'hidden';
                    element.style.pointerEvents = 'none';
                });
            });
        """);
        } catch (Exception ignored) {
            // # Se não existir overlay, não há nada para remover.
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

        SelenideElement botaoRobot = $x(
                "//*[self::button or self::a]" +
                        "[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'robot')]"
        ).shouldBe(Condition.visible);

        clicarComJavaScript(botaoRobot);
    }

    @Step("Clicar na opção para jogar com um amigo")
    public void clicarPlayWithFriend() {
        voltarAoTopo();

        SelenideElement botaoFriend = $x(
                "//*[self::button or self::a]" +
                        "[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'friend')]"
        ).shouldBe(Condition.visible);

        clicarComJavaScript(botaoFriend);
    }

    @Step("Preencher nickname caso o campo esteja disponível")
    public void preencherNicknameSeAparecer(String nickname) {
        // # Garante que overlays/cookies não bloqueiam o campo de nickname.
        aceitarCookiesSeAparecer();
        sleep(1000);

        try {
            SelenideElement inputNickname = $x(
                    "//input[" +
                            "@formcontrolname='username' or " +
                            "@placeholder='Nickname' or " +
                            "contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'nickname') or " +
                            "contains(translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'name')" +
                            "]"
            );

            if (inputNickname.exists() && inputNickname.isDisplayed() && inputNickname.isEnabled()) {
                // # Não usamos click normal porque o overlay pode interceptar o clique.
                // # Em vez disso, escrevemos via JavaScript e disparamos eventos input/change.
                executeJavaScript("""
                const input = arguments[0];
                const value = arguments[1];

                input.scrollIntoView({
                    behavior: 'instant',
                    block: 'center'
                });

                input.focus();
                input.value = value;

                input.dispatchEvent(new Event('input', { bubbles: true }));
                input.dispatchEvent(new Event('change', { bubbles: true }));
            """, inputNickname, nickname);

                sleep(1000);
                return;
            }
        } catch (Exception ignored) {
            // # Se o campo específico falhar, tenta fallback abaixo.
        }

        // # Fallback: tenta todos os inputs de texto até encontrar um utilizável.
        var inputs = $$("input");

        for (SelenideElement input : inputs) {
            try {
                String type = input.getAttribute("type");

                if ("hidden".equalsIgnoreCase(type)) {
                    continue;
                }

                if (input.isDisplayed() && input.isEnabled()) {
                    executeJavaScript("""
                    const input = arguments[0];
                    const value = arguments[1];

                    input.scrollIntoView({
                        behavior: 'instant',
                        block: 'center'
                    });

                    input.focus();
                    input.value = value;

                    input.dispatchEvent(new Event('input', { bubbles: true }));
                    input.dispatchEvent(new Event('change', { bubbles: true }));
                """, input, nickname);

                    sleep(1000);
                    return;
                }
            } catch (Exception ignored) {
                // # Se este input não aceitar texto, tenta o próximo.
            }
        }

        // # Se não houver campo de nickname editável, o teste não deve falhar aqui.
        // # Alguns fluxos do site permitem continuar sem nickname explícito.
    }

    @Step("Clicar num elemento usando JavaScript")
    private void clicarComJavaScript(SelenideElement elemento) {
        executeJavaScript("""
            arguments[0].scrollIntoView({
                behavior: 'instant',
                block: 'center'
            });
        """, elemento);

        sleep(1000);

        executeJavaScript("arguments[0].click();", elemento);

        sleep(2000);
    }
}