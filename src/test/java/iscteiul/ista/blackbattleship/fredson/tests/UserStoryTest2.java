package iscteiul.ista.blackbattleship.fredson.tests;

import iscteiul.ista.blackbattleship.fredson.pages.HomePage;
import iscteiul.ista.blackbattleship.fredson.pages.RulesPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * UserStoryTest2:
 * Como jogador, quero consultar as regras do jogo
 * para perceber como funciona a Batalha Naval online.
 */
public class UserStoryTest2 extends BaseTest {

    HomePage homePage = new HomePage();
    RulesPage rulesPage = new RulesPage();

    @Test
    @DisplayName("UserStoryTest2 - Consultar regras do jogo")
    void jogadorConsegueConsultarRegras() {
        homePage.abrirPaginaInicial();
        homePage.scrollAteAsRegras();
        homePage.validarRegrasDoJogo();
        rulesPage.validarTextoDasRegras();
    }
}