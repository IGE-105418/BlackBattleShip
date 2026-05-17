# BlackBattleShip – Testes de Aceitação com Selenium

Projeto laboratorial de Engenharia de Software para testes de caixa-preta/aceitação com Selenium WebDriver e Selenide.

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

> Backlog criado com apoio de LLM, com base na análise funcional do website BlackBattleShip. As User Stories estão escritas em português de Portugal.

### US1 – Aceder à página principal
**Como** jogador,  
**quero** aceder à página principal do BlackBattleShip,  
**para** confirmar que o jogo está disponível e pronto a ser utilizado.

### US2 – Criar nickname
**Como** jogador,  
**quero** introduzir um nickname,  
**para** ser identificado durante a partida.

### US3 – Jogar contra o robot
**Como** jogador individual,  
**quero** iniciar uma partida contra o robot,  
**para** poder jogar sem precisar de outro jogador humano.

### US4 – Criar jogo multiplayer
**Como** anfitrião,  
**quero** criar uma partida multiplayer,  
**para** convidar outro jogador a entrar no jogo.

### US5 – Entrar numa partida multiplayer
**Como** jogador convidado,  
**quero** entrar numa partida através de um link ou código,  
**para** jogar contra outro jogador.

### US6 – Consultar instruções/regras
**Como** jogador novo,  
**quero** consultar as instruções do jogo,  
**para** compreender as regras antes de começar.

### US7 – Posicionar navios
**Como** jogador,  
**quero** posicionar os meus navios no tabuleiro,  
**para** preparar a minha estratégia de defesa.

### US8 – Confirmar posicionamento dos navios
**Como** jogador,  
**quero** confirmar a posição dos meus navios,  
**para** iniciar a partida com a minha configuração escolhida.

### US9 – Atacar uma célula adversária
**Como** jogador,  
**quero** clicar numa célula do tabuleiro adversário,  
**para** tentar atingir um navio inimigo.

### US10 – Ver resultado da jogada
**Como** jogador,  
**quero** receber feedback após atacar uma célula,  
**para** saber se acertei ou falhei.

### US11 – Alternar turnos
**Como** jogador,  
**quero** que o sistema alterne os turnos corretamente,  
**para** garantir que a partida decorre de forma justa.

### US12 – Ver fim de jogo
**Como** jogador,  
**quero** ver uma mensagem de vitória ou derrota no fim da partida,  
**para** saber claramente o resultado final.

### US13 – Iniciar nova partida
**Como** jogador,  
**quero** iniciar uma nova partida depois do fim do jogo,  
**para** poder jogar novamente sem dificuldade.

### US14 – Criar campeonato
**Como** organizador,  
**quero** criar um campeonato entre vários jogadores,  
**para** organizar partidas em formato competitivo.

### US15 – Utilizar o Chat Global/Privado
**Como** jogador,  
**quero** aceder à página de chat,  
**para** poder comunicar e interagir com outros utilizadores da plataforma.

### US16 – Consultar e gerir a lista de amigos
**Como** jogador,  
**quero** aceder à secção de amigos,  
**para** ver quem está online e gerir a minha rede de contactos no jogo.

### US17 – Consultar o histórico de partidas de Batalha Naval
**Como** jogador,  
**quero** aceder à página de histórico com o filtro de Batalha Naval ativo,  
**para** analisar o meu desempenho e os resultados das minhas últimas partidas.

### US18 – Explorar avatares na Loja
**Como** jogador,  
**quero** aceder à secção de avatares na loja,  
**para** poder personalizar a imagem do meu perfil.

---

## Tecnologias Utilizadas

| Tecnologia | Propósito |
|-----------|-----------|
| Java 21 | Linguagem de programação |
| Maven 3.9+ | Gestão de dependências e execução dos testes |
| JUnit 5 | Framework de testes |
| Selenium WebDriver 4 | Automação do browser |
| Selenide 7.6.0 | Simplificação dos testes sobre Selenium |
| Allure Report 2.29.0 | Geração de relatórios de execução |
| Google Chrome | Browser usado nos testes |

---

## Estrutura do Projeto

```text
BlackBattleShip/
│
├── pom.xml                          # Configuração Maven e dependências (Selenide/Allure)
├── browsers.json                    # Configuração de browsers
├── README.md                        # Este ficheiro
├── reports/                         # Relatórios estáticos gerados pelo Allure
│
└── src/
    ├── main/
    │   └── java/
    │       └── iscteiul/ista/blackbattleship/
    │           └── pages/           # Page Object Model (POM) - Classes Selenide
    │
    └── test/
        ├── java/
        │   └── iscteiul/ista/blackbattleship/
        │       └── tests/           # Classes de Teste JUnit 5
        │
        └── resources/
            └── allure.properties    # Configuração de diretoria do Allure
