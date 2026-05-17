package iscteiul.ista.blackbattleship.fredson.tests;

import iscteiul.ista.blackbattleship.fredson.pages.GamePage;
import iscteiul.ista.blackbattleship.fredson.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * UserStoryTest4:
 * Como jogador, quero iniciar uma partida com um amigo
 * para poder partilhar o jogo com outro jogador.
 */
public class UserStoryTest4 extends BaseTest {

    HomePage homePage = new HomePage();
    GamePage gamePage = new GamePage();

    @Test
    @DisplayName("UserStoryTest4 - Iniciar partida com amigo")
    void jogadorConsegueIniciarJogoComAmigo() {
        homePage.abrirPaginaInicial();
        homePage.validarPaginaInicial();
        homePage.clicarPlayWithFriend();

        gamePage.validarZonaDeJogoOuConfiguracao();
    }
}