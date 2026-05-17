package iscteiul.ista.blackbattleship.fredson.tests;

import iscteiul.ista.blackbattleship.fredson.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * UserStoryTest1:
 * Como jogador, quero aceder à página principal da Batalha Naval
 * para ver as opções disponíveis.
 */
public class UserStoryTest1 extends BaseTest {

    HomePage homePage = new HomePage();

    @Test
    @DisplayName("UserStoryTest1 - Aceder à página principal da Batalha Naval")
    void jogadorConsegueAcederPaginaPrincipal() {
        homePage.abrirPaginaInicial();
        homePage.validarPaginaInicial();
        homePage.validarOpcoesDeJogo();
    }
}