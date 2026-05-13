# BlackBattleShip – Testes de Aceitação com Selenium

Projeto laboratorial de Engenharia de Software para testes de caixa-preta/aceitação com Selenium WebDriver.

Website em teste: [https://papergames.io/en/battleship](https://papergames.io/en/battleship)

> **Vídeo de demonstração:** a adicionar no final da Parte 2.

---

## Membros do Grupo

| Número | Nome |
|--------|------|
| 111722 | Eduardo Carvalho |
| 105418 | Francisco Cabral |
| 105333 | Francisca Bastos |
| 111825 | Fredson Munguambe |

---

## Objetivo do Projeto

Este projeto tem como objetivo automatizar testes de aceitação para o website BlackBattleShip, seguindo o padrão **Page Object Model (POM)**.

Os testes procuram validar o comportamento da aplicação do ponto de vista do utilizador, sem depender da implementação interna do sistema.

---

## Scrum Product Backlog – BlackBattleShip

> Backlog criado com apoio de LLM, com base na análise funcional do website BlackBattleShip.

### US1 – Aceder à página principal

**Como** jogador,  
**quero** aceder à página principal do BlackBattleShip,  
**para** confirmar que o jogo está disponível e pronto a ser utilizado.

**Critérios de aceitação:**
- A página principal deve abrir sem erro.
- O título ou conteúdo principal do jogo deve estar visível.
- Deve existir uma forma clara de iniciar ou configurar uma partida.

---

### US2 – Criar nickname

**Como** jogador,  
**quero** introduzir um nickname,  
**para** ser identificado durante a partida.

**Critérios de aceitação:**
- Deve existir um campo para introduzir o nickname.
- O nickname introduzido deve ficar registado na interface.
- O jogador deve conseguir continuar após preencher o nickname.

---

### US3 – Jogar contra o robot

**Como** jogador individual,  
**quero** iniciar uma partida contra o robot,  
**para** poder jogar sem precisar de outro jogador humano.

**Critérios de aceitação:**
- Deve existir uma opção para jogar contra o robot.
- Ao selecionar essa opção, o jogo deve iniciar ou avançar para a preparação da partida.
- O tabuleiro de jogo deve ficar acessível ao jogador.

---

### US4 – Criar jogo multiplayer

**Como** anfitrião,  
**quero** criar uma partida multiplayer,  
**para** convidar outro jogador a entrar no jogo.

**Critérios de aceitação:**
- Deve existir uma opção para criar uma partida multiplayer.
- O sistema deve gerar um link ou código de convite.
- O link ou código deve poder ser usado por outro jogador.

---

### US5 – Entrar numa partida multiplayer

**Como** jogador convidado,  
**quero** entrar numa partida através de um link ou código,  
**para** jogar contra outro jogador.

**Critérios de aceitação:**
- O jogador deve conseguir abrir o link ou inserir o código.
- A aplicação deve reconhecer a sala de jogo.
- O jogador deve conseguir juntar-se à partida.

---

### US6 – Consultar instruções/regras

**Como** jogador novo,  
**quero** consultar as instruções do jogo,  
**para** compreender as regras antes de começar.

**Critérios de aceitação:**
- Deve existir uma área ou ligação para instruções/regras.
- As regras devem ser apresentadas de forma legível.
- O jogador deve conseguir voltar ao fluxo principal do jogo.

---

### US7 – Posicionar navios

**Como** jogador,  
**quero** posicionar os meus navios no tabuleiro,  
**para** preparar a minha estratégia de defesa.

**Critérios de aceitação:**
- O tabuleiro do jogador deve estar visível.
- O jogador deve conseguir selecionar ou posicionar navios.
- O sistema deve impedir posições inválidas.

---

### US8 – Confirmar posicionamento dos navios

**Como** jogador,  
**quero** confirmar a posição dos meus navios,  
**para** iniciar a partida com a minha configuração escolhida.

**Critérios de aceitação:**
- Deve existir uma ação para confirmar o posicionamento.
- A partida só deve avançar quando a configuração for válida.
- O jogador deve receber feedback se o posicionamento estiver incompleto ou inválido.

---

### US9 – Atacar uma célula adversária

**Como** jogador,  
**quero** clicar numa célula do tabuleiro adversário,  
**para** tentar atingir um navio inimigo.

**Critérios de aceitação:**
- O tabuleiro adversário deve estar visível durante o turno do jogador.
- O jogador deve conseguir selecionar uma célula válida.
- O sistema deve registar a jogada efetuada.

---

### US10 – Ver resultado da jogada

**Como** jogador,  
**quero** receber feedback após atacar uma célula,  
**para** saber se acertei ou falhei.

**Critérios de aceitação:**
- A célula atacada deve apresentar feedback visual.
- O sistema deve distinguir entre acerto e falha.
- O jogador não deve conseguir atacar novamente a mesma célula como se fosse nova.

---

### US11 – Alternar turnos

**Como** jogador,  
**quero** que o sistema alterne os turnos corretamente,  
**para** garantir que a partida decorre de forma justa.

**Critérios de aceitação:**
- Após uma jogada, o turno deve passar para o adversário quando aplicável.
- O jogador deve perceber visualmente quando é o seu turno.
- O jogador não deve conseguir jogar fora do seu turno.

---

### US12 – Ver fim de jogo

**Como** jogador,  
**quero** ver uma mensagem de vitória ou derrota no fim da partida,  
**para** saber claramente o resultado final.

**Critérios de aceitação:**
- O jogo deve detetar quando todos os navios de um jogador foram destruídos.
- Deve ser apresentada uma mensagem de vitória ou derrota.
- A partida deve impedir novas jogadas após terminar.

---

### US13 – Iniciar nova partida

**Como** jogador,  
**quero** iniciar uma nova partida depois do fim do jogo,  
**para** poder jogar novamente sem dificuldade.

**Critérios de aceitação:**
- Deve existir uma opção para reiniciar ou criar uma nova partida.
- A aplicação deve voltar ao estado inicial ou de configuração.
- Os dados da partida anterior não devem interferir com a nova partida.

---

### US14 – Criar campeonato

**Como** organizador,  
**quero** criar um campeonato entre vários jogadores,  
**para** organizar partidas em formato competitivo.

**Critérios de aceitação:**
- Deve existir uma opção relacionada com campeonatos.
- O organizador deve conseguir configurar ou iniciar o campeonato.
- O sistema deve apresentar informação relevante sobre os participantes ou partidas.

---

## Tecnologias Utilizadas

| Tecnologia | Propósito |
|-----------|-----------|
| Java 21 | Linguagem de programação |
| Maven | Gestão de dependências e execução dos testes |
| JUnit 5 | Framework de testes |
| Selenium WebDriver 4 | Automação do browser |
| Selenide | Simplificação dos testes sobre Selenium |
| Google Chrome | Browser usado nos testes |

---

## Estrutura Atual do Projeto

```text
BlackBattleShip/
│
├── pom.xml
├── browsers.json
├── README.md
├── reports/
│
└── src/
    └── test/
        └── java/
            └── iscteiul/
                └── ista/
                    └── blackbattleship/
                        ├── MainPage.java
                        └── MainPageTest.java