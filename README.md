# BlackBattleShip – Testes de Aceitação com Selenium

Suíte de testes automáticos de caixa-preta para o website **Black BattleShip**  
([https://www.blackbattleship.com/](https://www.blackbattleship.com/))

> **Vídeo de demonstração:** *(link YouTube a adicionar após gravação)*

---

## Membros do Grupo

| Número | Nome |
|--------|------|
| _(preencher)_ | _(preencher)_ |
| _(preencher)_ | _(preencher)_ |
| _(preencher)_ | _(preencher)_ |

---

## Scrum Product Backlog – Black BattleShip

> Gerado com apoio de LLM. User Stories em português de Portugal.

### US1 – Aceder à página principal e verificar disponibilidade do jogo
**Como** jogador, **quero** aceder à homepage do Black BattleShip **para** confirmar que o site está disponível e apresenta os elementos principais do jogo.

### US2 – Criar um nickname e iniciar sessão de jogo
**Como** jogador, **quero** introduzir um nickname único **para** ser identificado nas partidas que dispute.

### US3 – Iniciar jogo contra o robot (modo single player)
**Como** jogador individual, **quero** iniciar uma partida contra o robot **para** praticar sem necessitar de outro jogador humano.

### US4 – Criar uma sala de jogo multiplayer e obter o link/código
**Como** anfitrião, **quero** criar uma sala de jogo multiplayer **para** convidar outro jogador através de um link ou código único.

### US5 – Entrar numa sala multiplayer com código/link
**Como** jogador convidado, **quero** entrar numa sala existente com o código ou link fornecido **para** disputar uma partida com o anfitrião.

### US6 – Consultar as instruções e regras do jogo
**Como** jogador novo, **quero** aceder às instruções do jogo **para** aprender as regras antes de começar a jogar.

### US7 – Posicionar navios no tabuleiro antes do jogo começar
**Como** jogador, **quero** colocar os meus navios no tabuleiro de 10×10 **para** definir a minha estratégia de defesa.

### US8 – Efectuar jogadas durante a partida (atacar células do adversário)
**Como** jogador, **quero** clicar em células do tabuleiro adversário **para** tentar afundar os navios do oponente.

### US9 – Receber feedback visual de acerto ou falha após cada jogada
**Como** jogador, **quero** ver se a minha jogada foi um acerto (hit) ou uma falha (miss) **para** adaptar a minha estratégia de ataque.

### US10 – Visualizar mensagem de fim de jogo (vitória ou derrota)
**Como** jogador, **quero** ver uma mensagem clara de vitória ou derrota no fim da partida **para** saber o resultado do jogo.

### US11 – Iniciar uma nova partida após o fim do jogo
**Como** jogador, **quero** poder iniciar uma nova partida rapidamente após o fim do jogo **para** jogar de novo sem reiniciar o browser.

### US12 – Organizar um campeonato entre vários jogadores
**Como** organizador, **quero** criar um campeonato com vários jogadores **para** gerir partidas em formato de torneio.

---

---

## Tecnologias

| Tecnologia             | Versão   | Propósito                              |
|------------------------|----------|----------------------------------------|
| Java                   | 21       | Linguagem de programação               |
| Maven                  | 3.9+     | Gestão de dependências e build         |
| Selenium WebDriver     | 4.27.0   | Automação do browser                   |
| WebDriverManager       | 5.9.2    | Gestão automática do ChromeDriver      |
| JUnit 5 (Jupiter)      | 5.11.3   | Framework de testes                    |
| Chrome / ChromeDriver  | Latest   | Browser alvo                           |

---

## Estrutura do Projeto

```
BlackBattleShip/
│
├── pom.xml                          # Configuração Maven
├── browsers.json                    # Configuração de browsers
├── README.md                        # Este ficheiro
│
├── docs/
│   ├── ProductBacklog.md            # Backlog completo do produto
│   └── UserStories.md               # User Stories e rastreabilidade
│
├── reports/                         # Relatórios de execução (gerados)
│
└── src/
    ├── main/
    │   └── java/
    │       └── pages/               # Page Object Model (POM)
    │           ├── BasePage.java        ← Classe base com métodos partilhados
    │           ├── MainPage.java        ← Homepage / Menu principal
    │           ├── GamePage.java        ← Tabuleiro de jogo
    │           ├── LobbyPage.java       ← Lobby multiplayer
    │           └── InstructionsPage.java← Página de instruções
    │
    └── test/
        └── java/
            └── tests/               # Classes de Teste JUnit 5
                ├── BaseTest.java        ← Setup/Teardown do WebDriver
                ├── UserStoryTest1.java  ← US1: Homepage e Verificação
                ├── UserStoryTest2.java  ← US2: Nickname e Jogo vs Robot
                ├── UserStoryTest3.java  ← US3: Instruções
                └── UserStoryTest4.java  ← US4: Multiplayer / Lobby
```

---

## Padrão de Design: Page Object Model (POM)

```
┌──────────────────────────┐       ┌──────────────────────────┐
│     Classe de Teste       │  usa  │     Page Object           │
│  (src/test/java/tests/)   │──────▶│  (src/main/java/pages/)  │
│                           │       │                           │
│  @BeforeEach setUp()      │       │  Localizadores (By)       │
│  @AfterEach tearDown()    │       │  Métodos de interacção    │
│  @Test métodos            │       │  Waits explícitos         │
│  JUnit Assertions         │       │  Herda de BasePage        │
└──────────────────────────┘       └──────────────────────────┘
```

### Hierarquia de classes

```
BasePage
  ├── MainPage
  ├── GamePage
  ├── LobbyPage
  └── InstructionsPage

BaseTest
  ├── UserStoryTest1
  ├── UserStoryTest2
  ├── UserStoryTest3
  └── UserStoryTest4
```

---

## User Stories Implementadas

| ID  | User Story                          | Testes | Estado     |
|-----|-------------------------------------|--------|------------|
| US1 | Acesso e verificação da homepage    | 7      | Concluída  |
| US2 | Nickname e jogo vs robot            | 6      | Concluída  |
| US3 | Navegação nas instruções            | 6      | Concluída  |
| US4 | Lobby e sala multiplayer            | 7      | Concluída  |
| —   | **Total**                           | **26** |            |

---

## Pré-requisitos

1. **Java 21** instalado e na `PATH`
2. **Maven 3.9+** instalado e na `PATH`
3. **Google Chrome** instalado (WebDriverManager trata do ChromeDriver automaticamente)
4. Ligação à internet (para aceder a `blackbattleship.com` e para WebDriverManager)

Verificar versões:
```bash
java -version
mvn -version
```

---

## Como Executar os Testes

### Opção 1 – Via IntelliJ IDEA (recomendado para desenvolvimento)

1. Abrir o projeto: **File → Open** → seleccionar a pasta `BlackBattleShip`
2. Aguardar que o IntelliJ indexe o Maven e descarregue dependências
3. Clicar com o botão direito numa classe de teste → **Run 'UserStoryTest1'**
4. Para executar todos: clicar com o botão direito na pasta `tests` → **Run 'All Tests'**

**Atalho rápido:**
- `Ctrl+Shift+F10` na classe aberta → executa a classe
- `Shift+F10` → re-executa o último teste

### Opção 2 – Via Maven (linha de comandos)

```bash
# Executar todos os testes
mvn test

# Executar apenas uma classe específica
mvn test -Dtest=UserStoryTest1

# Executar apenas um método específico
mvn test -Dtest=UserStoryTest1#testHomepageOpens

# Executar com output detalhado
mvn test -Dsurefire.useFile=false
```

### Opção 3 – Executar em modo headless (sem janela Chrome)

Descomentar a linha em `BaseTest.java`:
```java
// options.addArguments("--headless=new");  ← remover o //
```

---

## Configuração do WebDriver

O `WebDriverManager` (incluído no `pom.xml`) gere automaticamente o ChromeDriver:

```java
WebDriverManager.chromedriver().setup();  // em BaseTest.java
```

Não é necessário descarregar nem configurar manualmente o `chromedriver.exe`.

> **Nota:** Em ambientes sem internet, descarregar manualmente o ChromeDriver compatível
> com a versão do Chrome instalado e colocar na `PATH` do sistema.

---

## Ajustar Localizadores (Seletores CSS/XPath)

O site é uma **Single Page Application (SPA)** em JavaScript. Se algum teste falhar
com `TimeoutException` ou `NoSuchElementException`:

1. Abrir o site no Chrome e premir **F12** (DevTools)
2. Seleccionar o elemento desejado com o inspector
3. Verificar o `id`, `class`, atributos `data-*` e texto
4. Actualizar o localizador na classe Page Object correspondente

**Exemplo de como encontrar um selector:**
```
DevTools → Elements → Ctrl+F → pesquisar pela classe ou ID
→ clicar com botão direito → Copy → Copy selector
```

Os localizadores estão centralizados nos Page Objects (`src/main/java/pages/`),
portanto qualquer ajuste faz-se apenas uma vez e afecta todos os testes.

---

## Estrutura de um Teste (Exemplo)

```java
@Test
@DisplayName("TC2.2 – Pode-se digitar um nickname no campo")
void testEnterNickname() {
    final String testNickname = "TestPlayer123";     // Arrange
    mainPage.enterNickname(testNickname);             // Act
    String actualValue = mainPage.getNicknameValue(); // Act
    assertEquals(testNickname, actualValue,           // Assert
        "O nickname introduzido deve ser '" + testNickname + "'");
}
```

Cada teste segue o padrão **Arrange → Act → Assert**.

---

## Dependências Maven (pom.xml)

```xml
<!-- Selenium WebDriver 4 -->
org.seleniumhq.selenium:selenium-java:4.27.0

<!-- WebDriverManager (gestão automática de drivers) -->
io.github.bonigarcia:webdrivermanager:5.9.2

<!-- JUnit 5 -->
org.junit.jupiter:junit-jupiter-api:5.11.3
org.junit.jupiter:junit-jupiter-engine:5.11.3
org.junit.jupiter:junit-jupiter-params:5.11.3

<!-- Logging -->
org.slf4j:slf4j-simple:2.0.16
```

---

## Problemas Comuns

| Problema                          | Causa                               | Solução                                          |
|-----------------------------------|-------------------------------------|--------------------------------------------------|
| `SessionNotCreatedException`      | Versão ChromeDriver incompatível    | WebDriverManager resolve automaticamente         |
| `TimeoutException`                | Selector não encontrou o elemento   | Inspecionar DOM e ajustar selector no Page Object|
| `NoSuchElementException`          | Elemento não existe na página       | Verificar se a página carregou correctamente     |
| `StaleElementReferenceException`  | Página foi actualizada              | Usar `waitForClickable()` antes de interagir     |
| Chrome não abre                   | Google Chrome não instalado         | Instalar Chrome e tentar novamente               |
| Testes lentos                     | Waits muito longos                  | Reduzir `DEFAULT_TIMEOUT` em `BasePage.java`     |

---

## Documentação Adicional

- [`docs/ProductBacklog.md`](docs/ProductBacklog.md) – Backlog completo com todos os PBIs
- [`docs/UserStories.md`](docs/UserStories.md) – User Stories e rastreabilidade
- [Selenium WebDriver Docs](https://www.selenium.dev/documentation/)
- [JUnit 5 Docs](https://junit.org/junit5/docs/current/user-guide/)
- [WebDriverManager Docs](https://bonigarcia.dev/webdrivermanager/)
