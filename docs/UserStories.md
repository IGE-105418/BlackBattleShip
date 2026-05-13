# User Stories – Black BattleShip Test Suite

**Projeto:** BlackBattleShip – Testes de Aceitação Selenium  
**Website:** https://www.blackbattleship.com/  
**Metodologia:** Scrum / BDD  

---

## US1 – Acesso e Verificação da Homepage

**Como** jogador,  
**quero** aceder à homepage do Black BattleShip,  
**para** verificar que o site está disponível e os elementos principais estão presentes.

### Critérios de Aceitação

- [ ] **CA1.1** – O URL da página contém "blackbattleship.com"
- [ ] **CA1.2** – O título da aba do browser não está vazio
- [ ] **CA1.3** – O título contém "Battle", "Ship" ou "Black"
- [ ] **CA1.4** – Existe pelo menos um botão de acção visível
- [ ] **CA1.5** – Os botões visíveis têm texto não vazio
- [ ] **CA1.6** – A página atinge estado `readyState = complete`

### Cenários de Teste Automatizados

| Teste           | Classe                | Método                              |
|-----------------|-----------------------|-------------------------------------|
| TC1.1           | UserStoryTest1        | `testHomepageOpens()`               |
| TC1.2           | UserStoryTest1        | `testPageTitleNotEmpty()`           |
| TC1.3           | UserStoryTest1        | `testPageTitleContainsBattleOrShip()` |
| TC1.4           | UserStoryTest1        | `testPageHasButtons()`              |
| TC1.5           | UserStoryTest1        | `testButtonsHaveText()`             |
| TC1.7           | UserStoryTest1        | `testPageIsLoaded()`                |

**Prioridade:** Alta | **Story Points:** 5 | **Estado:** Concluída

---

## US2 – Criação de Nickname e Início de Jogo vs Robot

**Como** jogador,  
**quero** introduzir o meu nickname e iniciar uma partida contra o robot,  
**para** poder jogar sem necessitar de outro jogador humano.

### Critérios de Aceitação

- [ ] **CA2.1** – Existe um campo de texto para introduzir o nickname
- [ ] **CA2.2** – O nickname digitado aparece no campo (valor reflectido)
- [ ] **CA2.3** – Ao limpar o campo, o valor fica vazio
- [ ] **CA2.4** – Existe um botão "Jogar vs Robot" (ou equivalente) visível
- [ ] **CA2.5** – Após clicar em "Jogar vs Robot", o tabuleiro de jogo aparece ou o URL muda
- [ ] **CA2.6** – O campo aceita nicknames com caracteres como underscore e números

### Cenários de Teste Automatizados

| Teste           | Classe                | Método                              |
|-----------------|-----------------------|-------------------------------------|
| TC2.1           | UserStoryTest2        | `testNicknameInputIsVisible()`      |
| TC2.2           | UserStoryTest2        | `testEnterNickname()`               |
| TC2.3           | UserStoryTest2        | `testClearNicknameField()`          |
| TC2.4           | UserStoryTest2        | `testPlayVsRobotButtonIsVisible()`  |
| TC2.5           | UserStoryTest2        | `testStartGameVsRobot()`            |
| TC2.6           | UserStoryTest2        | `testNicknameWithSpecialChars()`    |

**Prioridade:** Alta | **Story Points:** 8 | **Estado:** Concluída

---

## US3 – Navegação para as Instruções do Jogo

**Como** jogador novo,  
**quero** aceder às instruções/regras do Black BattleShip,  
**para** aprender as regras antes de começar a jogar.

### Critérios de Aceitação

- [ ] **CA3.1** – Existe um link ou botão de "Instruções" / "How to Play" visível
- [ ] **CA3.2** – Ao clicar no link, é exibido conteúdo com regras ou instruções
- [ ] **CA3.3** – O conteúdo das instruções não está em branco
- [ ] **CA3.4** – O título da aba continua a referenciar o jogo ou as instruções
- [ ] **CA3.5** – Existe forma de regressar ao menu (botão "Voltar" ou navegação do browser)
- [ ] **CA3.6** – Após o ciclo homepage→instruções→homepage, o URL é o correcto

### Cenários de Teste Automatizados

| Teste           | Classe                | Método                                  |
|-----------------|-----------------------|-----------------------------------------|
| TC3.1           | UserStoryTest3        | `testInstructionsLinkIsVisible()`       |
| TC3.2           | UserStoryTest3        | `testInstructionsPageLoads()`           |
| TC3.3           | UserStoryTest3        | `testInstructionsContentNotEmpty()`     |
| TC3.4           | UserStoryTest3        | `testInstructionsPageTitle()`           |
| TC3.5           | UserStoryTest3        | `testBackFromInstructions()`            |
| TC3.6           | UserStoryTest3        | `testNavigationCycleUrl()`              |

**Prioridade:** Média | **Story Points:** 8 | **Estado:** Concluída

---

## US4 – Criar e Entrar em Sala Multiplayer

**Como** jogador,  
**quero** criar uma sala multiplayer ou entrar numa sala com código,  
**para** poder disputar uma partida contra outro jogador humano online.

### Critérios de Aceitação

- [ ] **CA4.1** – Existe um botão "Multiplayer" visível na homepage
- [ ] **CA4.2** – Após clicar em Multiplayer, o lobby ou sala é apresentado
- [ ] **CA4.3** – O lobby tem opção de criar sala ou campo de código
- [ ] **CA4.4** – Criar sala gera e exibe um código identificador
- [ ] **CA4.5** – Existe campo para introduzir o código de uma sala existente
- [ ] **CA4.6** – Código inválido não causa crash (aplicação mantém-se funcional)
- [ ] **CA4.7** – A interface do lobby tem pelo menos um botão ou campo interactivo

### Cenários de Teste Automatizados

| Teste           | Classe                | Método                                      |
|-----------------|-----------------------|---------------------------------------------|
| TC4.1           | UserStoryTest4        | `testMultiplayerButtonIsVisible()`          |
| TC4.2           | UserStoryTest4        | `testClickMultiplayerNavigatesToLobby()`    |
| TC4.3           | UserStoryTest4        | `testCreateRoomButtonAvailable()`           |
| TC4.4           | UserStoryTest4        | `testCreateRoomDisplaysRoomCode()`          |
| TC4.5           | UserStoryTest4        | `testRoomCodeInputIsAccessible()`           |
| TC4.6           | UserStoryTest4        | `testInvalidRoomCodeHandled()`              |
| TC4.7           | UserStoryTest4        | `testLobbyInterfaceElements()`              |

**Prioridade:** Alta | **Story Points:** 13 | **Estado:** Concluída

---

## Rastreabilidade: User Story → PBI → Teste

| User Story | PBIs                       | Testes                    |
|------------|----------------------------|---------------------------|
| US1        | PBI-01 a PBI-06            | TC1.1 – TC1.7             |
| US2        | PBI-07 a PBI-15            | TC2.1 – TC2.6             |
| US3        | PBI-36 a PBI-41            | TC3.1 – TC3.6             |
| US4        | PBI-25 a PBI-31, PBI-44-45 | TC4.1 – TC4.7             |

---

## Resumo

| User Story | Total Testes | Story Points | Prioridade |
|------------|-------------|--------------|------------|
| US1        | 7           | 5            | Alta       |
| US2        | 6           | 8            | Alta       |
| US3        | 6           | 8            | Média      |
| US4        | 7           | 13           | Alta       |
| **Total**  | **26**      | **34**       | —          |
