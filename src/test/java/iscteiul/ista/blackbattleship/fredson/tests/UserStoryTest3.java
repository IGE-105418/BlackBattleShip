package iscteiul.ista.blackbattleship.fredson.tests;

import iscteiul.ista.blackbattleship.fredson.pages.GamePage;
import iscteiul.ista.blackbattleship.fredson.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * UserStoryTest3:
 * Como jogador, quero iniciar uma partida contra um robot
 * para poder jogar sozinho.
 * feito pelo Fredson
 */
public class UserStoryTest3 extends BaseTest {

    HomePage homePage = new HomePage();
    GamePage gamePage = new GamePage();

    @Test
    @DisplayName("UserStoryTest3 - Iniciar partida contra robot")
    void jogadorConsegueIniciarJogoContraRobot() {
        homePage.abrirPaginaInicial();
        homePage.validarPaginaInicial();
        homePage.clicarPlayVsRobot();

        gamePage.validarZonaDeJogoOuConfiguracao();
    }
}