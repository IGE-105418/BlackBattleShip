# Contexto do Projecto – BlackBattleShip

## O que é este projecto

Suíte de testes automáticos de caixa-preta para https://www.blackbattleship.com/  
Ficha laboratorial nº 5 de Engenharia de Software – LIGE/LIGE-PL 2025/2026  
Repositório: https://github.com/IGE-105418/BlackBattleShip

---

## Stack

| Tecnologia | Versão | Nota |
|---|---|---|
| Java | 21 | JDK bundled do IntelliJ em `C:\Program Files\JetBrains\IntelliJ IDEA 2025.3.3\jbr` |
| Maven | 3.9.6 | Usar `mvnw` com `JAVA_HOME` apontado para o JBR acima |
| Selenium WebDriver | 4.27.0 | Parte 1 da ficha |
| WebDriverManager | 5.9.2 | Gere o ChromeDriver automaticamente |
| Selenide | 7.6.0 | Parte 2 da ficha |
| JUnit 5 | 5.11.3 | |
| Allure | 2.29.0 | Parte 2 da ficha |
| Chrome | 148 | ChromeDriver descarregado automaticamente para `~/.cache/selenium` |

**Nota JDK:** o sistema tem Java 17 no PATH mas o projecto requer Java 21.  
Para compilar/correr via terminal: `JAVA_HOME="/c/Program Files/JetBrains/IntelliJ IDEA 2025.3.3/jbr" ./mvnw test`  
No IntelliJ funciona directamente (usa o JBR bundled).

---

## Estrutura de ficheiros

```
BlackBattleShip/
├── pom.xml                          ← Selenium 4 + Selenide + Allure + JUnit 5
├── browsers.json
├── README.md                        ← Product Backlog (12 US) + instruções
├── CONTEXT.md                       ← este ficheiro
│
├── docs/
│   ├── ProductBacklog.md            ← 49 PBIs em 6 épicos
│   └── UserStories.md               ← rastreabilidade US → PBI → Teste
│
├── reports/                         ← relatórios Allure gerados aqui
│
└── src/
    ├── main/java/pages/             ── PAGE OBJECTS (Parte 1 – Selenium puro)
    │   ├── BasePage.java            ← base: WebDriver, WebDriverWait, utilitários
    │   ├── MainPage.java            ← homepage: nickname, botões, links
    │   ├── GamePage.java            ← tabuleiro: células, estado, fim de jogo
    │   ├── LobbyPage.java           ← multiplayer: criar/entrar sala
    │   └── InstructionsPage.java   ← instruções: conteúdo, navegação
    │
    └── test/
        ├── resources/
        │   └── allure.properties    ← allure.results.directory=target/allure-results
        │
        └── java/
            ├── iscteiul/ista/blackbattleship/   ── DEMO JetBrains (Parte 1A)
            │   ├── MainPage.java    ← Page Object JetBrains (LOCALIZADORES DESACTUALIZADOS)
            │   └── MainPageTest.java← 3 testes: search, toolsMenu, navigationToAllTools
            │
            ├── tests/               ── TESTES BlackBattleShip (Parte 1 – Selenium puro)
            │   ├── BaseTest.java    ← @BeforeEach/@AfterEach: ChromeDriver setup
            │   ├── UserStoryTest1.java ← US1: Homepage (7 testes)
            │   ├── UserStoryTest2.java ← US2: Nickname + Robot (6 testes)
            │   ├── UserStoryTest3.java ← US3: Instruções (6 testes)
            │   └── UserStoryTest4.java ← US4: Multiplayer/Lobby (7 testes)
            │
            └── testsuite/           ── TESTES BlackBattleShip (Parte 2 – Selenide)
                ├── pages/
                │   └── MainPageSelenide.java ← Page Object Selenide
                └── UserStorySelenideTest1.java ← US1 com Selenide + Allure annotations
```

---

## Estado actual dos testes

### `MainPageTest` (JetBrains demo – Parte 1A)
- **Estado:** ❌ 3/3 falham com `TimeoutException`
- **Causa:** O site da JetBrains mudou o HTML; os `data-test-marker` e `data-test` attributes já não existem
- **O que fazer:** Abrir https://www.jetbrains.com/ no Chrome, F12, inspeccionar os elementos actuais e actualizar os `By.*` em `src/test/java/iscteiul/ista/blackbattleship/MainPage.java`
- **Nota:** A ficha pede EXACTAMENTE isto (Passo 1A.3.c) — faz parte do exercício

### `UserStoryTest1-4` (BlackBattleShip – Parte 1)
- **Estado:** ⚠️ Ainda não correram (fix do pom.xml feito, falta executar)
- **Fix feito:** O surefire só incluía `**/*Test.java`; adicionado `**/*Test*.java` para apanhar `UserStoryTest1.java` etc.
- **Possível problema:** O site blackbattleship.com pode estar inacessível ou os seletores podem não bater com o DOM real (é uma SPA em JS)
- **Seletores:** estão em `src/main/java/pages/` — se falharem, usar F12 e actualizar os `By.*` nos Page Objects

### `UserStorySelenideTest1` (Parte 2 – Selenide)
- **Estado:** ⚠️ Ainda não correu
- **Nota:** Serve de exemplo/template para cada membro do grupo criar o seu próprio pacote

---

## Commits

| Hash | Mensagem |
|---|---|
| `523aadc` | projeto inicial |
| `7521a39` | Implementar suíte de testes Selenium + Selenide para BlackBattleShip |
| `d806956` | fix: incluir UserStoryTest1-4 na execução do Maven Surefire |

---

## Como correr os testes

### Via IntelliJ (recomendado)
- Clicar direito numa classe de teste → Run
- Para todos: clicar direito na pasta `tests` → Run All Tests

### Via terminal (requer Java 21)
```bash
JAVA_HOME="/c/Program Files/JetBrains/IntelliJ IDEA 2025.3.3/jbr" ./mvnw test
```

### Só os testes BlackBattleShip (ignorar JetBrains)
```bash
JAVA_HOME="..." ./mvnw test -Dtest="UserStoryTest1,UserStoryTest2,UserStoryTest3,UserStoryTest4"
```

---

## O que falta fazer (por membro do grupo)

1. **Corrigir `MainPageTest`** — F12 no jetbrains.com, actualizar seletores em `MainPage.java` (pacote `iscteiul.ista.blackbattleship`)
2. **Gravar Selenium IDE** — instalar extensão no Edge ou Firefox, gravar 4+ cenários, guardar como `TestSuite_NUMERO.side` na raiz
3. **Branch próprio** — `git checkout -b branch-NUMERO`
4. **4+ User Stories** — implementar no pacote `tests/`, 1 commit por US com mensagem `UserStoryTest1`, `UserStoryTest2`, etc.
5. **Parte 2 – Selenide** — criar pacote próprio `src/test/java/testsuite_NUMERO/`, implementar os mesmos testes com Selenide + anotações Allure
6. **Relatório Allure** — gerar via IntelliJ (AllureReport: Generate On The Fly), copiar para `reports/`
7. **Pull Request** — push do branch, PR para main, fechar Issue
8. **Tag** — o membro com 2º menor número cria tag no último commit após todos os PRs
9. **Vídeo** — gravar demo ≤ 5 min, publicar no YouTube, adicionar link ao `README.md`

---

## Notas técnicas

- **Seletores dos Page Objects** estão todos centralizados em `src/main/java/pages/` — 1 alteração afecta todos os testes
- **Waits** — usar sempre explicit waits (`WebDriverWait`); o implicit wait está desactivado propositadamente
- **Headless** — para correr sem janela Chrome, descomentar `options.addArguments("--headless=new")` em `BaseTest.java`
- **Allure** — para gerar relatório: IntelliJ → clicar direito em `target/allure-results` → AllureReport: Generate On The Fly
